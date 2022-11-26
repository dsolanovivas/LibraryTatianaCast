package com.co.mintic.library.controller;

import com.co.mintic.library.entity.Miembro;
import com.co.mintic.library.service.BusquedaService;
import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Busqueda")
public class BusquedaController {

    private final BusquedaService busquedaService;


    public BusquedaController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarBusqueda/{idMiembro}/{idLibro}", method = RequestMethod.GET)
    public ResponseEntity guardarBusqueda(@PathVariable("idMiembro") Integer idMiembro, @PathVariable ("idLibro") Integer idLibro){
        busquedaService.crearBusqueda(idMiembro, idLibro);
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).build();
    }

    private HttpHeaders getCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin","*");
        httpHeaders.add("Access-Control-Allow-Headers", "*");
        return httpHeaders;
    }
}
