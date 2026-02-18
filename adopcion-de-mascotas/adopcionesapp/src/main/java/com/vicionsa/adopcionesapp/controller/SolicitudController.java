package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Solicitud;
import com.vicionsa.adopcionesapp.entity.Mascota;
import com.vicionsa.adopcionesapp.entity.Adoptante;
import com.vicionsa.adopcionesapp.repository.SolicitudRepository;
import com.vicionsa.adopcionesapp.repository.MascotaRepository;
import com.vicionsa.adopcionesapp.repository.AdoptanteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudController {

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

        Optional<Solicitud> existente = solicitudRepo.findById(id);

        if (existente.isPresent()) {

            Solicitud solicitud = existente.get();

            solicitud.setFecha(solicitudActualizada.getFecha());
            solicitud.setEstadoSolicitud(solicitudActualizada.getEstadoSolicitud());

            // 🔎 Buscar mascota real
            Mascota mascota = mascotaRepo.findById(
                    solicitudActualizada.getMascota().getIdMascota()
            ).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

            // 🔎 Buscar adoptante real
            Adoptante adoptante = adoptanteRepo.findById(
                    solicitudActualizada.getAdoptante().getIdAdoptante()
            ).orElseThrow(() -> new RuntimeException("Adoptante no encontrado"));

            solicitud.setMascota(mascota);
            solicitud.setAdoptante(adoptante);

            return solicitudRepo.save(solicitud);
        }

        return null;
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
