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
 * Contains data to create a Ticket. designed to send using a socket
 *
 * @author it-ra
 */
@SuppressWarnings("serial")
public class TicketAsk implements Serializable {

    // ATTRIBUTES
    private String nameUser;
    private int cant;
    private LocalDate dateToUse;
    private TicketType type;

    // CONSTRUCTORS
    public TicketAsk(String nameUser, int unidades, LocalDate dateToUse, TicketType type) {
        this.nameUser = nameUser;
        this.dateToUse = dateToUse;
        this.cant = cant;
        this.type = type;
    }

    // GETTER & SETTERS
    /**
     * Return the name of the user who makes the purchase 
     *
     * @return Name of the user who makes the purchase 
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     * Modify the name of the user who makes the purchase 
     *
     * @param nameUser New name of the user who makes the purchase 
     */
    public void setNombre(String nameUser) {
        this.nameUser = nameUser;
    }

    /**
     * Return the date on which the ticket is valid
     *
     * @return Date on which the ticket is valid
     */
    public LocalDate getDateToUse() {
        return dateToUse;
    }

    /**
     * Modify the date on which the ticket is valid
     *
     * @param dateToUse Date on which the ticket is valid
     */
    public void setDateToUse(LocalDate dateToUse) {
        this.dateToUse = dateToUse;
    }

    /**
     * return cant of ticket to buy
     *
     * @return cant of ticket to buy
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * Modify cant of ticket to buy
     *
     * @param unidades
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * return date
     *
     *
     * @return name of ticket buyer
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modify name of ticket buyer
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * return type of ticket to buy
     *
     * @return name of ticket buyer
     */
    public TicketType getTipo() {
        return tipo;
    }

    /**
     * Modify type of ticket to buy
     */
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
