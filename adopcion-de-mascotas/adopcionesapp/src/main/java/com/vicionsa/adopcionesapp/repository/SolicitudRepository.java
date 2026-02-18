package com.vicionsa.adopcionesapp.repository;

import com.vicionsa.adopcionesapp.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    List<Solicitud> findByEstadoSolicitud(String estadoSolicitud);

    List<Solicitud> findByMascotaIdMascota(Integer idMascota);

    List<Solicitud> findByAdoptanteIdAdoptante(Integer idAdoptante);
}
