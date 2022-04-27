package com.ecommerce.entities;

import com.ecommerce.enums.TipoProducto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
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

    public Producto() {
    }

    public Producto(String id, String descripcion, int stock, float precioVenta, TipoProducto tipoProducto, boolean activo, Date fechaAlta, Date fechaBaja) {
        this.id = id;
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

    public Date getfechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
