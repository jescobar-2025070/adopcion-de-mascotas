package com.vicionsa.adopcionesapp.repository;

import com.vicionsa.adopcionesapp.entity.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptanteRepository extends JpaRepository<Adoptante, Integer> {

}
