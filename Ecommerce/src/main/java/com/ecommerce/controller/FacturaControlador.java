
package com.ecommerce.controller;

import com.ecommerce.entities.Factura;
import com.ecommerce.entities.Producto;
import com.ecommerce.enums.EstadoFactura;
import com.ecommerce.services.FacturaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FacturaControlador {
    
    @Autowired
    private FacturaServicio facturaServicio;
    
   @GetMapping("/factura")
   public String factura(){
       return "factura.html";
   }
    
   @PostMapping("/factura")
   public String alta(ModelMap modelo, @RequestParam String idUsuario, @RequestParam List<Producto> productos, @RequestParam EstadoFactura estadoFactura){
       
       try {
       facturaServicio.alta(idUsuario, productos, estadoFactura);
       modelo.put("alta", "La factura ha sido cargada con éxito");
       } catch (Exception e) {
           modelo.put("error", e.getMessage());
       }
       return "factura.html";
   }
   
   
   
   

   
   
   @GetMapping("/factura/lista")
   public String lista(ModelMap modelo){
       List<Factura> facturas = facturaServicio.listar();
       modelo.put("lista", facturas);
       return "lista-factura.html";
       
   }
       
       
       
       
 
    
}
