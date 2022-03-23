package domein;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@Table(name = "Bewerking")
@DiscriminatorColumn(name = "Soort")
public abstract class Bewerking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	protected Bewerking()
	{

	}

	public abstract Map<String, Double> calculate(Map<String, Double> data);

}