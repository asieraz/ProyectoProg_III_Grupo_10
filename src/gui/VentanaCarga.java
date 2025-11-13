package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class VentanaCarga extends JFrame {

    private static final long serialVersionUID = 1L;
    private int cont = 0;
    private JProgressBar jProgressBar;
    private JLabel jLabel;

    public VentanaCarga() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cargar Pantalla");
        setSize(240, 55);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear y configurar la barra de progreso
        jProgressBar = new JProgressBar(0, 100);
        jProgressBar.setValue(cont);
        jProgressBar.setStringPainted(true);
        add(jProgressBar, BorderLayout.NORTH);

        setVisible(true);


        // HILO PARA ACTUALIZAR LA BARRA
       
        Thread hiloCarga = new Thread(() -> {
            try {
                while (cont <= 100) {
                    jProgressBar.setValue(cont);
                    Thread.sleep(25); // velocidad de carga
                    cont++;

                    if (cont > 100) {
                        break;
                    }
                }

                // Cuando llega al 100%, abrir VentanaInicio
                javax.swing.SwingUtilities.invokeLater(() -> {
                    new VentanaInicio();
                    dispose();
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        hiloCarga.start();
    }

    public static void main(String[] args) {
        new VentanaCarga();
    }
}
