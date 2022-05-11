
package com.ecommerce.util;

import com.ecommerce.entities.Producto;
import com.lowagie.text.pdf.PdfPTable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;



@Component("producto/lista")
public class ListarProductosPDF extends AbstractPdfView{


    @Override
    protected void buildPdfDocument(Map<String, Object> model, com.lowagie.text.Document document,
            com.lowagie.text.pdf.PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
         List<Producto> productos = (List<Producto>) model.get("productos");
         
         PdfPTable tablaProductos = new PdfPTable(3);
         
         productos.forEach(producto ->{
             tablaProductos.addCell(producto.getNombre());
             tablaProductos.addCell(producto.getDescripcion());
             tablaProductos.addCell(Float.toString(producto.getPrecioVenta()));
             //tablaProductos.addCell(imagenServicio.crearImagen(producto.getImagen()));
             
         });
         
         document.add(tablaProductos);
    }

   

  
    
}
