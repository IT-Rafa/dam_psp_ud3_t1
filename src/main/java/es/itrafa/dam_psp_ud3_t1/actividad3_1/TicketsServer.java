/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author it-ra
 */
public class TicketsServer {

    public static void main(String[] args) {

        try {
            int port = 2000;
            
            // Creamos servidor
            ServerSocket connTCP = new ServerSocket(port);

            // Ponemos servidor en espera
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format("Server waiting in port %d", port));

            // Recibimos intento comunicación de cliente
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
            connTCP.close();

        } catch (IOException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void manageClientData(Socket connCli) throws IOException {

        try {
            // Capturamos datos de la petición
            ObjectInputStream inputObject = new ObjectInputStream(connCli.getInputStream());
            
            TicketAsk dato = (TicketAsk) inputObject.readObject();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Recibidos datos petición ticket:\n** " + dato.toString()));
            
            // Creamos objeto Ticket en base datos enviados

            Ticket ticketToSend = new Ticket(dato);
            
            // Enviamos objeto Ticket
            ObjectOutputStream outObject = new ObjectOutputStream(connCli.getOutputStream());
            outObject.writeObject(ticketToSend);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Enviado Ticket: \n** " + ticketToSend.toString()));
            outObject.close();
            inputObject.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
