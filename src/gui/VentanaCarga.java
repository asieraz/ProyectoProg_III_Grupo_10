package gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class VentanaCarga extends JFrame {

    private static final long serialVersionUID = 1L;
    private int cont = 0;
    private JProgressBar jProgressBar;
    @SuppressWarnings("unused")
	private JLabel jLabel;
    protected static HashMap<String, String> mapa = new HashMap<>();
    
    
    public static void cargarDatosCSV(){
    	try (Scanner sc = new Scanner(
    	        VentanaCarga.class.getClassLoader().getResourceAsStream("data/personas.csv"))) {
    	    while(sc.hasNextLine()) {
    	        String linea = sc.nextLine();
    	        String[] campos = linea.split(";");
    	        mapa.put(campos[3], campos[4] + "\n");
    	    }
    	    
    	    sc.close();
    	    
    	    } catch (Exception e) {
    	    e.printStackTrace();
    	}

    	
    	
    }

    public VentanaCarga() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cargar Pantalla");
        setSize(300, 250);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear y configurar la barra de progreso
       

        
        ImageIcon icon = new ImageIcon(
        	    VentanaCarga.class.getClassLoader().getResource("img/logoSuper.png")
        	);
        JLabel jImagen = new JLabel(icon, JLabel.CENTER);
        add(jImagen, BorderLayout.NORTH);

       
        JLabel jTexto = new JLabel("Cargando...", JLabel.CENTER);
        add(jTexto, BorderLayout.CENTER);

  
        jProgressBar = new JProgressBar(0, 100);
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(cont);
        add(jProgressBar, BorderLayout.SOUTH);

        setVisible(true);

     
        Thread hilo = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {

                    int value = i;

                    // Actualizaciones de UI siempre en Swing EDT
                    SwingUtilities.invokeLater(() -> {
                        jProgressBar.setValue(value);
                    });

                    Thread.sleep(25);
                }

                // Abrir siguiente ventana al terminar
                SwingUtilities.invokeLater(() -> {
                    new VentanaInicio();
                    dispose();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        hilo.start();
    }
    

    public static void main(String[] args) {
        new VentanaCarga();
        cargarDatosCSV();
        System.out.println(mapa);
    }
}
