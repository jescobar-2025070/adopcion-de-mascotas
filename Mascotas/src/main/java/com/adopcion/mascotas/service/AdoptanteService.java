package com.adopcion.mascotas.service;

import com.adopcion.mascotas.entity.Adoptante;
import com.adopcion.mascotas.repository.AdoptanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdoptanteService {

    private final AdoptanteRepository repository;

    public AdoptanteService(AdoptanteRepository repository) {
        this.repository = repository;
    }

    public List<Adoptante> listar() {
        return repository.findAll();
    }

    public Adoptante guardar(Adoptante adoptante) {
        return repository.save(adoptante);
    }
}
