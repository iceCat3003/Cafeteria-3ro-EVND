/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.enums.EstadoCompraVenta;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Clase para la tabla Compras
 * 
 * @author Espinoza Cortés Daniel
 */
public class Compra {
    int idCompra;
    BigDecimal total;
    EstadoCompraVenta estado;
    LocalDateTime fecha;
    Usuario usuario;

    public Compra() {}

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public EstadoCompraVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompraVenta estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", total=" + total + ", estado=" + estado + ", fecha=" + fecha + ", usuario=" + usuario.toString() + '}';
    }
    
}
