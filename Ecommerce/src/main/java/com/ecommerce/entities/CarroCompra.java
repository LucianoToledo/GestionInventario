package com.ecommerce.entities;

public class CarroCompra {
    private String idProducto;
    private String idCliente;

    public CarroCompra() {
    }

    public CarroCompra(String idProducto, String idCliente) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }    
}
