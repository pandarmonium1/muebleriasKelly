package model.entity;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator( value ="SalaComedor")
public class SalaComedor extends Product {	
	@Persistent int numOfChairs;
	public SalaComedor(int a, int b, int c,boolean d ,int e) {
		super(a,b,c,d);
		numOfChairs = e;
	}
	public int getNumOfChairs() {
		return numOfChairs;
	}	
	public void setNumOfChairs(int numOfChairs) {
		this.numOfChairs = numOfChairs;
	}	
	public String toString(){
		return super.toString()+numOfChairs+"";
	}	

}
