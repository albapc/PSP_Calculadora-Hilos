package calculadorahilos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alba
 */
public class Cliente extends Thread {

    private Socket clienteSocket;
    private InetSocketAddress addr;
    private InputStream is;
    private OutputStream os;

    public void run() {
        Cliente cli = new Cliente();
        cli.conexion();
        cli.enviarMensaje();
        cli.close();
    }

    public void conexion() {
        try {
            System.out.println("Creando socket cliente");
            clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");

            //en máquinas distintas hay que añadir la ip del servidor en ambas
            addr = new InetSocketAddress("localhost", 5555);
            clienteSocket.connect(addr);

            is = clienteSocket.getInputStream();
            os = clienteSocket.getOutputStream();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensaje() {
        try {
            System.out.println("Enviando mensaje");
            String op1 = JOptionPane.showInputDialog("Introduce un número:");
            String op2 = JOptionPane.showInputDialog("Introduce otro número:");
            String ope = JOptionPane.showInputDialog("Indicar operación:\n"
                    + "1: Suma\n2: Resta\n3: Producto\n4: División");
            String mensaje = op1 + "," + op2 + "," + ope + ",";
            os.write(mensaje.getBytes());
            System.out.println("Mensaje enviado");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            System.out.println("Cerrando el socket cliente");
            clienteSocket.close();
            System.out.println("Terminado");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
