package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;



public class BD {
	
	 @SuppressWarnings("unused")
	private static final Scanner sc = new Scanner(System.in);

	    public static void borrarBD(Connection db) {
	        String sql =
	                "DROP TABLE IF EXISTS producto; " +
	                "DROP TABLE IF EXISTS departamento; " +
	                "DROP TABLE IF EXISTS proveedor; " +
	                "DROP TABLE IF EXISTS empleado; " +
	                "DROP TABLE IF EXISTS seccion; ";

	        try (Statement stmt = db.createStatement()) {
	            stmt.executeUpdate(sql);
	            System.out.println("Tablas eliminadas correctamente");
	        } catch (SQLException e) {
	            System.out.println("Error al borrar tablas: " + e.getMessage());
	        }
	    }



	    public static void eliminarEmpleado(Connection db, int NSS) {

	        String sql = "DELETE FROM empleado WHERE NSS_Empleado = ?";

	        try (PreparedStatement stmt = db.prepareStatement(sql)) {
	            stmt.setInt(1, NSS);
	            stmt.executeUpdate();
	            System.out.println("Empleado eliminado: " + NSS);
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar empleado: " + e.getMessage());
	        }
	    }


	    public static void eliminarProducto(Connection db, int idProducto) {
	        String sql = "DELETE FROM producto WHERE id_Producto = ?";

	        try (PreparedStatement stmt = db.prepareStatement(sql)) {
	            stmt.setInt(1, idProducto);
	            stmt.executeUpdate();
	            System.out.println("Producto eliminado: " + idProducto);
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar producto: " + e.getMessage());
	        }
	    }


	    public static void modificarProducto(Connection db, int id, String nuevoNombre, float nuevoPrecio) {

	        int maxId = obtenerIdUltimoProducto(db);

	        if (id < 1 || id > maxId) {
	            System.out.printf("ID incorrecto. Elija entre 1 y %d: ", maxId);
	        }

	        String sql = "UPDATE producto SET nombre = ?, precio = ? WHERE id_Producto = ?";

	        try (PreparedStatement stmt = db.prepareStatement(sql)) {
	            stmt.setString(1, nuevoNombre);
	            stmt.setFloat(2, nuevoPrecio);
	            stmt.setInt(3, id);

	            stmt.executeUpdate();

	            System.out.printf("Producto modificado: %d, nombre: %s%n", id, nuevoNombre);

	        } catch (SQLException e) {
	            System.out.println("Error al modificar producto: " + e.getMessage());
	        }
	    }


	    	public static void modificarEmpleado(Connection db, int NSS, String nuevoNombre, String nuevaContrasena, int idDept, int idSec) {

	        if (idDept < 1 || idDept > 6) {
	            System.out.println("Departamento no válido. Intente de nuevo (1-6): ");
	        }

	        if (idSec < 1 || idSec > 6) {
	            System.out.println("Sección no válida. Intente de nuevo (1-6): ");
	        }

	        String sql =
	                "UPDATE empleado SET nombre = ?, contrasena = ?, id_Departamento = ?, id_Seccion = ? " +
	                "WHERE NSS_Empleado = ?";

	        try (PreparedStatement stmt = db.prepareStatement(sql)) {
	            stmt.setString(1, nuevoNombre);
	            stmt.setString(2, nuevaContrasena);
	            stmt.setInt(3, idDept);
	            stmt.setInt(4, idSec);
	            stmt.setInt(5, NSS);

	            stmt.executeUpdate();
	            System.out.printf("Empleado modificado: NSS %d, nombre: %s%n", NSS, nuevoNombre);

	        } catch (SQLException e) {
	            System.out.println("Error al modificar empleado: " + e.getMessage());
	        }
	    }


	    public static int obtenerIdUltimoProducto(Connection db) {
	        String sql = "SELECT MAX(id_Producto) FROM producto";

	        try (Statement stmt = db.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            return rs.next() ? rs.getInt(1) : 0;

	        } catch (SQLException e) {
	            System.out.println("Error obteniendo max ID: " + e.getMessage());
	            return 0;
	        }
	    }


	    public static void editarJefeDepartamento(Connection db, int numDept, int nuevoNSS) {


	        String sql = "UPDATE departamento SET NSS_Jefe = ? WHERE id_Departamento = ?";

	        try (PreparedStatement stmt = db.prepareStatement(sql)) {
	            stmt.setInt(1, nuevoNSS);
	            stmt.setInt(2, numDept);

	            stmt.executeUpdate();

	            System.out.println("Nuevo jefe asignado: " + nuevoNSS);

	        } catch (SQLException e) {
	            System.out.println("Error modificando jefe: " + e.getMessage());
	        }
	    }
}





