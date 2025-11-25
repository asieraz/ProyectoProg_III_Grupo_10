package BD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Departamento;
import domain.Empleado;
import domain.Producto;
import domain.Proveedor;

public class InsertarDatos {
	
	
	public static void crearTablasInit(Connection db) {
	    
	    String sql =
	            "PRAGMA foreign_keys = ON;"

	            + "CREATE TABLE IF NOT EXISTS proveedor ("
	            + "id_Proveedor INTEGER PRIMARY KEY, "
	            + "nombre TEXT, "
	            + "codigo_Postal INTEGER, "
	            + "contrasena TEXT"
	            + ");"

	            + "CREATE TABLE IF NOT EXISTS seccion ("
	            + "cod_seccion INTEGER PRIMARY KEY, "
	            + "nombre TEXT"
	            + ");"

	            + "CREATE TABLE IF NOT EXISTS departamento ("
	            + "id_Departamento INTEGER PRIMARY KEY, "
	            + "nombre TEXT, "
	            + "NSS_Jefe INTEGER"
	            + ");"

	            + "CREATE TABLE IF NOT EXISTS empleado ("
	            + "NSS_Empleado INTEGER PRIMARY KEY, "
	            + "nombre TEXT, "
	            + "contrasena TEXT, "
	            + "id_Departamento INTEGER, "
	            + "id_Seccion INTEGER, "
	            + "FOREIGN KEY(id_Departamento) REFERENCES departamento(id_Departamento) ON DELETE CASCADE, "
	            + "FOREIGN KEY(id_Seccion) REFERENCES seccion(cod_seccion) ON DELETE CASCADE"
	            + ");"

	            + "CREATE TABLE IF NOT EXISTS producto ("
	            + "id_Producto INTEGER PRIMARY KEY, "
	            + "nombre TEXT, "
	            + "precio REAL, "
	            + "id_Proveedor INTEGER, "
	            + "cod_Seccion INTEGER, "
	            + "FOREIGN KEY(id_Proveedor) REFERENCES proveedor(id_Proveedor) ON DELETE CASCADE, "
	            + "FOREIGN KEY(cod_Seccion) REFERENCES seccion(cod_seccion) ON DELETE CASCADE"
	            + ");";

	    try (Statement stmt = db.createStatement()) {
	        stmt.executeUpdate(sql);
	        System.out.println("Tablas creadas correctamente");
	    } catch (SQLException e) {
	        System.out.println("Error al crear las tablas: " + e.getMessage());
	    }
	}
	
	
	public static void insertarDepartamento(Connection db, Departamento departamento) {
		String sql = String.format(
				"INSERT INTO departamento (id_Departamento, nombre, NSS_Jefe) VALUES (%d, '%s', %d);",
				departamento.getIdDepartamento(),
				departamento.getNombreDepartamento(),
				departamento.getNSSJefe()
				);

		try (Statement stmt = db.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al insertar el departamento: " + e.getMessage());
		}
	}


	public static void insertarEmpleado(Connection db, Empleado empleado) {
		String sql = String.format(
				"INSERT INTO empleado (NSS_Empleado, nombre, contrasena, id_Departamento, id_Seccion) " +
						"VALUES (%d, '%s', '%s', %d, %d);",
						empleado.getNss(),
						empleado.getNombreEmpleado(),
						empleado.getContrasena(),
						empleado.getIdDepartamento(),
						empleado.getCodSeccion()
				);

		try (Statement stmt = db.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al insertar el empleado: " + e.getMessage());
		}
	}


	public static void insertarProducto(Connection db, Producto producto) {
		String sql = String.format(
				"INSERT INTO producto (id_Producto, nombre, precio, id_Proveedor, cod_Seccion) " +
						"VALUES (%d, '%s', %f, %d, %d);",
						producto.getIdProd(),
						producto.getNombreProd(),
						producto.getPrecio(),
						producto.getCodProveedor(),
						producto.getCodSeccion()
				);

		try (Statement stmt = db.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al insertar el producto: " + e.getMessage());
		}
	}


	public static void insertarProveedor(Connection db, Proveedor proveedor) {
		String sql = String.format(
				"INSERT INTO proveedor (id_Proveedor, nombre, codigo_Postal, contrasena) " +
						"VALUES (%d, '%s', %d, '%s');",
						proveedor.getCodProveedor(),
						proveedor.getNombreProveedor(),
						proveedor.getCodPostal(),
						proveedor.getContrasena()
				);

		try (Statement stmt = db.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Error al insertar el proveedor: " + e.getMessage());
		}
	}

}
