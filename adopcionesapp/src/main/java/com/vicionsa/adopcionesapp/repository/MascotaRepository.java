package com.vicionsa.adopcionesapp.repository;

import com.vicionsa.adopcionesapp.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

}
