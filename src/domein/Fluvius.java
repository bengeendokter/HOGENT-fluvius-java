package domein;

import java.util.List;

import javax.persistence.Entity;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

@Entity
public class Fluvius
{
	private ObservableList<Categorie> categorien;
	private ObservableList<MvoDoelstelling> doelstellingen;
	
	private GenericDao<Categorie> categorieRepo;
	private GenericDao<MvoDoelstelling> mvoDoelstellingRepo;
	
	// TODO gebruik Repo attributen bij alle methodes
	public Fluvius()
	{
		setCategorieRepo(new GenericDaoJpa<>(Categorie.class));
		setMvoDoelstellingRepo(new GenericDaoJpa<>(MvoDoelstelling.class));
	}
	
	public void setCategorieRepo(GenericDao<Categorie> mock)
	{
		categorieRepo = mock;
	}
	
	public void setMvoDoelstellingRepo(GenericDao<MvoDoelstelling> mock)
	{
		mvoDoelstellingRepo = mock;
	}

	public void voegCategorieToe(Categorie categorie)
	{
		categorien.add(categorie);
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		categorien.remove(categorie);
	}
	
	public ObservableList<Categorie> getCategorien()
	{
		return FXCollections.unmodifiableObservableList((ObservableList<Categorie>) categorieRepo.findAll());
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
	
	public ObservableList<MvoDoelstelling> getDoelstellingen()
	{
		return FXCollections.unmodifiableObservableList((ObservableList<MvoDoelstelling>) mvoDoelstellingRepo.findAll());
	}
	
	public void voegDoelstellingObserverToe(ListChangeListener<MvoDoelstelling> listener)
	{
		doelstellingen.addListener(listener);
	}
}
