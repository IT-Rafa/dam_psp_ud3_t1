/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_2;

/**
 *
 * @author it-ra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MultiCastServer().start();

        MultiCastClient c1 = new MultiCastClient("Cliente 1");
        c1.start();
        
        MultiCastClient c2 = new MultiCastClient("Cliente 2");
        c2.start();
        
    }

}
