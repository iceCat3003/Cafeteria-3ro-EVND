/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.enums.EstatusPedido;
import backend.modelos.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author icecat
 */
public class PedidoDAO implements InterfazDAO<Pedido> {

    @Override
    public ArrayList<Pedido> obtenerRegistros() {
        ArrayList<Pedido> tabla = new ArrayList<>();
        String sql="SELECT * FROM Pedidos";
        ConexionDB conexion = new ConexionDB();
        try(
                Connection conn= conexion.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ResultSet rs = prep.executeQuery())
        {
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdEmpleado(rs.getInt("idEmpleado"));
                pedido.setEstatusPedido(EstatusPedido.valueOf(rs.getString("estatusPedido")));
                pedido.setHoraRealizada(rs.getObject("horaRealizada", LocalDateTime.class));
                pedido.setHoraEntregada(rs.getObject("horaEntregada", LocalDateTime.class));
            }
        } catch (SQLException e){
            System.err.println("Fallo al obtener registros: "+e);
        }
        return tabla;
    }

    @Override
    public Pedido obtenerPorID(int id) {
        Pedido pedido = null;
        String sql="SELECT * FROM Pedidos WHERE idPedido=?";
        ConexionDB conexion = new ConexionDB();
        try(
                Connection conn= conexion.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                )
        {
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdEmpleado(rs.getInt("idEmpleado"));
                pedido.setEstatusPedido(EstatusPedido.valueOf(rs.getString("estatusPedido")));
                pedido.setHoraRealizada(rs.getObject("horaRealizada", LocalDateTime.class));
                pedido.setHoraEntregada(rs.getObject("horaEntregada", LocalDateTime.class));
            }
        } catch (SQLException e){
            System.err.println("Fallo al obtener registros: "+e);
        }
        return pedido;
    }

    @Override
    public boolean insertar(Pedido pedido) {
        ConexionDB con = new ConexionDB();
        String sql = "INSERT INTO Pedidos VALUES "
                + "(0,?,?,?,?)";
        try (
                Connection conn = con.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ){
            prep.setInt(1, pedido.getIdEmpleado());
            prep.setString(2, pedido.getEstatusPedido().toString());
            prep.setObject(3, pedido.getHoraRealizada());
            prep.setObject(4, pedido.getHoraEntregada());
            return prep.executeUpdate()>0;
        } catch (SQLException e){
            System.err.println("Fallo al insertar pedido: "+e);
        }
        return false;
    }

    @Override
    public boolean actualizar(Pedido pedido) {
        ConexionDB con = new ConexionDB();
        String sql = "UPDATE Pedidos SET "
                + "idEmpleado=?,"
                + "estatusPedido=?,"
                + "horaRealizada=?,"
                + "horaEntregada=? "
                + "WHERE idPedido=?";
        try(
                Connection conn = con.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ){
            prep.setInt(1, pedido.getIdEmpleado());
            prep.setString(2, pedido.getEstatusPedido().toString());
            prep.setObject(3, pedido.getHoraRealizada());
            prep.setObject(4, pedido.getHoraEntregada());
            prep.setInt(5, pedido.getIdPedido());
            return prep.executeUpdate()>0;
        } catch (SQLException e){
            System.err.println("Error al actualizar: "+e);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        ConexionDB con = new ConexionDB();
        String sql = "DELETE Pedidos WHERE idPedido=?";
        try(
                Connection conn = con.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ){
            prep.setInt(1, id);
            return prep.executeUpdate()>0;
        } catch (SQLException e){
            System.err.println("Error al borrar: "+e);
        }
        return false;
    }
    
}
