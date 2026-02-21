package com.vicionsa.adopcionesapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_refugio")
    private Integer idRefugio;

    @NotBlank(message = "El nombre del refugio es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    @Column(name = "telefono")
    private String telefono;

    @JsonManagedReference
    @OneToMany(mappedBy = "refugio")
    @Column(name = "mascotas")
    private List<Mascota> mascotas;

    public Refugio() {
    }

    public Refugio(Integer idRefugio, String nombre, String direccion, String telefono, List<Mascota> mascotas) {
        this.idRefugio = idRefugio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mascotas = mascotas;
    }

    public Integer getIdRefugio() {
        return idRefugio;
    }

    public void setIdRefugio(Integer idRefugio) {
        this.idRefugio = idRefugio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}