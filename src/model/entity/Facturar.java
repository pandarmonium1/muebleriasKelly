package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy; 
import javax.jdo.annotations.IdentityType; 
import javax.jdo.annotations.PersistenceCapable; 
import javax.jdo.annotations.Persistent; 
import javax.jdo.annotations.PrimaryKey; 
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Facturar {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String codigoProd;
	@Persistent
	private int cantidad;
	@Persistent
	private String descripcion;
	@Persistent
	private float precioUnidad;
	@Persistent
	private float total;
	public Facturar(){
		
	}
	public Facturar(String codigoPro,int cantidad, String descripcion, float precioUnidad,float total) {
		this.codigoProd= codigoPro;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.precioUnidad = precioUnidad;
		this.total =total; 
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigoPro(){
		return codigoProd;
	}
	public void setCodigoPro(String codigoProd){
		this.codigoProd = codigoProd;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
}
