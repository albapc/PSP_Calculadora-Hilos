package calculadorahilos;

import javax.swing.JOptionPane;

/**
 *
 * @author Alba
 */
public class CalculadoraHilos {

    public static void main(String[] args) {
        Servidor sv = new Servidor();
        sv.conexion();
        int nHilos = Integer.parseInt(JOptionPane.showInputDialog("Introducir número de hilos:"));
        for (int i = 0; i < nHilos; i++) {
            Cliente cli = new Cliente();
            cli.start();
            sv.read();
        }
        sv.close();
    }
}
