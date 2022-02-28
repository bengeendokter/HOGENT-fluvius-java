package domein;

import java.util.List;

import javafx.collections.ListChangeListener;

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

//	public void voegCategorieToe(Categorie categorie)
//	{
//		fluvius.voegCategorieToe(categorie);
//	}
	
	public void voegCategorieToe(String categorie)
	{
		System.out.printf("Voeg categorie %s toe%n", categorie);
		fluvius.voegCategorieToe(categorie);
	}

//	public void verwijderCategorie(Categorie categorie)
//	{
//		fluvius.verwijderCategorie(categorie);
//	}
	
	public void verwijderCategorie(String categorie)
	{
		System.out.printf("Verwijder categorie %s%n", categorie);
		fluvius.verwijderCategorie(categorie);
	}

//	public ObservableList<Categorie> getCategorien()
//	{
//		return fluvius.getCategorien();
//	}
	
	public List<String> geefCategorien()
	{
		return fluvius.geefCategorien();
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}
	
//	public ObservableList<SdGoal> getSdGoals()
//	{
//		return fluvius.getSdGoals();
//	}
	
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

//	public void wijzigCategorieDoelstellingen(Categorie categorie, List<SdGoal> sdGoals)
//	{
//		fluvius.wijzigCategorieDoelstellingen(categorie, sdGoals);
//	}
	
	public void wijzigCategorieDoelstellingen(String naam, List<String> sdGoals)
	{
		fluvius.wijzigCategorieDoelstellingen(naam, sdGoals);
	}
	
}
