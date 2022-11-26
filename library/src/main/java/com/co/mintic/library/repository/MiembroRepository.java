package com.co.mintic.library.repository;

import com.co.mintic.library.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro,Integer> {
    List<Miembro> findAll();
    List<Miembro>findAllByBibliotecarioEquals(Integer bibliotecario);

    Optional<Miembro> findMiembroByIdMiembroAndBibliotecario(Integer idMiembro, Integer bibliotecario);

    Optional<Miembro> findByCorreoAndContrasena(String correo, String contrasena);

    List<Miembro> findAllByDniContainsOrNombreContainsOrApellidosContainsAndBibliotecarioEquals(String busquedaDni, String busquedaNombre, String busquedaApellido, Integer biblliotecario);
}
