package com.ecommerce.services;

import com.ecommerce.Errores.ErrorServicio;
import com.ecommerce.entities.Factura;
import com.ecommerce.entities.Producto;
import com.ecommerce.enums.EstadoFactura;
import com.ecommerce.repositories.FacturaRepositorio;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Transactional(rollbackFor = {Exception.class})
    public Factura alta(String idUsuario, List<Producto> productos, EstadoFactura estadoFactura) throws ErrorServicio, Exception {
        
        validar(idUsuario, productos, estadoFactura);
        Factura factura = new Factura();
        factura.setUsuario(usuarioServicio.buscarPorId(idUsuario));
        factura.setCantidadItem(productos.size());
        factura.setProducto(productos);
        factura.setTotal(sumarTotal(productos));       // hacer logica para sumar las facturas 
        factura.setEstadoFactura(estadoFactura);
        factura.setFechaFactura(new Date());
        factura.setActivo(true);
        factura.setBajaFactura(null);
        return facturaRepositorio.save(factura);
    }
       
    @Transactional(rollbackFor = {Exception.class})
    public void modificar(String idFactura, String idUsuario, List<Producto> productos, EstadoFactura estadoFactura,Producto producto)
            throws ErrorServicio {
        validar(idUsuario, productos, estadoFactura);
        Optional<Factura> respuesta = facturaRepositorio.findById(idFactura);
        if (respuesta.isPresent()) {
            Factura factura = respuesta.get();
            if (factura.getUsuario().getId().equals(idUsuario)) {
              //  factura.setProducto(elimiProducto(productos, producto));
                factura.setCantidadItem(productos.size());
                factura.setEstadoFactura(estadoFactura);
                factura.setTotal(sumarTotal(productos));
                facturaRepositorio.save(factura);
            } else {
                throw new ErrorServicio("La factura corresponde a otro usuario");
            }
        } else {
            throw new ErrorServicio("No existe una factura con el identificador solicitado");
        }
    }
        
    @Transactional(rollbackFor = {Exception.class})
    public void baja(String idFactura, String idUsuario) throws ErrorServicio {
        Optional<Factura> respuesta = facturaRepositorio.findById(idFactura);
        if (respuesta.isPresent()) {
            Factura factura = respuesta.get();
            if (factura.getUsuario().getId().equals(idUsuario)) {
                factura.setActivo(false);
                factura.setBajaFactura(new Date());
                facturaRepositorio.save(factura);
            } else {
                throw new ErrorServicio("La factura corresponde a otro usuario");
            }
        } else {
            throw new ErrorServicio("No existe una factura con el identificador solicitado");
        }
    }
   
    @Transactional(readOnly = true)
   public List<Factura> listar(){
       List facturas = facturaRepositorio.findAll();
       return facturas;
   }
   
   private double sumarTotal(List<Producto> productos){
       double total = 0;
       for (Producto producto : productos) {
           total += producto.getPrecioVenta();
       }
        return total;
   }
    
    private void validar(String idUsuario, List<Producto> productos, EstadoFactura estadoFactura)throws ErrorServicio{
        if (idUsuario == null || idUsuario.isEmpty()){
            throw new ErrorServicio("Debe indicar el ID de el usuario");
        }
        
        if (productos == null || productos.isEmpty()){
            throw new ErrorServicio("Debe ingrear al menos un prodcuto");
        }
        if (estadoFactura == null){
            throw new ErrorServicio("Debe indicar el estado de la factura");
        }
    }
    
//    private List<Producto> elimiProducto(List<Producto> productos, Producto producto) throws ErrorServicio{
//        Iterator<Producto> iterador = productos.iterator();
//        while (iterador.hasNext())
//            if (productos.contains(producto)){
//                iterador.remove();
//            }else {
//                throw new ErrorServicio("No se encuentra el producto que desea eliminar");
//            }
//        return productos;
//    
//}
//    
//    private List<Producto> agregarProducto(List<Producto> productos, Producto producto){
//        productos.add(producto);
//        return productos;
//        
//    }
    
    
   
   }     



    
    


