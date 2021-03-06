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
 * School MultiCast Client.
 * <p>
 * Starts a MultiCast client that receives the messages from multicast group.
 *
 * @author it-ra
 */
public class MultiCastClient extends Thread {

    /**
     * identify client
     */
    private final int idCli;
    /**
     * Port through which the connection will be established
     */
    private final int port = 4000;
    /**
     * Address which the connection will be established
     */
    private final String host = "235.1.1.1";
    private final int MAXSIZE_BYTES = 100;

    public MultiCastClient(int idCli) {
        this.idCli = idCli;
    }

    /**
     *
     */
    @Override
    public void run() {

        try {
            // Create sockte for connection
            MulticastSocket socket = new MulticastSocket(port);
            // Join to server group
            socket.joinGroup(InetAddress.getByName(host));

            // Prepare to receives messages
            while (true) {
                // vars to receive new datagram packet
                // if it´s shared the old bytes don´t remove (init out of while)
                byte[] b = new byte[MAXSIZE_BYTES];
                DatagramPacket dgram = new DatagramPacket(b, b.length);

                // Waiting for a new datagram
                socket.receive(dgram);

                // new datagram is receive
                // convert to string
                String msg = new String(dgram.getData());
                Logger.getLogger(MultiCastServer.class.getName()).log(
                        Level.INFO, String.format(
                                "Cliente %d recibió mensaje desde %s de %s bytes de tamaño: "
                                + "%n** MENSAJE RECIBIDO POR CLI_%d: %s",
                                this.idCli, dgram.getAddress(),
                                dgram.getLength(), this.idCli, msg));

                // if it´s disconnect server warning. close client connection
                if (msg.contains("Este servicio se apagará")) {
                    Logger.getLogger(MultiCastServer.class.getName()).log(
                            Level.INFO, String.format(
                                    "CLIENTE %d DESCONECTADO", this.idCli));
                    socket.close();
                    return;
                }
            }

            // socket exceptions
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
 
            // Other exceptions
        } catch (IOException ex) {
            // Handles both the main() and makeRequest() methods
            Logger.getLogger(MultiCastServer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }
}
