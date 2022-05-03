package com.ecommerce.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class PortalControlador {
    
    @GetMapping("/")
    public String pruebaLogin(){
        return "prueba-login.html";
    }
    
}
