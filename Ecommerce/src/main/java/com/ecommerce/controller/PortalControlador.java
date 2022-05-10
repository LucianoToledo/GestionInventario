package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")

public class PortalControlador {

    @GetMapping("/")
    public String pruebaLogin() {
        return "prueba-login.html";
    }

    @GetMapping("/login")
    public String login(ModelMap model, @RequestParam(required = false) String error, @RequestParam(required = false) String logout) {
        if (error != null) {
            model.put("error", "Usuario o Clave incorrectos >:(");
        }

        if (logout != null) {
            model.put("logout", "Has cerrado sesión exitosamente :)");
        }
        return "login.html";
    }
    
    @GetMapping("/detalles")
    public String detalles(){
        return "detalles";
    }

}
