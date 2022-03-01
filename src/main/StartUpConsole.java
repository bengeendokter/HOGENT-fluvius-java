package main;

import java.util.ArrayList;
import java.util.Arrays;

import domein.AanmeldController;
import domein.Categorie;
import domein.DomeinController;

public class StartUpConsole
{
	
	public static void main(String[] args)
	{
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		

		
		dc.voegCategorieToe("Sociaal");
//		dc.verwijderCategorie("Sociaal");
//		dc.voegCategorieToe("Klimaat");
//		dc.wijzigCategorieNaam("Klimaat", "Economie");
//		dc.geefCategorien();
//		dc.geefSdGoals();
//		dc.wijzigCategorieDoelstellingen("Economie", new ArrayList<>(Arrays.asList("Geen armoede", "Geen honger")));
//		dc.verwijderCategorie("Economie");
	}
	
}
