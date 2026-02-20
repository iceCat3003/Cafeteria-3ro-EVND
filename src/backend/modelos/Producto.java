/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.enums.EsDisponibleProducto;
import backend.enums.TemperaturaProducto;
import java.math.BigDecimal;

/**
 *
 * @author icecat
 */
public class Producto {
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private String tamPorcion;
    private BigDecimal precio;
    private EsDisponibleProducto esDisponible;
    private TemperaturaProducto temperatura;
    
    private ProductoCategoria productoCategoria;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getTamPorcion() {
        return tamPorcion;
    }

    public void setTamPorcion(String tamPorcion) {
        this.tamPorcion = tamPorcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public EsDisponibleProducto getEsDisponible() {
        return esDisponible;
    }

    public void setEsDisponible(EsDisponibleProducto esDisponible) {
        this.esDisponible = esDisponible;
    }

    public TemperaturaProducto getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(TemperaturaProducto temperatura) {
        this.temperatura = temperatura;
    }
    
    public ProductoCategoria getProductoCategoria() {
        return productoCategoria;
    }
    
    public void setProductoCategoria() {
        this.productoCategoria = productoCategoria;
        if(productoCategoria!=null){
            this.idCategoria=productoCategoria.getIdCategoria();
        }
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", idCategoria=" + idCategoria + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tamPorcion=" + tamPorcion + ", precio=" + precio + ", esDisponible=" + esDisponible + ", temperatura=" + temperatura + '}';
    }
    
}
