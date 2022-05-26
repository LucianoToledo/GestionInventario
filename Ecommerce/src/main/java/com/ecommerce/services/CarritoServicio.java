//package com.ecommerce.services;
//
//import com.ecommerce.entities.Carrito;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Service
//public class CarritoServicio {
//
//    @Autowired
//    ProductoServicio productoServicio;
//
//    private final List<Carrito> carritoCompra = new ArrayList<>();
//
//    public void agregarProducto(String cantidadProducto, String nombreProducto) { //recibo estos datos del controlador
//
//        Carrito cc = new Carrito();
//        cc.setCantidadProducto(cantidadProducto);
//        cc.setNombreProducto(nombreProducto);
//
//        carritoCompra.add(cc);
//
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true);
//
//        session.setAttribute("itemsCarrito", carritoCompra);
//    }
//
//    public void limpiarCarrito(List<Carrito> carrito) {
//        carrito.clear();
//    }
//
//    public void realizarCompra(List<Carrito> carrito) throws Exception {
//        for (Carrito item : carrito) {
//            productoServicio.venderProducto(productoServicio.buscarPorNombre(item.getNombreProducto()).getId(), Integer.parseInt(item.getCantidadProducto()));
//        }
//    }
//
//}
