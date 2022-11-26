package com.co.mintic.library.service;

import com.co.mintic.library.entity.Categoria;
import com.co.mintic.library.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getAllCategorias(){
        return categoriaRepository.findAll();
    }
}
