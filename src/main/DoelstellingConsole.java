package main;

import java.util.ArrayList;
import java.util.List;

import domein.AanmeldController;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.DomeinController;
import domein.MVODatasource;
import domein.Rol;

public class DoelstellingConsole {

	public static void main(String[] args) {
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		
//		DTOMVODoelstelling a = geenRollenToegekend();
//		dc.voegMVODoelstellingToe(a);
//		
//		DTOMVODoelstelling b = geenDatasourcesToegekend();
//		dc.voegMVODoelstellingToe(b);
		// Naam is uniek
//		DTOMVODoelstelling d = naamIsUniekUitvoeren();
//		dc.voegMVODoelstellingToe(d);
		
		// Naam is niet uniek
//		DTOMVODoelstelling e = naamIsNietUniekUitvoeren();
//		dc.voegMVODoelstellingToe(e);
		
		
		dc.setCurrentDoelstelling(dc.getDoelstellingen().filtered(e-> e.getNaam().equals("doelstelling1")).get(0));
		dc.voegMVODoelstellingToe(naamIsUniekUitvoeren());
		dc.wijzigMVODoelstelling(naamIsUniekUitvoerenWijziging());
		//dc.verwijderMVODoelstelling();

	}
	private static DTOMVODoelstelling geenRollenToegekend() {
		List<Rol> rollen = new ArrayList<>();
		List<MVODatasource> datasources = new ArrayList<>();
		return new DTOMVODoelstelling("testUniek", null, 20, "som", rollen, datasources);
	}
	
	private static DTOMVODoelstelling geenDatasourcesToegekend() {
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		List<MVODatasource> datasources = new ArrayList<>();
		return new DTOMVODoelstelling("testUniek", null, 20, "som", rollen, datasources);
	}
	
	
	private static DTOMVODoelstelling naamIsUniekUitvoeren() {
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd = new MVODatasource(new DTODatasource("aantal mannen", "excel", "fluvius.com/qra/abi"));
		datasources.add(mvd);
		return new DTOMVODoelstelling("testtweeeeeee", null, 20, "som", rollen, datasources);
	}
	
	private static DTOMVODoelstelling naamIsUniekUitvoerenWijziging() {
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("Testrol");
		rollen.add(rol);
		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd = new MVODatasource(new DTODatasource("aantal vrouwen", "excel", "fluvius.com/qra/abi"));
		datasources.add(mvd);
		return new DTOMVODoelstelling("testdrieeeeeeee", null, 20, "gewogen gemiddelde", rollen, datasources);
	}
	
	private static DTOMVODoelstelling naamIsNietUniekUitvoeren() {
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd = new MVODatasource(new DTODatasource("aantal kinderen", "excel", "fluvius.com/qra/abi"));
		datasources.add(mvd);
		return new DTOMVODoelstelling("testUniek", null, 20, "som", rollen, datasources);
	}

}
