package com.co.mintic.library.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Cacheable(value = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "LIBRO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRO", nullable = false)
    private Integer idLibro;

    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;

    @Column(name = "NOMBRE",length = 127)
    private String nombre;

    @Column(name = "ISBN",length = 127)
    private String isbn;

    @Column(name = "DESCRIPCION",length = 1023)
    private String descripcion;

    @Column(name = "AUTOR",length = 127)
    private String autor;

    @Column(name = "FOTO",length = 511)
    private String foto;

    @Column(name = "ARCHIVO_IMAGEN")
    @Lob
    private byte[] archivoImagen;

    @Column(name = "NUMERO_EJEMPLARES",length = 11)
    private String numeroEjemplares;

    @Column(name = "EDICION",length = 11)
    private String edicion;

    @Column(name = "EDITORIAL",length = 63)
    private String editorial;

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public byte[] getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(byte[] archivoImagen) {
        this.archivoImagen = archivoImagen;
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
