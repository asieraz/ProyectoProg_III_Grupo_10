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
//import BD.ConfigManager;
import domain.DataBackupService;

public class Main {
	
	
	
	protected static HashMap<String, String> mapa = new HashMap<>();

    public static void main(String[] args) {
    	
    	System.out.println("Lanzando...");
    	
    	// Configuración --> Salta IOException
//        new ConfigManager();
//        System.out.println("ConfigManager cargado.");

        // Backup en segundo plano
        DataBackupService backupService = new DataBackupService();
        backupService.backupAsync("Inicio del programa");
   
    	//cargarDatosCSV();
        //System.out.println(mapa);
    	
        // Cargar parámetros y driver
        new GestorBD();

        
        File dbFile = new File("resources/db/nueva.db");

        if (!dbFile.exists()) {
            GestorBD.crearBBDD();  
            CargarDatos.cargar();  // Si quieres insertar datos iniciales
        }
		
        // Prueba de funcionamiento
//        System.out.println("Usuarios en la BD:");
//        GestorBD.obtenerUsuarios();
						
        SwingUtilities.invokeLater(() -> new VentanaCarga());
        System.out.println("Aplicación iniciada correctamente.");
		
		//gestorBD.borrarBBDD();
    	
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
