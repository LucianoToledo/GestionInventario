package com.ecommerce.services;

import com.ecommerce.Errores.ErrorServicio;
import com.ecommerce.entities.Producto;
import com.ecommerce.enums.TipoProducto;
import com.ecommerce.repositories.ProductoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Transactional(rollbackFor = Exception.class)
    public void crearProducto(String descripcion, int stock, float precioVenta, TipoProducto tipoProducto) throws Exception {//Se borran fecha alta, baja y activo

        
        validarProducto(descripcion, stock, precioVenta, tipoProducto);
        
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

    public void validarProducto(String descripcion, Integer stock, Float precioVenta, TipoProducto tipoProducto) throws Exception {

        if (descripcion == null || descripcion.isEmpty()) {
            throw new Exception("Debe ingresar una descripción del producto.");
        }

        if (stock == null) {
            throw new Exception("Debe ingresar el stock.");
        }

        if (precioVenta == null) {
            throw new Exception("Debe ingresar el precio para la venta.");
        }

        if (tipoProducto == null) {
            throw new Exception("Debe ingresar el tipo del producto.");
        }
    }

    @Transactional(readOnly = true)//Falta controlador
    public Producto buscarPorId(String id) throws Exception {

        Optional<Producto> respuesta = productoRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Producto producto = respuesta.get();
            return producto;
        } else {
            throw new Exception("No existe ese producto");
        }
    }
    
    
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String query){ 
        return  productoRepositorio.buscarPorNombre(query);
    }

    @Transactional(rollbackFor = Exception.class)
    public void fechaAltaProducto(String id) throws Exception {
        Producto producto = buscarPorId(id);

        if (!producto.isActivo()) {
            producto.setFechaAlta(new Date());
            producto.setFechaBaja(null);
            producto.setActivo(true);
            productoRepositorio.save(producto);
        } else {
            throw new Exception("El producto ya se dió de alta.");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void fechaBajaProducto(String id) throws Exception {
        Producto producto = buscarPorId(id);

        if (producto.isActivo()) {
            producto.setFechaBaja(new Date());
            producto.setActivo(false);
            producto.setFechaAlta(null);
            productoRepositorio.save(producto);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void actualizarProducto(String id, String descripcion, Integer stock, Integer precioVenta, TipoProducto tipoProducto) throws Exception {
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
    public void eliminarProducto(String id) throws Exception {
        Producto producto = buscarPorId(id);

        if (producto.isActivo()) {
            productoRepositorio.deleteById(id);
        } else {
            throw new Exception("No se encontró el producto que desea eliminar");
        }
    }

    @Transactional(rollbackFor = Exception.class)      
    public void actualizarStockProducto(String id, int stock) throws Exception {
        
        if (stock < 1) {
            throw new ErrorServicio("Error: El stock ingresado es menor o igual a 0");
        }
        Producto producto = buscarPorId(id);
        
        if (producto.isActivo()) {
            producto.setStock(producto.getStock()+stock);
            productoRepositorio.save(producto);
        }else{
            throw new ErrorServicio("Error: No se puede actualizar el stock, el producto "+producto.getNombre()+" se encuentra dado de baja");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void venderProducto(String id, int stock) throws Exception {
        
        if (stock < 1) {
            throw new ErrorServicio("Error: El stock ingresado es menor o igual a 0");
        }
        
        Producto producto = buscarPorId(id);
        
        if (producto.getStock() < stock){
            throw new ErrorServicio("Error: No hay stock suficiente, cantidad de existencias - "+producto.getStock());
        }
        
        if (producto.isActivo()) {
            producto.setStock(producto.getStock()-stock);
            productoRepositorio.save(producto);
        }else{
            throw new ErrorServicio("Error: No se puede actualizar el stock, el producto "+producto.getNombre()+" se encuentra dado de baja");
        }
    }
    
    
    @Transactional(readOnly = true)
    public List<Producto> listar(){
      return productoRepositorio.findAll();
    }
}
