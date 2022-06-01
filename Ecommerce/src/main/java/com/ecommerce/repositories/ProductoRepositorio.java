package com.ecommerce.repositories;

import com.ecommerce.entities.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    @Query("Select p FROM Producto p WHERE p.nombre LIKE :nombre")
    public List<Producto> buscarListaPorNombre(@Param("nombre") String nombre);
    
    @Query("Select p FROM Producto p WHERE p.nombre LIKE :nombre")
    public Producto buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:query%")
    public List<Producto> buscarPor(@Param("query") String query);
    
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:query%")
    public Page<Producto> buscarPor(@Param("query") String query, Pageable pageable);
    
}
