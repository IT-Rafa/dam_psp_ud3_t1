package es.itrafa.dam_psp_ud3_t1.actividad3_1.client;

import es.itrafa.dam_psp_ud3_t1.actividad3_1.Ticket;
import es.itrafa.dam_psp_ud3_t1.actividad3_1.TicketAsk;
import es.itrafa.dam_psp_ud3_t1.actividad3_1.TicketType;
import es.itrafa.dam_psp_ud3_t1.actividad3_1.server.TicketsServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
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
    /**
     * Listening Port through which the connection will be established
     */
    static final private int SERVERPORT = 2000;
    /**
     * Server IP
     */
    static final private String SERVERHOST = "localhost";

    // MAIN METHOD
    /**
     * It tries to connect to the server and, if successful, exchanges data.
     *
     * @param args Arguments input
     */
    public static void run() {
        Ticket ticketReceived;
        try {
            // Start communication with Ticket server (try-with-resources)
            try (Socket ServerConn = new Socket(SERVERHOST, SERVERPORT)) {
                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, String.format(
                                "Iniciada comunicación con servidor ", SERVERHOST));

                // Manage data exchange. Sshould produce a Ticket object
                ticketReceived = makeRequest(ServerConn);

                if (ticketReceived != null) {
                    // Ticket recibido
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.INFO, String.format(
                                    "Recibido Ticket: \n** %s", ticketReceived.toString()));
                } else {
                    // Failed data exchange
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.SEVERE, "Fallo de petición");
                }

                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, "Fin comunicación con servidor");
            }
        } catch (ConnectException ex) {
            // Handles both the main() and makeRequest() methods
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // Handles both the main() and makeRequest() methods
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    // OTHER METHODS
    /**
     * Controls the exchange of data between client and server from the client
     * side.
     *
     * @param ServerConn
     * @return ticket with purchase information
     * 
     * @throws IOException
     */
    private static Ticket makeRequest(Socket ServerConn) throws IOException {
        // Var to capture Ticket object
        Ticket ticketReceived = null;

        // Creamos petición mediante objeto TicketAsk
        TicketAsk askTicket = new TicketAsk(
                "Rafa",
                LocalDate.now(),
                TicketType.PENSIONISTAS,
                3);

        // Prepare input and output streams (try-with resources)
        try (
                ObjectOutputStream outObject
                = new ObjectOutputStream(ServerConn.getOutputStream());
                ObjectInputStream inputObject
                = new ObjectInputStream(ServerConn.getInputStream());) {

            // Send request to Ticket Server
            outObject.writeObject(askTicket);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Enviados datos petición ticket:\n%s ",
                            askTicket.toString()));

            // Get Ticket from Ticket Server
            ticketReceived = (Ticket) inputObject.readObject();
            // streams closed

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return ticketReceived;
    }
}
