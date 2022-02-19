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

/**
 *
 * @author it-ra
 */
public class TicketsClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String Host = "localhost";
        int puerto = 2000;

        System.out.println("Programa cliente iniciado ....");

        Socket ServerConn = new Socket(Host, puerto);

        ObjectOutputStream outObject = new ObjectOutputStream(ServerConn.getOutputStream());
        TicketAsk askTicket = new TicketAsk("Rafa", 3, LocalDate.now(), TicketType.Tercera_edad);
        outObject.writeObject(askTicket);
        
        // Esperamos objeto tipo Ticket del servidor
        ObjectInputStream inputObject = new ObjectInputStream(ServerConn.getInputStream());

        Ticket dato = (TicketAsk) perEnt.readObject();
        System.out.println("Recibo" + dato.getNombre() + "*" + dato.getEdad());

        //Modifico el objeto
        dato.setNombre("Juan Ramos");
        dato.setEdad(22);

        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        perSal.writeObject(dato);
        System.out.println("Envio" + dato.getNombre() + "*" + dato.getEdad());

        perEnt.close();
        perSal.close();
        cliente.close();

    }
}
