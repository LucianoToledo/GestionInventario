package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.entities.Usuario;
import com.ecommerce.repositories.ProductoRepositorio;
import com.ecommerce.services.FacturaServicio;
import com.ecommerce.services.ProductoServicio;
import com.ecommerce.services.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private FacturaServicio facturaServicio;
<<<<<<< HEAD
    

=======
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    
>>>>>>> 24f901b251b95bb0bfa678cc3b73abd5fa3c70f5
    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Producto> productos = productoServicio.listar();
        modelo.put("productos", productos);
        return "producto/lista";
    }

    @GetMapping("/crear")
    public String crear() {
        return "agregar.html";
    }

    @PostMapping("/crear")
    public String crear(@RequestParam String nombre, @RequestParam String stock,
            @RequestParam String precioVenta, @RequestParam String tipoProducto, @RequestParam String descripcion,
            @RequestParam MultipartFile archivo, ModelMap modelo, HttpSession session) {

        try {
            productoServicio.crearProducto(nombre, stock, precioVenta, descripcion, tipoProducto, archivo);
            modelo.put("exito", "El producto '" + nombre + "'se cargó exisotasemnte");

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            System.out.println(e.getMessage());
        }
        session.setAttribute("productocreado", "El producto se creo correctamente");
        return "redirect:/producto/lista";
    }

    @GetMapping("/detalles/{id}")
    public String detallesProducto(@PathVariable String id, ModelMap modelo) throws Exception {
        modelo.put("producto", productoServicio.buscarPorId(id));
        return "producto/detalles";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable String id, ModelMap modelo) throws Exception {
        modelo.put("producto", productoServicio.buscarPorId(id));
        return "producto/editar";
    }

    @PostMapping("/editar")
    public String actualizar(@RequestParam String id, @RequestParam String nombre, @RequestParam String stock,
            @RequestParam String precioVenta, @RequestParam String tipoProducto, RedirectAttributes attr) {

        try {
            productoServicio.actualizarProducto(id, nombre, Integer.parseInt(stock),
                    Float.parseFloat(precioVenta), tipoProducto);
            attr.addFlashAttribute("exito", "El Producto ha sido actualizado!!");
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            attr.addFlashAttribute("error", "No se ha actualizado el Producto");
        }
        return "redirect:/producto/lista";
    }

    @GetMapping("check/{id}")
    public String checkEstado(@PathVariable String id) throws Exception {
        productoServicio.checkEstado(id);
        return "redirect:/producto/lista";
    }

    //codigo de avelHttpSession session
    @PostMapping("/comprar")
    public String comprarProucto(ModelMap modelo, @RequestParam String idUsuario, @RequestParam String idProducto, @RequestParam String cantidad) {
        try {
            productoServicio.comprar(idProducto, Integer.parseInt(cantidad));
            facturaServicio.crear(idUsuario, idProducto, Integer.parseInt(cantidad));
        } catch (Exception ex) {
            modelo.put("error", "debe loguearse primero");
            Logger.getLogger(ProductoControlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/buscarPorNombre")
    public String buscarPorNombre(ModelMap modelo, @RequestParam String query) {
        try {
            if (query == null) {
                modelo.put("error", "No se encontró Producto con ese nombre");
            }
            modelo.put("p", productoServicio.buscarPorQuery(query));
           
        } catch (Exception e) {
            modelo.put("error", "No se encontró Producto con ese nombre");
             System.out.println("----> "+productoServicio.buscarPorQuery(query));
        }
        return "shop.html";
    }

    @GetMapping("/fechaAlta/{id}")
    public String fechaAlta(RedirectAttributes attr, @PathVariable String id) {

        try {
            productoServicio.fechaAltaProducto(id);
            attr.addFlashAttribute("exito", "Se ha cambiado el estado del Producto!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:";
    }

    @GetMapping("/fechaBaja/{id}")
    public String fechaBaja(RedirectAttributes attr, @PathVariable String id) {

        try {
            productoServicio.fechaBajaProducto(id);
            attr.addFlashAttribute("exito", "Se ha cambiado el estado del Producto!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(RedirectAttributes attr, @PathVariable String id) {

        try {
            productoServicio.eliminarProducto(id);
            attr.addFlashAttribute("exito", "El Producto se ha eliminado exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "El Producto no se ha eliminado!!");
        }

        return "redirect;";
    }

    //Ver si se hace con Modal o vista nueva
    @GetMapping("/actualizarStock/{id}")
    public String actualizarStock(RedirectAttributes attr, @PathVariable String id, @RequestParam int stock) {

        try {
            productoServicio.actualizarStockProducto(id, stock);
            attr.addFlashAttribute("exito", "El stock del Producto se actualizó exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:";
    }

    //Ver si se hace con Modal o vista nueva
    @GetMapping("/venderProducto")
    public String venderProducto(RedirectAttributes attr, @PathVariable String id, @RequestParam int stock) {

        try {
            productoServicio.venderProducto(id, stock);
            attr.addFlashAttribute("exito", "El stock del Producto se actualizó exitosamente!!");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:";
    }

    @GetMapping("/shop_1")
    public String findAll(ModelMap model, @PageableDefault(size = 2) Pageable pageable) {

        Page<Producto> productos = productoServicio.getAll(pageable);

        System.out.println(productos.getNumberOfElements());
        System.out.println(productos.getContent());

        model.put("page", productos);

        return "shop_1.html";
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortField") String sortDir, Model model){
//        int pageSize = 5;
//        
//        Page<Producto> page = productoServicio.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Producto> productos = page.getContent();
//        
//        model.addAttribute("currentPage",pageNo);
//        model.addAttribute("totalPages",page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        
//        model.addAttribute("sortField",sortField);
//        model.addAttribute("sortDir",sortDir);
//        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
//                
//        model.addAttribute("productos",productos);
//
//        return "index";
//    }
//    @GetMapping("/shop/")
//    public String showPage(Model model,@RequestParam(defaultValue = "0") int page){
//        model.addAttribute("productos",
//              productoRepositorio.findAll(new PageRequest(page, 6)));
//        return "shop.html";
//    }
}
