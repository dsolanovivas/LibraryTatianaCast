package com.co.mintic.library.jsonparams;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroJson {

    @JsonProperty("idCategoria")
    private Integer idCategoria;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("autor")
    private String autor;

    @JsonProperty("foto")
    private String foto;

    //@JsonProperty("archivoImagen")
    //private String archivoImagen;

    @JsonProperty("numeroEjemplares")
    private String numeroEjemplares;

    @JsonProperty("edicion")
    private String edicion;

    @JsonProperty("editorial")
    private String editorial;

    @JsonProperty("idBibliotecario")
    private Integer idBibliotecario;

    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Integer idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public void setNumeroEjemplares(String numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
