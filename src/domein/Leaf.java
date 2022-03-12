package domein;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Leaf")
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
	
	public double getBerekendewaarde() throws IOException {
		
		List<Double> lijst = new ArrayList<>(datasource.getData());
		
	
		setValue(getFormule().calculate(lijst));
		return getValue();
	}


	@Override
	public void print() throws IOException {
		System.out.printf("naam: %s, data: %s%n", super.getNaam(), datasource.getData());

	}

}