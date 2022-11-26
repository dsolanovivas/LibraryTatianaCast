package com.co.mintic.library.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Cacheable(value = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "CATEGORIA")
@NoArgsConstructor(access = AccessLevel.PUBLIC)

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA", nullable = false)
    private Integer idCategoria;

    @Column(name = "CATEGORIA",length = 63)
    private String categoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
