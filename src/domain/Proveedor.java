package domain;

import java.util.Objects;

public class Proveedor {
	protected int codProveedor;
	protected String nombreProveedor;
	protected int codPostal;
	protected String contrasena;
	
	
	public Proveedor(int codProveedor, String nombreProveedor, int codPostal, String contrasena) {
		super();
		this.codProveedor = codProveedor;
		this.nombreProveedor = nombreProveedor;
		this.codPostal = codPostal;
		this.contrasena = contrasena;
	}

	public Proveedor() {
		
	}

	public int getCodProveedor() {
		return codProveedor;
	}


	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}


	public String getNombreProveedor() {
		return nombreProveedor;
	}


	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}


	public int getCodPostal() {
		return codPostal;
	}


	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codPostal, codProveedor, contrasena, nombreProveedor);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		return codPostal == other.codPostal && codProveedor == other.codProveedor
				&& Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(nombreProveedor, other.nombreProveedor);
	}
	
	
	
	
	

}
