package domein;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;

public class DomeinController
{
	private Gebruiker aangemeldeGebruiker;
	
	private Fluvius fluvius;

	public DomeinController(Gebruiker aangemeldeGebruiker)
	{
		this.aangemeldeGebruiker = aangemeldeGebruiker;
	}
	
	public Gebruiker getAangemeldeGebruiker()
	{
		return this.aangemeldeGebruiker;
	}
	
	public void sluitPersistentie()
	{
		GenericDaoJpa.closePersistency();
	}

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

	public ObservableList<MvoDoelstelling> geefDoelstellingen()
	{
		throw new UnsupportedOperationException();
	}

	public void wijzigCategorieNaam(Categorie categorie, String naam)
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
	
}
