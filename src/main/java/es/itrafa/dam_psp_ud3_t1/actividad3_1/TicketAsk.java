/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.time.LocalDate;

/**
 *
 * @author it-ra
 */
public class TicketAsk {

    // CONSTRUCTORS
    public TicketAsk(String usuario, int unidades, LocalDate fecha, TicketType tipo) {
        this.usuario = usuario;
        this.unidades = unidades;
        this.fecha = fecha;
        this.tipo = tipo;
    }
    // ATTRIBUTES
    private String usuario;
    private int unidades;
    private LocalDate fecha;
    private TicketType tipo;

    // GETTER & SETTERS
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public TicketType getTipo() {
        return tipo;
    }

    public void setTipo(TicketType tipo) {
        this.tipo = tipo;
    }
}
