package com.adopcion.mascotas.repository;

import com.adopcion.mascotas.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {}