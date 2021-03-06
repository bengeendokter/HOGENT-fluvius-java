package domein;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("LEAF")
public class Leaf extends Component {

	private static final long serialVersionUID = 1L;

	// EIGEN ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MVODatasource datasource;

	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Leaf(DTOMVODoelstelling d) throws IOException {
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

	private void setDatasource(MVODatasource datasource) {
		if (datasource == null) {
			throw new IllegalArgumentException("Een sub MVO Doelstelling moet gekoppeld zijn aan een datasource");
		}

		this.datasource = datasource;
	}

	public Map<String, Double> getBerekendewaarde() throws IOException {

		Map<String, Double> map = datasource.getData(datasource.getKolom());
		System.out.println(map.toString());
		map = getFormule().calculate(map);
		Map<String, Double> mapNewName = new HashMap<>();
		final int size = map.size();
		map.values().forEach(v -> mapNewName
				.put(String.format("%s%s", getNaam(), size > 1 ? String.format("_%s", mapNewName.size()) : ""), v));

		// historiek
		setValue(mapNewName);

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

	@Override
	public String getEenheid() {
		return datasource.getMaat();
	}

}