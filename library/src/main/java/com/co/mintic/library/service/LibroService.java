package com.co.mintic.library.service;

import com.co.mintic.library.entity.Libro;
import com.co.mintic.library.jsonparams.LibroJson;
import com.co.mintic.library.repository.LibroRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<JSONObject>getAllLibros() throws UnsupportedEncodingException {
        List<Libro> libroList = libroRepository.findAll();
        List<JSONObject> librosJsonArray = getLibrosJsonArray(libroList);
        return librosJsonArray;
    }
    public Libro createLibro (Integer idCategoria, String nombre, String isbn, String descripcion, String autor, String foto, String numeroEjemplares, String edicion, String editorial){
        Libro libro = new Libro();
        libro.setIdCategoria(idCategoria);
        libro.setNombre(nombre);
        libro.setIsbn(isbn);
        libro.setDescripcion(descripcion);
        libro.setAutor(autor);
        libro.setFoto(foto);
        libro.setNumeroEjemplares(numeroEjemplares);
        libro.setEdicion(edicion);
        libro.setEditorial(editorial);

        Libro libro1 = libroRepository.save(libro);
        return libro1;

    }
    public boolean borrarLibro(Integer idLibro){
        Optional<Libro> optionalLibro = libroRepository.findById(idLibro);
        if(optionalLibro.isPresent()){
            libroRepository.delete(optionalLibro.get());
            return true;
        }
        return false;
    }
    public Libro getLibro(Integer idLibro){
        Optional<Libro> optionalLibro = libroRepository.findById(idLibro);
        if(optionalLibro.isPresent()){
            return optionalLibro.get();
        }
        return null;
    }
    public List<JSONObject> getLibrosJsonArray(List<Libro> librosList) throws UnsupportedEncodingException {
        List<JSONObject> librosJsonArray = new ArrayList<>();
        for(Libro libro: librosList){
            byte[] encodebase64 = Base64.getEncoder().encode(libro.getArchivoImagen());
            String base64Encoded = new String(encodebase64, "UTF-8");

            JSONObject jsonLibro = new JSONObject(libro);
            jsonLibro.remove("archivoImagen");
            jsonLibro.put("archivoImagen",base64Encoded);
            librosJsonArray.add(jsonLibro);
        }
        return librosJsonArray;
    }
    public List<JSONObject> getLibros(String nombreLibro) throws UnsupportedEncodingException {
        List<Libro> listlLibro = libroRepository.findAllByNombreContains(nombreLibro);
        List<JSONObject> librosJsonArray = getLibrosJsonArray(listlLibro);
        return librosJsonArray;
    }

    public List<JSONObject> getLibrosByNombreAndIdCategoria(String nombreLibro,Integer idCategoria) throws UnsupportedEncodingException {
        List<Libro> listlLibro = libroRepository.findAllByNombreContainsAndIdCategoria(nombreLibro,idCategoria);
        List<JSONObject> librosJsonArray = getLibrosJsonArray(listlLibro);
        return librosJsonArray;
    }

    public List<JSONObject> getLibros(Integer idCategoria) throws UnsupportedEncodingException {
        List<Libro> listlLibro = libroRepository.findAllByIdCategoria(idCategoria);
        List<JSONObject> librosJsonArray = getLibrosJsonArray(listlLibro);
        return librosJsonArray;
    }

    public Libro guardarFotoLibro(MultipartFile foto,Integer idLibro){
        String nombreFoto = StringUtils.cleanPath(foto.getOriginalFilename());
        Libro libro = null;
        try {
            if (!nombreFoto.contains("..")) {
                Optional<Libro> optionalLibro = libroRepository.findById(idLibro);
                if (optionalLibro.isPresent()) {
                    libro = optionalLibro.get();
                    libro.setArchivoImagen(foto.getBytes());
                    libro.setFoto(nombreFoto);
                    return libroRepository.save(libro);
                }
            }
        } catch (IOException exception){
            System.out.println(exception.getMessage());
            //throw new FileStorageException
        }
        return libro;
    }

}
