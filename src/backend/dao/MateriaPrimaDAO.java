/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.exceptions.CadenaInvalidaException;
import backend.exceptions.NumeroInvalidoException;
import backend.modelos.MateriaPrima;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para el CRUD de la tabla MateriasPrimas de la base de datos
 * 
 * @author Espinoza Cortés Daniel
 */
public class MateriaPrimaDAO implements InterfazDAO<MateriaPrima> {

    /**
     * <p>Devuelve una listacon todas las materias primas con los valores de todas
     * sus columnas</p>
     * 
     * @return <p>Un ArrayList de objetos MateriaPrima con todos los atributos
     *         correspondientes a cada fila</p>
     */
    @Override
    public ArrayList<MateriaPrima> obtenerRegistros() {
        ArrayList<MateriaPrima> tabla = new ArrayList<>();
        final String SQL = "SELECT * FROM MateriasPrimas";
        ConexionDB conexion = new ConexionDB();
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                ) {
            try {
                while (rs.next()) {
                    MateriaPrima mp = new MateriaPrima();
                    mp.setIdMateria(rs.getInt("idMateria"));
                    mp.setNombre(rs.getString("nombre"));
                    mp.setCantidad(rs.getInt("cantidad"));
                    mp.setFoto(rs.getBytes("foto"));
                    mp.setCosto(rs.getBigDecimal("costo"));
                    mp.setDescripcion(rs.getString("descripcion"));
                    tabla.add(mp);
                }
            } catch (CadenaInvalidaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (NumeroInvalidoException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return tabla;
    }

    @Override
    public MateriaPrima obtenerPorID(int id) {
        MateriaPrima mp = null;
        final String SQL = "SELECT * FROM MateriasPrimas WHERE idMateria=?";
        ConexionDB conexion = new ConexionDB();
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                ) {
            try {
                while (rs.next()) {
                    mp.setIdMateria(rs.getInt("idMateria"));
                    mp.setNombre(rs.getString("nombre"));
                    mp.setCantidad(rs.getInt("cantidad"));
                    mp.setFoto(rs.getBytes("foto"));
                    mp.setCosto(rs.getBigDecimal("costo"));
                    mp.setDescripcion(rs.getString("descripcion"));
                }
            } catch (CadenaInvalidaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (NumeroInvalidoException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return mp;
    }

    @Override
    public int insertar(MateriaPrima mp) {
        ConexionDB conexion = new ConexionDB();
        final String SQL = "INSERT INTO MateriasPrimas"
                + "(nombre,cantidad,foto,costo,descripcion) VALUES "
                + "(?," //1
                + "?,"  //2
                + "?,"  //3
                + "?,"  //4
                + "?)"; //5
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL)
                ) {
            ps.setString(1, mp.getNombre());
            ps.setInt(2, mp.getCantidad());
            ps.setBytes(3, mp.getFoto());
            ps.setBigDecimal(4, mp.getCosto());
            ps.setString(5, mp.getDescripcion());
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return 0;
    }

    @Override
    public boolean actualizar(MateriaPrima mp) {
        ConexionDB conexion = new ConexionDB();
        final String SQL = "UPDATE MateriasPrimas SET "
                + "nombre=?, "          //1
                + "cantidad=?, "        //2
                + "foto=?, "            //3
                + "costo=?, "           //4
                + "descripcion=? "      //5
                + "WHERE idMateria=?";  //6
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ) {
            ps.setString(1, mp.getNombre());
            ps.setInt(2, mp.getCantidad());
            ps.setBytes(3, mp.getFoto());
            ps.setBigDecimal(4, mp.getCosto());
            ps.setString(5, mp.getDescripcion());
            ps.setInt(6, mp.getIdMateria());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
