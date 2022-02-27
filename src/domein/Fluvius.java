package domein;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class Fluvius
{
	
	private List<Categorie> categorien;
	private List<MvoDoelstelling> doelstellingen;
	private GenericDaoJpa categorieDao;
	private GenericDaoJpa mvoDoelstellingDao;

	public Fluvius()
	{

	}

	public void voegCategorieToe(Categorie categorie)
	{
		categorien.add(categorie);
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		categorien.remove(categorie);
	}
	
	// TODO werk uit plus unmodifiable
	public ObservableList<Categorie> geefCategorien()
	{
		return (ObservableList<Categorie>) categorien;
	}
	
	// TODO
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		throw new UnsupportedOperationException();
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
	
	// TODO werk uit plus unmodifiable
	public ObservableList<MvoDoelstelling> geefDoelstellingen()
	{
		return (ObservableList<MvoDoelstelling>) doelstellingen;
	}
	
	// TODO
	public void voegDoelstellingObserverToe(ListChangeListener<MvoDoelstelling> listener)
	{
		throw new UnsupportedOperationException();
	}
	
}
