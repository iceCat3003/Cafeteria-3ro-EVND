/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.exceptions.CadenaInvalidaException;
import backend.exceptions.NumeroInvalidoException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Clase para la tabla MateriasPrimas
 * 
 * @author Espinoza Cortés Daniel
 */
public class MateriaPrima {
    private int idMateria;
    private String nombre;
    private int cantidad;
    private byte[] foto;
    private BigDecimal costo;
    private String descripcion;

    public MateriaPrima() {}

    /**
     * Obtiene el idMateria correspondiente a la columna del mismo nombre
     * 
     * @return un entero correspondiente a idMateria de la tabla MateriasPrimas 
     */
    public int getIdMateria() {
        return idMateria;
    }

    /**
     * Establece el idMateria correspondiente a la columna del mismo nombre
     * @param idMateria Un entero correspondiente al idMateria
     */
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * Obtiene el nombre de la materia prima
     * 
     * @return Una cadena correspondiente a la columna nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre correspondiente a la columna del mismo nombre
     * 
     * @param nombre una cadena de no mas de 50 caracteres, sin caracteres especiales
     * 
     * @throws CadenaInvalidaException
     * <ul>
     *         <li>Cuando la cadena incluye caracteres que no son A-Z, a-z, Ñ, ñ
     *         o vocales con acento.</li>
     *         <li>Cuando la cadena contiene más de 50 caracteres.</li>
     *         <li>Cuando la cadena está vacía o contiene solo espacios.</li>
     *         <li>Cuando el parámetro es null.</li>
     * </ul>
     */
    public void setNombre(String nombre) throws CadenaInvalidaException {
        if (nombre !=null) {
            if (!nombre.isBlank()) {
                if (nombre.length() <= 50) {
                    if (nombre.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                        this.nombre = nombre.trim();
                    } else {
                        throw new CadenaInvalidaException(
                                "La cadena no puede tener caracteres especiales");
                    }
                } else {
                    throw new CadenaInvalidaException("La cadena excede 50 caracteres");
                }
            } else {
                throw new CadenaInvalidaException("No se admiten cadenas vacías");
            }
        } else {
            throw new CadenaInvalidaException("El valor no puede ser null");
        }
    }

    /**
     * Obtiene la cantidad de piezas disponibles de acuerdo a la columna del mismo nombre
     * 
     * @return Un entero correspondiente a la columna cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad absoluta de piezas, debe ser un valor de 0 o mayor
     * 
     * @param cantidad un entero correspondiente a la columna cantidad
     * 
     * @throws IllegalArgumentException
     *         <p>Cuando el valor ingresado es menor a cero</p>
     */
    public void setCantidad(int cantidad) throws IllegalArgumentException {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            throw new IllegalArgumentException("La cantidad debe ser igual o mayor a cero");
        }
    }

    /**
     * <p>Obtiene la foto correspondiente al mediumblob de su columna correspondiente
     * como un byte[]</p>
     * 
     * @return un byte[] de una foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Establece un byte[] para la columna foto, no debe exceder 16,777,215 bytes
     * 
     * @param foto Una foto convertida a byte[]
     */
    public void setFoto(byte[] foto) {
        if (foto.length <= 16777215) {
            this.foto = foto;
        } else {
            throw new IllegalArgumentException("la foto excede 16,777,215 bytes");
        }
    }

    /**
     * Obtiene el valor de la columna costo como un BigDecimal
     * 
     * @return Un BigDecimal correspondiente a la columna correspondiente a costo
     */
    public BigDecimal getCosto() {
        return costo;
    }

    /**
     * Establece el costo de la MateriaPrima
     * 
     * @param costo 
     *         <p>Debe ser un valor con 10 posiciones enteras y 2 de decimales,
     *         conforme a la base de datos</p>
     * 
     * @throws NumeroInvalidoException
     *         <ul>
     *              <li>Cuando el valor es negativo</li>
     *              <li>Cuando el valor excede 9,999,999,999.99</li>
     *         </ul>
     * @throws IllegalArgumentException Cuando el parámetro es null
     */
    public void setCosto(BigDecimal costo) throws NumeroInvalidoException {
        if (costo == null) {
            throw new NumeroInvalidoException("El costo no puede ser null");
        }

        if (costo.compareTo(BigDecimal.ZERO) < 0) {
            throw new NumeroInvalidoException("El costo debe ser 0 o mayor");
        }

        if (costo.compareTo(new BigDecimal("9999999999.99")) > 0) {
            throw new NumeroInvalidoException(
                "El costo excede el máximo permitido: 9,999,999,999.99"
            );
        }
        this.costo = costo.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Obtiene la descripcion correspondiente a la columna del mismo nombre
     * 
     * @return Una cadena correspondiente a la columna descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion para la tabla descripcion
     * 
     * @param descripcion una cadena correspondiente a la columna descripcion
     * 
     * @throws CadenaInvalidaException
     *         <p>Cuando la cadena incluye caracteres especiales como . , \ = etc.
     *
     *          Cuando la cadena está conformada por más de 255 caracteres
     */
    public void setDescripcion(String descripcion) throws CadenaInvalidaException {
        if(descripcion != null && !descripcion.isBlank()) {
            if (descripcion.length() <= 255) {
                if (descripcion.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                    this.descripcion = descripcion.trim();
                } else {
                    throw new CadenaInvalidaException(
                            "La cadena no puede contener caracteres especiales");
                }
            } else{
                throw new CadenaInvalidaException(
                        "La cadena debe tener un máximo de 255 caracteres");
            }
        } else {
            this.descripcion = null;
        }
    }

    /**
     * Devuelve una cadena con los atributos del objeto
     * 
     * @return un String con atributos
     */
    @Override
    public String toString() {
        return "MateriaPrima{" + 
                "idMateria=" + idMateria + 
                ", nombre=" + nombre + 
                ", cantidad=" + cantidad + 
                ", foto=" + foto + 
                ", costo=" + costo + 
                ", descripcion=" + descripcion + '}';
    }
    
}
