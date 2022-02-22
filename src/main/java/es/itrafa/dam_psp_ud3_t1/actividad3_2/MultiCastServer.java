package es.itrafa.dam_psp_ud3_t1.actividad3_2;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * School MultiCast Server.
 * <p>
 * Starts a MultiCast server that sends multiple messages to all clients that join its
 * group.
 *
 * @author it-ra
 */
public class MultiCastServer extends Thread {

    /**
     * Port through which the connection will be established
     */
    private final int port = 4000;
    /**
     * Address which the connection will be established
     */
    private final String host = "235.1.1.1";
    /**
     * time in seconds from disconnection warning and disconnection
     */
    private final int secsToOff = 5;

    /**
     * Init server socket
     */
    @Override
    public void run() {
        // Server creation indicating port (try-with-resources)
        try (MulticastSocket socket = new MulticastSocket(port)) {
            // Messages list to send
            // Note, The last one indicate the close of server.
            // If it changes, must be adapted in client program
            String[] messages = {
                "El instituto va a cerrar.",
                "La temperatura exterior es de",
                "Asientos disponibles en la biblioteca:",
                String.format("Este servicio se apagará en %d segundos", this.secsToOff)
            };
            // vars to send datagram packet
            byte[] b;
            DatagramPacket dpacket;

            // Por cada mensaje
            for (String message : messages) {
                // mensajes que dependen de  datos externos (inventados)
                if (message.equalsIgnoreCase(messages[1])) {
                    message = String.format("%s %d%s", messages[1], getTemp(), "º");
                } else if (message.equalsIgnoreCase(messages[2])) {
                    message = String.format("%s %d", messages[2], getLibFreeChairs());
                }
                // store message as byte array
                b = message.getBytes();
                // prepare message as datagram to send
                dpacket = new DatagramPacket(b, b.length, InetAddress.getByName(host), port);
                Logger.getLogger(MultiCastServer.class.getName()).log(
                        Level.INFO, String.format(
                                "Servidor envía mensaje a %s:%d, de %d bytes%n** MENSAJE ENVIADO POR SERV.: %s",
                                dpacket.getAddress(), dpacket.getPort(), b.length, message));
                // send datagram
                socket.send(dpacket);
            }
            // when last one was sent (disconnect server warning)
            // wait to give clients time to disconnect  
            Thread.sleep(this.secsToOff * 1000);

            // server closing (try-with-resources)
            Logger.getLogger(MultiCastServer.class.getName()).log(
                    Level.INFO, "SERVER OFF");

            // socket Exceptions
        } catch (BindException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, "SERVER: BindException: Probably, the port is in use");
        } catch (ConnectException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, "SERVER: ConnectException: Probably, no server listening");
        } catch (NoRouteToHostException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, "SERVER: NoRouteToHostException: Probably, a firewall is intervening or intermediate router is down");
        } catch (PortUnreachableException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, "SERVER: PortUnreachableException: an ICMP Port Unreachable message has been received on a connected datagram");
            // other exceptions
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * To replace if it was real
     * @return the external temperature in cc.
     */
    private int getTemp() {
        return 18;
    }
    /**
     * To replace if it was real
     * 
     * @return cant of free chairs in the library.
     */
    private int getLibFreeChairs() {
        return 7;
    }
}
