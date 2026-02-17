package com.adopcion.mascotas.controller;

import com.adopcion.mascotas.entity.Adoptante;
import com.adopcion.mascotas.service.AdoptanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptantes")
public class AdoptanteController {

    private final AdoptanteService service;

    public AdoptanteController(AdoptanteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Adoptante>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Adoptante> guardar(@Valid @RequestBody Adoptante adoptante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(adoptante));
    }
}
