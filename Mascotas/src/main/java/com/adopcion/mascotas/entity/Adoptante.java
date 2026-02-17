package com.adopcion.mascotas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Adoptante")
public class Adoptante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdoptante;

    @NotBlank
    private String nombreAdoptante;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String telefono;

    @NotBlank
    private String direccion;
}
