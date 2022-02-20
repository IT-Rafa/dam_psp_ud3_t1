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
            Socket connCli = connTCP.accept();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Client request received from ip %s ",
                            connCli.getRemoteSocketAddress().toString()));
            // Leemos objeto
            ObjectInputStream inputObject = new ObjectInputStream(connCli.getInputStream());

            TicketAsk dato = (TicketAsk) inputObject.readObject();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Recibida Petición Ticket:\n** " + dato.toString()));

            // Creamos objeto
            Ticket ticketToSend = new Ticket(dato.getUsuario(), dato.getFecha(), calculateImport(dato.getTipo(), dato.getUnidades()));

            // Enviamos objeto
            ObjectOutputStream outObject = new ObjectOutputStream(connCli.getOutputStream());

            outObject.writeObject(ticketToSend);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Enviado Ticket: \n** " + ticketToSend.toString()));

            // Cerramos todo
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, "Cerrando servidor");
            outObject.close();
            inputObject.close();
            connCli.close();
            connTCP.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static BigDecimal calculateImport(TicketType tipo, int unidades) {
        // 10 €, Niños: 3 €, Carnet joven: 5 € y 3ª edad: 4 €
        switch (tipo) {
            case NORMAL:
                return new BigDecimal(10 * unidades);

            case MENORES:
                return new BigDecimal(3 * unidades);

            case JOVENES:
                return new BigDecimal(5 * unidades);

            case PENSIONISTAS:
                return new BigDecimal(4 * unidades);

            default:
                return null;
        }
    }
}
