package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "CValue")
@NamedQueries({
	@NamedQuery(name = "cvalue.findByID", query = "select c from domein.ComponentValue c where c.c.doelstellingID = :id")})
public class ComponentValue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ---------------------------------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Component c;

	@ElementCollection
	@MapKeyColumn(name="name")
	@Column(name="value")
	@CollectionTable(name="valueattributes",
	joinColumns= @JoinColumn(name="componentvalueID"))
	private Map<String, Double> value;// = null;
	
	//historiek
	private int datum;

	public ComponentValue(Map<String, Double> value, int datum, Component c) {
		//eerste keer aanmaken
		if (value==null) {
			//this.datum = datum;
			setDatum(datum, value);
			setC(c);
			
		}
		//setValue(value);
		
		//setDatum(datum, value);
		/*this.value = value;
		this.datum = datum;*/
	}
	
	public ComponentValue() {
	}

	public void setDatum(int datum1, Map<String, Double> value) {
		//eerste keer aanmaken
		if (value == null) {
			this.datum = datum1;
			//this.value = null;
		} else {
			//alleen overschrijven als YEAR hetzelfde is
			if (datum == datum1) {
				/*this.datum = datum;
				setValue(value);*/
			}
		}
		
			
	}

	public void setValue(Map<String, Double> value) {
		this.value = value;
		
	}

	public int getDatum() {
		return datum;
	}
	
	public Map<String, Double> getValue() {
		//System.out.printf("%s	|	%s%n", naam, formule.toString());
		if (value != null) {
			value.entrySet().forEach(es -> System.out.printf("%s : %s%n", es.getKey(), es.getValue()));
			//System.out.printf("%n%n");
			return value;
		} 
		return null;
		
	}

	public Component getC() {
		return c;
	}

	public void setC(Component c) {
		this.c = c;
	}
	
	
	
	
	
	
	
	
}
