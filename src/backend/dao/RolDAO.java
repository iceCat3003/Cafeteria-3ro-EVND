/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.exceptions.CadenaInvalidaException;
import backend.modelos.Rol;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author icecat
 */
public class RolDAO implements InterfazDAO<Rol> {

    /**
     * Obtiene todos los roles disponibles de la tabla Roles en un ArrayList
     * 
     * @return Un ArrayList de Rol
     */
    @Override
    public ArrayList<Rol> obtenerRegistros() {
        ArrayList<Rol> tabla = new ArrayList<>();
        final String SQL = "SELECT * FROM Roles";
        ConexionDB conexion = new ConexionDB();
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                ) {
            try {
                while (rs.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt(1));
                    rol.setNombreRol(rs.getString(2));
                    tabla.add(rol);
                }
            } catch (CadenaInvalidaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return tabla;
    }

    /**
     * Obtiene una sola fila de la tabla Roles
     * 
     * @param id el idRol correspondiente a la columna idRol de tabla Roles
     * 
     * @return Un objeto Rol con un idRol y un nombreRol
     */
    @Override
    public Rol obtenerPorID(int id) {
        Rol rol = null;
        if(id>0){
            final String SQL = "SELECT * FROM Roles WHERE idRol=?";
            ConexionDB conexion = new ConexionDB();
            try (
                    Connection con = conexion.conectar();
                    PreparedStatement ps = con.prepareStatement(SQL);
                    ResultSet rs = ps.executeQuery();
                    ) {
                try {
                    while (rs.next()) {
                        rol = new Rol();
                        rol.setIdRol(rs.getInt(1));
                        rol.setNombreRol(rs.getString(2));
                    }
                } catch (CadenaInvalidaException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("El id debe ser un número mayor a cero");
        }
        return rol;
    }

    /**
     * Inserta una fila en la tabla Roles utilizando un objeto Rol
     * 
     * @param rol Un objeto usuario con su atributo nombreRol definido
     * 
     * @return El idRol asignado a la nueva fila, si no fue exitoso devolverá 0 
     */
    @Override
    public int insertar(Rol rol) {
        if (rol.getNombreRol()!=null) {
            ConexionDB conexion = new ConexionDB();
            final String SQL = "INSERT INTO Roles(nombreRol) VALUES(?)";
            try (
                    Connection con = conexion.conectar();
                    PreparedStatement ps = con.prepareStatement(SQL);
                    ) {
                ps.setString(1, rol.getNombreRol());
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("Al objeto Rol le falta atributo nombre");
        }
        return 0;
    }

    /**
     * <p>Realiza un UPDATE a la única columna modificable de la tabla Roles,
     * devuelve verdadero solo si la actualización fue exitosa</p>
     * 
     * @param rol 
     * @return 
     */
    @Override
    public boolean actualizar(Rol rol) {
        if (rol.getIdRol()>0 && rol.getNombreRol()!=null) {
            ConexionDB conexion = new ConexionDB();
            final String SQL = "UPDATE Roles SET nombreRol=? WHERE idRol=?";
            try (
                    Connection con = conexion.conectar();
                    PreparedStatement ps = con.prepareStatement(SQL);
                    ) {
                ps.setString(1, rol.getNombreRol());
                ps.setInt(2, rol.getIdRol());
                return ps.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("El objeto Rol debe estar completo y con id válido");
        }
        return false;
    }

    /**
     * <p>Borra una fila de la tabla Roles acorde a la columna idRol usado como
     * parámetro, devolverá un entero si el borrado fue exitoso</p>
     * 
     * @param id un entero correspondiente a la columna idRol de la fila a borrar
     * 
     * @return verdadero si es exitoso, falso si no es exitoso
     */
    @Override
    public boolean borrar(int id) {
        if (id>0) {
            ConexionDB conexion = new ConexionDB();
            final String SQL = "DELETE Roles WHERE idRol=?";
            try (
                    Connection con = conexion.conectar();
                    PreparedStatement ps = con.prepareStatement(SQL);
                    ) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("El id debe ser un número mayor a cero");
        }
        return false;
    }
    
}
