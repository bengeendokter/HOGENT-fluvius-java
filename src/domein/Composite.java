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
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("COMP")
public class Composite extends Component implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	private static final int maxAantalLagen = 3; 
	
	// EIGEN ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@OneToMany(cascade = CascadeType.ALL,mappedBy="parentComponent")
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
		if(mvocomponent instanceof Composite)
		{
			Composite c = (Composite) mvocomponent;
			int children  = c.getNumberOfChildLayers() + 1;//inclusief deze laag
			int parents = getNumberOfParentLayers() + 1;//inclusief deze laag
			if(parents + children > maxAantalLagen) throw new IllegalArgumentException(String.format("Er mogen max %s aantal lagen zijn.", maxAantalLagen));
			System.out.printf("aantal lagen: %s  %n", parents + children);
		}
		
		mvocomponent.setParentComponent(this);
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
		
		int parents = getNumberOfParentLayers() + 1;//inclusief deze laag
		
		
		subDoelstellingen.forEach(sd -> {
			if(sd instanceof Composite)
			{
				Composite c= (Composite) sd;
				int children  = c.getNumberOfChildLayers() + 1;//inclusief deze laag
				if(parents + children > maxAantalLagen) throw new IllegalArgumentException(String.format("Er mogen max %s aantal lagen zijn.", maxAantalLagen));
				System.out.printf("aantal lagen: %s  %n", parents + children);
			}
		});
		
		components = subDoelstellingen.stream().map(doel -> (Component) doel).collect(Collectors.toList());
		components.forEach(c -> c.setParentComponent(this));
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