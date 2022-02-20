/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SuppressWarnings("serial")
public class Ticket implements Serializable {

    // ATTRIBUTES
    private String nombre;
    private LocalDate fecha;
    private BigDecimal importe;

    // CONSTRUCTORS
    public Ticket(String nombre, LocalDate fecha, BigDecimal importe) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.importe = importe;
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
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setImporte(BigDecimal unidades) {
        this.importe = unidades;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
                        new Locale("es", "ES"));
        String msg = String.format(
                "Ticket para el día %s; A nombre de %s por %s €",
                this.fecha.format(formatter),
                this.nombre,
                new DecimalFormat("#0.##").format(this.importe)
        );
        return msg;
    }

}
