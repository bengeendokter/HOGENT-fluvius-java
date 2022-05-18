package domein;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

@Entity
public class ComponentData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ElementCollection
	@MapKeyColumn(name = "name")
	@Column(name = "value")
	@CollectionTable(name = "valueattributes", joinColumns = @JoinColumn(name = "id"))
	private Map<String, Double> values;

	@ManyToOne
	@JoinColumn(name = "COMPONENT_ID")
	private Component component;

	private int jaar;
	
	private double doelwaarde;

	public ComponentData(Map<String, Double> values, Component component, int jaar, double doelwaarde) {

		this.component = component;
		this.values = values;
		this.jaar = jaar;
		this.doelwaarde = doelwaarde;
	}

	public double getDoelwaarde() {
		return doelwaarde;
	}

	public int getJaar() {
		return jaar;
	}

	protected ComponentData() {
		super();
	}

	public Map<String, Double> getValue() {
		return values;
	}

	public int getId() {
		return id;
	}

}
