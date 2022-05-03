
package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.enums.TipoProducto;
import com.ecommerce.services.ProductoServicio;
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
   private ProductoServicio productoServicio;
    
    @GetMapping("/crear")
    public String crear(){
        return"";
    }
    
    @PostMapping("/crear")
    public String crear(ModelMap modelo, @RequestParam String descripcion, @RequestParam int stock, 
            @RequestParam float precioVenta, @RequestParam TipoProducto tipoProducto){
        
        try{
            productoServicio.crearProducto(descripcion, stock, precioVenta, tipoProducto);
            modelo.put("exito", "El producto '" + descripcion + "'se cargó exisotasemnte");
        }catch(Exception e){
             modelo.put("error", e.getMessage() );
        }
        return"";
    }
       
   @GetMapping("/fechaAlta/{id}")
   public String fechaAlta(RedirectAttributes attr, @PathVariable String id){
       
       try{
           productoServicio.fechaAltaProducto(id);
          attr.addFlashAttribute("exito", "Se ha cambiado el estado del Producto!!");  
       }catch(Exception e){
          attr.addFlashAttribute("error", e.getMessage());
       }
       
           return"redirect:";
   }
   
   @GetMapping("/fechaBaja/{id}")
   public String fechaBaja(RedirectAttributes attr, @PathVariable String id){
       
       try{
           productoServicio.fechaBajaProducto(id);
           attr.addFlashAttribute("exito", "Se ha cambiado el estado del Producto!!"); 
       }catch (Exception e){
           attr.addFlashAttribute("error", e.getMessage());
       }
       return "redirect:";
   }
   
   //Ver si se hace con Modal o vista nueva
   @PostMapping("/actualizar/{id}")//Ver si es Get o Post!
   public String actualizar(RedirectAttributes attr, @PathVariable String id, @RequestParam String descripcion, @RequestParam Integer stock, 
           @RequestParam Integer precioVenta, @RequestParam TipoProducto tipoProducto){
       
       try{
           productoServicio.actualizarProducto(id, descripcion, stock, precioVenta, tipoProducto);
           attr.addFlashAttribute("exito", "El Producto ha sido actualizado!!");
       }catch(Exception e){
           attr.addFlashAttribute("error", "No se ha actualizado el Producto");
       }
       return "redirect:";
   }
   
    @GetMapping("/eliminar/{id}")
    public String eliminar(RedirectAttributes attr, @PathVariable String id){
        
        try{
            productoServicio.eliminarProducto(id);
            attr.addFlashAttribute("exito", "El Producto se ha eliminado exitosamente!!");
        }catch(Exception e){
            attr.addFlashAttribute("error", "El Producto no se ha eliminado!!");
        }
        
        return "redirect;";
    }
    
    //Ver si se hace con Modal o vista nueva
    @GetMapping("/actualizarStock/{id}")
    public String actualizarStock(RedirectAttributes attr, @PathVariable String id, @RequestParam int stock){
        
        try{
            productoServicio.actualizarStockProducto(id, stock);
            attr.addFlashAttribute("exito", "El stock del Producto se actualizó exitosamente!!");
        }catch(Exception e){
            attr.addFlashAttribute("error", "El stock del Producto no se ha actualizado!!");
        }
        
        return"redirect:";
    }
 
   @GetMapping("/lista")
    public String lista(ModelMap modelo){
          List<Producto> productos = productoServicio.listar();
          modelo.put("productos", productos);
          return "";        
    }
    
    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(ModelMap modelo, @RequestParam String query){
        
        try{
           productoServicio.buscarPorNombre(query);
           modelo.put("exito", "El Producto se ha encontrado");
        }catch(Exception e){
            modelo.put("error", "No se encontró Producto con ese nombre");
        }
        return"";
    }
}
