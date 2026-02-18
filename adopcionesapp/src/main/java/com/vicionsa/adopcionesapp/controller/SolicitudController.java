package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Adoptante;
import com.vicionsa.adopcionesapp.entity.Mascota;
import com.vicionsa.adopcionesapp.entity.Solicitud;
import com.vicionsa.adopcionesapp.repository.AdoptanteRepository;
import com.vicionsa.adopcionesapp.repository.MascotaRepository;
import com.vicionsa.adopcionesapp.repository.SolicitudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudController {
    private static final String MASCOTA_DISPONIBLE = "Disponible";
    private static final String MASCOTA_ADOPTADA   = "Adoptada";

    private static final String SOLICITUD_APROBADA  = "Aprobada";
    private static final String SOLICITUD_RECHAZADA = "Rechazada";

    private final SolicitudRepository solicitudRepo;
    private final MascotaRepository mascotaRepo;
    private final AdoptanteRepository adoptanteRepo;

    public SolicitudController(SolicitudRepository solicitudRepo,
                               MascotaRepository mascotaRepo,
                               AdoptanteRepository adoptanteRepo) {
        this.solicitudRepo = solicitudRepo;
        this.mascotaRepo = mascotaRepo;
        this.adoptanteRepo = adoptanteRepo;
    }

    @GetMapping
    public List<Solicitud> listar() {
        return solicitudRepo.findAll();
    }

    @GetMapping("/{id}")
    public Solicitud buscarPorId(@PathVariable Integer id) {
        return solicitudRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Solicitud guardar(@RequestBody Solicitud solicitud) {

        Mascota mascota = mascotaRepo.findById(
                solicitud.getMascota().getIdMascota()
        ).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if (MASCOTA_ADOPTADA.equalsIgnoreCase(mascota.getEstado())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La mascota con id " + mascota.getIdMascota() + " ya ha sido adoptada."
            );
        }

        Adoptante adoptante = adoptanteRepo.findById(
                solicitud.getAdoptante().getIdAdoptante()
        ).orElseThrow(() -> new RuntimeException("Adoptante no encontrado"));

        solicitud.setMascota(mascota);
        solicitud.setAdoptante(adoptante);

        return solicitudRepo.save(solicitud);
    }

    @PutMapping("/{id}")
    public Solicitud actualizar(@PathVariable Integer id,
                                @RequestBody Solicitud solicitudActualizada) {

        Solicitud solicitud = solicitudRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitud no encontrada"));

        String estadoAnterior = solicitud.getEstadoSolicitud();
        String estadoNuevo    = solicitudActualizada.getEstadoSolicitud();

        Mascota mascota = mascotaRepo.findById(
                solicitudActualizada.getMascota().getIdMascota()
        ).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Adoptante adoptante = adoptanteRepo.findById(
                solicitudActualizada.getAdoptante().getIdAdoptante()
        ).orElseThrow(() -> new RuntimeException("Adoptante no encontrado"));

        if (SOLICITUD_APROBADA.equalsIgnoreCase(estadoNuevo)
                && !SOLICITUD_APROBADA.equalsIgnoreCase(estadoAnterior)) {

            mascota.setEstado(MASCOTA_ADOPTADA);
            mascotaRepo.save(mascota);

            List<Solicitud> otrasSolicitudes =
                    solicitudRepo.findByMascotaIdMascota(mascota.getIdMascota());

            for (Solicitud otra : otrasSolicitudes) {
                if (!otra.getIdSolicitud().equals(id)
                        && !SOLICITUD_APROBADA.equalsIgnoreCase(otra.getEstadoSolicitud())
                        && !SOLICITUD_RECHAZADA.equalsIgnoreCase(otra.getEstadoSolicitud())) {

                    otra.setEstadoSolicitud(SOLICITUD_RECHAZADA);
                    solicitudRepo.save(otra);
                }
            }

        } else if (SOLICITUD_RECHAZADA.equalsIgnoreCase(estadoNuevo)
                && SOLICITUD_APROBADA.equalsIgnoreCase(estadoAnterior)) {

            boolean otraAprobacion = solicitudRepo
                    .findByMascotaIdMascota(mascota.getIdMascota())
                    .stream()
                    .anyMatch(s -> !s.getIdSolicitud().equals(id)
                            && SOLICITUD_APROBADA.equalsIgnoreCase(s.getEstadoSolicitud()));

            if (!otraAprobacion) {
                mascota.setEstado(MASCOTA_DISPONIBLE);
                mascotaRepo.save(mascota);
            }
        }

        solicitud.setFecha(solicitudActualizada.getFecha());
        solicitud.setEstadoSolicitud(estadoNuevo);
        solicitud.setMascota(mascota);
        solicitud.setAdoptante(adoptante);

        return solicitudRepo.save(solicitud);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {

        if (solicitudRepo.existsById(id)) {
            solicitudRepo.deleteById(id);
            return "Solicitud eliminada correctamente";
        }

        return "La solicitud no existe";
    }
}
