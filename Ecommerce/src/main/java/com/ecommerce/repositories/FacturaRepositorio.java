
package com.ecommerce.repositories;

import com.ecommerce.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacturaRepositorio extends JpaRepository<Factura, String>{
    
}
