package domain;

import java.util.Objects;

public class Producto {
	protected int idProd;
	protected String nombreProd;
	protected float precio;
	protected int codSeccion;
	protected int codProveedor;
	public Producto(int idProd, String nombreProd, float precio, int codSeccion, int codProveedor) {
		super();
		this.idProd = idProd;
		this.nombreProd = nombreProd;
		this.precio = precio;
		this.codSeccion = codSeccion;
		this.codProveedor = codProveedor;
	}
	public Producto() {
		
	}
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	public String getNombreProd() {
		return nombreProd;
	}
	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCodSeccion() {
		return codSeccion;
	}
	public void setCodSeccion(int codSeccion) {
		this.codSeccion = codSeccion;
	}
	public int getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codProveedor, codSeccion, idProd, nombreProd, precio);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return codProveedor == other.codProveedor && codSeccion == other.codSeccion && idProd == other.idProd
				&& Objects.equals(nombreProd, other.nombreProd)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio);
	}
	
	

}
