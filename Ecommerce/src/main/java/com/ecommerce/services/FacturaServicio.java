package com.ecommerce.services;

import com.ecommerce.Errores.ErrorServicio;
import com.ecommerce.entities.Factura;
import com.ecommerce.entities.Producto;
import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.EstadoFactura;
import com.ecommerce.repositories.FacturaRepositorio;
import java.util.ArrayList;
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
    @Autowired
    private ProductoServicio productoServicio;

    @Transactional
    public void crear(String idUsuario, String idProducto, int cantidad) throws Exception {
        try {
            Factura factura = new Factura();

            Producto producto = productoServicio.buscarPorId(idProducto);
            Usuario usuario = usuarioServicio.buscarPorId(idUsuario);

            if (producto == null) {
                throw new Exception("No existe el producto");
            }

            if (usuario == null) {
                throw new Exception("No existe el usuario");
            }

//            List<Producto> productos = new ArrayList<>();
//            productos.add(producto);

            factura.setFechaFactura(new Date());
            factura.setCantidadItem(cantidad);
            factura.setTotal(producto.getPrecioVenta() * cantidad);
            factura.setEstadoFactura(EstadoFactura.APROBADA);
            factura.setProducto(producto);
            factura.setUsuario(usuario);
            factura.setActivo(true);

            facturaRepositorio.save(factura);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
      
    }
    
    @Transactional
    public List<Factura> buscarFactura(String id){
        if (id == null) {
            throw new RuntimeException("No se encontro el id");
        }
        return facturaRepositorio.buscarCompras(id);
    } 
    
    @Transactional
    public int cantidadProducto(String id){
        if (id == null) {
            throw new RuntimeException("No se encontro el id");
        }
        return facturaRepositorio.buscarCompras(id).size();
    }
//    @Transactional(rollbackFor = {Exception.class})
//    public void modificar(String idFactura, String idUsuario, List<Producto> productos, EstadoFactura estadoFactura)
//            throws ErrorServicio {
//        validar(idUsuario, productos, estadoFactura);
//        Optional<Factura> respuesta = facturaRepositorio.findById(idFactura);
//        if (respuesta.isPresent()) {
//            Factura factura = respuesta.get();
//            if (factura.getUsuario().getId().equals(idUsuario)) {
//              //  factura.setProducto(elimiProducto(productos, producto));
//                factura.setCantidadItem(productos.size());
//                factura.setEstadoFactura(estadoFactura);
//                factura.setTotal(sumarTotal(productos));
//                facturaRepositorio.save(factura);
//            } else {
//                throw new ErrorServicio("La factura corresponde a otro usuario");
//            }
//        } else {
//            throw new ErrorServicio("No existe una factura con el identificador solicitado");
//        }
//    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void alta(String idFactura) throws ErrorServicio {
        Factura factura = buscarPorId(idFactura);
        if (!factura.isActivo()) {
            factura.setActivo(true);
            factura.setFechaFactura(new Date());
            factura.setBajaFactura(new Date());
            facturaRepositorio.save(factura);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void baja(String idFactura) throws ErrorServicio {
        Factura factura = buscarPorId(idFactura);
        if (factura.isActivo()) {
            factura.setActivo(false);
            factura.setBajaFactura(new Date());
            facturaRepositorio.save(factura);
        }
    }

    @Transactional(readOnly = true)
    public List<Factura> listar() {
        return facturaRepositorio.findAll();
    }

    private double sumarTotal(List<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioVenta();
        }
        return total;
    }

    private void validar(String idUsuario, List<Producto> productos, EstadoFactura estadoFactura) throws ErrorServicio {
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new ErrorServicio("Debe indicar el ID de el usuario");
        }
        if (productos == null || productos.isEmpty()) {
            throw new ErrorServicio("Debe ingrear al menos un prodcuto");
        }
        if (estadoFactura == null) {
            throw new ErrorServicio("Debe indicar el estado de la factura");
        }
    }

    @Transactional(readOnly = true)
    public Factura buscarPorId(String id) throws ErrorServicio {
        Optional<Factura> resupuesta = facturaRepositorio.findById(id);
        if (resupuesta.isPresent()) {
            return resupuesta.get();
        } else {
            throw new ErrorServicio("No se encontró la factura");
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
