package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.services.FacturaServicio;
import com.ecommerce.services.ProductoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private FacturaServicio facturaServicio;

    @GetMapping("/index")
    public String index(ModelMap model) {
        model.put("ListadosProductos", productoServicio.listar());
        return "index";
    }
    
    @GetMapping("/login2")
    public String login2(ModelMap model){
        
        return "login2.html";
    }

    
//    @GetMapping("/login")
//    public String login(ModelMap model, @RequestParam(required = false) String error, @RequestParam(required = false) String logout) {
//        if (error != null) {
//            model.put("error", "Usuario o Clave incorrectos >:(");
//        }
//
//        if (logout != null) {
//            model.put("logout", "Has cerrado sesión exitosamente :)");
//        }
//        return "login.html";
//    }
    

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable String id, ModelMap model) throws Exception {
        model.put("producto", productoServicio.buscarPorId(id));
        return "detalles";
    }
    

    @GetMapping("/shop")
    public String shop(ModelMap model, @RequestParam(required = false) String query, @PageableDefault(size = 3) Pageable pageable) {
        Page<Producto> productos;

        if (query == null) {
           productos = productoServicio.getAll(pageable);
        } else {
            productos = productoServicio.buscarPorQuery(query, pageable);
        }
        
        model.put("page", productos);
        
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
    
    @GetMapping("/comprar/{id}")
    public String comprar(@PathVariable String id,ModelMap modelo){
        modelo.put("compras", facturaServicio.buscarFactura(id));
        return "comprar";
    }
    
    @GetMapping("/cantidad/{id}")
    public ResponseEntity<?> cantidadComprada(@PathVariable String id){
        
        return ResponseEntity.ok(facturaServicio.cantidadProducto(id));
        
    }
}
