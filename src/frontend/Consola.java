/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frontend;

import backend.dao.EmpleadoDAO;
import backend.enums.EstatusEmpleado;
import backend.enums.PuestoEmpleado;
import backend.modelos.Empleado;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author icecat
 */
public class Consola {

    /**
     * @param args the command line arguments
     */
    public static int id;
    public static void main(String[] args) {
        int opc;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("""
                               Seleccione una opcion:
                               1- Mostrar todos los empleados
                               2- Buscar empleado por ID
                               3- Buscar empleado por nombre
                               4- Insertar empleados
                               5- Actualizar un empleado
                               6- Borrar un empleado
                               0- Salir""");
            opc=sc.nextInt();
            switch(opc){
                case 1: listar();break;
                case 2: buscarPorID();break;
                case 3: buscarPorNombre();break;
                case 4: insertarEmpleados();break;
                case 5: actualizarEmpleado(); break;
                case 6: borrarEmpleado(); break;
                case 0: System.out.println("Adiós");
                default:
            }
        }while (opc!=0);
    }
    public static void listar(){
        EmpleadoDAO dao = new EmpleadoDAO();
        ArrayList<Empleado> tabla = dao.obtenerRegistros();
        if (!tabla.isEmpty()) {
            for (Empleado registros : tabla) {
                System.out.println(registros);
            }
        } else {
            System.out.println("Lista vacia o no hay registros que mostrar");
        }
    }
    public static void buscarPorID(){
        Scanner sc = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO();
        System.out.println("Ingrese el id del empleado");
        id = sc.nextInt();
        Empleado empleado = dao.obtenerPorID(id);
        System.out.println(empleado);
    }
    
    
    public static void buscarPorNombre(){
        Scanner sc = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO();
        System.out.println("Ingrese un nombre o un apellido del empleado");
        ArrayList<Empleado> tabla = dao.obtenerPorNombre(sc.next());
        if (!tabla.isEmpty()) {
            for (Empleado registros : tabla) {
                System.out.println(registros);
            }
        } else {
            System.out.println("No existen empleados con ese nombre o apellido");
        }
    }
    
    public static void insertarEmpleados(){
        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado empleado = new Empleado();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int opc;
        System.out.println("Ingrese el nombre de usuario");
        empleado.setUsuario(sc.next());
        
        System.out.println("Ingrese la contraseña");
        empleado.setContrasenia(sc.next());
        
        System.out.println("Ingrese el número de teléfono");
        empleado.setTelefono(sc.next());
        
        System.out.println("Ingrese su primer nombre");
        empleado.setNombre1(sc.next());
        
        System.out.println("Ingrese su segundo nombre");
        empleado.setNombre2(sc.next());
        
        System.out.println("Ingrese su primer apellido");
        empleado.setApellido1(sc.next());
        
        System.out.println("Ingrese su segundo apellido");
        empleado.setApellido2(sc.next());
        
        System.out.println("Ingrese la ubicación de la foto de usuario");
        empleado.setFoto(sc.next());
        
        System.out.println("""
                           Elija el puesto
                           1- Gerente
                           2- Cajero
                           3- Barista""");
        opc=sc.nextInt();
        switch(opc){
            case 1 -> empleado.setPuesto(PuestoEmpleado.GERENTE);
            case 2 -> empleado.setPuesto(PuestoEmpleado.CAJERO);
            case 3 -> empleado.setPuesto(PuestoEmpleado.BARISTA);
            default -> System.out.println("Opción incorrecta");
        }
        System.out.println("""
                           Elija el estatus
                           1- Activo
                           2- Inactivo
                           3- Baja temporal""");
        opc=sc.nextInt();
        switch (opc) {
            case 1 -> empleado.setEstatus(EstatusEmpleado.ACTIVO);
            case 2 -> empleado.setEstatus(EstatusEmpleado.INACTIVO);
            case 3 -> empleado.setEstatus(EstatusEmpleado.BAJA_TEMPORAL);
            default -> System.out.println("Opción incorrecta");
        }
        
        System.out.println(dao.insertar(empleado)?"Empleado añadido correctamente":"Error en inserción de empleado");
    }
    
    public static void actualizarEmpleado() {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int opc;
        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado empleado = new Empleado();
        buscarPorID();
        
        empleado.setIdEmpleado(id);
        
        System.out.println("Ingrese el nombre de usuario");
        empleado.setUsuario(sc.next());
        
        System.out.println("Ingrese la contraseña");
        empleado.setContrasenia(sc.next());
        
        System.out.println("Ingrese el número de teléfono");
        empleado.setTelefono(sc.next());
        
        System.out.println("Ingrese su primer nombre");
        empleado.setNombre1(sc.next());
        
        System.out.println("Ingrese su segundo nombre");
        empleado.setNombre2(sc.next());
        
        System.out.println("Ingrese su primer apellido");
        empleado.setApellido1(sc.next());
        
        System.out.println("Ingrese su segundo apellido");
        empleado.setApellido2(sc.next());
        
        System.out.println("Ingrese la ubicación de la foto de usuario");
        empleado.setFoto(sc.next());
        
        System.out.println("""
                           Elija el puesto
                           1- Gerente
                           2- Cajero
                           3- Barista""");
        opc=sc.nextInt();
        switch(opc){
            case 1 -> empleado.setPuesto(PuestoEmpleado.GERENTE);
            case 2 -> empleado.setPuesto(PuestoEmpleado.CAJERO);
            case 3 -> empleado.setPuesto(PuestoEmpleado.BARISTA);
            default -> System.out.println("Opción incorrecta");
        }
        System.out.println("""
                           Elija el estatus
                           1- Activo
                           2- Inactivo
                           3- Baja temporal""");
        opc=sc.nextInt();
        switch (opc) {
            case 1 -> empleado.setEstatus(EstatusEmpleado.ACTIVO);
            case 2 -> empleado.setEstatus(EstatusEmpleado.INACTIVO);
            case 3 -> empleado.setEstatus(EstatusEmpleado.BAJA_TEMPORAL);
            default -> System.out.println("Opción incorrecta");
        }
        
        System.out.println(dao.actualizar(empleado)?"Empleado actualizado correctamente":"Error en actualización de empleado");
        id=0;
    }
    
    public static void borrarEmpleado() {
        EmpleadoDAO dao = new EmpleadoDAO();
        Scanner sc = new Scanner(System.in);
        buscarPorID();
        System.out.println("Estás seguro de que quieres borrar a este empleado"
                + "\n(s) para borrar, cualquier otra tecla para cancelar");
        if (sc.next().equalsIgnoreCase("s")) {
            System.out.println(dao.borrar(id)?"Empleado borrado exitosamente":"error en borrado de empleado");
        }
    }
}
