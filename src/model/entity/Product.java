package model.entity;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.VALUE_MAP, value ="PRODUCT_TYPE")
public  class Product {	 
	public Long getId() {
		return id;
	}
	public boolean isHasGlass() {
		return hasGlass;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setHasGlass(boolean hasGlass) {
		this.hasGlass = hasGlass;
	}
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long	id;
	@Persistent private int width, heigh, price;
	@Persistent boolean hasGlass;
	public Product(int a, int b,int c, boolean d) {
		width =b;
		heigh=a;
		price =c;
		hasGlass=d;
	}
	public int getHeigh() {
		return heigh;
	}
	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}
	public int getWidth() {
		return width;
	}
	public int getPrice() {
		return price;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String toString(){
		return heigh+" "+width+" "+price+" "+ hasGlass+"";
	}
	public boolean hasGlass() {
		return hasGlass;
	}
	public void hasGlass(boolean hasGlass) {
		this.hasGlass = hasGlass;
	}

}