package domein;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "types")
@DiscriminatorColumn(name = "Soort")
public abstract class TypeDatasource1 implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeNr")
	private int id;
	
	
	//List<Double>
	public abstract List<String> getData() throws IOException; 
		
	
}
