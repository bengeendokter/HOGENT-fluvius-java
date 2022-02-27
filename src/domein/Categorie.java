package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	// TODO gebruik Repo attributen bij alle methodes
	public Categorie(String naam, List<MvoDoelstelling> doelstellingen, List<Rol> rollen)
	{
		setNaam(naam);
		wijzigDoelstellingen(doelstellingen);
		wijzigRollen(rollen);
		setMvoDoelstellingRepo(new GenericDaoJpa<>(MvoDoelstelling.class));
	}
	
	public void setMvoDoelstellingRepo(GenericDao<MvoDoelstelling> mock)
	{
		mvoDoelstellingRepo = mock;
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
	
	public ObservableList<MvoDoelstelling> getDoelstellingen()
	{
		return FXCollections.unmodifiableObservableList((ObservableList<MvoDoelstelling>) mvoDoelstellingRepo.findAll());
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
