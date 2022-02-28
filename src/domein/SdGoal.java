package domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	private int parentSDG_id;

	public SdGoal(String naam)
	{
		this.naam = naam;
	}
	
	protected SdGoal()
	{

	}

	public String getNaam()
	{
		return naam;
	}

	@Override
	public String toString()
	{
		return naam;
	}
}
