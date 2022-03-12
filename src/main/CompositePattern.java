package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.Component;
import domein.Composite;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.Leaf;
import domein.MVODatasource;
import domein.Rol;
import domein.SdGoal;
import domein.Som;

public class CompositePattern {

	public static void main(String[] args) throws IOException {
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		
		SdGoal goal = new SdGoal("goal1");
		SdGoal goal2 = new SdGoal("goal2");
		SdGoal goal3 = new SdGoal("goal3");
		SdGoal goal4 = new SdGoal("goal4");
		SdGoal goal5 = new SdGoal("goal5");
		
		DTOMVODoelstelling dd1 = new DTOMVODoelstelling("hoofddoel1", "", 20.0, rollen,goal);
		DTOMVODoelstelling dd2 = new DTOMVODoelstelling("hoofddoel2", "", 10.0, rollen,goal2);
		DTOMVODoelstelling dd3 = new DTOMVODoelstelling("subdoel11", "", 20.0, rollen,goal3);
		DTOMVODoelstelling dd4 = new DTOMVODoelstelling("subdoel21", "", 10.0, rollen,goal4);
		DTOMVODoelstelling dd5 = new DTOMVODoelstelling("subdoel22", "", 10.0, rollen,goal5);
		
		// AANMAKEN VAN COMPOSITE OBJECTEN
		Component hoofddoel1 = new Composite(dd1);
		Component hoofddoel2 = new Composite(dd2);
		
		// AANMAKEN VAN LEAF OBJECTEN
		Component subdoel11 = new Leaf(dd3);
		Component subdoel21 = new Leaf(dd4);
		Component subdoel22 = new Leaf(dd5);
		
		// AANMAKEN VAN DATASOURCES
		MVODatasource ds1 = new MVODatasource(new DTODatasource("CSV1", "csv", "src/data/csvDouble.csv", null, null, null, null));
		MVODatasource ds2 = new MVODatasource(new DTODatasource("S2", "excel", "src/data/xlsDouble.xls", "8Gb", null, null, null));
		MVODatasource ds3 = new MVODatasource(new DTODatasource("X2", "excel", "src/data/xlsxDouble.xlsx", "26Gb", null, null, null));
		
		// DATASOURCES TOEVOEGEN AAN DE BLADEREN
		subdoel11.addDatasource(ds1);
		subdoel21.addDatasource(ds2);
		subdoel22.addDatasource(ds3);
		
		subdoel11.setFormule(new Som());
		subdoel21.setFormule(new Som());
		subdoel22.setFormule(new Som());
		
		hoofddoel1.add(subdoel11);
		hoofddoel1.add(hoofddoel2);
		
		// FORMULES INSTELLEN BIJ DE COMPOSITE
		hoofddoel1.setFormule(new Som());
		hoofddoel2.setFormule(new Som());
		
		hoofddoel2.add(subdoel21);
		hoofddoel2.add(subdoel22);
		
		// BOVENSTE COMPOSITE PRINTEN
		hoofddoel1.print();
		
		// WAARDE VRAGEN VAN BOVENSTE COMPOSITE
		System.out.println(hoofddoel1.getBerekendewaarde());

	}

}