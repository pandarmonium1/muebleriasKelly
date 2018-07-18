package model.entity;


public class Producto2 {
	
	private Long id;
	
private String name;
 private double pPrecio;
private double pUTotal;
private double cant;

	
	public Producto2(String name, double pPrecio) {
		this.name = name;
		this.pPrecio = pPrecio;
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
	
	
}