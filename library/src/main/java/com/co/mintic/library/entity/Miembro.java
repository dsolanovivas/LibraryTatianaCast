package com.co.mintic.library.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Cacheable(value = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MIEMBRO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MIEMBRO", nullable = false)
    private Integer idMiembro;

    @Column(name = "DNI",length = 127)
    private String dni;

    @Column (name ="NOMBRE", length = 127)
    private String nombre;

    @Column(name ="APELLIDOS", length = 127)
    private String apellidos;

    @Column(name ="TELEFONO", length = 15)
    private String telefono;

    @Column(name ="CORREO", length = 63)
    private String correo;

    @Column(name ="CONTRASENA", length = 511)
    private String contrasena;

    @Column(name ="BIBLIOTECARIO")
    private Integer bibliotecario;

    public Integer getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Integer bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
}
