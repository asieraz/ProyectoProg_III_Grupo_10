package main;

import javax.swing.SwingUtilities; 

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import com.formdev.flatlaf.themes.FlatMacLightLaf;

import BD.CargarDatos;
import BD.GestorBD;
import gui.VentanaCarga; 

public class Main {
	
	protected static HashMap<String, String> mapa = new HashMap<>();

    public static void main(String[] args) {
    	
    	System.out.println("Lanzando...");
   
    	cargarDatosCSV();
        System.out.println(mapa);
    	
    	
    	GestorBD gestorBD = new GestorBD();
		
		//GestorBD.crearBBDD();
		
		//CargarDatos.cargar();
						
        SwingUtilities.invokeLater(() -> new VentanaCarga());
		
		//gestorBD.borrarBBDD();
    	
        
//    	try {
//            UIManager.setLookAndFeel(new FlatMacLightLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            System.err.println("ERROR: No se ha encontrado la librería de Look-and-Feel FlatLAF. Utilizando el LAF " +
//                    "por defecto.");
//        }
    }
    
    public static void cargarDatosCSV(){
    	File f = new File("resources/data/personas.csv");
    	try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos =  linea.split(";");
			
				
					
					mapa.put(campos[3], campos[4]+ "\n");
					
					
					
				}
			
				 	
			sc.close();
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }


}
