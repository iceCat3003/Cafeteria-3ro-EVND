/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.math.BigDecimal;

/**
 * Clase para la tabla MateriasPrimas
 * 
 * @author Espinoza Cortés Daniel
 */
public class MateriaPrima {
    private int idMateria;
    private String nombre;
    private int cantidad;
    private byte[] foto;
    private BigDecimal costo;
    private String descripcion;

    public MateriaPrima() {}

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MateriaPrima{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", cantidad=" + cantidad + ", foto=" + foto + ", costo=" + costo + ", descripcion=" + descripcion + '}';
    }
    
}
