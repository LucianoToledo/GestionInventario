package com.ecommerce.entities;

import com.ecommerce.enums.TipoProducto;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    

    public Producto() {
    }

    public Producto(String id, String descripcion, int stock, float precioVenta, TipoProducto tipoProducto) {
        this.id = id;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.tipoProducto = tipoProducto;
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

}
