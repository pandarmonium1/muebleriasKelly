package model.entity;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable(identityType =IdentityType.APPLICATION)
public class Proforma  {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String name;
	@Persistent private String direccion;
	@Persistent private String telefono;
	@Persistent private Date date;
	@Persistent private double tPrecio;
	@Persistent private double IGV;
	@Persistent private int cant;
	@Persistent private List<Long> productos;
	
	
	public Proforma(String name, String direccion, String telefono) {
		this.name = name;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tPrecio=0.0;
		this.IGV=0.0;
		this.date = new Date();
		this.productos=new ArrayList<Long>();
		this.tPrecio=0;
		
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public double gettPrecio() {
		return tPrecio;
	}
	public void settPrecio(double tPrecio) {
		this.tPrecio = tPrecio;
	}

	public double getIGV() {
		return IGV;
	}
	public void setIGV(double iGV) {
		IGV = iGV;
	}

	public int getUnidad() {
		return cant;
	}
	public void setUnidad(int unidad) {
		this.cant = unidad;
	}


	public List<Long> getProductos() {
		return productos;
	}


	public void setProductos(List<Long> productos) {
		this.productos = productos;
	}



} 