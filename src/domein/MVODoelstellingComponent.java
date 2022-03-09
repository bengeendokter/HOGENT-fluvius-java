package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
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
	@NamedQuery(name = "MVODoelstellingComponent.findByNaam", query = "select c from domein.MVODoelstellingComponent c where c.naam = :naam")})
public abstract class MVODoelstellingComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doelstellingID;
	
	// Attributen
	@Column(unique=true)
	private String naam;
	private String icon;
	private double doelwaarde;
	private String doelstellingsType;
	
	// Attributen van associaties
	@OneToMany(cascade = CascadeType.ALL)
	private List<Rol> rollen = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<MVODatasource> datasources = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private SdGoal hoofdSdg;
	@ManyToOne(cascade = CascadeType.ALL)
	private SdGoal subSdg;
	
	// Constructoren
	public MVODoelstellingComponent(DTOMVODoelstelling d) {
		setNaam(d.naam);
		setIcon(d.icon);
		setDoelwaarde(d.doelwaarde);
		setDoelstellingsType(d.doelstellingsType);
		setRollen(d.rollen);
		setDatasources(d.datasources);
		setHoofdSdg(d.hoofdSdg);
		setSubSdg(d.subSdg);
	}
	
	

	protected MVODoelstellingComponent() {
		
	}
	
	// Getters
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

	public String getDoelstellingsType() {
		return doelstellingsType;
	}

	public List<Rol> getRollen() {
		return Collections.unmodifiableList(rollen);
	}

	public List<MVODatasource> getDatasources() {
		return Collections.unmodifiableList(datasources);
	}
	
	public SdGoal getHoofdSdg() {
		return hoofdSdg;
	}

	public SdGoal getSubSdg() {
		return subSdg;
	}

	// Setters
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
	
	private void setDoelstellingsType(String doelstellingsType) {
		this.doelstellingsType = doelstellingsType;
	}
	
	private void setDatasources(List<MVODatasource> datasources) {
		if(datasources.isEmpty()) {
			throw new IllegalArgumentException("Een MVO Doelstelling moet minstens aan 1 datasource zijn gekoppeld");
		}
		this.datasources = datasources;
	}

	private void setRollen(List<Rol> rollen) {
		if(rollen.isEmpty()) {
			throw new IllegalArgumentException("Een MVO Doelstelling moet minstens voor 1 rol zichtbaar zijn");
		}
		this.rollen = rollen;
	}
	
	private void setSubSdg(SdGoal subSdg) {
		this.subSdg = subSdg;
	}

	private void setHoofdSdg(SdGoal hoofdSdg) {
		if(hoofdSdg == null) {
			throw new IllegalArgumentException("De MVO Doelstelling moet gekoppeld zijn aan een SDG");
		}
		this.hoofdSdg = hoofdSdg;
	}

	// Typische component methodes
	public void add(MVODoelstellingComponent mvocomponent) {
		throw new UnsupportedOperationException();
	}

	public void remove(MVODoelstellingComponent mvocomponent) {
		throw new UnsupportedOperationException();
	}

	public MVODoelstellingComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

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
		MVODoelstellingComponent other = (MVODoelstellingComponent) obj;
		return Objects.equals(naam, other.naam);
	}
	
	@Override
	public String toString() {
		return naam;
	}
	
	
	

	
	
	
	
	

}
