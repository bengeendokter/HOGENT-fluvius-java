package domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SDG")
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
}
