package domein;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("COMP")
public class Composite extends Component implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// EIGEN ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@OneToMany(cascade = CascadeType.ALL)
	private List<Component> components = new ArrayList<>();
	
	
	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Composite(DTOMVODoelstelling d) {
		super(d);
		setComponents(d.subDoelstellingen);
	}

	protected Composite() {
		
	}
	
	// TYPISCHE COMPOSITE PATTERN METHODES
	// ---------------------------------------------------------------------------------------------------
	public void add(Component mvocomponent) {
		components.add(mvocomponent);
    }

    public void remove(Component mvocomponent) {
    	components.remove(mvocomponent);
    }

    public Component getChild(int i) {
        return components.get(i);
    }

    // EIGEN METHODES
 	// ---------------------------------------------------------------------------------------------------
    public List<Component> getComponents(){
		return components;
	}

	private void setComponents(List<Doelstelling> subDoelstellingen)
	{
		if(subDoelstellingen == null)
		{
			subDoelstellingen = new ArrayList<>();
		}
		
		components = subDoelstellingen.stream().map(doel -> (Component) doel).collect(Collectors.toList());
	}
	
	public double getBerekendewaarde() throws IOException {
		
		List<Double> lijst = new ArrayList<>();
		for(Component e: components) {
				if(e instanceof Leaf) {
					lijst.add( e.getBerekendewaarde());
				}
				else {
					if(e.getComponents() != null) {
						for(Component d: e.getComponents()) {
							lijst.add((double) d.getBerekendewaarde());
						}
					}
				}		
		}
		setValue(getFormule().calculate(lijst));
		return getValue();
	}

	@Override
	public void print() {

		System.out.print("\n" + getNaam());
//        System.out.println("---------------------");
//
//        components.forEach(Component::print);
		
	}
	
	
	
	public Iterator<Component> createIterator() {
		return components.iterator();
	}

	public boolean isLeaf() {
		return false;
	}

  
}