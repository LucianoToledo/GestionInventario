//package com.ecommerce.controller;
//
//import com.ecommerce.entities.Carrito;
//import com.ecommerce.services.CarritoServicio;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/carrito")
//public class CarritoControlador {
//
//    @Autowired
//    CarritoServicio carritoServicio;
//
////    @GetMapping("/carrito")
////    public String carrito() {
////        return "carro-compras";
////    }
//    @PostMapping("/agregarProductoCarrito")
//    public String agregarProductoCarrito(@RequestParam String nombre, @RequestParam String cantidad, HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
//        carritoServicio.agregarProducto(cantidad, nombre);
//        return "redirect:" + referer;
//        // return "redirect:/carro-compras"; //otra posibilidad es que redirija a la misma pagina en la que esta parado el usuario
//    }
//
//    @GetMapping("/")
//    public String itemsCarrito(ModelMap model, HttpSession session) {
//        model.put("listado", session.getAttribute("itemsCarrito"));
//        return "carro-compras.html";
//    }
//
//    @GetMapping("/limpiar")
//    public String limpiarCarrito(HttpSession session) {
//        carritoServicio.limpiarCarrito((List<Carrito>) session.getAttribute("itemsCarrito"));
//        return "index.html";
//    }
//
//    @PostMapping("comprar")
//    public String comprar(@RequestBody List<Carrito> productos) {
//        carritoServicio.limpiarCarrito(productos);
//        return "redirect:/index";  //otra posibilidad es que redirija al pdf con la factura
//    }
//
//}
