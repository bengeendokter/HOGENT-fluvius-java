package domein;

import java.util.Collection;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class Fluvius
{
	
	private Collection<Categorie> categorien;
	private GenericDaoJpa categorieDao;
	private GenericDaoJpa mvoDoelstellingDao;
	private Collection<MvoDoelstelling> doelstellingen;
	
	public void voegCategorieToe(Categorie categorie)
	{
		throw new UnsupportedOperationException();
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		throw new UnsupportedOperationException();
	}
	
	public ObservableList<Categorie> geefCategorien()
	{
		throw new UnsupportedOperationException();
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		throw new UnsupportedOperationException();
	}
	
	public void wijzigCategorieNaam(Categorie categorie, String nieuweNaam)
	{
		throw new UnsupportedOperationException();
	}
	
	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
	{
		throw new UnsupportedOperationException();
	}
	
	public void wijzigCategorieDoelstellingen(Categorie categorie, List<MvoDoelstelling> doelstellingen)
	{
		throw new UnsupportedOperationException();
	}
	
	public ObservableList<MvoDoelstelling> geefDoelstellingen()
	{
		throw new UnsupportedOperationException();
	}
	
	public void voegDoelstellingObserverToe(ListChangeListener<MvoDoelstelling> listener)
	{
		throw new UnsupportedOperationException();
	}
	
}
