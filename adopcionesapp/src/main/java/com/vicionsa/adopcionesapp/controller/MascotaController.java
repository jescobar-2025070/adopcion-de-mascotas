package com.vicionsa.adopcionesapp.controller;

import com.vicionsa.adopcionesapp.entity.Mascota;
import com.vicionsa.adopcionesapp.entity.Refugio;
import com.vicionsa.adopcionesapp.repository.MascotaRepository;
import com.vicionsa.adopcionesapp.repository.RefugioRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin
public class MascotaController {

    private final MascotaRepository mascotaRepository;
    private final RefugioRepository refugioRepository;

    public MascotaController(MascotaRepository mascotaRepository,
                             RefugioRepository refugioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.refugioRepository = refugioRepository;
    }

    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mascota buscarPorId(@PathVariable Integer id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    @PostMapping
    public Mascota guardarMascota(@Valid @RequestBody Mascota mascota) {

        Integer idRefugio = mascota.getRefugio().getIdRefugio();

        Refugio refugio = refugioRepository.findById(idRefugio)
                .orElseThrow(() -> new RuntimeException("Refugio no encontrado"));

        mascota.setRefugio(refugio);

        return mascotaRepository.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascota actualizarMascota(@PathVariable Integer id,
                                     @Valid @RequestBody Mascota mascotaActualizada) {

        return mascotaRepository.findById(id).map(mascota -> {

            mascota.setNombreMascota(mascotaActualizada.getNombreMascota());
            mascota.setEspecie(mascotaActualizada.getEspecie());
            mascota.setRaza(mascotaActualizada.getRaza());
            mascota.setEdad(mascotaActualizada.getEdad());
            mascota.setEstado(mascotaActualizada.getEstado());

            return mascotaRepository.save(mascota);

        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    @DeleteMapping("/{id}")
    public String eliminarMascota(@PathVariable Integer id) {

        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }

        mascotaRepository.deleteById(id);
        return "Mascota eliminada correctamente";
    }
}