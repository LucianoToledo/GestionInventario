package com.ecommerce.controller;

import com.ecommerce.entities.Producto;
import com.ecommerce.entities.Usuario;
import com.ecommerce.services.ProductoServicio;
import com.ecommerce.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/imagen")
public class ImagenController {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/producto")
    public ResponseEntity<byte[]> imagenProducto(@RequestParam String id){
        try {
            Producto producto =productoServicio.buscarPorId(id);
            
            if (producto.getImagen() == null) {
                throw new Exception("No se encontro la foto");
            }
            
            byte[] foto = producto.getImagen().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> imagenUsuario(@RequestParam String idUsuario){
        try {
            Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
            if (usuario.getImagen() == null) {
                throw new Exception("Imagen no encontrada");
            }
            byte[] imagen = usuario.getImagen().getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
