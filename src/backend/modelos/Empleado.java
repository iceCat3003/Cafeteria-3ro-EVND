/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.enums.EstatusEmpleado;
import backend.enums.PuestoEmpleado;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author icecat
 */
public class Empleado {
    private int idEmpleado;
    private String usuario;
    private String contrasenia;
    private String telefono;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private PuestoEmpleado puesto;
    private EstatusEmpleado estatus;
    private LocalDate fechaIngreso;
    private LocalDate fechaBaja;
    private String foto;

    public Empleado() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public PuestoEmpleado getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoEmpleado puesto) {
        this.puesto = puesto;
    }

    public EstatusEmpleado getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEmpleado estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" +idEmpleado +
                ", usuario=" + usuario +
                ", contrasenia=" + contrasenia +
                ", telefono=" + telefono +
                ", nombre1=" + nombre1 +
                ", nombre2=" + nombre2 +
                ", apellido1=" + apellido1 +
                ", apellido2=" + apellido2 +
                ", puesto=" + puesto +
                ", estatus=" + estatus +
                ", fechaIngreso=" + fechaIngreso +", fechaBaja=" + fechaBaja + '}';
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
}
