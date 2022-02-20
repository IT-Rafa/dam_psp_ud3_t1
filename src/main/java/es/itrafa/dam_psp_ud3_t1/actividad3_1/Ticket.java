/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Ticket implements Serializable{
    
        // ATTRIBUTES
    private String nombre;
    private LocalDate fecha;
    private int unidades;
    
    // CONSTRUCTORS
    public Ticket(String nombre, LocalDate fecha, int unidades) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.unidades = unidades;
    }
    
    // GETTER & SETTERS
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the unidades
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
 

}