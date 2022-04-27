package com.ecommerce.services;

import com.ecommerce.entities.Producto;
import com.ecommerce.enums.TipoProducto;
import com.ecommerce.repositories.ProductoRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicio {
 
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Transactional(rollbackFor = Exception.class)
    public void crearProducto(String descripcion, int stock, float precioVenta, TipoProducto tipoProducto, Date fechaAlta, Date fechaBaja, boolean activo){
        
        Producto producto = new Producto();
        
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setPrecioVenta(precioVenta);
        producto.setTipoProducto(tipoProducto);
        producto.setActivo(true);
        producto.setFechaAlta(new Date());
        producto.setFechaBaja(null);        
        
        productoRepositorio.save(producto);
    }
    
    public void validarProducto(String descripcion, Integer stock, Integer precioVenta, TipoProducto tipoProducto) throws Exception{
        
        if (descripcion == null || descripcion.isEmpty()){
            throw new Exception("Debe ingresar una descripción del producto.");
        }
        
        if(stock == null){
            throw new Exception("Debe ingresar el stock.");            
        }
        
        if(precioVenta == null){
            throw new Exception("Debe ingresar el precio para la venta.");            
        }
        
        if(tipoProducto == null){
            throw new Exception("Debe ingresar el tipo del producto.");            
        }
    }
    
    @Transactional
    public Producto buscarPorId(String id) throws Exception{
    
        Optional<Producto> respuesta = productoRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Producto producto = respuesta.get();
            return producto;
        }else{
            throw new Exception("No existe ese producto");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void fechaAltaProducto(String id)throws Exception{
        Producto producto = buscarPorId(id);
        
        if(!producto.isActivo()){
            producto.setFechaAlta(new Date());
            producto.setFechaBaja(null);
            producto.setActivo(true);
            productoRepositorio.save(producto);
        }else{
            throw new Exception("El producto ya se dió de alta.");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void fechaBajaProducto(String id) throws Exception{
        Producto producto = buscarPorId(id);
        
        if(producto.isActivo()){
            producto.setFechaBaja(new Date());
            producto.setActivo(false);
            producto.setFechaAlta(null);
            productoRepositorio.save(producto);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void actualizarProducto(String id, String descripcion, Integer stock, Integer precioVenta, TipoProducto tipoProducto) throws Exception{
        Producto producto = buscarPorId(id);
        
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setPrecioVenta(precioVenta);
        producto.setTipoProducto(tipoProducto);
        producto.setActivo(true);
        producto.setFechaAlta(new Date());
        
        productoRepositorio.save(producto);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminarProducto(String id)throws Exception{
        Producto producto = buscarPorId(id);
        
        if(producto.isActivo()){
        productoRepositorio.deleteById(id);
    }else{
        throw new Exception("No se encontró el producto que desea eliminar");
    }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void actualizarStockProducto(String id, int stock)throws Exception{
        Producto producto = buscarPorId(id);
        
        if(producto.isActivo()){
            producto.getStock();
        }
}
}