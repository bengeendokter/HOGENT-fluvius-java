package domein;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@Table(name = "MVODoelstelling")
@DiscriminatorColumn(name = "Soort")
@NamedQueries({
	@NamedQuery(name = "MVODoelstellingComponent.findByNaam", query = "select c from domein.Component c where c.naam = :naam")})
public abstract class Component implements Serializable{
	
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
	@Transient
	private Bewerking formule;
	@Transient
	private double value = 0.0;
	
	// CONSTRUCTOREN
	// ---------------------------------------------------------------------------------------------------
	public Component(DTOMVODoelstelling d) {
		setNaam(d.naam);
		setIcon(d.icon);
		setDoelwaarde(d.doelwaarde);
		setRollen(d.rollen);
		setSdGoal(d.sdGoal);
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
		if(rollen.isEmpty() || rollen == null) {
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
	
	private void setSdGoal(SdGoal goal) {
		this.sdGoal = goal;
	}
	
	public void setFormule(Bewerking bewerking) {
		this.formule = bewerking;
	}
	
	public void setValue(double waarde) {
		this.value = waarde;
	}
	
	public double getValue() {
		return value;
	}
	
	// METHODEN DIE OVERAL VOORKOMEN + HEBBEN EEN VERSCHILLENDE IMPLEMENTATIE --> ABSTRACT
	// ---------------------------------------------------------------------------------------------------
	public abstract void print();
	public abstract double getBerekendewaarde() throws IOException;
	
	// METHODEN DIE NIET OVERAL VOORKOMEN --> UNSUPPORTED
	// ---------------------------------------------------------------------------------------------------
	public void addDatasource(MVODatasource data) {
		throw new UnsupportedOperationException();
	}

	public List<Component> getComponents(){
		throw new UnsupportedOperationException();
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
	
}