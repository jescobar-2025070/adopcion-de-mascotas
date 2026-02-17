package com.adopcion.mascotas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @NotNull
    private LocalDate fecha;

    @NotBlank
    private String estadoSolicitud;

    @ManyToOne
    @JoinColumn(name = "idMascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "idAdoptante")
    private Adoptante adoptante;
}
