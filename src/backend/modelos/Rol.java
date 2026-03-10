/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.exceptions.CadenaInvalidaException;

/**
 *
 * @author icecat
 */
public class Rol {
    private int idRol;
    private String nombreRol;

    public Rol() {}

    /**
     * Obtiene el id de la tabla Roles
     * 
     * @return Un entero correspondiente a la columna idRol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Establece el id de la tabla Roles
     * 
     * @param idRol Un entero correspondiente a la columna idRol
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * Obtiene el nombreRol de la tabla Roles
     * 
     * @return Un String correspondiente a la columna nombreRol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombreRol de la tabla Roles
     * 
     * @param nombreRol Un string correspondiente a la columna nombreRol
     * 
     * @throws CadenaInvalidaException
     *          <p>Cuando el parámetro contiene caracteres especiales
     * 
     *          Cuando el parámetro contiene más de 20 caracteres
     * 
     *          Cuando el parámetro es un conjunto vacío
     * 
     *          Cuando el parámetro es null
     */
    public void setNombreRol(String nombreRol) throws CadenaInvalidaException {
        if (nombreRol != null){
            if (!nombreRol.isBlank()) {
                if (nombreRol.trim().length() <= 20) {
                    if (nombreRol.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                        this.nombreRol = nombreRol.trim();
                    } else {
                        throw new CadenaInvalidaException(nombreRol+" incluye caracteres no permitidos");
                    }
                } else {
                    throw new CadenaInvalidaException(nombreRol+" contiene demasiados caracteres (>20)");
                }
            } else {
                throw new CadenaInvalidaException("No se permiten cadenas vacías");
            }
        } else {
            throw new CadenaInvalidaException("El parámetro no puede ser null");
        }
    }
    
    
}
