package calculadorahilos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alba
 */
public class Servidor {

    ServerSocket serverSocket;
    InetSocketAddress addr;
    OutputStream os;
    InputStream is;
    Socket newSocket;

    public void conexion() {
        try {
            System.out.println("Creando socket servidor");
            serverSocket = new ServerSocket();
            System.out.println("Realizando el bind");
            addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() {
        try {
            String[] mensaje;

            System.out.println("Aceptando conexiones");
            newSocket = serverSocket.accept();
            System.out.println("Conexión recibida");

            is = newSocket.getInputStream();
            os = newSocket.getOutputStream();

            byte[] msg = new byte[25];
            is.read(msg);
            mensaje = new String(msg).split(",");
            System.out.println("Número 1: " + mensaje[0]);
            System.out.println("Número 2: " + mensaje[1]);
            System.out.println("Tipo de operación: " + mensaje[2]);

            int ope = Integer.parseInt(mensaje[2]);

            switch (ope) {
                case 1:
                    System.out.println(mensaje[0] + " + " + mensaje[1]);
                    System.out.println("Resultado: " + (Integer.valueOf(mensaje[0]) + Integer.valueOf(mensaje[1])));
                    break;
                case 2:
                    System.out.println(mensaje[0] + " - " + mensaje[1]);
                    System.out.println("Resultado: " + (Integer.valueOf(mensaje[0]) - Integer.valueOf(mensaje[1])));
                    break;
                case 3:
                    System.out.println(mensaje[0] + " * " + mensaje[1]);
                    System.out.println("Resultado: " + (Integer.valueOf(mensaje[0]) * Integer.valueOf(mensaje[1])));
                    break;
                case 4:
                    System.out.println(mensaje[0] + " / " + mensaje[1]);
                    System.out.println("Resultado: " + (Integer.valueOf(mensaje[0]) / Integer.valueOf(mensaje[1])));
                    break;
                default:
                    System.out.println("Operación no válida");
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            System.out.println("Cerrando el nuevo socket");
            newSocket.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
            System.out.println("Terminado");
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
