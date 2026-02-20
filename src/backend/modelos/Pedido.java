/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.enums.EstatusPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author icecat
 */
public class Pedido {
    private int idPedido;
    private int idEmpleado;
    private EstatusPedido estatusPedido;
    private LocalDateTime horaRealizada;
    private LocalDateTime horaEntregada;
    private ArrayList<PedidoDetalle> pedidoDetalle;

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EstatusPedido getEstatusPedido() {
        return estatusPedido;
    }

    public void setEstatusPedido(EstatusPedido estatusPedido) {
        this.estatusPedido = estatusPedido;
    }

    public LocalDateTime getHoraRealizada() {
        return horaRealizada;
    }

    public void setHoraRealizada(LocalDateTime horaRealizada) {
        this.horaRealizada = horaRealizada;
    }

    public LocalDateTime getHoraEntregada() {
        return horaEntregada;
    }

    public void setHoraEntregada(LocalDateTime horaEntregada) {
        this.horaEntregada = horaEntregada;
    }

    public ArrayList<PedidoDetalle> getPedidoDetalle() {
        return pedidoDetalle;
    }

    public void setPedidoDetalle(ArrayList<PedidoDetalle> pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido +
                ", idEmpleado=" + idEmpleado +
                ", estatusPedido=" + estatusPedido +
                ", horaRealizada=" + horaRealizada +
                ", horaEntregada=" + horaEntregada +
                ", pedidoDetalle=" + pedidoDetalle + '}';
    }
    
}
