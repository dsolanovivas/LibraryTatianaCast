package com.co.mintic.library.controller;

import com.co.mintic.library.entity.Categoria;
import com.co.mintic.library.entity.Miembro;
import com.co.mintic.library.service.CategoriaService;
import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/Categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/allCategorias", method = RequestMethod.GET)
    public ResponseEntity getAllCategorias(){
        String res = "";
        List<Categoria> categoriaList = categoriaService.getAllCategorias();
        JSONArray jsonArray = new JSONArray(categoriaList);
        res = jsonArray.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }

    private HttpHeaders getCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin","*");
        httpHeaders.add("Access-Control-Allow-Headers", "*");
        return httpHeaders;
    }
}
