package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "CValue")
public class ComponentValue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ---------------------------------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int componentvalueID;

	@ElementCollection
	@MapKeyColumn(name="name")
	@Column(name="value")
	@CollectionTable(name="valueattributes",
	joinColumns= @JoinColumn(name="componentvalueID"))
	private Map<String, Double> value;
	
	private LocalDate datum;

	public ComponentValue(Map<String, Double> value, LocalDate datum) {
		//eerste keer aanmaken
		if (value==null) this.datum = datum;
		setValue(value);
		setDatum(datum, value);
		/*this.value = value;
		this.datum = datum;*/
	}
	
	public ComponentValue() {
	}

	public void setDatum(LocalDate datum1, Map<String, Double> value) {
		
		//alleen overschrijven als YEAR hetzelfde is
		if (datum.getYear() == datum1.getYear()) {
			this.datum = datum;
			setValue(value);
		}	
	}

	public void setValue(Map<String, Double> value) {
		this.value = value;
		
	}

	public LocalDate getDatum() {
		return datum;
	}
	
	public Map<String, Double> getValue() {
		//System.out.printf("%s	|	%s%n", naam, formule.toString());
		value.entrySet().forEach(es -> System.out.printf("%s : %s%n", es.getKey(), es.getValue()));
		//System.out.printf("%n%n");
		return value;
	}
	
	
	
	
	
	
}
