/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author it-ra
 */
public class TicketsClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String Host = "localhost";
        int puerto = 2000;

        Logger.getLogger(TicketsServer.class.getName()).log(
                Level.INFO, String.format("Iniciada comunicación con servidor ", Host));
        Socket ServerConn = new Socket(Host, puerto);

        TicketAsk askTicket = new TicketAsk("Rafa", 3, LocalDate.now(), TicketType.PENSIONISTAS);

        ObjectOutputStream outObject = new ObjectOutputStream(ServerConn.getOutputStream());
        outObject.writeObject(askTicket);
        Logger.getLogger(TicketsServer.class.getName()).log(
                Level.INFO, String.format("Iniciada petición de entradas:\n%s ", askTicket.toString()));

        // Esperamos objeto tipo Ticket del servidor
        ObjectInputStream inputObject = new ObjectInputStream(ServerConn.getInputStream());
        Ticket dato = (Ticket) inputObject.readObject();
        Logger.getLogger(TicketsServer.class.getName()).log(
                Level.INFO, String.format(
                        "Recibido Ticket: \n** %s", dato.toString()));

        Logger.getLogger(TicketsServer.class.getName()).log(
                Level.INFO, "Fin comunicación con servidor");
        inputObject.close();
        outObject.close();
        ServerConn.close();

    }
}
