package BD;

import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

import domain.Departamento;
import domain.Producto;
import domain.Proveedor;
import domain.Usuario;

public class CargarDatos {

	public static void cargar() {
		cargarUsuarios();
		cargarProductos();
		cargarDepartamentos();
		cargarProveedores();
	}
    
    public static void cargarUsuarios(){
    	String rutaArchivo = "resources/data/usuarios.csv";
    	 try {
 	        FileInputStream fis = new FileInputStream(rutaArchivo);
 	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
 	        BufferedReader br = new BufferedReader(isr);

 	        String linea;
 	        while ((linea = br.readLine()) != null) {
 	            String[] partes = linea.split(",");
 	            // corrección 5 -> 6 para no romper la carga
 	            if (partes.length == 6) {
 	                int codigo = Integer.parseInt(partes[0].trim());
 	                String username = partes[1].trim();
 	                LocalDate creacionCuenta = LocalDate.parse(partes[2].trim());
 	                String pais = partes[3].trim();
 	                String foto = partes[4].trim();
 	                String contrasena = partes[5].trim();

 	                Usuario usuario = new Usuario(codigo, username, creacionCuenta, pais, foto, contrasena);
 	                GestorBD.insertarUsuario(usuario);
 	            }
 	            
 	        }

 	        br.close();
 	    } catch (IOException e) {
 	        System.out.println("Error al leer el archivo: " + e.getMessage());
 	    }

    	
    }
    
	
	public static void cargarProductos() {
		String rutaArchivo = "resources/data/productos.csv";

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

	                
	                Producto producto = new Producto(id, nombre, precio, cantidad, categoria);
	                GestorBD.insertarProducto( producto);
	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	}
	
	
	
	
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

	                Departamento departamento = new Departamento(id, nombre, NSSJefe);
	                GestorBD.insertarDepartamento(departamento);

	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return departamentos;
	}

	
	public static void cargarProveedores() {
		String rutaArchivo = "resources/data/proveedores.csv";

	    try {
	        FileInputStream fis = new FileInputStream(rutaArchivo);
	        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        BufferedReader br = new BufferedReader(isr);

	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	         // corrección 3 -> 4 para no romper la carga
	            if (partes.length == 4) {
	                int cod = Integer.parseInt(partes[0].trim());
	                String nombre = partes[1].trim();
	                int codP = Integer.parseInt(partes[2].trim());
	                String contraseña = partes[3].trim();

	                Proveedor proveedor = new Proveedor(cod, nombre, codP, contraseña);
	                GestorBD.insertarProveedor(proveedor);

	                
	            }
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	}
	
	
	

}
