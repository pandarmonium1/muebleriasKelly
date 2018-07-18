package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Producto {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent private String name;
	@Persistent private double pPrecio;
	@Persistent private double pUTotal;
	@Persistent private double cant;
	@Persistent private long clasificacion;

	
	public Producto(String name, double pPrecio, long clasificacion) {
		this.name = name;
		this.pPrecio = pPrecio;
		this.clasificacion=clasificacion;
	}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getpPrecio() {
		return pPrecio;
	}
	public void setpPrecio(double pPrecio) {
		this.pPrecio = pPrecio;
	}



	public double getpUTotal() {
		return pUTotal;
	}



	public void setpUTotal(double pUTotal) {
		this.pUTotal = pUTotal;
	}



	public double getCant() {
		return cant;
	}



	public void setCant(double cant) {
		this.cant = cant;
	}



	public long getClasificacion() {
		return clasificacion;
	}



	public void setClasificacion(long clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	
}