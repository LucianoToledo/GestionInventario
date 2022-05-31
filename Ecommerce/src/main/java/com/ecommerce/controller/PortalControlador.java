package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.services.ProductoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/index")
    public String index(ModelMap model) {
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
    public String detalles(@PathVariable String id, ModelMap model) throws Exception {
        model.put("producto", productoServicio.buscarPorId(id));
        return "detalles";
    }

    @GetMapping("/shop")
    public String shop(ModelMap model, @RequestParam(required = false) String query) {

        List<Producto> lista = new ArrayList();

        if (query == null) {
            lista = productoServicio.listar();
        } else {
            lista = productoServicio.buscarPorQuery(query);
        }
        
        model.put("ListadosProductos", lista);
        return "shop.html";
    }

    @GetMapping("about")
    public String about(ModelMap model) {

        return "about.html";
    }

    @GetMapping("contact")
    public String contacto(ModelMap model) {

        return "contact.html";
    }

    @GetMapping("shop-single/{id}")
    public String shopSingle(@PathVariable String id, ModelMap model) {
        try {
            model.put("Producto", productoServicio.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "shop-single.html";
    }
}
