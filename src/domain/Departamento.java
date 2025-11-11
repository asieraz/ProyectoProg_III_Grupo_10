package domain;

import java.util.Objects;

public class Departamento {

	protected int idDepartamento;
	protected String nombreDepartamento;
	int NSSJefe;
	

	public Departamento(int idDepartamento, String nombreDepartamento, int nSSJefe) {
		super();
		this.idDepartamento = idDepartamento;
		this.nombreDepartamento = nombreDepartamento;
		NSSJefe = nSSJefe;
	}


	public int getIdDepartamento() {
		return idDepartamento;
	}


	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}


	public String getNombreDepartamento() {
		return nombreDepartamento;
	}


	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}


	public int getNSSJefe() {
		return NSSJefe;
	}


	public void setNSSJefe(int nSSJefe) {
		NSSJefe = nSSJefe;
	}


	@Override
	public int hashCode() {
		return Objects.hash(NSSJefe, idDepartamento, nombreDepartamento);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return NSSJefe == other.NSSJefe && idDepartamento == other.idDepartamento
				&& Objects.equals(nombreDepartamento, other.nombreDepartamento);
	}
	
	
}


