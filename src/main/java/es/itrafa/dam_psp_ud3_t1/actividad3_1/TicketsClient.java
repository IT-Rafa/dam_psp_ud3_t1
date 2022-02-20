package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Amusement Park Ticket Client.
 * <p>
 * Establishes communication with a Ticket Server for an amusement park, sends
 * the ticket request and receives the ticket.
 *
 * @author it-ra
 */
public class TicketsClient {

    // ATTRIBUTES
    static final private int SERVERPORT = 2000;
    static final private String SERVERHOST = "localhost";

    // MAIN METHOD
    /**
     * It tries to connect to the server and, if successful, exchanges data.
     *
     * @param args
     */
    public static void main(String[] args) {
        Ticket ticketReceived;
        try {
            // Iniciamos comunicación con servidor (try-with-resources
            try (Socket ServerConn = new Socket(SERVERHOST, SERVERPORT)) {
                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, String.format(
                                "Iniciada comunicación con servidor ", SERVERHOST));

                // Gestionamos comunicación con servidor y guardamos ticket enviado
                ticketReceived = makeRequest(ServerConn);
                if (ticketReceived != null) {
                    // Ticket recibido
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.INFO, String.format(
                                    "Recibido Ticket: \n** %s", ticketReceived.toString()));
                } else {
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.SEVERE, "Fallo de petición");
                }

                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, "Fin comunicación con servidor");
            }
        } catch (IOException ex) {
            // Gestiona tanto el método main() como makeRequest()
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    // OTHER METHODS
    /**
     * Make request
     *
     * @param ServerConn
     * @throws IOException
     */
    private static Ticket makeRequest(Socket ServerConn) throws IOException {

        // Creamos petición mediante objeto TicketAsk
        TicketAsk askTicket = new TicketAsk(
                "Rafa",
                LocalDate.now(),
                TicketType.PENSIONISTAS,
                3);

        Ticket ticketReceived = null;

        // Preparamos flujos entrada/salida (try-with resources)
        try (
                ObjectOutputStream outObject
                = new ObjectOutputStream(ServerConn.getOutputStream());
                ObjectInputStream inputObject
                = new ObjectInputStream(ServerConn.getInputStream());) {

            // Enviamos petición mediante objeto TicketAsk
            outObject.writeObject(askTicket);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Enviados datos petición ticket:\n%s ",
                            askTicket.toString()));

            // Esperamos objeto tipo Ticket del servidor
            ticketReceived = (Ticket) inputObject.readObject();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return ticketReceived;
    }
}
