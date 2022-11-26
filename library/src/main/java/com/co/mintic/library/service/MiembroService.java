package com.co.mintic.library.service;

import com.co.mintic.library.entity.Libro;
import com.co.mintic.library.entity.Miembro;
import com.co.mintic.library.jsonparams.MiembroJson;
import com.co.mintic.library.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;

    @Autowired
    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public List<Miembro> getAllMiembros(){
        return miembroRepository.findAll();
    }

    public List<Miembro> getAllBibliotecarios(){
        int bibliotecario = 1;
        return miembroRepository.findAllByBibliotecarioEquals(bibliotecario);
    }

    public List<Miembro> getAllLectores(){
        int bibliotecario = 0;
        return miembroRepository.findAllByBibliotecarioEquals(bibliotecario);
    }

    public Miembro createMiembro(MiembroJson miembroJson){
        Miembro miembro = new Miembro();
        miembro.setDni(miembroJson.getDni());
        miembro.setNombre(miembroJson.getNombre());
        miembro.setApellidos(miembroJson.getApellidos());
        miembro.setTelefono(miembroJson.getTelefono());
        miembro.setCorreo(miembroJson.getCorreo());
        miembro.setContrasena(miembroJson.getContrasena());
        miembro.setBibliotecario(miembroJson.getBibliotecario());

        Miembro miembro1 = miembroRepository.save(miembro);
        return miembro1;
    }

    public Miembro createMiembroLector(String dni, String nombre, String apellidos, String telefono, String correo, String contrasena){
        Miembro miembro = new Miembro();
        int bibliotecario = 0;
        miembro.setDni(dni);
        miembro.setNombre(nombre);
        miembro.setApellidos(apellidos);
        miembro.setTelefono(telefono);
        miembro.setCorreo(correo);
        miembro.setContrasena(contrasena);
        miembro.setBibliotecario(bibliotecario);

        Miembro miembro1 = miembroRepository.save(miembro);
        return miembro1;
    }
    public Miembro getBibliotecarioById(Integer idBibliotecario){
        int bibliotecario = 1;
        Optional<Miembro> optionalMiembro = miembroRepository.findMiembroByIdMiembroAndBibliotecario(idBibliotecario, bibliotecario);
        if (optionalMiembro.isPresent()){
            return optionalMiembro.get();
        }
        return null;
    }

    public boolean borrarMiembro(Integer idMiembro) {
        Optional<Miembro> optionalMiembro = miembroRepository.findById(idMiembro);
        if(optionalMiembro.isPresent()){
            miembroRepository.delete(optionalMiembro.get());
            return true;
        }
        return false;
    }

    public Miembro getMiembroLogin(String correo, String contrasena){
        Optional<Miembro> optionalMiembro = miembroRepository.findByCorreoAndContrasena(correo, contrasena);
        if (optionalMiembro.isPresent()){
            Miembro miembro = optionalMiembro.get();
            miembro.setContrasena(null);
            return miembro;
        }
        return null;
    }

    public List<Miembro> getbusquedaLectores(String busqueda) {
        int bibliotecario = 0;
        return miembroRepository.findAllByDniContainsOrNombreContainsOrApellidosContainsAndBibliotecarioEquals(busqueda, busqueda, busqueda, bibliotecario);
    }
}
