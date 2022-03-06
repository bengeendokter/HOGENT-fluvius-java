package main;

import java.util.ArrayList;
import java.util.List;

import domein.AanmeldController;
import domein.DTOMVODoelstelling;
import domein.DomeinController;
import domein.MVODatasource;
import domein.Rol;

public class DoelstellingConsole {

	public static void main(String[] args) {
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		
		List<Rol> rollen = new ArrayList<>();
		List<MVODatasource> datasources = new ArrayList<>();
		
		DTOMVODoelstelling d = new DTOMVODoelstelling("test", null, 20, "som", rollen, datasources);
		
		dc.voegMVODoelstellingToe(d);

	}

}
