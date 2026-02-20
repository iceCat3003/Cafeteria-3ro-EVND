/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.enums.EstatusEmpleado;
import backend.enums.PuestoEmpleado;
import backend.modelos.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author icecat
 */
public class EmpleadoDAO implements InterfazDAO<Empleado> {

    @Override
    public ArrayList<Empleado> obtenerRegistros() {
        ArrayList<Empleado> tabla = new ArrayList<>();
        String sql="SELECT * FROM Empleados";
        ConexionDB conexion = new ConexionDB();
        try(
                Connection conn= conexion.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ResultSet result = prep.executeQuery())
        {
            while(result.next()){
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(result.getInt("idEmpleado"));
                empleado.setUsuario(result.getString("usuario"));
                empleado.setNombre1(result.getString("nombre1"));
                empleado.setNombre2(result.getString("nombre2"));
                empleado.setApellido1(result.getString("apellido1"));
                empleado.setApellido2(result.getString("apellido2"));
                empleado.setContrasenia(result.getString("contrasenia"));
                empleado.setTelefono(result.getString("telefono"));
                empleado.setEstatus(EstatusEmpleado.valueOf(result.getString("estatus")));
                empleado.setPuesto(PuestoEmpleado.valueOf(result.getString("puesto")));
                empleado.setFechaIngreso(result.getObject("fechaIngreso", LocalDate.class));
                empleado.setFechaBaja(result.getObject("fechaBaja", LocalDate.class));
                tabla.add(empleado);
            }
        }catch(SQLException e){
            System.err.println("Error al obtener registros"+e);
        }
        return tabla;
    }

    @Override
    public Empleado obtenerPorID(int id) {
        Empleado empleado=null;
        String sql= "SELECT * FROM Empleados WHERE idEmpleado=?";
        ConexionDB conexion = new ConexionDB();
        try(
                Connection con = conexion.conectar();
                PreparedStatement prep = con.prepareStatement(sql);
                )
        {
            prep.setInt(1, id);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                empleado = new Empleado();
                empleado.setIdEmpleado(result.getInt("idEmpleado"));
                empleado.setUsuario(result.getString("usuario"));
                empleado.setNombre1(result.getString("nombre1"));
                empleado.setNombre2(result.getString("nombre2"));
                empleado.setApellido1(result.getString("apellido1"));
                empleado.setApellido2(result.getString("apellido2"));
                empleado.setContrasenia(result.getString("contrasenia"));
                empleado.setTelefono(result.getString("telefono"));
                empleado.setEstatus(EstatusEmpleado.valueOf(result.getString("estatus")));
                empleado.setPuesto(PuestoEmpleado.valueOf(result.getString("puesto")));
                empleado.setFechaIngreso(result.getObject("fechaIngreso", LocalDate.class));
                empleado.setFoto(result.getString("foto"));
            }
        }catch(SQLException e){
            System.err.println("Error al obtener registros\n"+e);
        }
        return empleado;
    }
    
    public ArrayList<Empleado> obtenerPorNombre(String nombre){
        ArrayList<Empleado> tabla= new ArrayList<>();
        String sql= "SELECT * FROM Empleados "
                + "WHERE nombre1 LIKE ? OR nombre2 LIKE ? "
                + "OR apellido1 LIKE ? OR apellido2 LIKE ?";
        ConexionDB conexion = new ConexionDB();
        try(
                Connection con = conexion.conectar();
                PreparedStatement prep = con.prepareStatement(sql);
                )
        {
            prep.setString(1, nombre);
            prep.setString(2, nombre);
            prep.setString(3, nombre);
            prep.setString(4, nombre);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(result.getInt("idEmpleado"));
                empleado.setUsuario(result.getString("usuario"));
                empleado.setNombre1(result.getString("nombre1"));
                empleado.setNombre2(result.getString("nombre2"));
                empleado.setApellido1(result.getString("apellido1"));
                empleado.setApellido2(result.getString("apellido2"));
                empleado.setContrasenia(result.getString("contrasenia"));
                empleado.setTelefono(result.getString("telefono"));
                empleado.setEstatus(EstatusEmpleado.valueOf(result.getString("estatus")));
                empleado.setPuesto(PuestoEmpleado.valueOf(result.getString("puesto")));
                empleado.setFechaIngreso(result.getObject("fechaIngreso", LocalDate.class));
                empleado.setFoto(result.getString("foto"));
                tabla.add(empleado);
            }
        }catch(SQLException e){
            System.err.println("Error al obtener registros:\n"+e);
        }
        return tabla;
    }

    @Override
    public boolean insertar(Empleado empleado) {
        ConexionDB con = new ConexionDB();
        String sql = "INSERT INTO Empleados VALUES "
                + "(0,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = con.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ){
            prep.setString(1, empleado.getUsuario());
            prep.setString(2, empleado.getContrasenia());
            prep.setString(3, empleado.getTelefono());
            prep.setString(4, empleado.getNombre1());
            prep.setString(5, empleado.getNombre2());
            prep.setString(6, empleado.getApellido1());
            prep.setString(7, empleado.getApellido2());
            prep.setString(8, empleado.getPuesto().toString());
            prep.setString(9, empleado.getEstatus().toString());
            prep.setObject(10, empleado.getFechaIngreso());
            prep.setObject(11, empleado.getFechaBaja());
            prep.setString(12, empleado.getFoto());
            return prep.executeUpdate()>0;
        } catch (SQLException e){
            System.err.println("Error al insertar: "+e);
        }
        return false;
    }

    @Override
    public boolean actualizar(Empleado empleado) {
        ConexionDB con = new ConexionDB();
        String sql = "UPDATE Empleados SET "
                + "usuario=?, "
                + "contrasenia=?, "
                + "telefono=?, "
                + "nombre1=?, "
                + "nombre2=?, "
                + "apellido1=?, "
                + "apellido2=?, "
                + "puesto=?, "
                + "estatus=?, "
                + "fechaIngreso=?, "
                + "fechaBaja=?, "
                + "foto=? "
                + "WHERE idEmpleado=?";
        try (
                Connection conn = con.conectar();
                PreparedStatement prep = conn.prepareStatement(sql);
                ){
            prep.setString(1, empleado.getUsuario());
            prep.setString(2, empleado.getContrasenia());
            prep.setString(3, empleado.getTelefono());
            prep.setString(4, empleado.getNombre1());
            prep.setString(5, empleado.getNombre2());
            prep.setString(6, empleado.getApellido1());
            prep.setString(7, empleado.getApellido2());
            prep.setString(8, empleado.getPuesto().toString());
            prep.setString(9, empleado.getEstatus().toString());
            prep.setObject(10, empleado.getFechaIngreso());
            prep.setObject(11, empleado.getFechaBaja());
            prep.setString(12, empleado.getFoto());
            prep.setInt(13, empleado.getIdEmpleado());
            return prep.executeUpdate()>0;
        } catch (SQLException e){
            System.err.println("Error al actualizar: "+e);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        ConexionDB conexion = new ConexionDB();
        String sql = "DELETE FROM Empleados "
                + "WHERE idEmpleado=?";
        try(
                Connection con = conexion.conectar();
                PreparedStatement prep = con.prepareStatement(sql);
                ){
            prep.setInt(1, id);
            return prep.executeUpdate()>0;
        }catch(SQLException e){
            System.err.println("Error al borrar: "+e);
        }
        return false;
    }

    
    
}
