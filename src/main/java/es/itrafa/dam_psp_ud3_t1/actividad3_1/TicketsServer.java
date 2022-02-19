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
            ServerSocket ticketServer = new ServerSocket(port);

            // Ponemos servidor en espera
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format("Server waiting in port %d", port));
            Socket ticketCli = ticketServer.accept();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Client request received from ip %s ",
                            ticketCli.getRemoteSocketAddress().toString()));


            // Pendiente cliente
            // El cliente (Cliente1Objeto)
            // 1º espera un objeto del servidor
            // 2º lo modifica
            // 3º lo reenvia al servidor
            // 1º Preparamos envío objeto
            // Preparamos flujo salida objetos
            ObjectOutputStream outObjeto = new ObjectOutputStream(ticketCli.getOutputStream());

            // Creamos objeto
            Ticket per = new Ticket("Juan", 20);

            // Enviamos objeto
            outObjeto.writeObject(per);
            System.out.println("Envio" + per.getNombre() + "*" + per.getEdad());
            // 2º El cliente recibe, modifica y nos envia el objeto modificado

            // 3º Preparamos recepción del objeto
            // Preparamos flujo entrada objetos
            ObjectInputStream inObjeto = new ObjectInputStream(ticketCli.getInputStream());
            // Leemos objeto
            Ticket dato = (Ticket) inObjeto.readObject();
            System.out.println("Recibo" + dato.getNombre() + "*" + dato.getEdad());

            // Cerramos todo
            inObjeto.close();
            outObjeto.close();
            ticketCli.close();
            ticketServer.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(TicketsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
