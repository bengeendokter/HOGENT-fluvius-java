package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categorie")
@NamedQueries({
	@NamedQuery(name = "categorie.findByNaam", query = "select c from domein.Categorie c where c.naam = :naam")})
public class Categorie implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categorieID;
	
	private String naam;
	private String afbeeldingnaam;
	private String icon;

	@OneToMany
	private List<SdGoal> sdGoals;
	
//	@OneToMany
//	private List<Rol> rollen;
	
	public Categorie(String naam, List<SdGoal> sdGoals
//			, List<Rol> rollen
			)
	{
		setNaam(naam);
		wijzigDoelstellingen(sdGoals);
//		wijzigRollen(rollen);
	}
	
	public Categorie(String naam)
	{
		this(naam, new ArrayList<>());
	}
	
	protected Categorie()
	{
		
	}

	public final void setNaam(String naam)
	{
		if(naam == null || naam.isBlank())
		{
			throw new IllegalArgumentException("De naam van de Categorie mag niet leeg zijn");
		}
		
		this.naam = naam;
	}
	
	public String getNaam()
	{
		return naam;
	}


	public String getAfbeeldingnaam()
	{
		return afbeeldingnaam;
	}

	public String getIcon()
	{
		return icon;
	}
	
	public List<SdGoal> getDoelstellingen()
	{
		return Collections.unmodifiableList(sdGoals);
	}
	
//	public List<Rol> getRollen()
//	{
//		return Collections.unmodifiableList(rollen);
//	}
	
	public void wijzigDoelstellingen(List<SdGoal> sdGoals)
	{
		this.sdGoals = sdGoals;
	}

//	public void wijzigRollen(List<Rol> rollen)
//	{
//		this.rollen = rollen;
//	}

	@Override
	public int hashCode()
	{
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(!(obj instanceof Categorie))
		{
			return false;
		}
		Categorie other = (Categorie) obj;
		return Objects.equals(naam, other.naam);
	}

	@Override
	public String toString()
	{
		return naam;
	}
}
