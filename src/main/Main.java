package main;

import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.VentanaCarga; // 👈 importa tu clase VentanaCarga

public class Main {

    public static void main(String[] args) {
    	System.out.println("Lanzando...");
        
//    	try {
//            UIManager.setLookAndFeel(new FlatMacLightLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            System.err.println("ERROR: No se ha encontrado la librería de Look-and-Feel FlatLAF. Utilizando el LAF " +
//                    "por defecto.");
//        }
        SwingUtilities.invokeLater(() -> new VentanaCarga());
    }

}
