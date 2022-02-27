package domein;

import java.util.List;
import java.util.Objects;

public class Categorie
{
	private String naam;
	private List<MvoDoelstelling> doelstellingen;
	private List<Rol> rollen;

	
	public Categorie(String naam, List<MvoDoelstelling> doelstellingen, List<Rol> rollen)
	{
		setNaam(naam);
		wijzigDoelstellingen(doelstellingen);
		wijzigRollen(rollen);
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
	
	public void wijzigDoelstellingen(List<MvoDoelstelling> doelstellingen)
	{
		this.doelstellingen = doelstellingen;
	}
	
	public List<MvoDoelstelling> getDoelstellingen()
	{
		return doelstellingen;
	}

	public void wijzigRollen(List<Rol> rollen)
	{
		this.rollen = rollen;
	}
	
	public List<Rol> getRollen()
	{
		return rollen;
	}

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
