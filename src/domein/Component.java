package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@Table(name = "MVODoelstelling")
@DiscriminatorColumn(name = "Soort")
@NamedQueries({
	@NamedQuery(name = "MVODoelstellingComponent.findByNaam", query = "select c from domein.Component c where c.naam = :naam")})
public abstract class Component implements Doelstelling, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// GEMEENSCHAPPELIJKE ATTRIBUTEN
	// ---------------------------------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doelstellingID;
	@Column(unique=true)
	private String naam;
	private String icon;
	private double doelwaarde;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Rol> rollen = new ArrayList<>();
	@ManyToOne
	private SdGoal sdGoal;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Bewerking formule;
	
	@ElementCollection
	@MapKeyColumn(name="name")
	@Column(name="value")
	@CollectionTable(name="valueattributes", joinColumns=@JoinColumn(name="doelstellingID"))
	private Map<String, Double> value;
	
	@ManyToOne
	private Composite parentComponent = null;
	
	
	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Component(DTOMVODoelstelling d) {
		setNaam(d.naam);
		setIcon(d.icon);
		setDoelwaarde(d.doelwaarde);
		setRollen(d.rollen);
		setSdGoal(d.sdGoal);
		setFormule(d.bewerking);
	}
	
	protected Component() {
		
	}
	
	// METHODEN DIE OVERAL VOORKOMEN + DEZELFDE IMPLEMENTATIE HEBBEN
	// ---------------------------------------------------------------------------------------------------
	public int getDoelstellingID() {
		return doelstellingID;
	}
	
	public String getNaam() {
		return naam;
	}

	public String getIcon() {
		return icon;
	}

	public double getDoelwaarde() {
		return doelwaarde;
	}

	public List<Rol> getRollen() {
		return Collections.unmodifiableList(rollen);
	}
	
	public SdGoal getSdGoal() {
		return sdGoal;
	}
	
	public Bewerking getFormule() {
		return formule;
	}
	
	public void setDoelstellingID(int mock) {
		doelstellingID = mock;
	}
	
	private void setNaam(String naam) {
		if(naam == null || naam.isBlank()) {
			throw new IllegalArgumentException("De naam van de MVO Doelstelling mag niet leeg zijn");
		}
		this.naam = naam;
	}

	private void setIcon(String icon) {
		this.icon = icon;
	}
	
	private void setDoelwaarde(double doelwaarde) {
		this.doelwaarde = doelwaarde;
	}

	private void setRollen(List<Rol> rollen) {
		if(rollen == null || rollen.isEmpty()) {
			throw new IllegalArgumentException("Een MVO Doelstelling moet minstens voor 1 rol zichtbaar zijn");
		}
		// Controleren dat de lijst van rollen niet bestaat uit dezelfde rollen
		Set<Rol> rollen2 = new HashSet<>();
		for(Rol rol: rollen) {
			if(rollen2.add(rol) == false) {
				throw new IllegalArgumentException("Een MVO Doelstelling bevat meerdere keren dezelfde rol");
			}
		}
		
		this.rollen = rollen;
	}
	
	private void setSdGoal(SdGoal goal)
	{
		if(goal == null)
		{
			throw new IllegalArgumentException("Een MVO Doelstelling moet gekoppeld zijn aan een SDG");
		}
		
		this.sdGoal = goal;
	}
	
	public void setFormule(Bewerking bewerking) {
		if(bewerking == null)
		{
			throw new IllegalArgumentException("Een MVO Doelstelling moet een bewerking hebben");
		}
		
		this.formule = bewerking;
	}
	
	public void setValue(Map<String, Double> waarde) {
		this.value = waarde;
	}
	
	public Map<String, Double> getValue() {
		System.out.printf("%s	|	%s%n", naam, formule.toString());
		value.entrySet().forEach(es -> System.out.printf("%s : %s%n", es.getKey(), es.getValue()));
		System.out.printf("%n%n");
		return value;
	}
	
	// METHODEN DIE OVERAL VOORKOMEN + HEBBEN EEN VERSCHILLENDE IMPLEMENTATIE --> ABSTRACT
	// ---------------------------------------------------------------------------------------------------
	public abstract void print();
	//public abstract Map<String, Double> Value() throws IOException;
	
	// METHODEN DIE NIET OVERAL VOORKOMEN --> UNSUPPORTED
	// ---------------------------------------------------------------------------------------------------
	public void addDatasource(MVODatasource data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Component> getComponents(){
		return new ArrayList<>();
	}
	
	@Override
	public MVODatasource getDatasource()
	{
		return null;
	}
	
	
	public int getWaarde() {
		throw new UnsupportedOperationException();
	}
	
	
	// TYPISCHE COMPOSITE PATTERN METHODES
	// ---------------------------------------------------------------------------------------------------
	public void add(Component mvocomponent) {
		throw new UnsupportedOperationException();
	}

	public void remove(Component mvocomponent) {
		throw new UnsupportedOperationException();
	}

	public Component getChild(int i) {
		throw new UnsupportedOperationException();
	}

	// TYPISCHE METHODES VOOR JPA
	// ---------------------------------------------------------------------------------------------------
	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		return Objects.equals(naam, other.naam);
	}
	
	public abstract Iterator<domein.Component> createIterator();

	public abstract boolean isLeaf();

	public Composite getParentComponent() {
		return parentComponent;
	}

	protected void setParentComponent(Composite parentComponent) {
		this.parentComponent = parentComponent;
		
	}
	
	//telt aantal parent lagen 
	protected int getNumberOfParentLayers(Component stop)
	{
		int numberOfParentLayers = 0;
		Component parent = parentComponent;
		while(parent != null && parent != stop)
		{
			numberOfParentLayers++;
			parent = parent.getParentComponent();
		}
		
		return numberOfParentLayers;
	}
	
	protected int getNumberOfParentLayers()
	{
		return getNumberOfParentLayers(null);
	}
	
	//telt aantal kind lagen 
	protected int getNumberOfChildLayers()
	{
		int numberOfChildLayers = 0;
		CompositeIterator it = new CompositeIterator(createIterator());
		Component nextComp = null;
		
		while(it.hasNext())
		{
			nextComp = it.next();
			int parents = nextComp.getNumberOfParentLayers(parentComponent);
			if(parents > numberOfChildLayers) numberOfChildLayers = parents;
		}
		
		return numberOfChildLayers;
	}
	
}