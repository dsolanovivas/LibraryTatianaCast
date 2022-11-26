package com.co.mintic.library.controller;

import com.co.mintic.library.entity.Miembro;
import com.co.mintic.library.jsonparams.MiembroJson;
import com.co.mintic.library.service.MiembroService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Miembro")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/allMiembros", method = RequestMethod.GET)
    public ResponseEntity getAllMiembros(){
        String res = "";
        List<Miembro> miembroList = miembroService.getAllMiembros();
        JSONArray jsonArray = new JSONArray(miembroList);
        res = jsonArray.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/allBibliotecarios", method = RequestMethod.GET)
    public ResponseEntity getAllBibliotecarios(){
        String res = "";
        List<Miembro> miembroList = miembroService.getAllBibliotecarios();
        JSONArray jsonArray = new JSONArray(miembroList);
        res = jsonArray.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/allLectores", method = RequestMethod.GET)
    public ResponseEntity getAllLectores(){
        String res = "";
        List<Miembro> miembroList = miembroService.getAllLectores();
        JSONArray jsonArray = new JSONArray(miembroList);
        res = jsonArray.toString();
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.GET).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarMiembro", method = RequestMethod.POST)
    public ResponseEntity createMiembro(@RequestBody MiembroJson miembroJson){
        Miembro miembro = miembroService.createMiembro(miembroJson);
        String res = "null";
        if (miembro.getIdMiembro() > 0) {
            res = "done";
            return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarMiembroLector/{dni}/{nombre}/{apellidos}/{telefono}/{correo}/{contrasena}", method = RequestMethod.POST)
    public ResponseEntity createMiembroLector(@PathVariable("dni") String dni,
                                              @PathVariable("nombre") String nombre,
                                              @PathVariable("apellidos") String apellidos,
                                              @PathVariable("telefono") String telefono,
                                              @PathVariable("correo") String correo,
                                              @PathVariable("contrasena") String contrasena){
        Miembro miembro = miembroService.createMiembroLector(dni, nombre, apellidos, telefono, correo, contrasena);
        String res = "null";
        if (miembro.getIdMiembro() > 0) {
            res = "done";
            return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);

    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/borrarMiembro/{idMiembro}/{idBibliotecario}", method = RequestMethod.POST)
    public ResponseEntity deleteMiembro(@PathVariable("idMiembro")Integer idMiembro, @PathVariable("idBibliotecario") Integer idBibliotecario) {
        Miembro miembro = miembroService.getBibliotecarioById(idBibliotecario);
        String res = "null";
        if(miembro != null) {
            boolean MiembroBorrado = miembroService.borrarMiembro(idMiembro);
            if (MiembroBorrado) {
                res = "done";
                return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        res = "Not allowed";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/loginMiembro/{correo}/{contrasena}", method = RequestMethod.POST)
    public ResponseEntity loginMiembro(@PathVariable("correo")String correo, @PathVariable("contrasena") String contrasena) {
        Miembro miembro = miembroService.getMiembroLogin(correo, contrasena);
        String res = "null";
        if(miembro != null) {
            JSONObject jsonMiembro = new JSONObject(miembro);
            res = jsonMiembro.toString();
            return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
        }
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHeaders()).allow(HttpMethod.POST).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/busquedaLectores/{busqueda}", method = RequestMethod.GET)
    public ResponseEntity getbusquedaLectores(@PathVariable("busqueda") String busqueda){
        String res = "";
        List<Miembro> miembroList = miembroService.getbusquedaLectores(busqueda);
        JSONArray jsonArray = new JSONArray(miembroList);
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
