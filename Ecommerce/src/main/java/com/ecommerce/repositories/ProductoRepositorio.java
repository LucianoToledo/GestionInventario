package com.ecommerce.repositories;

import com.ecommerce.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    @Query("Select p FROM Producto p WHERE p.nombre LIKE :nombre")
    public List<Producto> buscarPorNombre(@Param("nombre") String nombre);
}
