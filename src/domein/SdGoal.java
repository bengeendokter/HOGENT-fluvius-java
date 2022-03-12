package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
@Table(name = "SDG")
@NamedQueries({
	@NamedQuery(name = "sdGoal.findByNaam", query = "select s from domein.SdGoal s where s.naam = :naam")})
public class SdGoal implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSDG")
	private int sdGoalID;
	
	private String afbeeldingnaam;
	private String naam;
	private String icon;
	private int parentSDG_id;
	
	@OneToMany(mappedBy="sdGoal")
	private List<Component> componenten;

	public SdGoal(String naam)
	{
		this.naam = naam;
	}
	
	public SdGoal(String afbeeldingnaam, String naam)
	{
		this.afbeeldingnaam = afbeeldingnaam;
		this.naam = naam;
		this.icon = String.format("file:src/images/%s.jpg", afbeeldingnaam);
	}

	protected SdGoal()
	{

	}

	public String getAfbeeldingnaam()
	{
		return afbeeldingnaam;
	}
	
	public int getAfbeeldingNaamAlsInt() {
		return Integer.parseInt(afbeeldingnaam);
	}

	public void setAfbeeldingnaam(String afbeeldingnaam)
	{
		this.afbeeldingnaam = afbeeldingnaam;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public int getParentSDG_id()
	{
		return parentSDG_id;
	}

	public void setParentSDG_id(int parentSDG_id)
	{
		this.parentSDG_id = parentSDG_id;
	}

	public String getNaam()
	{
		return naam;
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
		if(!(obj instanceof SdGoal))
		{
			return false;
		}
		SdGoal other = (SdGoal) obj;
		return Objects.equals(naam, other.naam);
	}

	@Override
	public String toString()
	{
		return naam;
	}
}
