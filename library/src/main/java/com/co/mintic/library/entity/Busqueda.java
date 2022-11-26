package com.co.mintic.library.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Cacheable(value = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "BUSQUEDA")
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Busqueda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BUSQUEDA", nullable = false)
    private Integer idBusqueda;

    @Column (name = "ID_MIEMBRO")
    private Integer idMiembro;

    @Column (name = "ID_LIBRO")
    private Integer idLibro;

    @Column (name = "FECHA")
    private Timestamp fecha;

    public Integer getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(Integer idBusqueda) {
        this.idBusqueda = idBusqueda;
    }

    public Integer getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
