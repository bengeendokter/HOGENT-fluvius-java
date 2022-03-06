package main;

import domein.AanmeldController;

public class StartUpConsole
{
	
	public static void main(String[] args)
	{
//		AanmeldController aanmeldController = new AanmeldController();
//		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");

//		dc.voegCategorieToe("Sociaal");
//		dc.verwijderCategorie("Sociaal");
//		dc.voegCategorieToe("Klimaat");
//		dc.wijzigCategorieNaam("Klimaat", "Economie");
//		dc.geefCategorien();
//		dc.geefSdGoals();
//		dc.wijzigCategorieDoelstellingen("Economie", new ArrayList<>(Arrays.asList("Geen armoede", "Geen honger")));
//		dc.verwijderCategorie("Economie");
		AanmeldController aanmeldController = new AanmeldController(true);
	}
	
}
