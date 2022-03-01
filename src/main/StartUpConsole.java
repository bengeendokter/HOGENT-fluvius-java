package main;

import domein.AanmeldController;
import domein.DomeinController;

public class StartUpConsole
{
	
	public static void main(String[] args)
	{
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		
//		dc.voegCategorieToe("Sociaal");
//		dc.verwijderCategorie("Sociaal");
//		dc.voegCategorieToe("Klimaat");
	}
	
}
