package com.ecommerce.controller;

import com.ecommerce.Errores.ErrorServicio;
import com.ecommerce.entities.Factura;
import com.ecommerce.entities.Producto;
import com.ecommerce.entities.Usuario;
import com.ecommerce.enums.EstadoFactura;
import com.ecommerce.services.FacturaServicio;
import com.ecommerce.services.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/factura")
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/crear")
    public String factura(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("usuarios", usuarios);
        return "factura.html";
    }


    @GetMapping("/alta/{idFactura}")
    public String alta(RedirectAttributes attr, @PathVariable String idFactura) throws ErrorServicio {
        try {
            facturaServicio.alta(idFactura);
            attr.addFlashAttribute("alta", "La factura ha sido dada de alta");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect: factura-alta.html";

    }

    @GetMapping("/baja")
    public String baja(RedirectAttributes attr, @RequestParam String idFactura) throws ErrorServicio {
        try {
            facturaServicio.baja(idFactura);
            attr.addFlashAttribute("baja", "La factura ha sido dada de baja");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect: factura-baja.html";

    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Factura> facturas = facturaServicio.listar();
        modelo.put("lista", facturas);
        return "lista-factura.html";
    }

}
