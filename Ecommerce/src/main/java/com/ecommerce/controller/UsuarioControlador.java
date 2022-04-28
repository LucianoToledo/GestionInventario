
package com.ecommerce.controller;


import com.ecommerce.enums.RolUsuario;
import com.ecommerce.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
  
  @Autowired
  private UsuarioServicio usuarioServicio;
  
  @GetMapping("/form")
  public String form(){
      
      return "";
  }
   
  @PostMapping("/form")
  public String agregarUsuario(ModelMap modelo,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion, 
          @RequestParam String email, @RequestParam String password, @RequestParam String confirmarPassword, 
          @RequestParam RolUsuario rolUsuario ){
      
      try{
          usuarioServicio.agregarUsuario(nombre, apellido, direccion, email, password, confirmarPassword, rolUsuario);
          modelo.put("exito", "El Usuario '" + nombre + apellido +"' se agregó exitosamente");
      }catch (Exception e){
          modelo.put("error", "Erro!!, No se agregó el Usuario");
      }   
      
      return"";
  }
}
