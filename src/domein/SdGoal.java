package domein;

import java.io.Serializable;

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
	private int sdGoalID;
	
	private String naam;

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
