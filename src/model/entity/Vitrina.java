package model.entity;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator( value ="Vitrina")
public class Vitrina extends Product {	
	@Persistent int numOfPieces;
	public Vitrina(int a, int b, int c,boolean d,int e) {
		super(a,b,c,d);
		numOfPieces=e;
	}
	public int getNumOfPieces() {
		return numOfPieces;
	}	
	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}	
	public String toString(){
		return super.toString()+numOfPieces;
	}
}
