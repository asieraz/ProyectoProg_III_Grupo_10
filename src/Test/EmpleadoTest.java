package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Empleado;

public class EmpleadoTest {

    @Test
    public void testCrearEmpleado() {
        Empleado e = new Empleado(123456789, "Juan", "9876", 1, 2);

        assertNotNull(e);
        assertEquals(123456789, e.getNss());
        assertEquals("Juan", e.getNombreEmpleado());
        assertEquals("9876", e.getContrasena());
        assertEquals(1, e.getCodSeccion());
        assertEquals(2, e.getIdDepartamento());
        }
        
    @Test
    public void testContarEmpleadosRecursivo() {
        List<Empleado> lista = new ArrayList<>();
        lista.add(new Empleado(123456789, "Juan", "9876", 1, 2));
        lista.add(new Empleado(40, "Luis", "6666", 2, 1));

        int total = Empleado.contarEmpleadosRecursivo(lista, 0);

        assertEquals(2, total);
    }

}

