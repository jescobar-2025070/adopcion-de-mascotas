package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Refugio;
import com.vicionsa.adopcionesapp.repository.RefugioRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refugios")
public class RefugioController {

    private final RefugioRepository refugioRepo;

    public RefugioController(RefugioRepository refugioRepo) {
        this.refugioRepo = refugioRepo;
    }

    @GetMapping
    public List<Refugio> listar() {
        return refugioRepo.findAll();
    }

    @PostMapping
    public Refugio guardar(@Valid @RequestBody Refugio refugio) {
        return refugioRepo.save(refugio);
    }

    @GetMapping("/{id}")
    public Refugio buscar(@PathVariable Integer id) {
        return refugioRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Refugio actualizar(@PathVariable Integer id,
                              @Valid @RequestBody Refugio refugioActualizado) {

        return refugioRepo.findById(id).map(refugio -> {

            refugio.setNombre(refugioActualizado.getNombre());
            refugio.setDireccion(refugioActualizado.getDireccion());
            refugio.setTelefono(refugioActualizado.getTelefono());

            return refugioRepo.save(refugio);

        }).orElseThrow(() -> new RuntimeException("Refugio no encontrado"));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        refugioRepo.deleteById(id);
        return "Refugio eliminado";
    }
}