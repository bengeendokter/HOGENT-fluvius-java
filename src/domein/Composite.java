package domein;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("COMP")
public class Composite extends Component implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	private static final int maxAantalLagen = 3; 
	
	// EIGEN ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy="parentComponent")
	private List<Component> components = new ArrayList<>();
	
	
	
	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Composite(DTOMVODoelstelling d) throws IOException {
		super(d);
		setComponents(d.subDoelstellingen);
		
		//historiek
		//initeel setten
		Map<String, Double> x = getBerekendewaarde();
		//getComponentValue(getJaar(), getDoelstellingID()).setValue(x);
		
		//getComponentValue(getJaar(), getDoelstellingID()).setDatum(LocalDate.now(), x);
		
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
	
	public Map<String, Double> getBerekendewaarde() throws IOException {
		
		Map<String, Double> map = new HashMap<>();
//		int value = 0;
		for(Component e: components) {
			e.getBerekendewaarde().entrySet().forEach(es -> {
				map.put(es.getKey(), es.getValue());
			});
//				if(e instanceof Leaf) {
//					e.getBerekendewaarde().entrySet().forEach(es -> {
//						map.put(String.format("%s%s", e.getNaam(), map.size() > 1 ? String.format("_%s",value) : ""), es.getValue());
//					});
//				}
//				else {
//					if(e.getComponents() != null) {
//						for(Component d: e.getComponents()) {
//							e.getBerekendewaarde().entrySet().forEach(es -> {
//								map.put(String.format("%s%s", d.getNaam(), map.size() > 1 ? String.format("_%s",value) : ""), es.getValue());
//							});
//						}
//					}
//				}		
		}
		
		
		Map<String, Double> tempMap = getFormule().calculate(map);
		Map<String, Double> mapNewName = new HashMap<>();
		if(getFormule() instanceof GeenBewerking)
		{
			//historiek
			//setValue(tempMap);
			
			//gevallen waarbij value moet veranderen: initeel of bij zelfde datum
			ComponentValue cv = getComponentValue(getJaar(), getDoelstellingID());
			if (cv.getValue() == null ||  getJaar() == cv.getDatum())
				getComponentValue(getJaar(), getDoelstellingID()).setValue(tempMap);
			
		}
		else
		{
			final int size = tempMap.size();
			tempMap.values().forEach(v -> mapNewName.put(String.format("%s%s", getNaam(), size > 1 ? String.format("_%s",mapNewName.size()) : ""), v));
			
			//historiek
			//setValue(mapNewName);
			
			
			//gevallen waarbij value moet veranderen: initeel of bij zelfde datum
			ComponentValue cv = getComponentValue(getJaar(), getDoelstellingID());
			if (cv.getValue() == null ||  getJaar() == cv.getDatum())
				getComponentValue(getJaar(), getDoelstellingID()).setValue(mapNewName);
		}
		
		
		//return getValue();
		return getComponentValue(getJaar(), getDoelstellingID()).getValue();
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

	@Override
	public String getEenheid()
	{
		if(getComponents().isEmpty())
		{
			return "";
		}
		
		// vraag recursief eenheid tot een leaf gevonden wordt
		return getComponents().get(0).getEenheid();
	}
}