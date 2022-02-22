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
public class MultiCastClient extends Thread {

    private String clientName;
    private final int port = 4000;
    private final String host = "235.1.1.1";

    public MultiCastClient(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void run() {

        try {

            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(InetAddress.getByName(host));
            int i = 100;
            while (true) {
                byte[] b = new byte[100];
                DatagramPacket dgram = new DatagramPacket(b, b.length);
                socket.receive(dgram); // Se bloquea hasta que llegue un datagrama

                String msg = new String(dgram.getData());
                Logger.getLogger(MultiCastServer.class.getName()).log(
                        Level.INFO, String.format(
                                "%s recibió mensaje desde %s; de tamaño %s: %n%s",
                                this.clientName, dgram.getAddress(), dgram.getLength(), msg));
                if (msg.startsWith("Desconexión servidor                                                                                ")) {
                    Logger.getLogger(MultiCastServer.class.getName()).log(
                            Level.INFO, String.format(
                                    "%s CERRADO", this.clientName));
                    socket.close();
                    return;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(MultiCastClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
