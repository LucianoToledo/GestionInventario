package com.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authenticated")
@PreAuthorize("isAuthenticated()") //es como una mencion que devuelve true o false
public class AuthenticatedController {
    
    
    @GetMapping("/")
    public String index(){
        return "prueba-login.html";
    }
    
}
