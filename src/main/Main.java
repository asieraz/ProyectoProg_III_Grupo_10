package main;

import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import BD.CargarDatos;
import BD.GestorBD;
import gui.VentanaCarga;
import domain.DataBackupService;

public class Main {

    protected static HashMap<String, String> mapa = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Lanzando...");

        // Backup en segundo plano
        DataBackupService backupService = new DataBackupService();
        backupService.backupAsync("Inicio del programa");

        // Crear carpeta de la DB si no existe
        File carpetaDB = new File("resources/db");
        if (!carpetaDB.exists()) {
            carpetaDB.mkdirs();
        }

        
        // Crear BD si no existe
        File dbFile = new File("resources/db/nueva.db");
        if (!dbFile.exists()) {
            GestorBD.crearBBDD();
            CargarDatos.cargar(); // Insertar datos iniciales
        }

        
        // Cargar CSV externo de forma segura
        cargarDatosCSV();

        // Lanzar GUI
        SwingUtilities.invokeLater(() -> new VentanaCarga());

        System.out.println("Aplicación iniciada correctamente.");
    }

    
    public static void cargarDatosCSV() {
        // Intentar leer desde el archivo externo primero
        File archivoExterno = new File("resources/data/personas.csv");

        try (Scanner sc = archivoExterno.exists()
                ? new Scanner(archivoExterno, StandardCharsets.UTF_8)
                : getScannerDesdeJar("/data/personas.csv")) {

            if (sc == null) {
                System.out.println("Archivo personas.csv no encontrado!");
                return;
            }

            
            
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] campos = linea.split(";");
                if (campos.length >= 5) {
                    mapa.put(campos[3], campos[4] + "\n");
                }
            }

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
 
    private static Scanner getScannerDesdeJar(String rutaRecurso) {
        InputStream is = Main.class.getResourceAsStream(rutaRecurso);
        if (is == null) return null;
        return new Scanner(is, StandardCharsets.UTF_8);
    }
}

