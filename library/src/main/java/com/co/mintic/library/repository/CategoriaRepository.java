package com.co.mintic.library.repository;

import com.co.mintic.library.entity.Categoria;
import com.co.mintic.library.entity.Miembro;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    List<Categoria> findAll();


}
