package com.ecommerce.controller;

import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.RolUsuario;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registro")
    public String form() {
        return "registro.html";
    }

    @GetMapping("/pruebaRegistroCorreto")
    public String pruebaRegistro() {
        return "prueba-registro.html";
    }

    @PostMapping("/registro")
    public String registroUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion, @RequestParam String email, @RequestParam String password, @RequestParam String confirmarPassword, @RequestParam(required = false) RolUsuario rolUsuario) {
        try {
            usuarioServicio.agregarUsuario(nombre, apellido, direccion, email, password, confirmarPassword);
            modelo.put("exito", "El Usuario '" + nombre + apellido + "' se agregó exitosamente");
        } catch (Exception ex) { //el "ex" no se esta usando, se deberia usar para traer el mensaje del servicio de usuario ex.getMessage()
            modelo.put("error",ex.getMessage()); //el mensaje deberia traerlo del servicio de usuario
        }
        return "login.html";
    }
    
  @GetMapping ("/eliminarUsuario/{id}") 
  public String eliminarUsuario(RedirectAttributes attr, @PathVariable String id){
      
      try{
          usuarioServicio.eliminarUsuario(id);
          attr.addFlashAttribute("exito", "El Usuario se ha eliminado exitosamente!!");
      }catch(Exception e){
          attr.addFlashAttribute("error", "El Usuario no se ha eliminado!!");
      }
      
      return "redirect:";
  }
  
  //Ver si se hace con modal o vista nueva
  @GetMapping("/modificarUsuario/{id}")
  public String modificarUsuario(RedirectAttributes attr, @PathVariable String id,@RequestParam String nombre, @RequestParam String apellido, 
          @RequestParam String direccion, @RequestParam String email, @RequestParam String password, 
          @RequestParam String confirmarPassword, @RequestParam RolUsuario rolUsuario){
      
      try{
          usuarioServicio.modificarUsuario(id, nombre, apellido, direccion, email, password, confirmarPassword, rolUsuario);
          attr.addFlashAttribute("exito", "El Usuario se ha modificado exitosamente!!");
      }catch (Exception e){
          attr.addFlashAttribute("error", "El Usuario no se ha modificado!!");
      }
      return"";
  }
  
  //Ver que método se usa
  @GetMapping("/modificarRolUsuario/{id}")
  public String modificarRolUsuario(RedirectAttributes attr, @PathVariable String id, @RequestParam RolUsuario rol){
      
      try{
          usuarioServicio.modificarRolUsuario(id, rol);
          attr.addFlashAttribute("exito", "El Rol de Usuario se ha modificado exitosamente!!");
      }catch(Exception e){
          attr.addFlashAttribute("error", "El Rol de Usuario no se ha modificado!!");
      }
      return"redirect:";
  }
  
  @GetMapping("/bajaUsuario/{id}")
  public String bajaUsuario(RedirectAttributes attr, @PathVariable String id){
      
      try{
         usuarioServicio.bajaUsuario(id);
         attr.addFlashAttribute("exito", "El Usuario se ha dado de Baja exitosamente!!");
      }catch(Exception e){
        attr.addFlashAttribute("error", e.getMessage());  
      }
      
      return"redirect:";
  }
  
  @GetMapping("/altaUsuario/{id}")
  public String altaUsuario(RedirectAttributes attr, @PathVariable String id){
      
      try{
         usuarioServicio.bajaUsuario(id);
         attr.addFlashAttribute("exito", "El Usuario se ha dado de Alta exitosamente!!");
      }catch(Exception e){
        attr.addFlashAttribute("error", e.getMessage());  
      }
      
      return"redirect:";
  }
  
  
  @GetMapping("/buscarPorRol")
  public String buscarPorRol(ModelMap modelo, @RequestParam RolUsuario rol){
      
      List<Usuario> usuarios = usuarioServicio.buscarPorRol(rol);
      modelo.put("usuarios", usuarios);
      
      return"";
  }
  
  
  @GetMapping("/buscarPorNombreApellidoEmail")
  public String buscarPorNombreApellidoEmail(ModelMap modelo, @RequestParam String query){
      
         List<Usuario> usuarios = usuarioServicio.buscarPorNombreApellidoEmail(query); 
         modelo.put("usuarios", usuarios);
      
      return"";
  }
  
  
  @GetMapping("/buscarPorEmail")
  public String buscarPorEmail(ModelMap modelo, @RequestParam String email){
      
      try{
          usuarioServicio.buscarPorEmail(email);
          modelo.put("exito", "El email del Usuario se ha encontrado!!");
      }catch(Exception e){
          modelo.put("error", e.getMessage());
      }
      return"";
  }
  
  @GetMapping("/listaUsuarios")
  public String listaUsuarios(ModelMap modelo){
          List<Usuario> usuarios = usuarioServicio.listarUsuarios();
          modelo.put("usuarios", usuarios);
          
       return "lista-usuario.html";        
    }
}
