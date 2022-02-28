package domein;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class Fluvius
{
	private ObservableList<Categorie> categorien;
	private ObservableList<MvoDoelstelling> doelstellingen;
	
	private GenericDao<Categorie> categorieRepo;
	private GenericDao<MvoDoelstelling> mvoDoelstellingRepo;
	
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
	
	public ObservableList<Categorie> getCategorien()
	{
		categorien = FXCollections.observableArrayList(categorieRepo.findAll());
		return FXCollections.unmodifiableObservableList(categorien);
	}

	public ObservableList<MvoDoelstelling> getDoelstellingen()
	{
		doelstellingen = FXCollections.observableArrayList(mvoDoelstellingRepo.findAll());
		return FXCollections.unmodifiableObservableList(doelstellingen);
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		categorien.addListener(listener);
	}
	
	public void voegDoelstellingObserverToe(ListChangeListener<MvoDoelstelling> listener)
	{
		doelstellingen.addListener(listener);
	}
	
	// TODO gebruik Repo attributen bij voeg toe en verwijder methodes
	public void voegCategorieToe(Categorie categorie)
	{
		categorieRepo.insert(categorie);
		getCategorien();
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		categorieRepo.delete(categorie);
		getCategorien();
	}

	public void wijzigCategorieNaam(Categorie categorie, String nieuweNaam)
	{
		categorie.setNaam(nieuweNaam);
		getCategorien();
	}
	
	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
	{
		categorie.wijzigRollen(rollen);
		getCategorien();
	}
	
	public void wijzigCategorieDoelstellingen(Categorie categorie, List<MvoDoelstelling> doelstellingen)
	{
		categorie.wijzigDoelstellingen(doelstellingen);
		getCategorien();
	}
}
