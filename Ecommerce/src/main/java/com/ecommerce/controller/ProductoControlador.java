
package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.enums.TipoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {
    
   @Autowired
    //private ProductoServicio productoServicio;
    
    @GetMapping("/form")
    public String form(){
        return"";
    }
    
    @PostMapping("/form")
    public String form(ModelMap modelo, @RequestParam String descripcion, @RequestParam int stock, 
            @RequestParam float precioVenta/*falta Enum!!*/){
        
        try{
            //productoServicio.crearProducto();
            modelo.put("exito", "El producto '" + descripcion + "'se cargó exisotasemnte");
        }catch(Exception e){
             modelo.put("erro", "El producto '" + descripcion + "'no se ha cargado");
        }
        return"";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
          //List<Producto> productos = productoServicio.listar();
          //modelo.put("productos", productos);
          return "";        
    }
    
    //Ver como se arma front!
    @GetMapping("/modificar/{id}")
    public String modificar(RedirectAttributes attr, @RequestParam String descripcion, @RequestParam int stock, 
            @RequestParam float precioVenta, @PathVariable String id){
        
        try{
            
             attr.addFlashAttribute("exito", "El Producto se ha modificado exitosamnete!!");
        }catch(Exception e){
            attr.addFlashAttribute("error", "Error! el Producto no se ha modificado");
        }
        return"redirect:";
    }
    
    //Ver como se arma front!
    @GetMapping("/eliminar/{id}")
    public String eliminar(RedirectAttributes attr, @PathVariable String id){
        
        attr.addFlashAttribute("exito", "El Producto se ha eliminado exitosamente!!");
        return "redirect;";
    }
    
   @GetMapping("/estado/{id}")
   public String cambiarEstado(RedirectAttributes attr, @PathVariable String id){
       
       try{
           
          attr.addFlashAttribute("exito", "Se ha cambiado el estado del Producto!!");  
       }catch(Exception e){
          attr.addFlashAttribute("error", "No se ha cambiado el estado del Producto!! .");
       }
       
           return"";
   }
}
