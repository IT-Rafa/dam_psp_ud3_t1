/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author it-ra
 */
public class MultiCastServer extends Thread {

    private final int port = 4000;
    private final String host = "235.1.1.1";

    @Override
    public void run() {

        try {
            MulticastSocket socket = new MulticastSocket(port);
            String[] messages = {"Cierre instituto", "Temp. exterior", "Asientos biblioteca", "Desconexión servidor"};

            byte[] b;
            DatagramPacket dgram;

            for (String message : messages) {
                b = message.getBytes();
                dgram = new DatagramPacket(b, b.length, InetAddress.getByName(host), port);
                Logger.getLogger(MultiCastServer.class.getName()).log(
                        Level.INFO, String.format(
                                "Servidor envía mensaje a %s:%d, de %d bytes%nMensaje: %s",
                                dgram.getAddress(), dgram.getPort(), b.length, message));
                socket.send(dgram);
                Thread.sleep(1000);
            }
            
            Logger.getLogger(MultiCastServer.class.getName()).log(
                        Level.INFO, "SERVIDOR CERRADO");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
