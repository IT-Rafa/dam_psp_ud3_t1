package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains main method to test exercise 3.1
 * 
 * @author it-ra
 */
public class Main {
    /**
     * Run server and client as threads to avoid BindException fail
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init server
        new TicketsServer().start();
        // init client
        new TicketsClient().start();
    }
}
