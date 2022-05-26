package com.ecommerce.controller;

import com.ecommerce.services.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private ProductoServicio productoServicio;

    /**
     * @return 
    @GetMapping("/")
    public String pruebaLogin() {
        return "prueba-login.html";
    }
    */
    
    @GetMapping()
    public String index(ModelMap model){
        model.put("ListadosProductos", productoServicio.listar());
        return "index";
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
    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable String id,ModelMap model) throws Exception{
        model.put("producto", productoServicio.buscarPorId(id));
        return "detalles";
    }
    
     @GetMapping("shop")
    public String shop(ModelMap model){
        
        return "shop.html";
    }
    
    @GetMapping("index2")
    public String index2(ModelMap model){
        model.put("ListadosProductos", productoServicio.listar());
        
        return "index2.html";
    }
    
    @GetMapping("about")
    public String about(ModelMap model){
        
        return "about.html";
    }
    
    @GetMapping("contact")
    public String contacto(ModelMap model){
        
        return "contact.html";
    }
}
