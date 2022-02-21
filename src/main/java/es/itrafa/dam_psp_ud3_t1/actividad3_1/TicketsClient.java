package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Amusement Park Ticket Client.
 * <p>
 * Establishes communication with a Ticket Server for an amusement park, sends
 * the ticket request and receives the ticket.
 * <p>
 * Extends Thread to avoid BindException when test

 * @author it-ra
 */
public class TicketsClient extends Thread {

    // ATTRIBUTES
    /**
     * Listening Port through which the connection will be established
     */
    final private int SERVERPORT = 2000;
    /**
     * Server IP
     */
    final private String SERVERHOST = "localhost";

    // MAIN METHOD
    /**
     * It tries to connect to the server and, if successful, exchanges data.
     */
    @Override
    public void run() {
        Ticket ticketReceived;
        try {
            // Start communication with Ticket server (try-with-resources)
            try (Socket ServerConn = new Socket(SERVERHOST, SERVERPORT)) {
                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, String.format(
                                "CLIENT: Started communication with server ", SERVERHOST));

                // Manage data exchange. Sshould produce a Ticket object
                ticketReceived = makeRequest(ServerConn);

                if (ticketReceived != null) {
                    // Ticket recibido
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.INFO, String.format(
                                    "CLIENT: Ticket received successfully: \n** %s", ticketReceived.toString()));
                } else {
                    // Failed data exchange
                    Logger.getLogger(TicketsServer.class.getName()).log(
                            Level.SEVERE, "CLIENT: Ticket not received ");
                }

                Logger.getLogger(TicketsServer.class.getName()).log(
                        Level.INFO, "CLIENT: End communication with server");
            }
            // socketExceptions
        } catch (BindException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, "CLIENT: BindException: Probably, the port is in use");
        } catch (ConnectException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, "CLIENT: ConnectException: Probably, no server listening");
        } catch (NoRouteToHostException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, "CLIENT: NoRouteToHostException: Probably, a firewall is intervening or intermediate router is down");
        } catch (PortUnreachableException ex) {
            Logger.getLogger(TicketsClient.class.getName()).
                    log(Level.SEVERE, "CLIENT: PortUnreachableException: an ICMP Port Unreachable message has been received on a connected datagram");
            
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
            Logger.getLogger(TicketsServer.class
                    .getName()).log(
                            Level.INFO, String.format(
                                    "CLIENT: Enviados datos petición ticket:\n%s ",
                                    askTicket.toString()));

            // Get Ticket from Ticket Server
            ticketReceived = (Ticket) inputObject.readObject();
            // streams closed

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsClient.class
                    .getName()).
                    log(Level.SEVERE, null, ex);
        }
        return ticketReceived;
    }
}
