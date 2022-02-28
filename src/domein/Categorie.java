package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categorieID;
	
	private String naam;
	
	@OneToMany
	private List<MvoDoelstelling> doelstellingen;
	
//	@OneToMany
//	private List<Rol> rollen;
	
	public Categorie(String naam, List<MvoDoelstelling> doelstellingen
//			, List<Rol> rollen
			)
	{
		setNaam(naam);
		wijzigDoelstellingen(doelstellingen);
//		wijzigRollen(rollen);
	}
	
	protected Categorie()
	{
		
	}

	public final void setNaam(String naam)
	{
		if(naam == null || naam.isBlank())
		{
			throw new IllegalArgumentException("De naam van Rol mag niet leeg zijn");
		}
		
		this.naam = naam;
	}
	
	public String getNaam()
	{
		return naam;
	}
	
	public List<MvoDoelstelling> getDoelstellingen()
	{
		return Collections.unmodifiableList(doelstellingen);
	}
	
//	public List<Rol> getRollen()
//	{
//		return Collections.unmodifiableList(rollen);
//	}
	
	public void wijzigDoelstellingen(List<MvoDoelstelling> doelstellingen)
	{
		this.doelstellingen = doelstellingen;
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
}
