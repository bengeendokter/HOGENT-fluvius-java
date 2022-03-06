package main;

import java.util.ArrayList;
import java.util.Arrays;

import domein.AanmeldController;
import domein.DTODatasource;
import domein.DomeinController;

public class StartUpConsole
{
	
	public static void main(String[] args)
	{
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		
		//DomeinController dc = new DomeinController(null);

//		dc.voegCategorieToe("Sociaal");
//		dc.verwijderCategorie("Sociaal");
//		dc.voegCategorieToe("Klimaat");
//		dc.wijzigCategorieNaam("Klimaat", "Economie");
//		dc.geefCategorien();
//		dc.geefSdGoals();
//		dc.wijzigCategorieDoelstellingen("Economie", new ArrayList<>(Arrays.asList("Geen armoede", "Geen honger")));
//		dc.verwijderCategorie("Economie");
		
		dc.setCurrentDatasource(dc.getDatasources().filtered(e-> e.getNaam().equals("aantal vrouwen")).get(0));
		//dc.voegMVODatasourceToe(new DTODatasource("aantal vrouwen", "excel", "fluvius.com/qra/abi"));
		//dc.wijzigMVODatasource(new DTODatasource("aantal vrouwen2", "csv", "fluvius.com/1"));
		dc.verwijderMVODatasource();
	}
	
}
