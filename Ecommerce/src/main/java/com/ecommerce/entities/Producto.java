package com.ecommerce.entities;

import com.ecommerce.enums.TipoProducto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String descripcion;
    private int stock;
    private float precioVenta;
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;
    private boolean activo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @OneToOne
    private Imagen imagen;

    public Producto() {
    }

    public Producto(String id, String nombre, String descripcion, int stock, float precioVenta, TipoProducto tipoProducto, boolean activo, Date fechaAlta, Date fechaBaja) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.tipoProducto = tipoProducto;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock + ", precioVenta=" + precioVenta + ", tipoProducto=" + tipoProducto + ", activo=" + activo + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", imagen=" + imagen + '}';
    }
    
    

}
