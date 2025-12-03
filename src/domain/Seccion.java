package domain;

import java.util.Objects;

public class Seccion {
	protected int codSeccion;
	protected String nombreSeccion;
	public Seccion(int codSeccion, String nombreSeccion) {
		super();
		this.codSeccion = codSeccion;
		this.nombreSeccion = nombreSeccion;
	}
	public Seccion() {
		
	}
	public int getCodSeccion() {
		return codSeccion;
	}
	public void setCodSeccion(int codSeccion) {
		this.codSeccion = codSeccion;
	}
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codSeccion, nombreSeccion);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seccion other = (Seccion) obj;
		return codSeccion == other.codSeccion && Objects.equals(nombreSeccion, other.nombreSeccion);
	}
	
	

}
