/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.math.BigDecimal;

/**
 * Clase para la tabla intermedia Venta_Producto
 * 
 * @author Espinoza Cortés Daniel
 */
public class Venta_Producto {
    private int idVentaProducto;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;

    public Venta_Producto() {}

    public int getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(int idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Venta_Producto{" + "idVentaProducto=" + idVentaProducto + ", venta=" + venta.toString() + ", producto=" + producto.toString() + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + '}';
    }
    
}
