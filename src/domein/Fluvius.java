package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

@Entity
public class Fluvius
{
	private ObservableList<Categorie> categorien;
	private ObservableList<MvoDoelstelling> doelstellingen;
	
	private GenericDaoJpa categorieDao;
	private GenericDaoJpa mvoDoelstellingDao;

	public Fluvius()
	{
		categorieDao = new GenericDaoJpa<Categorie>(Categorie.class);
		mvoDoelstellingDao = new GenericDaoJpa<MvoDoelstelling>(MvoDoelstelling.class);
	}

	public void voegCategorieToe(Categorie categorie)
	{
		categorien.add(categorie);
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		categorien.remove(categorie);
	}
	
	public ObservableList<Categorie> geefCategorien()
	{
		return FXCollections.unmodifiableObservableList(categorien);
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		categorien.addListener(listener);
	}
	
	public void wijzigCategorieNaam(Categorie categorie, String nieuweNaam)
	{
		categorie.setNaam(nieuweNaam);
	}
	
	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
	{
		categorie.wijzigRollen(rollen);
	}
	
	public void wijzigCategorieDoelstellingen(Categorie categorie, List<MvoDoelstelling> doelstellingen)
	{
		categorie.wijzigDoelstellingen(doelstellingen);
	}
	
	public ObservableList<MvoDoelstelling> geefDoelstellingen()
	{
		return FXCollections.unmodifiableObservableList(doelstellingen);
	}
	
	public void voegDoelstellingObserverToe(ListChangeListener<MvoDoelstelling> listener)
	{
		doelstellingen.addListener(listener);
	}
}
