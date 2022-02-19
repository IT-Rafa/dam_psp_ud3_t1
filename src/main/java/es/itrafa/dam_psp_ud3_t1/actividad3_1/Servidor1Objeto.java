/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud3_t1.actividad3_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author it-ra
 */
public class Servidor1Objeto {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 6000;
        // Creamos servidor
        ServerSocket servidor = new ServerSocket(port);
        System.out.println("Servidor listo en puerto" + port);

        // Ponemos servidor en espera
        Socket cliente = servidor.accept();

        // Pendiente cliente
        // El cliente (Cliente1Objeto)
        // 1º espera un objeto del servidor
        // 2º lo modifica
        // 3º lo reenvia al servidor
        
        
        // 1º Preparamos envío objeto
        // Preparamos flujo salida objetos
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        // Creamos objeto
        Persona per = new Persona("Juan", 20);

        // Enviamos objeto
        outObjeto.writeObject(per);
        System.out.println("Envio" + per.getNombre() + "*" + per.getEdad());
        // 2º El cliente recibe, modifica y nos envia el objeto modificado

        // 3º Preparamos recepción del objeto
        // Preparamos flujo entrada objetos
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        // Leemos objeto
        Persona dato = (Persona) inObjeto.readObject();
        System.out.println("Recibo" + dato.getNombre() + "*" + dato.getEdad());

        // Cerramos todo
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();

    }
}
