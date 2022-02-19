/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author it-ra
 */
@SuppressWarnings("serial")
public class Ticket implements Serializable{

    public Ticket(String nombre, Tipo tipo, int unidades, LocalDate fecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidades = unidades;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    private enum Tipo {
        Normal, Niños, Carnet_joven, tercera_edad
    }
    private String nombre;
    private Tipo tipo;
    private int unidades;
    private LocalDate fecha;

}
