
package com.ecommerce.services;

import com.ecommerce.repositories.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServicio {
    
    @Autowired
    private FacturaRepositorio facturaRepositorio;
    
    
    
}
