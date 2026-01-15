package BD;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.*;

import domain.Departamento;
import domain.Empleado;
import domain.Producto;
import domain.Proveedor;
import domain.Usuario;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestGestorBD {

	private static GestorBD gestor;

	@BeforeAll
	public static void setup() {
	    gestor = new GestorBD();
	    GestorBD.crearBBDD();

	    // Insertar un registro mínimo en la tabla seccion
	    try (var con = java.sql.DriverManager.getConnection(GestorBD.CONNECTION_STRING);
	         var ps = con.prepareStatement("INSERT INTO seccion (cod_seccion, nombre_seccion) VALUES (?, ?)")) {

	        ps.setInt(1, 1);
	        ps.setString(2, "Sección Principal");
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        fail("Error al insertar sección inicial: " + e.getMessage());
	    }
	}

    @AfterAll
    public static void tearDown() {
        gestor.borrarBBDD();
    }

    // TEST DEPARTAMENTO 
    @Test
    @Order(1)
    public void testInsertarYObtenerDepartamento() {
        Departamento d = new Departamento();
        d.setIdDepartamento(1);
        d.setNombreDepartamento("Recursos Humanos");
        d.setNSSJefe(12345);

        GestorBD.insertarDepartamento(d);

        Departamento dObtenido = GestorBD.getDepartamentoById(1);
        assertNotNull(dObtenido);
        assertEquals("Recursos Humanos", dObtenido.getNombreDepartamento());
    }

    @Test
    @Order(2)
    public void testActualizarDepartamento() {
        Departamento d = GestorBD.getDepartamentoById(1);
        d.setNombreDepartamento("Finanzas");
        d.setNSSJefe(54321);

        GestorBD.actualizarDepartamento(d);

        Departamento actualizado = GestorBD.getDepartamentoById(1);
        assertEquals("Finanzas", actualizado.getNombreDepartamento());
        assertEquals(54321, actualizado.getNSSJefe());
    }

    @Test
    @Order(3)
    public void testEliminarDepartamento() {
        GestorBD.deleteDepartamentoById(1);
        Departamento eliminado = GestorBD.getDepartamentoById(1);
        assertNull(eliminado);
    }

    // TEST USUARIO 
    @Test
    @Order(4)
    public void testInsertarYObtenerUsuario() {
        Usuario u = new Usuario();
        u.setCodigo(1);
        u.setUsername("juan");
        u.setCreacionCuenta(LocalDate.now());
        u.setPais("España");
        u.setFoto("foto.jpg");
        u.setContrasena("1234");

        GestorBD.insertarUsuario(u);

        Usuario obtenido = GestorBD.getUsuarioById(1);
        assertNotNull(obtenido);
        assertEquals("juan", obtenido.getUsername());
    }

    @Test
    @Order(5)
    public void testActualizarUsuario() {
        Usuario u = GestorBD.getUsuarioById(1);
        u.setUsername("juan_updated");
        u.setPais("Portugal");

        GestorBD.actualizarUsuario(u);

        Usuario actualizado = GestorBD.getUsuarioById(1);
        assertEquals("juan_updated", actualizado.getUsername());
        assertEquals("Portugal", actualizado.getPais());
    }

    @Test
    @Order(6)
    public void testEliminarUsuario() {
        GestorBD.deleteUsuarioById(1);
        Usuario eliminado = GestorBD.getUsuarioById(1);
        assertNull(eliminado);
    }

    // TEST EMPLEADO 
    @Test
    @Order(7)
    public void testInsertarYObtenerEmpleado() {
        // Necesitamos un departamento y seccion primero
        Departamento dep = new Departamento();
        dep.setIdDepartamento(2);
        dep.setNombreDepartamento("IT");
        dep.setNSSJefe(11111);
        GestorBD.insertarDepartamento(dep);

        Empleado e = new Empleado();
        e.setNss(1001);
        e.setNombreEmpleado("Pedro");
        e.setContrasena("pass");
        e.setIdDepartamento(2);
        e.setCodSeccion(1); // asumir que la sección existe

        GestorBD.insertarEmpleado(e);

        Empleado obtenido = GestorBD.getEmpleadoById(1001);
        assertNotNull(obtenido);
        assertEquals("Pedro", obtenido.getNombreEmpleado());
    }

    @Test
    @Order(8)
    public void testActualizarEmpleado() {
        Empleado e = GestorBD.getEmpleadoById(1001);
        e.setNombreEmpleado("Pedro Updated");
        GestorBD.actualizarEmpleado(e);

        Empleado actualizado = GestorBD.getEmpleadoById(1001);
        assertEquals("Pedro Updated", actualizado.getNombreEmpleado());
    }

    @Test
    @Order(9)
    public void testEliminarEmpleado() {
        GestorBD.deleteEmpleadoById(1001);
        Empleado eliminado = GestorBD.getEmpleadoById(1001);
        assertNull(eliminado);
    }

    @Test
    public void testContarEmpleadosRecursivo() {
        List<Empleado> lista = new ArrayList<>();
        lista.add(new Empleado(123456789, "Juan", "9876", 1, 2));
        lista.add(new Empleado(40, "Luis", "6666", 2, 1));

        int total = Empleado.contarEmpleadosRecursivo(lista, 0);

        assertEquals(2, total);
    }
    
    // TEST PRODUCTO
    @Test
    @Order(10)
    public void testInsertarYObtenerProducto() {
        // Necesitamos proveedor y sección
        Proveedor p = new Proveedor();
        p.setCodProveedor(1);
        p.setNombreProveedor("Proveedor1");
        p.setCodPostal(28001);
        p.setContrasena("pass");
        GestorBD.insertarProveedor(p);

        Producto prod = new Producto();
        prod.setIdProd(1);
        prod.setNombreProd("Laptop");
        prod.setPrecio(1200f);
        prod.setCodProveedor(1);
        prod.setCodSeccion(1);

        GestorBD.insertarProducto(prod);

        Producto obtenido = GestorBD.getProductoById(1);
        assertNotNull(obtenido);
        assertEquals("Laptop", obtenido.getNombreProd());
    }

    @Test
    @Order(11)
    public void testActualizarProducto() {
        Producto prod = GestorBD.getProductoById(1);
        prod.setNombreProd("Laptop Updated");
        GestorBD.actualizarProducto(prod);

        Producto actualizado = GestorBD.getProductoById(1);
        assertEquals("Laptop Updated", actualizado.getNombreProd());
    }

    @Test
    @Order(12)
    public void testEliminarProducto() {
        GestorBD.deleteProductoById(1);
        Producto eliminado = GestorBD.getProductoById(1);
        assertNull(eliminado);
    }

    // TEST PROVEEDOR 
    @Test
    @Order(13)
    public void testInsertarYObtenerProveedor() {
        Proveedor p = new Proveedor();
        p.setCodProveedor(2);
        p.setNombreProveedor("Proveedor2");
        p.setCodPostal(28002);
        p.setContrasena("pass");

        GestorBD.insertarProveedor(p);

        Proveedor obtenido = GestorBD.getProveedorById(2);
        assertNotNull(obtenido);
        assertEquals("Proveedor2", obtenido.getNombreProveedor());
    }

    @Test
    @Order(14)
    public void testActualizarProveedor() {
        Proveedor p = GestorBD.getProveedorById(2);
        p.setNombreProveedor("Proveedor Updated");
        GestorBD.actualizarProveedor(p);

        Proveedor actualizado = GestorBD.getProveedorById(2);
        assertEquals("Proveedor Updated", actualizado.getNombreProveedor());
    }

    @Test
    @Order(15)
    public void testEliminarProveedor() {
        GestorBD.deleteProveedorById(2);
        Proveedor eliminado = GestorBD.getProveedorById(2);
        assertNull(eliminado);
    }

}
