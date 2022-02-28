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
		fluvius = new Fluvius();
	}
	
	public Gebruiker getAangemeldeGebruiker()
	{
		return aangemeldeGebruiker;
	}
	
	public void sluitPersistentie()
	{
		GenericDaoJpa.closePersistency();
	}

	public void voegCategorieToe(Categorie categorie)
	{
		fluvius.voegCategorieToe(categorie);
	}

	public void verwijderCategorie(Categorie categorie)
	{
		fluvius.verwijderCategorie(categorie);
	}

	public ObservableList<Categorie> getCategorien()
	{
		return fluvius.getCategorien();
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}

	public ObservableList<MvoDoelstelling> getDoelstellingen()
	{
		return fluvius.getDoelstellingen();
	}

	public void wijzigCategorieNaam(Categorie categorie, String naam)
	{
		fluvius.wijzigCategorieNaam(categorie, naam);
	}

//	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
//	{
//		fluvius.wijzigCategorieRollen(categorie, rollen);
//	}

	public void wijzigCategorieDoelstellingen(Categorie categorie, List<MvoDoelstelling> doelstellingen)
	{
		fluvius.wijzigCategorieDoelstellingen(categorie, doelstellingen);
	}
	
}
