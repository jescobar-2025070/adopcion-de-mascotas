package com.adopcion.mascotas.controller;

import com.adopcion.mascotas.entity.Mascota;
import com.adopcion.mascotas.repository.MascotaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaRepository repository;

    public MascotaController(MascotaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Mascota> guardar(@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(mascota));
    }
}