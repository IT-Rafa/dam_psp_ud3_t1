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
            System.out.println("Recibida Petición Ticket: " + dato.toString());

            // Creamos objeto
            Ticket per = new Ticket(dato.getUsuario(), dato.getFecha(), calculateImport(dato.getTipo(), dato.getUnidades()));

            // Enviamos objeto
            ObjectOutputStream outObject = new ObjectOutputStream(connCli.getOutputStream());

            outObject.writeObject(per);
            System.out.println("Enviado Ticket: " + per.toString());

            // Cerramos todo
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

    private static int calculateImport(TicketType tipo, int unidades) {
        // 10 €, Niños: 3 €, Carnet joven: 5 € y 3ª edad: 4 €
        switch (tipo) {
            case NORMAL:
                return unidades * 10;

            case MENORES:
                return unidades * 3;

            case JOVENES:
                return unidades * 5;
                
            case PENSIONISTAS:
                return unidades * 4;
                
            default:
                return -1;
        }
    }
}
