package es.itrafa.dam_psp_ud3_t1.actividad3_2;

/**
 * Contains main method to test exercise
 * 
 * @author it-ra
 */
public class Main {

    /**
     * Run server and several clients as threads to avoid BindException fail
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init server
        new MultiCastServer().start();
        
        // init clients
        int cantClients = 3;
        for (int i = 1; i <= cantClients; i++) {
            new MultiCastClient(i).start();
        }

    }
}
