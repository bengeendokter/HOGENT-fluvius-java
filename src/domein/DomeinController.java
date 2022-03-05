package domein;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DomeinController
{
	private Gebruiker aangemeldeGebruiker;
	
	private Fluvius fluvius;

	public DomeinController(Gebruiker aangemeldeGebruiker)
	{
		this.aangemeldeGebruiker = aangemeldeGebruiker;
		setFluvius(new Fluvius());
		
	}
	
	public void setFluvius(Fluvius fluvius) {
		this.fluvius = fluvius;
	}

	public Gebruiker getAangemeldeGebruiker()
	{
		return aangemeldeGebruiker;
	}

	public void voegCategorieToe(DTOCategorie categorie)
	{
		fluvius.voegCategorieToe(categorie);
	}

	public void verwijderCategorie()
	{
		fluvius.verwijderCategorie();
	}

	public ObservableList<Categorie> getCategorien()
	{
		return fluvius.getCategorien();
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}

	public ObservableList<SdGoal> getBeschikbareSdgs()
	{
		return fluvius.getBeschikbareSdgs();
	}

	public void wijzigCategorie(DTOCategorie categorie)
	{
		fluvius.wijzigCategorie(categorie);
	}

	public void setCurrentCategorie(Categorie categorie)
	{
		fluvius.setCurrentCategorie(categorie);
	}

	public Categorie getCurrentCategorie()
	{
		return fluvius.getCurrentCategorie();
	}

}