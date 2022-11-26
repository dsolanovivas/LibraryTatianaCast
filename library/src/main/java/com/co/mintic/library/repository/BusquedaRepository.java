package com.co.mintic.library.repository;

import com.co.mintic.library.entity.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Integer> {
}
