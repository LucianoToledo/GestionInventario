package com.ecommerce.controller;

import com.ecommerce.enums.RolUsuario;
import com.ecommerce.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registro")
    public String form() {
        return "registro.html";
    }

    @GetMapping("/pruebaRegistroCorreto")
    public String pruebaRegistro() {
        return "prueba-registro.html";
    }

    @PostMapping("/registro")
    public String registroUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion, @RequestParam String email, @RequestParam String password, @RequestParam String confirmarPassword, @RequestParam(required = false) RolUsuario rolUsuario) {
        try {
            usuarioServicio.agregarUsuario(nombre, apellido, direccion, email, password, confirmarPassword);
            modelo.put("exito", "El Usuario '" + nombre + apellido + "' se agregó exitosamente");
        } catch (Exception ex) { //el "ex" no se esta usando, se deberia usar para traer el mensaje del servicio de usuario ex.getMessage()
            modelo.put("error",ex.getMessage()); //el mensaje deberia traerlo del servicio de usuario
        }
        return "login.html";
    }
}
