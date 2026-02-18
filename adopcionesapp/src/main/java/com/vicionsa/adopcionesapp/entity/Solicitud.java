package com.vicionsa.adopcionesapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotBlank(message = "El estado de la solicitud es obligatorio")
    @Column(name = "estado_solicitud", nullable = false, length = 20)
    private String estadoSolicitud;

    @NotNull(message = "Debe seleccionar una mascota")
    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    @NotNull(message = "Debe seleccionar un adoptante")
    @ManyToOne
    @JoinColumn(name = "id_adoptante", nullable = false)
    private Adoptante adoptante;

    public Solicitud() {
    }

    public Solicitud(LocalDate fecha, String estadoSolicitud, Mascota mascota, Adoptante adoptante) {
        this.fecha = fecha;
        this.estadoSolicitud = estadoSolicitud;
        this.mascota = mascota;
        this.adoptante = adoptante;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }
}
