package com.adopcion.mascotas.repository;

import com.adopcion.mascotas.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {}