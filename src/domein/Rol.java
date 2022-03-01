package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rolID;
	
	private String naam;
	
	public Rol(String rol)
	{
		this.naam = rol;
	}
	
	protected Rol()
	{

	}
	
	public String getRol()
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
		if(!(obj instanceof Rol))
		{
			return false;
		}
		Rol other = (Rol) obj;
		return Objects.equals(naam, other.naam);
	}
}
