package BD;

import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

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
    
    public static void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CargarDatos.class.getClassLoader().getResourceAsStream("data/usuarios.csv"),
                        StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
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

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al leer usuarios.csv: " + e.getMessage());
        }
    }
    
	
    public static void cargarProductos() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CargarDatos.class.getClassLoader().getResourceAsStream("data/productos.csv"),
                        StandardCharsets.UTF_8))) {

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
                    GestorBD.insertarProducto(producto);
                }
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al leer productos.csv: " + e.getMessage());
        }
    }
	
	
	
	
    public static void cargarDepartamentos() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CargarDatos.class.getClassLoader().getResourceAsStream("data/departamentos.csv"),
                        StandardCharsets.UTF_8))) {

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

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al leer departamentos.csv: " + e.getMessage());
        }
    }

	
    public static void cargarProveedores() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CargarDatos.class.getClassLoader().getResourceAsStream("data/proveedores.csv"),
                        StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int cod = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    int codP = Integer.parseInt(partes[2].trim());
                    String contraseña = partes[3].trim();

                    Proveedor proveedor = new Proveedor(cod, nombre, codP, contraseña);
                    GestorBD.insertarProveedor(proveedor);
                }
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al leer proveedores.csv: " + e.getMessage());
        }
    }
	
	
	

}
