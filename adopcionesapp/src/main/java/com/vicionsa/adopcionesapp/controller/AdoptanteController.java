package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Adoptante;
import com.vicionsa.adopcionesapp.repository.AdoptanteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adoptantes")
@CrossOrigin
public class AdoptanteController {

    private final AdoptanteRepository repo;

    public AdoptanteController(AdoptanteRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public List<Adoptante> listar(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Adoptante buscarPorId(@PathVariable Integer id){
        Optional<Adoptante> adoptante = repo.findById(id);
        return adoptante.orElse(null);
    }

    @PostMapping
    public Adoptante guardar(@RequestBody Adoptante adoptante){
        return repo.save(adoptante);
    }

    @PutMapping("/{id}")
    public Adoptante actualizar(@PathVariable Integer id,
                                @RequestBody Adoptante adoptanteActualizado){

        Optional<Adoptante> existente = repo.findById(id);

        if(existente.isPresent()){
            Adoptante adoptante = existente.get();
            adoptante.setNombreAdoptante(adoptanteActualizado.getNombreAdoptante());
            adoptante.setCorreo(adoptanteActualizado.getCorreo());
            adoptante.setTelefono(adoptanteActualizado.getTelefono());
            adoptante.setDireccion(adoptanteActualizado.getDireccion());

            return repo.save(adoptante);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id){

        if(repo.existsById(id)){
            repo.deleteById(id);
            return "Adoptante eliminado correctamente";
        }

        return "El adoptante no existe";
    }
}
