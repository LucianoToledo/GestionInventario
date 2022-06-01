package com.ecommerce.controller;

import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.RolUsuario;
import com.ecommerce.services.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String registroUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion, @RequestParam String email, @RequestParam String password,
            @RequestParam String confirmarPassword, @RequestParam(required = false) RolUsuario rolUsuario, @RequestParam(required = false) MultipartFile foto) {
        try {
            usuarioServicio.agregarUsuario(nombre, apellido, direccion, email, password, confirmarPassword, foto);
            modelo.put("exito", "El Usuario '" + nombre + apellido + "' se agregó exitosamente");
        } catch (Exception ex) { //el "ex" no se esta usando, se deberia usar para traer el mensaje del servicio de usuario ex.getMessage()
            System.out.println("ex = " + ex.getMessage());
            modelo.put("error", ex.getMessage()); //el mensaje deberia traerlo del servicio de usuario
        }
        return "redirect:/login2";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(RedirectAttributes attr, @PathVariable String id) {

        try {
            usuarioServicio.eliminarUsuario(id);
            attr.addFlashAttribute("exito", "El Usuario se ha eliminado exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "El Usuario no se ha eliminado!!");
        }

        return "redirect:";
    }
    
    @GetMapping("/lista")
    public String index(ModelMap modelo){
        modelo.put("usuarios",usuarioServicio.listarUsuarios());
        return "/usuario/lista";
    }
    
    @GetMapping("/rol/{id}")
    public String cambiarRol(@PathVariable String id){
        try {
            usuarioServicio.swicthRolUsuario(id);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario/lista";
    }

    //Ver si se hace con modal o vista nueva
    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(RedirectAttributes attr, @PathVariable String id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String direccion, @RequestParam(required = false) String email, @RequestParam(required = false) String password,
            @RequestParam(required = false) String confirmarPassword, @RequestParam(required = false) MultipartFile foto) {

        try {
            usuarioServicio.modificarUsuario(id, nombre, apellido, direccion, email, password, confirmarPassword, foto);
            attr.addFlashAttribute("exito", "El Usuario se ha modificado exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "El Usuario no se ha modificado!!");
        }
        return "";
    }

    //Ver que método se usa
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarRolUsuario/{id}")
    public String modificarRolUsuario(RedirectAttributes attr, @PathVariable String id, @RequestParam RolUsuario rol) {

        try {
            usuarioServicio.modificarRolUsuario(id, rol);
            attr.addFlashAttribute("exito", "El Rol de Usuario se ha modificado exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "El Rol de Usuario no se ha modificado!!");
        }
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/bajaUsuario/{id}")
    public String bajaUsuario(RedirectAttributes attr, @PathVariable String id) {

        try {
            usuarioServicio.bajaUsuario(id);
            attr.addFlashAttribute("exito", "El Usuario se ha dado de Baja exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/altaUsuario/{id}")
    public String altaUsuario(RedirectAttributes attr, @PathVariable String id) {

        try {
            usuarioServicio.bajaUsuario(id);
            attr.addFlashAttribute("exito", "El Usuario se ha dado de Alta exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:";
    }

    //Get o Post??
    @GetMapping("/buscarPorRol")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String buscarPorRol(ModelMap modelo, @RequestParam RolUsuario rol) {

        List<Usuario> usuarios = usuarioServicio.buscarPorRol(rol);
        modelo.put("usuarios", usuarios);

        return "";
    }

    //Get o Post??
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/buscarPorNombreApellidoEmail")
    public String buscarPorNombreApellidoEmail(ModelMap modelo, @RequestParam String query) {

        List<Usuario> usuarios = usuarioServicio.buscarPorNombreApellidoEmail(query);
        modelo.put("usuarios", usuarios);

        return "";
    }

    //Get o Post??
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/buscarPorEmail")
    public String buscarPorEmail(ModelMap modelo, @RequestParam String email) {

        try {
            usuarioServicio.buscarPorEmail(email);
            modelo.put("exito", "El email del Usuario se ha encontrado!!");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/listarUsuarios")
    public String listaUsuarios(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("usuarios", usuarios);

        return "pruebaAdminVistaListaUsuarios";

    }
}
