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

	public void voegCategorieToe(Categorie categorie)
	{
		fluvius.voegCategorieToe(categorie);
	}
	
	public void voegCategorieToe(String naam, List<SdGoal> sdGoals, String icon)
	{
		System.out.printf("Voeg categorie %s toe%n", naam);
		fluvius.voegCategorieToe(naam, sdGoals, icon);
	}

	public void verwijderCategorie(Categorie categorie)
	{
		fluvius.verwijderCategorie(categorie);
	}
	
	public void verwijderCategorie(String categorie)
	{
		System.out.printf("Verwijder categorie %s%n", categorie);
		fluvius.verwijderCategorie(categorie);
	}

	public ObservableList<Categorie> getCategorien()
	{
		return fluvius.getCategorien();
	}
	
	public List<String> geefCategorien()
	{
		return fluvius.geefCategorien();
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}
	
	//stond in commentaar
	public ObservableList<SdGoal> getSdGoals()
	{
		return fluvius.getSdGoals();
	}
	
	public List<String> geefSdGoals()
	{
		return fluvius.geefSdGoals();
	}

	public void wijzigCategorieNaam(String oldName, String newName)
	{
		fluvius.wijzigCategorieNaam(oldName, newName);
	}

//	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
//	{
//		fluvius.wijzigCategorieRollen(categorie, rollen);
//	}

	public void wijzigCategorieSdGoals(Categorie categorie, List<SdGoal> sdGoals)
	{
		fluvius.wijzigCategorieSdGoals(categorie, sdGoals);
	}
	
	public void wijzigCategorieSdGoals(String naam, List<String> sdGoals)
	{
		fluvius.wijzigCategorieSdGoals(naam, sdGoals);
	}
	
	
	public void setCategorieIcon(String categorieNaam, String icon)
	{
		fluvius.setCategorieIcon(categorieNaam, icon);
	}
	
	public String getCategorieIcon(String categorieNaam)
	{
		return fluvius.getCategorieIcon(categorieNaam);
	}
}