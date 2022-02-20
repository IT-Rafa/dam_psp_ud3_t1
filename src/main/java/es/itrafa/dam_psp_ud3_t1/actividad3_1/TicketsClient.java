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

    public static void main(String[] args) {
        try {
            String Host = "localhost";
            int puerto = 2000;
            // Inicimos comunicación con servidor
            Socket ServerConn = new Socket(Host, puerto);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format("Iniciada comunicación con servidor ", Host));
            
            // Gestionamos comunicación con servidor
            serverSendData(ServerConn);
            
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, "Fin comunicación con servidor");
            
            ServerConn.close();
        } catch (IOException ex) {
            Logger.getLogger(TicketsClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void serverSendData(Socket ServerConn) throws IOException {
        try {
            TicketAsk askTicket = new TicketAsk("Rafa", 3, LocalDate.now(), TicketType.PENSIONISTAS);

            ObjectOutputStream outObject = new ObjectOutputStream(ServerConn.getOutputStream());
            outObject.writeObject(askTicket);
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format("Enviados datos petición ticket:\n%s ", askTicket.toString()));

            // Esperamos objeto tipo Ticket del servidor
            ObjectInputStream inputObject = new ObjectInputStream(ServerConn.getInputStream());
            Ticket dato = (Ticket) inputObject.readObject();
            Logger.getLogger(TicketsServer.class.getName()).log(
                    Level.INFO, String.format(
                            "Recibido Ticket: \n** %s", dato.toString()));

            inputObject.close();
            outObject.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
