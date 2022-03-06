package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MvoDoelstelling")
@NamedQueries({
	@NamedQuery(name = "doelstelling.findByNaam", query = "select c from domein.MVODoelstellingComponent c where c.naam = :naam")})
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
	private String type;
	
	// Attributen van associaties
	@OneToMany
	private List<Rol> rollen = new ArrayList<>();
	@OneToMany
	private List<MVODatasource> datasources = new ArrayList<>();
	
	// Constructoren
	public MVODoelstellingComponent(DTOMVODoelstelling d) {
		setNaam(d.naam);
		setIcon(d.icon);
		setDoelwaarde(d.doelwaarde);
		setType(d.type);
		setRollen(d.rollen);
		setDatasources(d.datasources);
	}
	
	protected MVODoelstellingComponent() {
		
	}
	
	// Getters
	public String getNaam() {
		return naam;
	}

	public String getIcon() {
		return icon;
	}

	public double getDoelwaarde() {
		return doelwaarde;
	}

	public String getType() {
		return type;
	}

	public List<Rol> getRollen() {
		return Collections.unmodifiableList(rollen);
	}

	public List<MVODatasource> getDatasources() {
		return Collections.unmodifiableList(datasources);
	}

	// Setters
	private void setNaam(String naam) {
		this.naam = naam;
	}

	private void setIcon(String icon) {
		this.icon = icon;
	}
	
	private void setDoelwaarde(double doelwaarde) {
		this.doelwaarde = doelwaarde;
		
	}
	
	private void setType(String type) {
		this.type = type;
	}
	
	private void setDatasources(List<MVODatasource> datasources) {
		this.datasources = datasources;
	}

	private void setRollen(List<Rol> rollen) {
		this.rollen = rollen;
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
	
	
	

	
	
	
	
	

}
