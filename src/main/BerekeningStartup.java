package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.Bewerking;
import domein.Composite;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.Datasource;
import domein.Doelstelling;
import domein.GeenBewerking;
import domein.Leaf;
import domein.MVODatasource;
import domein.Rol;
import domein.SdGoal;
import domein.Som;

public class BerekeningStartup {

	public static void main(String[] args) throws IOException {
		 String naam = "Datasource";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Som();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource(naam, "csv", "src/data/csvDouble.csv","","",""));
		   
		   Doelstelling aantalMannen = new Leaf(new DTOMVODoelstelling("aantal mannen", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, new GeenBewerking()));
		   Doelstelling aantalVrouwen = new Leaf(new DTOMVODoelstelling("aantal vrouwen", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule));
		   
		   subDoelstellingen.add(aantalMannen);
		   subDoelstellingen.add(aantalVrouwen);
		   
		   Doelstelling aantalWerknemers = new Composite(new DTOMVODoelstelling("aantal werknemers", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule));
		   Doelstelling verhouding = new Composite(new DTOMVODoelstelling("verhouding man/vrouw", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, new GeenBewerking()));

		   aantalWerknemers.getBerekendewaarde();
		   verhouding.getBerekendewaarde();
	}

}
