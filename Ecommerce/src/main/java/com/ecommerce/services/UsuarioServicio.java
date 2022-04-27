package com.ecommerce.services;

import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.RolUsuario;
import com.ecommerce.repositories.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional(rollbackFor = {Exception.class})
    public void agregarUsuario(String nombre, String apellido, String direccion, String email, String password, String confirmarPassword, RolUsuario rolUsuario) throws Exception {
        validarDatos(nombre, apellido, direccion, email, password, confirmarPassword);
        String passwordEncriptado = new BCryptPasswordEncoder().encode(password);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncriptado);
        usuario.setActivo(true);
        usuario.setFechaAltaUsuario(new Date());
        usuario.setFechaBajaUsuario(null);
        usuario.setRolUsuario(rolUsuario); //se tiene que pedir desde la vista

        usuarioRepositorio.save(usuario);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void eliminarUsuario(String id) throws Exception {
        usuarioRepositorio.deleteById(buscarPorId(id).getId());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void modificarUsuario(String id, String nombre, String apellido, String direccion, String email, String password, String confirmarPassword, RolUsuario rolUsuario) throws Exception {
        Usuario usuario = buscarPorId(id);

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDireccion(direccion);

        usuarioRepositorio.save(usuario);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void modificarRolUsuario(String id, RolUsuario rol) throws Exception {
        Usuario usuario = buscarPorId(id);
        usuario.setRolUsuario(rol); //Solo deberia ser accesible por un ADMIN, se debe mostrar por pantalla el rol actual de un usuario y debe estar un boton para cambiar el rol
        usuarioRepositorio.save(usuario);
    }

//otra posibilidad es hacer un cambio de rol directamente
//    @Transactional(rollbackFor = {Exception.class})
//    public void swicthRolUsuario(String id) throws Exception {
//        Usuario usuario = buscarPorId(id);
//                if (usuario.getRolUsuario().equals(RolUsuario.ADMIN)) {
//            usuario.setRolUsuario(RolUsuario.CLIENTE);
//        }
//        if (usuario.getRolUsuario().equals(RolUsuario.CLIENTE)) {
//            usuario.setRolUsuario(RolUsuario.ADMIN);
//        }
    // usuarioRepositorio.save(usuario);
//    }
    @Transactional(rollbackFor = {Exception.class})
    public void bajaUsuario(String id) throws Exception {
        Usuario usuario = buscarPorId(id);
        if (usuario.isActivo()) {
            usuario.setFechaBajaUsuario(new Date());
            usuario.setActivo(false);
            usuarioRepositorio.save(usuario);
        } else {
            throw new Exception("El usuario ya se encuentra dado de baja");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void altaUsuario(String id) throws Exception {
        Usuario usuario = buscarPorId(id);
        if (!usuario.isActivo()) {
            usuario.setFechaAltaUsuario(new Date());
            usuario.setFechaBajaUsuario(null);
            usuario.setActivo(true);
            usuarioRepositorio.save(usuario);
        } else {
            throw new Exception("El usuario ya se encuentra dado de alta");
        }
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRol(String rol){ //se debe recibir un string del enum de rol
        return usuarioRepositorio.buscarPorRol(rol);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNombreApellidoEmail(String query){ //aca recibo un nombre O un apellido O un email y lo busca
        return usuarioRepositorio.buscarPorNombreApellidoEmail(query);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(String id) throws Exception {
        Optional<Usuario> resupuesta = usuarioRepositorio.findById(id);
        if (resupuesta.isPresent()) {
            return resupuesta.get();
        } else {
            throw new Exception("No se encontró el usuario");
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) throws Exception {
        Usuario u = usuarioRepositorio.buscarPorEmail(email);
        if (u != null) {
            return u;
        } else {
            throw new Exception("No se encontró el usuario");
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public void validarDatos(String nombre, String apellido, String direccion, String email, String password, String confirmarPassword) throws Exception {
        if (nombre == null || nombre.isEmpty()) { //agregar validacion que la cadena sean solo letras
            throw new Exception("Error: El nombre del Usuario no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Error: El apellido del Usuario no puede ser nulo");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new Exception("Error: La direccion del Usuario no puede ser nula");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("Error: El email del Usuario no puede ser nulo");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Error: La contraseña del Usuario no puede ser nula");
        }
        if (confirmarPassword == null || confirmarPassword.isEmpty()) {
            throw new Exception("Error: La confirmacion de constraseña del Usuario no puede ser nula");
        }
        if (!password.equals(confirmarPassword)) {
            throw new Exception("Las contraseñas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = null;
        try {
            u = buscarPorEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!u.isActivo()) {
            throw new UsernameNotFoundException("El usuario está dado de baja");
        }
        if (u == null) {
            return null;
        }

        List<GrantedAuthority> permisos = new ArrayList<>();

        GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + u.getRolUsuario().toString());
        permisos.add(p1);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession", u);

        return new User(u.getEmail(), u.getPassword(), permisos);
    }
}
