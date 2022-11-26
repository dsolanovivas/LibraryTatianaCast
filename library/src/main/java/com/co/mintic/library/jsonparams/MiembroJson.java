package com.co.mintic.library.jsonparams;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MiembroJson {

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellidos")
    private String apellidos;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("correo")
    private String correo;

    @JsonProperty("contrasena")
    private String contrasena;

    @JsonProperty("bibliotecarios")
    private Integer bibliotecario;

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
