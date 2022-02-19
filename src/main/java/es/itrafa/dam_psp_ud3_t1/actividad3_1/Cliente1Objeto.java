/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author it-ra
 */
public class Cliente1Objeto {
        public static void main(String[] args) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int puerto = 6000;
        System.out.println("Programa cliente iniciado ....");
        
        Socket cliente = new Socket (Host,puerto);
        
        ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
        
        Persona dato = (Persona) perEnt.readObject();
        System.out.println("Recibo"+ dato.getNombre() +"*" + dato.getEdad());
        
        //Modifico el objeto
        dato.setNombre("Juan Ramos");
        dato.setEdad(22);
        
        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        
        perSal.writeObject(dato);
        System.out.println("Envio"+ dato.getNombre() +"*" + dato.getEdad());
        
        perEnt.close();
        perSal.close();
        cliente.close();
        
        
    }
}
