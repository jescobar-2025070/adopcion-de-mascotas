package com.vicionsa.adopcionesapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "adoptantes")
public class Adoptante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adoptante")
    private Integer idAdoptante;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre_adoptante", nullable = false, length = 150)
    private String nombreAdoptante;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(name = "correo", nullable = false, length = 150)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    public Adoptante() {
    }

    public Adoptante(String nombreAdoptante, String correo, String telefono, String direccion) {
        this.nombreAdoptante = nombreAdoptante;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(Integer idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public String getNombreAdoptante() {
        return nombreAdoptante;
    }

    public void setNombreAdoptante(String nombreAdoptante) {
        this.nombreAdoptante = nombreAdoptante;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
