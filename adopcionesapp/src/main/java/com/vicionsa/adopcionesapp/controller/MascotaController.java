package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Mascota;
import com.vicionsa.adopcionesapp.repository.MascotaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mascota buscarPorId(@PathVariable Integer id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        return mascota.orElse(null);
    }

    @PostMapping
    public Mascota guardarMascota(@Valid @RequestBody Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascota actualizarMascota(@PathVariable Integer id,
                                     @Valid @RequestBody Mascota mascotaActualizada) {

        Optional<Mascota> mascotaExistente = mascotaRepository.findById(id);

        if (mascotaExistente.isPresent()) {

            Mascota mascota = mascotaExistente.get();
            mascota.setNombreMascota(mascotaActualizada.getNombreMascota());
            mascota.setEspecie(mascotaActualizada.getEspecie());
            mascota.setRaza(mascotaActualizada.getRaza());
            mascota.setEdad(mascotaActualizada.getEdad());
            mascota.setEstado(mascotaActualizada.getEstado());

            return mascotaRepository.save(mascota);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarMascota(@PathVariable Integer id) {

        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return "Mascota eliminada correctamente";
        }

        return "La mascota no existe";
    }
}
