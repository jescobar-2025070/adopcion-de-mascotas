package com.adopcion.mascotas.service;

import com.adopcion.mascotas.entity.Mascota;
import com.adopcion.mascotas.entity.Solicitud;
import com.adopcion.mascotas.repository.MascotaRepository;
import com.adopcion.mascotas.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final MascotaRepository mascotaRepository;

    public SolicitudService(SolicitudRepository solicitudRepository, MascotaRepository mascotaRepository) {
        this.solicitudRepository = solicitudRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @Transactional
    public Solicitud crearSolicitud(Solicitud solicitud) {

        Mascota mascota = mascotaRepository.findById(
                solicitud.getMascota().getIdMascota())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        if ("ADOPTADO".equalsIgnoreCase(mascota.getEstado())) {
            throw new RuntimeException("La mascota ya fue adoptada");
        }

        mascota.setEstado("ADOPTADO");
        mascotaRepository.save(mascota);

        return solicitudRepository.save(solicitud);
    }
}
