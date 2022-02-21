/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Amusement Park Ticket Server.
 * <p>
 * Starts a server that receives requests from clients to buy tickets, through a
 * TicketAsk object for an amusement park, and returns the amount of the tickets
 * through a Ticket object. Use the 2000 port
 *
 * @author it-ra
 */
public class TicketsServer {

    // ATTRIBUTES
    /**
     * Port through which the connection will be established
     */
    static final private int PORT = 2000;

    // MAIN METHOD
    /**
     * Wait for cient connection, if it is successful, exchanges data.
     *
     * @param args Arguments input
     */
    public static void main(String[] args) {

        // Server creation indicating port (try-with-resources)
        try (ServerSocket connTCP = new ServerSocket(PORT)) {
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Server will wait a client request in port %d", PORT));

            // When happens, store the new connnection, by a new port,
            // to exchage of data with client
            Socket connCli = connTCP.accept();

            // A client request is received 
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Client request received from ip %s ",
                            connCli.getRemoteSocketAddress().toString()));

            // Manage client request
            manageClientData(connCli);

            // Closing
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, "Cerrando servidor");
            // Close client data connection
            connCli.close();
            // connTCP ya cerrado

        } catch (IOException ex) {
            // Handles both the main() and manageClientData() methods
            Logger.getLogger(TicketsServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Controls the exchange of data between client and server from the server
     * side.
     *
     * @param connCli Connection to exchange data with client
     * @throws IOException
     */
    private static void manageClientData(Socket connCli) throws IOException {

        try (ObjectInputStream inputObject
                = new ObjectInputStream(connCli.getInputStream());
                ObjectOutputStream outObject
                = new ObjectOutputStream(connCli.getOutputStream())) {

            // Get data of client request
            TicketAsk dato = (TicketAsk) inputObject.readObject();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Recibidos datos petición ticket:\n** %s",
                            dato.toString()));

            // Prepare Ticket
            Ticket ticketToSend = new Ticket(dato);

            // Send Ticket
            outObject.writeObject(ticketToSend);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Enviado Ticket: \n** %s",
                            ticketToSend.toString()));

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsServer.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
