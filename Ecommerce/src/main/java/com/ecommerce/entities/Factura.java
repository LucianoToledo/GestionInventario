package com.ecommerce.entities;

import com.ecommerce.enums.EstadoFactura;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Factura {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    private double total;
    private int cantidadItem;
    @Enumerated(EnumType.STRING)
    private EstadoFactura estadoFactura;
    @OneToOne
    private Producto producto;
    @OneToOne
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bajaFactura;
    private boolean activo;

    public Factura() {
    }

    public Factura(String id, Date fechaFactura, double total, int cantidadItem, EstadoFactura estadoFactura, Producto producto, Usuario usuario, Date bajaFactura, boolean activo) {
        this.id = id;
        this.fechaFactura = fechaFactura;
        this.total = total;
        this.cantidadItem = cantidadItem;
        this.estadoFactura = estadoFactura;
        this.producto = producto;
        this.usuario = usuario;
        this.bajaFactura = bajaFactura;
        this.activo = activo;
    }

    public Date getBajaFactura() {
        return bajaFactura;
    }

    public void setBajaFactura(Date bajaFactura) {
        this.bajaFactura = bajaFactura;
    }

    public boolean isActivo() {
        return activo;
    }

    

    public boolean isIsActivo() {
        return activo;
    }

    public void setIsActivo(boolean isActivo) {
        this.activo = isActivo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(int cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
