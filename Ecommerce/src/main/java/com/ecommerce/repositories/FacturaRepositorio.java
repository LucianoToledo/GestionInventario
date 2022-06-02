
package com.ecommerce.repositories;

import com.ecommerce.entities.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, String>{
    
    
    @Query("SELECT f FROM Factura f WHERE f.usuario.id = :id ")
    public List<Factura> buscarCompras(@Param("id") String id);
}
