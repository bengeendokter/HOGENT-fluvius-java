package domein;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("LEAF")
public class Leaf extends Component {

	private static final long serialVersionUID = 1L;

	// EIGEN ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@ManyToOne
	private MVODatasource datasource;

	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Leaf(DTOMVODoelstelling d) {
		super(d);
		setDatasource((MVODatasource) d.datasource);
	}

	protected Leaf() {

	}

	// EIGEN METHODEN
	// ---------------------------------------------------------------------------------------------------
	public void addDatasource(MVODatasource data) {
		this.datasource = data;
	}

	public MVODatasource getDatasource() {
		return datasource;
	}
	
	private void setDatasource(MVODatasource datasource)
	{
		if(datasource == null)
		{
			throw new IllegalArgumentException("Een sub MVO Doelstelling moet gekoppeld zijn aan een datasource");
		}
		
		this.datasource = datasource;
	}
	
	public double getBerekendewaarde() throws IOException {
		
		List<Double> lijst = new ArrayList<>(datasource.getData());
		
	
		setValue(getFormule().calculate(lijst));
		return getValue();
	}


	@Override
	public void print() {
		System.out.printf("naam: %s%n", super.getNaam());

	}
	
	public Iterator<Component> createIterator() {
		return new NullIterator();
	}

	public boolean isLeaf() {
		return true;
	}

}