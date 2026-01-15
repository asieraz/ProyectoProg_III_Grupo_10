package domain;

import java.util.List;
import java.util.Objects;



public class Empleado {
	protected int Nss;
	protected String nombreEmpleado;
	protected String contrasena;
	protected int codSeccion;
	protected int idDepartamento;
	public Empleado(int nss, String nombreEmpleado, String contrasena, int codSeccion, int idDepartamento) {
		super();
		Nss = nss;
		this.nombreEmpleado = nombreEmpleado;
		this.contrasena = contrasena;
		this.codSeccion = codSeccion;
		this.idDepartamento = idDepartamento;
	}
	public Empleado() {
		
	}
	public int getNss() {
		return Nss;
	}
	public void setNss(int nss) {
		Nss = nss;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getCodSeccion() {
		return codSeccion;
	}
	public void setCodSeccion(int codSeccion) {
		this.codSeccion = codSeccion;
	}
	public int getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Nss, codSeccion, contrasena, idDepartamento, nombreEmpleado);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Nss == other.Nss && codSeccion == other.codSeccion && Objects.equals(contrasena, other.contrasena)
				&& idDepartamento == other.idDepartamento && Objects.equals(nombreEmpleado, other.nombreEmpleado);
	}
	
	/* 
	 * Método recursivo que cuenta empleados en una lista
	 */
	public static int contarEmpleadosRecursivo(List<Empleado> lista, int indice) {
	    if (indice == lista.size()) {
	        return 0;
	    }
	    return 1 + contarEmpleadosRecursivo(lista, indice + 1);
	}


}
