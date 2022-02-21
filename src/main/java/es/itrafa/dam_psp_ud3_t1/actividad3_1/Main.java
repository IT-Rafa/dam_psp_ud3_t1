/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author it-ra
 */
public class Main {

    /**
     * Test Server a client 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TicketsServer().start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


        new TicketsClient().start();


    }
}
