package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import repository.GenericDao;
import repository.GenericDaoJpa;

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
	
	@OneToMany
	private List<Rol> rollen;

	private GenericDao<MvoDoelstelling> mvoDoelstellingRepo;
	private GenericDao<Rol> rolRepo;
	
	public Categorie(String naam, List<MvoDoelstelling> doelstellingen, List<Rol> rollen)
	{
		setNaam(naam);
		wijzigDoelstellingen(doelstellingen);
		wijzigRollen(rollen);
		setMvoDoelstellingRepo(new GenericDaoJpa<>(MvoDoelstelling.class));
		setRolRepo(new GenericDaoJpa<>(Rol.class));
	}
	
	public void setMvoDoelstellingRepo(GenericDao<MvoDoelstelling> mock)
	{
		mvoDoelstellingRepo = mock;
	}
	
	public void setRolRepo(GenericDao<Rol> mock)
	{
		rolRepo = mock;
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
		return mvoDoelstellingRepo.findAll();
	}
	
	public List<Rol> getRollen()
	{
		return rolRepo.findAll();
	}
	
	// TODO gebruik Repo attributen bij wijzig methodes
	public void wijzigDoelstellingen(List<MvoDoelstelling> doelstellingen)
	{
		this.doelstellingen = doelstellingen;
		mvoDoelstellingRepo.update(doelstellingen);
	}

	public void wijzigRollen(List<Rol> rollen)
	{
		this.rollen = rollen;
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
