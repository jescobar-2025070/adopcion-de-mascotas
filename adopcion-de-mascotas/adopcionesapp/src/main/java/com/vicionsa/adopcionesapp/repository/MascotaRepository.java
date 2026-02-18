package com.vicionsa.adopcionesapp.repository;

import com.vicionsa.adopcionesapp.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByRaza(String raza);
    List<Mascota> findByEdad(int edad);
}
