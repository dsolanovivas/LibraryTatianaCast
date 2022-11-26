package com.co.mintic.library.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
// Este controller se uso con propositos de prueba inicial de respuesta de la api.
@RestController
@RequestMapping(path = "/happy")
public class HappyController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/face", method = RequestMethod.GET)
    public ResponseEntity getHappyFace(){
        String res = ":D";

        return ResponseEntity.status(HttpStatus.OK).allow(HttpMethod.GET).body(res);
    }

}
