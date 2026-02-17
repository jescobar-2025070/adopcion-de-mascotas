package com.adopcion.mascotas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;

    @NotBlank
    private String nombreMascota;

    @NotBlank
    private String especie;

    private String raza;
    private Integer edad;

    @NotBlank
    private String estado;
}
