package com.ecommerce.repositories;

import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.RolUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("Select u FROM Usuario u WHERE u.email LIKE :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    @Query("Select u FROM Usuario u WHERE u.rolUsuario LIKE :rol")
    public List<Usuario> buscarPorRol(@Param("rol") RolUsuario rol);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:query% OR u.apellido LIKE %:query% OR u.email LIKE %:query%")
    public List<Usuario> buscarPorNombreApellidoEmail(@Param("query") String query);
}
