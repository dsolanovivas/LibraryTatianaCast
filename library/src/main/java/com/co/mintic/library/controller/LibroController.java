package com.co.mintic.library.controller;

import com.co.mintic.library.entity.Libro;
import com.co.mintic.library.entity.Miembro;
import com.co.mintic.library.jsonparams.LibroJson;
import com.co.mintic.library.service.LibroService;
import com.co.mintic.library.service.MiembroService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "/Libro")
public class LibroController {

    private final LibroService libroService;
    private final MiembroService miembroService;

    public LibroController(LibroService libroService, MiembroService miembroService) {
        this.libroService = libroService;
        this.miembroService = miembroService;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/allLibros", method = RequestMethod.GET)
    public ResponseEntity getAllLibros() throws UnsupportedEncodingException {
        String res = "";
        List<JSONObject> libroList = libroService.getAllLibros();
        JSONArray jsonArray = new JSONArray(libroList);
        res = jsonArray.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }
    /*@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarLibro", method = RequestMethod.POST)
    public ResponseEntity createLibro(@RequestBody LibroJson libroJson) {
        Miembro miembro = miembroService.getBibliotecarioById(libroJson.getIdBibliotecario());
        String res = "null";
        if(miembro != null) {
            Libro libro = libroService.createLibro(libroJson);
            if (libro.getIdLibro() > 0) {
                res = "done";
                return ResponseEntity.status(HttpStatus.OK).allow(HttpMethod.POST).body(res);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        res = "not allowed";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
    }*/

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarLibro/{idBibliotecario}/{idCategoria}/{nombre}/{isbn}/{descripcion}/{autor}/{foto}/{numeroEjemplares}/{edicion}/{editorial}", method = RequestMethod.POST)
    public ResponseEntity guardarLibro(@PathVariable("idBibliotecario") Integer idBibliotecario,
                                       @PathVariable("idCategoria") Integer idCategoria,
                                       @PathVariable("nombre") String nombre,
                                       @PathVariable("isbn") String isbn,
                                       @PathVariable("descripcion") String descripcion,
                                       @PathVariable("autor") String autor,
                                       @PathVariable("foto") String foto,
                                       @PathVariable("numeroEjemplares") String numeroEjemplares,
                                       @PathVariable("edicion") String edicion,
                                       @PathVariable("editorial") String editorial){
        Miembro miembro = miembroService.getBibliotecarioById(idBibliotecario);
        String res = "null";
        if(miembro != null) {
            Libro libro = libroService.createLibro(idCategoria, nombre, isbn, descripcion, autor, foto, numeroEjemplares, edicion,editorial);
            if (libro.getIdLibro() > 0) {
                res = String.valueOf(libro.getIdLibro());
                return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        res = "not allowed";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/borrarLibro/{idLibro}/{idBibliotecario}", method = RequestMethod.POST)
    public ResponseEntity deleteLibro(@PathVariable("idLibro")Integer idLibro, @PathVariable("idBibliotecario") Integer idBibliotecario) {
        Miembro miembro = miembroService.getBibliotecarioById(idBibliotecario);
        String res = "null";
        if(miembro != null) {
            boolean libroBorrado = libroService.borrarLibro(idLibro);
            if (libroBorrado) {
                res = "done";
                return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        res = "Not allowed";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerLibroId", method = RequestMethod.GET)
    public ResponseEntity obtenerLibroId(@RequestParam("idLibro")Integer idLibro) throws UnsupportedEncodingException {
        Libro libro = libroService.getLibro(idLibro);
        String res = "null";
        if (libro != null) {
            byte[] encodebase64 = Base64.getEncoder().encode(libro.getArchivoImagen());
            String base64Encoded = new String(encodebase64, "UTF-8");

            JSONObject jsonLibro = new JSONObject(libro);
            jsonLibro.remove("archivoImagen");
            jsonLibro.put("archivoImagen",base64Encoded);
            res = jsonLibro.toString();
            return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerLibroNombre/{nombreLibro}", method = RequestMethod.GET)
    public ResponseEntity obtenerLibroNombre(@PathVariable("nombreLibro")String nombreLibro) throws UnsupportedEncodingException {
        List<JSONObject> libroList = libroService.getLibros(nombreLibro);
        String res = "null";

        JSONArray jsonLibroList = new JSONArray(libroList);
        res = jsonLibroList.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerLibroCategoria/{idCategoria}", method = RequestMethod.GET)
    public ResponseEntity obtenerLibroCategoria(@PathVariable("idCategoria")Integer idCategoria) throws UnsupportedEncodingException {
        List<JSONObject> libroList = libroService.getLibros(idCategoria);
        String res = "null";

        JSONArray jsonLibroList = new JSONArray(libroList);
        res = jsonLibroList.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerLibroCategoriaNombre/{nombreDelLibro}/{idCategoria}", method = RequestMethod.GET)
    public ResponseEntity obtenerLibroCategoriaNombre(@PathVariable("nombreDelLibro")String nombreDelLibro,
                                                      @PathVariable("idCategoria")Integer idCategoria) throws UnsupportedEncodingException {
        List<JSONObject> libroList = libroService.getLibrosByNombreAndIdCategoria(nombreDelLibro,idCategoria);
        String res = "null";

        JSONArray jsonLibroList = new JSONArray(libroList);
        res = jsonLibroList.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);

    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarFotoLibro", method = RequestMethod.POST)
    public ResponseEntity guardarFotoLibro(@RequestParam("idLibro")Integer idLibro,
                                           @RequestParam("idBibliotecario")Integer idBibliotecario,
                                           @RequestParam("foto")MultipartFile foto) {
        Miembro miembro = miembroService.getBibliotecarioById(idBibliotecario);
        String res = "null";
        if (miembro != null) {
            res = "done";
            libroService.guardarFotoLibro(foto,idLibro);
            return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        res = "Not allowed";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }
    /*@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerFotoLibro/{idLibro}", method = RequestMethod.GET)
    public ResponseEntity<Resource> obtenerFotoLibro(@PathVariable("idLibro")Integer idLibro) {
        Libro libro = libroService.getLibro(idLibro);

        return ResponseEntity.status(HttpStatus.OK)
                .allow(HttpMethod.GET).contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + libro.getFoto() + "\"")
                .body(new ByteArrayResource(libro.getArchivoImagen()));
    }*/

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerFotoLibro/{idLibro}", method = RequestMethod.GET)
    public ResponseEntity obtenerFotoLibro(@PathVariable("idLibro")Integer idLibro) throws UnsupportedEncodingException {
        Libro libro = libroService.getLibro(idLibro);

        byte[] encodebase64 = Base64.getEncoder().encode(libro.getArchivoImagen());
        String base64Encoded = new String(encodebase64, "UTF-8");
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(base64Encoded);

    }

    private HttpHeaders getCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin","*");
        httpHeaders.add("Access-Control-Allow-Headers", "*");
        return httpHeaders;
    }
}
