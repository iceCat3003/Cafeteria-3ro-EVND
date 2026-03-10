/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.math.BigDecimal;

/**
 * Clase para la tabla intermedia Compra_Materia
 * 
 * @author Espinoza Cortés Daniel
 */
public class Compra_Materia {
    private int idCompraMateria;
    private Compra compra;
    private MateriaPrima materia;
    private int cantidad;
    private BigDecimal costoUnitario;

    public Compra_Materia() {}

    public int getIdCompraMateria() {
        return idCompraMateria;
    }

    public void setIdCompraMateria(int idCompraMateria) {
        this.idCompraMateria = idCompraMateria;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public MateriaPrima getMateria() {
        return materia;
    }

    public void setMateria(MateriaPrima materia) {
        this.materia = materia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    @Override
    public String toString() {
        return "Compra_Materia{" + "idCompraMateria=" + idCompraMateria + ", compra=" + compra.toString() + ", materia=" + materia.toString() + ", cantidad=" + cantidad + ", costoUnitario=" + costoUnitario + '}';
    }
    
}
