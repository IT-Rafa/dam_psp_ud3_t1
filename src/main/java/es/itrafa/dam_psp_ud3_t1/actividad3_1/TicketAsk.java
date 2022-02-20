/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author it-ra
 */
@SuppressWarnings("serial")
public class TicketAsk implements Serializable {

    // ATTRIBUTES
    private String usuario;
    private int unidades;
    private LocalDate fecha;
    private TicketType tipo;

    // CONSTRUCTORS
    public TicketAsk(String usuario, int unidades, LocalDate fecha, TicketType tipo) {
        this.usuario = usuario;
        this.unidades = unidades;
        this.fecha = fecha;
        this.tipo = tipo;
    }

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

    @Override
    public String toString() {
        final DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
                        new Locale("es", "ES"));

        String msg = String.format(
                "Petición de ticket por usuario %s de %d entradas para %s para el día %s",
                this.usuario,
                this.unidades,
                this.tipo.toString(),
                this.fecha.format(formatter));

        return msg;
    }

}
