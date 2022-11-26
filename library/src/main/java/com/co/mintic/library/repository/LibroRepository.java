package com.co.mintic.library.repository;

import com.co.mintic.library.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> findAll();
    List<Libro> findAllByNombreContains (String nombreLibro);

    List<Libro> findAllByNombreContainsAndIdCategoria (String nombreLibro,Integer idCategoria);

    List<Libro> findAllByNombre(String nombreLibro);
    List<Libro> findAllByIdCategoria(Integer idCategoria);
}
