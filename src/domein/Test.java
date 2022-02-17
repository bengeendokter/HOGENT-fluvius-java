package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 // Commentaar van Yigit
	//Cas
	// Ben
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	
	private String test;
	
	protected Test() {
		
	}
	
	public Test(String test) {
		this.test = test;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return id == other.id;
	}
	
	
}
