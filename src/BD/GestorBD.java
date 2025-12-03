package BD;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import domain.Departamento;
import domain.Empleado;
import domain.Producto;
import domain.Proveedor;
import domain.Usuario;

public class GestorBD {
	
	protected static String DRIVER_NAME;
	protected static String DATABASE_FILE;
	protected static String CONNECTION_STRING;
	
	public GestorBD() {	
		
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.load(new FileReader("resources/parametros.properties"));
			
			DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
			DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
			CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
			
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (Exception ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}

		public static void crearBBDD() {

			try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		        Statement stmt = con.createStatement()) {

		        stmt.execute("PRAGMA foreign_keys = ON");

		        stmt.execute("CREATE TABLE IF NOT EXISTS proveedor (" +
		                     "id_Proveedor INTEGER PRIMARY KEY, " +
		                     "nombre TEXT, " +
		                     "codigo_Postal INTEGER, " +
		                     "contrasena TEXT" +
		                     ");");

		        stmt.execute("CREATE TABLE IF NOT EXISTS departamento (" +
		                     "id_Departamento INTEGER PRIMARY KEY, " +
		                     "nombre TEXT, " +
		                     "NSS_Jefe INTEGER" +
		                     ");");

		        stmt.execute("CREATE TABLE IF NOT EXISTS empleado (" +
		                     "NSS_Empleado INTEGER PRIMARY KEY, " +
		                     "nombre TEXT, " +
		                     "contrasena TEXT, " +
		                     "id_Departamento INTEGER, " +
		                     "id_Seccion INTEGER, " +
		                     "FOREIGN KEY(id_Departamento) REFERENCES departamento(id_Departamento) ON DELETE CASCADE, " +
		                     "FOREIGN KEY(id_Seccion) REFERENCES seccion(cod_seccion) ON DELETE CASCADE" +
		                     ");");

		        stmt.execute("CREATE TABLE IF NOT EXISTS usuario (" +
		                     "codigo INTEGER PRIMARY KEY, " +
		                     "nombre TEXT, " +
		                     "fecha_creacion DATE NOT NULL, " +
		                     "pais TEXT, " +
		                     "foto TEXT, " +
		                     "contrasena TEXT" +
		                     ");");

		        stmt.execute("CREATE TABLE IF NOT EXISTS producto (" +
		                     "id_Producto INTEGER PRIMARY KEY, " +
		                     "nombre TEXT, " +
		                     "precio REAL, " +
		                     "id_Proveedor INTEGER, " +
		                     "cod_Seccion INTEGER, " +
		                     "FOREIGN KEY(id_Proveedor) REFERENCES proveedor(id_Proveedor) ON DELETE CASCADE, " +
		                     "FOREIGN KEY(cod_Seccion) REFERENCES seccion(cod_seccion) ON DELETE CASCADE" +
		                     ");");

		        System.out.println("\n- Tablas creadas correctamente");

		    } catch (SQLException ex) {
		        System.err.format("\n* Error al crear la BBDD: %s", ex.getMessage());
		        ex.printStackTrace();
		    }
}

		public void borrarBBDD() {
	
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {			
	        
			String sql = "DROP TABLE IF EXISTS proveedor;"
					+ "DROP TABLE IF EXISTS departamento;"
					+ "DROP TABLE IF EXISTS empleado;"
					+ "DROP TABLE IF EXISTS usuario;"
					+ "DROP TABLE IF EXISTS producto;";
			
	        PreparedStatement pstmt = con.prepareStatement(sql);
			
	        if (!pstmt.execute()) {
	        	System.out.println("\n- Se han borrado todas las tablas");
	        }

	        pstmt.close();		
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar la BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
			try {
				Files.delete(Paths.get(DATABASE_FILE));
				System.out.println("\n- Se ha borrado el fichero de la BBDD");
			} catch (Exception ex) {
				System.err.format("\n* Error al borrar el archivo de la BBDD: %s", ex.getMessage());
				ex.printStackTrace();						
			}
			
			
		
}
    
		public static void insertarDepartamento(Departamento departamento) {
		        String sql = "INSERT INTO departamento (id_Departamento, nombre, NSS_Jefe) VALUES (?, ?, ?)";
		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setInt(1, departamento.getIdDepartamento());
		            ps.setString(2, departamento.getNombreDepartamento());
		            ps.setInt(3, departamento.getNSSJefe());
		            ps.executeUpdate();

		            System.out.println("Departamento insertado: " + departamento.getNombreDepartamento());

		        } catch (SQLException e) {
		            System.err.println("Error al insertar el departamento: " + e.getMessage());
		        }
		}

		public static void insertarUsuario(Usuario usuario) {
		        String sql = "INSERT INTO usuario (codigo, nombre, fecha_creacio, pais, foto, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setInt(1, usuario.getCodigo());
		            ps.setString(2, usuario.getUsername());
		            ps.setDate(3, java.sql.Date.valueOf(usuario.getCreacionCuenta()));
		            ps.setString(4, usuario.getPais());
		            ps.setString(5, usuario.getFoto());
		            ps.setString(6, usuario.getContrasena());
		            ps.executeUpdate();

		            System.out.println("Usuario insertado: " + usuario.getUsername());

		        } catch (SQLException e) {
		            System.err.println("Error al insertar el usuario: " + e.getMessage());
		        }
		}

		public static void insertarEmpleado(Empleado empleado) {
		        String sql = "INSERT INTO empleado (NSS_Empleado, nombre, contrasena, id_Departamento, id_Seccion) VALUES (?, ?, ?, ?, ?)";
		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setInt(1, empleado.getNss());
		            ps.setString(2, empleado.getNombreEmpleado());
		            ps.setString(3, empleado.getContrasena());
		            ps.setInt(4, empleado.getIdDepartamento());
		            ps.setInt(5, empleado.getCodSeccion());
		            ps.executeUpdate();

		            System.out.println("Empleado insertado: " + empleado.getNombreEmpleado());

		        } catch (SQLException e) {
		            System.err.println("Error al insertar el empleado: " + e.getMessage());
		        }
		}
		
		

		public static void insertarProducto(Producto producto) {
		        String sql = "INSERT INTO producto (id_Producto, nombre, precio, id_Proveedor, cod_Seccion) VALUES (?, ?, ?, ?, ?)";
		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setInt(1, producto.getIdProd());
		            ps.setString(2, producto.getNombreProd());
		            ps.setFloat(3, producto.getPrecio());
		            ps.setInt(4, producto.getCodProveedor());
		            ps.setInt(5, producto.getCodSeccion());
		            ps.executeUpdate();

		            System.out.println("Producto insertado: " + producto.getNombreProd());

		        } catch (SQLException e) {
		            System.err.println("Error al insertar el producto: " + e.getMessage());
		        }
		}

		public static void insertarProveedor(Proveedor proveedor) {
		        String sql = "INSERT INTO proveedor (id_Proveedor, nombre, codigo_Postal, contrasena) VALUES (?, ?, ?, ?)";
		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql)) {

		            ps.setInt(1, proveedor.getCodProveedor());
		            ps.setString(2, proveedor.getNombreProveedor());
		            ps.setInt(3, proveedor.getCodPostal());
		            ps.setString(4, proveedor.getContrasena());
		            ps.executeUpdate();

		            System.out.println("Proveedor insertado: " + proveedor.getNombreProveedor());

		        } catch (SQLException e) {
		            System.err.println("Error al insertar el proveedor: " + e.getMessage());
		        }
		}
		
		public static ArrayList<Departamento> obtenerDepartamentos() {
		        ArrayList<Departamento> lista = new ArrayList<>();
		        String sql = "SELECT * FROM departamento";

		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql);
		             ResultSet rs = ps.executeQuery()) {

		            while (rs.next()) {
		                Departamento d = new Departamento();
		                d.setIdDepartamento(rs.getInt("id_Departamento"));
		                d.setNombreDepartamento(rs.getString("nombre"));
		                d.setNSSJefe(rs.getInt("NSS_Jefe"));
		                lista.add(d);
		            }

		            System.out.println("Se han recuperado " + lista.size() + " departamentos");

		        } catch (Exception ex) {
		            System.err.println("Error al obtener departamentos: " + ex.getMessage());
		            ex.printStackTrace();
		        }

		        return lista;
		}

		public static ArrayList<Usuario> obtenerUsuarios() {
		    	ArrayList<Usuario> lista = new ArrayList<>();
		        String sql = "SELECT * FROM usuario";

		        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		             PreparedStatement ps = con.prepareStatement(sql);
		             ResultSet rs = ps.executeQuery()) {

		            while (rs.next()) {
		                Usuario u = new Usuario();
		                u.setCodigo(rs.getInt("codigo"));
		                u.setUsername(rs.getString("nombre"));
		                u.setCreacionCuenta(rs.getDate("fecha_creacio").toLocalDate());
		                u.setPais(rs.getString("pais"));
		                u.setFoto(rs.getString("foto"));
		                u.setContrasena(rs.getString("contrasena"));
		                lista.add(u);
		            }

		            System.out.println("Se han recuperado " + lista.size() + " usuarios");

		        } catch (Exception ex) {
		            System.err.println("Error al obtener usuarios: " + ex.getMessage());
		            ex.printStackTrace();
		        }

		        return lista;
		}

	    public static ArrayList<Empleado> obtenerEmpleados() {
	    	ArrayList<Empleado> lista = new ArrayList<>();
	        String sql = "SELECT * FROM empleado";

	        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Empleado e = new Empleado();
	                e.setNss(rs.getInt("NSS_Empleado"));
	                e.setNombreEmpleado(rs.getString("nombre"));
	                e.setContrasena(rs.getString("contrasena"));
	                e.setIdDepartamento(rs.getInt("id_Departamento"));
	                e.setCodSeccion(rs.getInt("id_Seccion"));
	                lista.add(e);
	            }

	            System.out.println("Se han recuperado " + lista.size() + " empleados");

	        } catch (Exception ex) {
	            System.err.println("Error al obtener empleados: " + ex.getMessage());
	            ex.printStackTrace();
	        }

	        return lista;
	    }

	    public static ArrayList<Producto> obtenerProductos() {
	    	ArrayList<Producto> lista = new ArrayList<>();
	        String sql = "SELECT * FROM producto";

	        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Producto p = new Producto();
	                p.setIdProd(rs.getInt("id_Producto"));
	                p.setNombreProd(rs.getString("nombre"));
	                p.setPrecio(rs.getFloat("precio"));
	                p.setCodProveedor(rs.getInt("id_Proveedor"));
	                p.setCodSeccion(rs.getInt("cod_Seccion"));
	                lista.add(p);
	            }

	            System.out.println("Se han recuperado " + lista.size() + " productos");

	        } catch (Exception ex) {
	            System.err.println("Error al obtener productos: " + ex.getMessage());
	            ex.printStackTrace();
	        }

	        return lista;
	    }

	    public static ArrayList<Proveedor> obtenerProveedores() {
	    	ArrayList<Proveedor> lista = new ArrayList<>();
	        String sql = "SELECT * FROM proveedor";

	        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Proveedor p = new Proveedor();
	                p.setCodProveedor(rs.getInt("id_Proveedor"));
	                p.setNombreProveedor(rs.getString("nombre"));
	                p.setCodPostal(rs.getInt("codigo_Postal"));
	                p.setContrasena(rs.getString("contrasena"));
	                lista.add(p);
	            }

	            System.out.println("Se han recuperado " + lista.size() + " proveedores");

	        } catch (Exception ex) {
	            System.err.println("Error al obtener proveedores: " + ex.getMessage());
	            ex.printStackTrace();
	        }

	        return lista;
	    }
	
 



}
