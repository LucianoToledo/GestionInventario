package com.ecommerce.services;

import com.ecommerce.entities.CarroCompra;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class CarroComprasServicio {

    @Autowired
    ProductoServicio productoServicio;

    private final List<CarroCompra> carroCompra = new ArrayList<>();

    public void agregarProducto(String idProducto, String idCliente) {
        CarroCompra producto = new CarroCompra(idProducto, idCliente);

        carroCompra.add(producto);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        session.setAttribute("ListadoProductos", carroCompra);
    }

    public void limpiarCarrito(List<CarroCompra> carroCompras) {
        carroCompras.clear();
    }

    public void eliminarProductoCarro(List<CarroCompra> carroCompras, String idProducto) {
        Iterator<CarroCompra> it = carroCompras.iterator();
        while (it.hasNext()) {
            CarroCompra next = it.next();
            if (next.getIdProducto().equals(idProducto)) {
                it.remove();
            }

        }
    }
//    public boolean eliminarPais(String opc) {
//        boolean band = false;
//        Iterator<Pais> it = paises.iterator();
//        while (it.hasNext()) {
//            Pais next = it.next();
//            if (next.getNombre().equalsIgnoreCase(opc)) {
//                it.remove();
//                band = true;
//            }
//        }
//        return band;
//    }

}
