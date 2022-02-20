/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

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

    static final private int PORT = 2000;
    // MAIN METHOD
    /** 
     * Wait for cient connection, if it is successful, exchanges data.
     * 
     * @param args 
     */
    public static void main(String[] args) {

        // Creamos servidor (try-with-resources)
        try (ServerSocket connTCP = new ServerSocket(PORT)) {
            // Ponemos servidor en espera
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Server will wait a client request in port %d", PORT));

            // Se espera a que un cliente realice una conexión.
            // Al realizarse, devuelve un objeto Socket, a través del cual se 
            // establecerá  el resto de comunicación con el cliente
            Socket connCli = connTCP.accept();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Client request received from ip %s ",
                            connCli.getRemoteSocketAddress().toString()));

            // Gestionamos petición cliente
            manageClientData(connCli);

            // Cerramos Servidor
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, "Cerrando servidor");
            connCli.close();
            // connTCP ya cerrado

        } catch (IOException ex) {
            // Gestiona tanto el método main() como manageClientData()
            Logger.getLogger(TicketsServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private static void manageClientData(Socket connCli) throws IOException {

        try (ObjectInputStream inputObject
                = new ObjectInputStream(connCli.getInputStream());
                ObjectOutputStream outObject
                = new ObjectOutputStream(connCli.getOutputStream())) {

            // Capturamos datos de la petición
            TicketAsk dato = (TicketAsk) inputObject.readObject();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Recibidos datos petición ticket:\n** %s",
                            dato.toString()));

            // Creamos objeto Ticket en base datos enviados
            Ticket ticketToSend = new Ticket(dato);

            // Enviamos objeto Ticket
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
