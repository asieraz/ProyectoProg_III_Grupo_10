package BD;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import domain.Departamento;
import domain.Producto;
import domain.Proveedor;

public class CargarDatos {
	
	public static ArrayList<Producto> cargarProductos() {
		String rutaArchivo = "resources/data/productos.csv";
	    ArrayList<Producto> productos = new ArrayList<>();

	    try {
	        FileInputStream fis = new FileInputStream(rutaArchivo);
	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        BufferedReader br = new BufferedReader(isr);

	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length == 5) {
	                int id = Integer.parseInt(partes[0].trim());
	                String nombre = partes[1].trim();
	                float precio = Float.parseFloat(partes[2].trim());
	                int cantidad = Integer.parseInt(partes[3].trim());
	                int categoria = Integer.parseInt(partes[4].trim());

	                productos.add(new Producto(id, nombre, precio, cantidad, categoria));
	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return productos;
	}
	
	protected int idDepartamento;
	protected String nombreDepartamento;
	int NSSJefe;
	
	
	public static ArrayList<Departamento> cargarDepartamentos() {
		String rutaArchivo = "resources/data/departamentos.csv";
	    ArrayList<Departamento> departamentos = new ArrayList<>();

	    try {
	        FileInputStream fis = new FileInputStream(rutaArchivo);
	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        BufferedReader br = new BufferedReader(isr);

	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length == 3) {
	                int id = Integer.parseInt(partes[0].trim());
	                String nombre = partes[1].trim();
	                int NSSJefe = Integer.parseInt(partes[2].trim());

	                departamentos.add(new Departamento(id, nombre, NSSJefe));
	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return departamentos;
	}
	protected int codProveedor;
	protected String nombreProveedor;
	protected int codPostal;
	protected String contrasena;
	
	
	public static ArrayList<Proveedor> cargarProveedores() {
		String rutaArchivo = "resources/data/proveedores.csv";
	    ArrayList<Proveedor> proveedores = new ArrayList<>();

	    try {
	        FileInputStream fis = new FileInputStream(rutaArchivo);
	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        BufferedReader br = new BufferedReader(isr);

	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length == 3) {
	                int cod = Integer.parseInt(partes[0].trim());
	                String nombre = partes[1].trim();
	                int codP = Integer.parseInt(partes[2].trim());
	                String contraseña = partes[3].trim();

	                proveedores.add(new Proveedor(cod, nombre, codP, contraseña));
	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return proveedores;
	}
	
	
	

}
