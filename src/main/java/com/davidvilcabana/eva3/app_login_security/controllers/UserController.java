package com.davidvilcabana.eva3.app_login_security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que la clase es un controlador de Spring que manejará las solicitudes
                // HTTP y devolverá los resultados directamente en el cuerpo de la respuesta.
@RequestMapping("/user") // Especifica que todas las solicitudes a la ruta "/user" serán manejadas por
                         // este controlador.
public class UserController {
    @GetMapping // Anotación que se utiliza para mapear solicitudes HTTP GET a métodos
                // específicos en el controlador.
    public ResponseEntity<String> getUser() {//Método que maneja las solicitudes GET a la ruta "/user"
        return ResponseEntity.ok("sucesso!");//devuelve una respuesta http
    }

}
