package com.adopcion.mascotas.repository;

import com.adopcion.mascotas.entity.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptanteRepository extends JpaRepository<Adoptante, Integer> {}