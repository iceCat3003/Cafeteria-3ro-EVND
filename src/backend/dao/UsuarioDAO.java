/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.enums.EstadoUsuario;
import backend.enums.NivelAcceso;
import backend.exceptions.CadenaInvalidaException;
import backend.exceptions.NumeroInvalidoException;
import backend.modelos.Usuario;
import backend.modelos.Rol;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Espinoza Cortés Daniel
 */
public class UsuarioDAO implements InterfazDAO<Usuario> {

    /**
     * Obtiene una lista con todos los usuarios
     * (SELECT *...)
     * 
     * @return <p>Un ArrayList con todos los usuarios, de no haber usuarios se 
     *         devuelve el ArrayList vacío
     */
    @Override
    public ArrayList<Usuario> obtenerRegistros() {
        ArrayList<Usuario> tabla = new ArrayList<>();
        final String SQL = "SELECT Usuarios.idUsuario, " +
                "Usuarios.nombre1, " +
                "Usuarios.apellido1, " +
                "Usuarios.apellido2, " +
                "Usuarios.telefono, " +
                "Usuarios.usuario, " +
                "Usuarios.contrasenia, " +
                "Usuarios.estadoUsuario, " +
                "Usuarios.nivelAcceso, " +
                "Usuarios.idRol, " +
                "Roles.nombreRol, " +
                "Usuarios.imagen, " +
                "Usuarios.salario " +
                "FROM Usuarios " +
                "INNER JOIN Roles ON Usuarios.idRol=Roles.idRol";
        ConexionDB conexionDB = new ConexionDB();
        try(
                Connection conn= conexionDB.conectar();
                PreparedStatement prep = conn.prepareStatement(SQL);
                ResultSet rs = prep.executeQuery()) {
            try {
                while (rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre1(rs.getString("nombre1"));
                    usuario.setNombre2(rs.getString("nombre2"));
                    usuario.setApellido1(rs.getString("apellido1"));
                    usuario.setApellido2(rs.getString("apellido2"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setContrasenia(rs.getString("contrasenia"));
                    usuario.setEstadoUsuario(EstadoUsuario.valueOf(rs.getString("estadoUsuario")));
                    usuario.setNivelAcceso(NivelAcceso.valueOf(rs.getString("nivelAcceso")));
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setNombreRol(rs.getString("nombreRol"));
                    usuario.setRol(rol);
                    usuario.setImagen(rs.getBytes("imagen"));
                    usuario.setSalario(rs.getBigDecimal("salario"));
                    tabla.add(usuario);
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

    /**
     * <p>Obtiene un único Usuario acorde al ID
     * (SELECT * [...] WHERE idUsuario=[parametro])</p>
     * 
     * @param id 
     *         <p>debe ser un entero correspondiente al idUsuario en BD</p>
     * 
     * @return <p>Un objeto Usuario con los datos del usuario encontrado, 
     *         de no encontrarse devuelve un null</p>
     */
    @Override
    public Usuario obtenerPorID(int id) {
        Usuario usuario = null;
        if (id>0) {
            Rol rol = null;
            final String SQL = "SELECT Usuarios.idUsuario, Usuarios.nombre1, "+
                    "Usuarios.apellido1, " +
                    "Usuarios.apellido2, " +
                    "Usuarios.telefono, " +
                    "Usuarios.usuario, " +
                    "Usuarios.contrasenia, " +
                    "Usuarios.estadoUsuario, " +
                    "Usuarios.nivelAcceso, " +
                    "Usuarios.idRol, " +
                    "Roles.nombreRol, " +
                    "Usuarios.imagen, " +
                    "Usuarios.salario " +
                    "FROM Usuarios " +
                    "INNER JOIN Roles ON Usuarios.idRol=Roles.idRol " +
                    "WHERE Usuarios.idUsuario=?";
            ConexionDB conexionDB = new ConexionDB();
            try (
                    Connection con = conexionDB.conectar();
                    PreparedStatement prep = con.prepareStatement(SQL);
                    ResultSet rs = prep.executeQuery();
                    ) {
                try {
                    while (rs.next()) {
                        usuario = new Usuario();
                        usuario.setIdUsuario(rs.getInt("idUsuario"));
                        usuario.setNombre1(rs.getString("nombre1"));
                        usuario.setNombre2(rs.getString("nombre2"));
                        usuario.setApellido1(rs.getString("apellido1"));
                        usuario.setApellido2(rs.getString("apellido2"));
                        usuario.setTelefono(rs.getString("telefono"));
                        usuario.setUsuario(rs.getString("usuario"));
                        usuario.setContrasenia(rs.getString("contrasenia"));
                        usuario.setEstadoUsuario(EstadoUsuario.valueOf(rs.getString("estadoUsuario")));
                        usuario.setNivelAcceso(NivelAcceso.valueOf(rs.getString("nivelAcceso")));
                        rol = new Rol();
                        rol.setIdRol(rs.getInt("idRol"));
                        rol.setNombreRol(rs.getString("nombreRol"));
                        usuario.setRol(rol);
                        usuario.setImagen(rs.getBytes("imagen"));
                        usuario.setSalario(rs.getBigDecimal("salario"));
                    }
                } catch (CadenaInvalidaException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (NumeroInvalidoException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("El idUsuario debe ser mayor a cero");
        }
        return usuario;
    }
    
    /**
     * <p>Método para iniciar sesión, de ser correcta la información se devuelve un 
     * Usuario con toda su información</p>
     * 
     * @param usuario
     *         Una cadena correspondiente al nombre de usuario del Usuario
     * 
     * @param contrasenia
     *         Una cadena correspondiente a la columna contrasenia del Usuario
     * 
     * @return <p>Un objeto Usuario lleno con todos sus atributos, de no encontrar 
     *         una fila correspondiente, devuelve un null</p>
     */
    public Usuario loginUsuario(String usuario, String contrasenia){
        Usuario u = null;
        Rol rol = null;
        if (usuario!=null && !usuario.isBlank() && contrasenia!=null && contrasenia.isBlank()) {
            final String SQL = "SELECT Usuarios.idUsuario, Usuarios.nombre1, "+
                    "Usuarios.apellido1, " +
                    "Usuarios.apellido2, " +
                    "Usuarios.telefono, " +
                    "Usuarios.usuario, " +
                    "Usuarios.contrasenia, " +
                    "Usuarios.estadoUsuario, " +
                    "Usuarios.nivelAcceso, " +
                    "Usuarios.idRol, " +
                    "Roles.nombreRol, " +
                    "Usuarios.imagen, " +
                    "Usuarios.salario " +
                    "FROM Usuarios " +
                    "INNER JOIN Roles ON Usuarios.idRol=Roles.idRol " +
                    "WHERE Usuarios.usuario=? AND Usuarios.contrasenia=?";
            ConexionDB conexionDB = new ConexionDB();
            try (
                    Connection con = conexionDB.conectar();
                    PreparedStatement prep = con.prepareStatement(SQL);
                    ) {
                prep.setString(1, usuario);
                prep.setString(2, contrasenia);
                ResultSet rs = prep.executeQuery();
                try {
                    while (rs.next()) {
                        u = new Usuario();
                        u.setIdUsuario(rs.getInt("idUsuario"));
                        u.setNombre1(rs.getString("nombre1"));
                        u.setNombre2(rs.getString("nombre2"));
                        u.setApellido1(rs.getString("apellido1"));
                        u.setApellido2(rs.getString("apellido2"));
                        u.setTelefono(rs.getString("telefono"));
                        u.setUsuario(rs.getString("usuario"));
                        u.setContrasenia(rs.getString("contrasenia"));
                        u.setEstadoUsuario(EstadoUsuario.valueOf(rs.getString("estadoUsuario")));
                        u.setNivelAcceso(NivelAcceso.valueOf(rs.getString("nivelAcceso")));
                        rol.setIdRol(rs.getInt("idRol"));
                        rol.setNombreRol(rs.getString("nombreRol"));
                        u.setRol(rol);
                        u.setImagen(rs.getBytes("imagen"));
                        u.setSalario(rs.getBigDecimal("salario"));
                    }
                } catch (CadenaInvalidaException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (NumeroInvalidoException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            throw new IllegalArgumentException("Ambos parámetros deben tener contenido");
        }
        return u;
    }

    /**
     * Inserta una fila en la tabla Usuarios utilizando un objeto Usuario
     * 
     * @param usuario
     *         <p>Un objeto Usuario con los datos correspondientes a insertar en 
     *         una nueva fila en la tabla Usuarios</p>
     * 
     * @return <p>Devuelve el idUsuario de la nueva fila insertada, si la
     *         inserción no fue exitosa devolverá 0</p>
     */
    @Override
    public int insertar(Usuario usuario) {
        if (usuario.getNombre1()==null ||
                usuario.getApellido1()==null ||
                usuario.getTelefono()==null ||
                usuario.getUsuario()==null ||
                usuario.getContrasenia()==null ||
                usuario.getEstadoUsuario()==null ||
                usuario.getNivelAcceso()==null ||
                usuario.getRol()==null ||
                usuario.getRol().getIdRol()<1 ||
                usuario.getSalario()==null){
            throw new IllegalArgumentException(
                    "El objeto Usuario no está completado correctamente\n"+
                            usuario.toString());
        }
        int id =0;
        ConexionDB conexion = new ConexionDB();
        final String SQL = "INSERT INTO Usuarios VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(
                Connection con = conexion.conectar();
                PreparedStatement prep = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                ){
            prep.setString(1, usuario.getNombre1());
            prep.setString(2, usuario.getNombre2());
            prep.setString(3, usuario.getApellido1());
            prep.setString(4, usuario.getApellido2());
            prep.setString(5, usuario.getTelefono());
            prep.setString(6, usuario.getUsuario());
            prep.setString(7, usuario.getContrasenia());
            prep.setString(8, usuario.getEstadoUsuario().toString());
            prep.setString(9, usuario.getNivelAcceso().toString());
            prep.setInt(10, usuario.getRol().getIdRol());
            prep.setBytes(11, usuario.getImagen());
            prep.setBigDecimal(12, usuario.getSalario());
            try (ResultSet rs = prep.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt("idUsuario");
                }
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return id;
    }

    /**
     * <p>Realiza un UPDATE de todas las columnas de una fila en la tabla Usuarios,
     * devuelve verdadero si se actualizó correctamente y falso si no</p>
     * 
     * @param usuario
     *         Un usuario con sus atributos establecidos
     * 
     * @return verdadero si se actualiza correctamente, falso si no
     */
    @Override
    public boolean actualizar(Usuario usuario) {
        if (usuario.getNombre1()==null ||
                usuario.getApellido1()==null ||
                usuario.getTelefono()==null ||
                usuario.getUsuario()==null ||
                usuario.getContrasenia()==null ||
                usuario.getEstadoUsuario()==null ||
                usuario.getNivelAcceso()==null ||
                usuario.getRol()==null ||
                usuario.getRol().getIdRol()<1 ||
                usuario.getSalario()==null ||
                usuario.getIdUsuario()<1){
            throw new IllegalArgumentException(
                    "El objeto Usuario no está completado correctamente\n"+
                            usuario.toString());
        }
        ConexionDB conexion = new ConexionDB();
        final String SQL = "UPDATE Usuarios SET"
                + "nombre1=?,"//1
                + "nombre2=?,"//2
                + "apellido1=?,"//3
                + "apellido2=?,"//4
                + "telefono=?,"//5
                + "usuario=?,"//6
                + "contrasenia=?,"//7
                + "estadoUsuario=?,"//8
                + "nivelAcceso=?,"//9
                + "idRol=?,"//10
                + "imagen=?,"//11
                + "salario=? "//12
                + "WHERE idUsuario=?";//13
        try (
                Connection con = conexion.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ) {
            ps.setString(1, usuario.getNombre1());
            ps.setString(2, usuario.getNombre2());
            ps.setString(3, usuario.getApellido1());
            ps.setString(4, usuario.getApellido2());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getUsuario());
            ps.setString(7, usuario.getContrasenia());
            ps.setString(8, usuario.getEstadoUsuario().toString());
            ps.setString(9, usuario.getNivelAcceso().toString());
            ps.setInt(10, usuario.getRol().getIdRol());
            ps.setBytes(11, usuario.getImagen());
            ps.setBigDecimal(12, usuario.getSalario());
            ps.setInt(13, usuario.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    /**
     * <p>Borra la fila correspondiente al idUsuario de la tabla Usuarios de
     * acuerdo al parámetro, devuelve un verdadero si fue borrado correctamente</p>
     * 
     * @param id El idUsuario a borrar
     * @return verdadero si el borrado fue correcto, falso si no
     */
    @Override
    public boolean borrar(int id) {
        if (id<1) {
            throw new IllegalArgumentException("El id debe ser mayor a 0");
        }
        ConexionDB conexon = new ConexionDB();
        final String SQL = "DELETE Usuarios WHERE idUsuario=?";
        try (
                Connection con = conexon.conectar();
                PreparedStatement ps = con.prepareStatement(SQL);
                ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
}
