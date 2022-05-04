
package com.ecommerce.controller;

import com.ecommerce.Errores.ErrorServicio;
import com.ecommerce.entities.Factura;
import com.ecommerce.entities.Producto;
import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.EstadoFactura;
import com.ecommerce.services.FacturaServicio;
import com.ecommerce.services.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/factura")
public class FacturaControlador {
    
    @Autowired
    private FacturaServicio facturaServicio;
    @Autowired
    
    private UsuarioServicio usuarioServicio;
   @GetMapping("/crear")
   public String factura(ModelMap modelo){
       List<Usuario> usuarios = usuarioServicio.listarUsuarios();
       modelo.put("usuarios", usuarios);
       return "factura.html";
   }
    
   @PostMapping("/crear")
   public String crear(ModelMap modelo, @RequestParam String idUsuario, @RequestParam List<Producto> productos, @RequestParam EstadoFactura estadoFactura){
       
       try {
       facturaServicio.crear(idUsuario, productos, estadoFactura);
       modelo.put("alta", "La factura ha sido cargada con éxito");
       } catch (Exception e) {
           modelo.put("error", e.getMessage());
       }
       return "factura-crear.html";
   }
   
    @PostMapping("/alta/{idFactura}")
   public String alta(ModelMap modelo, @PathVariable String idFactura, @RequestParam String idUsuario) throws ErrorServicio{
       try {
           facturaServicio.alta(idFactura);
           modelo.put("alta", "La factura ha sido dada de alta");
       } catch (Exception e) {
           modelo.put("error", e.getMessage());
       }
       return "factura-alta.html";
       
   }
   
   @PostMapping("/baja")
   public String baja(ModelMap modelo, @RequestParam String idFactura, @RequestParam String idUsuario) throws ErrorServicio{
       try {
           facturaServicio.baja(idFactura);
           modelo.put("baja", "La factura ha sido dada de baja");
       } catch (Exception e) {
           modelo.put("error", e.getMessage());
       }
       return "factura-baja.html";
       
   }
   
   @GetMapping("/lista")
   public String lista(ModelMap modelo){
       List<Factura> facturas = facturaServicio.listar();
       modelo.put("lista", facturas);
       return "lista-factura.html";
   }
       
       
       
       
       
 
    
}
