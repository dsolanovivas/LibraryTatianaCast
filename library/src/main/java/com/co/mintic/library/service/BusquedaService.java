package com.co.mintic.library.service;

import com.co.mintic.library.entity.Busqueda;
import com.co.mintic.library.repository.BusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class BusquedaService {

    private final BusquedaRepository busquedaRepository;

    @Autowired
    public BusquedaService(BusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    public Busqueda crearBusqueda(Integer idMiembro, Integer idLibro){
        Busqueda busqueda = new Busqueda();
        busqueda.setIdMiembro(idMiembro);
        busqueda.setIdLibro(idLibro);
        busqueda.setFecha(Timestamp.from(Instant.now()));
        Busqueda busqueda1 = busquedaRepository.save(busqueda);
        return busqueda1;

    }

}
