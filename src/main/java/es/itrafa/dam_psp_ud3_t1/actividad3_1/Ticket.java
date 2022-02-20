package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents the purchase of a group of tickets by a user for a specific day.
 *
 * @author it-ra
 */
@SuppressWarnings("serial")
public class Ticket implements Serializable {

    // ATTRIBUTES
    private String nameUser;
    private LocalDate dateToUse;
    private BigDecimal totalPrice;

    // CONSTRUCTORS
    /**
     * Constructor for an object of type Ticket using a TicketAsk object
     *
     * @param peticion TicketAsk object with data to create a Ticket Object
     */
    public Ticket(TicketAsk peticion) {
        this.nameUser = peticion.getUsuario();
        this.dateToUse = peticion.getFecha();
        this.totalPrice = new BigDecimal(peticion.getTipo().getPrice() * peticion.getUnidades());
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
     * Return the price of all tickets buyer.
     * 
     * @return The price of all tickets buyer
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Modify the price of all tickets buyer.
     * 
     * @param totalPrice The price of all tickets buyer
     */
    public void setImporte(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
                        new Locale("es", "ES"));
        String msg = String.format(
                "Ticket para el día %s; A nombre de %s por %s €",
                this.dateToUse.format(formatter),
                this.nameUser,
                new DecimalFormat("#0.##").format(this.totalPrice)
        );
        return msg;
    }

}
