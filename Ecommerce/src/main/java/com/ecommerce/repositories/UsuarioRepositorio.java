package com.ecommerce.repositories;

import com.ecommerce.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("Select u FROM Usuario u WHERE u.email LIKE :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    @Query("Select u FROM Usuario u WHERE u.rolUsuaro LIKE :rol")
    public List<Usuario> buscarPorRol(@Param("rol") String rol);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %query% OR u.apellido %query% OR u.email %quert%")
    public List<Usuario> buscarPorNombreApellidoEmail(@Param("query") String query);
}
