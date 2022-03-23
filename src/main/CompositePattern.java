package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domein.AanmeldController;
import domein.DomeinController;
import domein.Rol;
import domein.SdGoal;

public class CompositePattern {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, SQLIntegrityConstraintViolationException, ExceptionInInitializerError {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fluvius");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();  
		
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		
		AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");
		
		SdGoal goal = dc.getBeschikbareSdgs().get(0);
		SdGoal goal2 = dc.getBeschikbareSdgs().get(1);
		SdGoal goal3 = dc.getBeschikbareSdgs().get(2);
		SdGoal goal4 = dc.getBeschikbareSdgs().get(3);
		SdGoal goal5 = dc.getBeschikbareSdgs().get(4);
		
		// TODO werkte niet meer, oude parameterlijst
//		DTOMVODoelstelling dd1 = new DTOMVODoelstelling("hoofddoel1", "", 20.0, rollen,goal);
//		DTOMVODoelstelling dd2 = new DTOMVODoelstelling("hoofddoel2", "", 10.0, rollen,goal2);
//		DTOMVODoelstelling dd3 = new DTOMVODoelstelling("subdoel11", "", 20.0, rollen,goal3);
//		DTOMVODoelstelling dd4 = new DTOMVODoelstelling("subdoel21", "", 10.0, rollen,goal4);
//		DTOMVODoelstelling dd5 = new DTOMVODoelstelling("subdoel22", "", 10.0, rollen,goal5);
//
//		
//		// AANMAKEN VAN COMPOSITE OBJECTEN
//		Component hoofddoel1 = new Composite(dd1);
//		Component hoofddoel2 = new Composite(dd2);
//		
//		// AANMAKEN VAN LEAF OBJECTEN
//		Component subdoel11 = new Leaf(dd3);
//		Component subdoel21 = new Leaf(dd4);
//		Component subdoel22 = new Leaf(dd5);
//		
//		
//		
//		
//		em.persist(hoofddoel1);
//		em.persist(hoofddoel2);
//		em.persist(subdoel11);
//		em.persist(subdoel21);
//		em.persist(subdoel22);
//		
//		// AANMAKEN VAN DATASOURCES
//		MVODatasource ds1 = new MVODatasource(new DTODatasource("CSV11111", "csv", "src/data/csvDouble.csv", null, null, null, null));
//		MVODatasource ds2 = new MVODatasource(new DTODatasource("S222222", "excel", "src/data/xlsDouble.xls", "8Gb", null, null, null));
//		MVODatasource ds3 = new MVODatasource(new DTODatasource("X222222", "excel", "src/data/xlsxDouble.xlsx", "26Gb", null, null, null));
//		
//		em.persist(ds1);
//		em.persist(ds2);
//		em.persist(ds3);
//		
//		// DATASOURCES TOEVOEGEN AAN DE BLADEREN
//		subdoel11.addDatasource(ds1);
//		subdoel21.addDatasource(ds2);
//		subdoel22.addDatasource(ds3);
//		
//		subdoel11.setFormule(new Som());
//		subdoel21.setFormule(new Som());
//		subdoel22.setFormule(new Som());
//		
//		hoofddoel1.add(subdoel11);
//		hoofddoel1.add(hoofddoel2);
//		
//		// FORMULES INSTELLEN BIJ DE COMPOSITE
//		hoofddoel1.setFormule(new Som());
//		hoofddoel2.setFormule(new Som());
//		
//		hoofddoel2.add(subdoel21);
//		hoofddoel2.add(subdoel22);
//
//		// BOVENSTE COMPOSITE PRINTEN
//		//hoofddoel1.print();
//		
//		Fluvius f = new Fluvius(hoofddoel1);
//		System.out.printf("Leafs: %s%n", f.geefDoelstellingenDieGeenSubsHebben().toString());
//		System.out.printf("Hoofddoelen: %s%n",f.geefDoelstellingenDieSubsHebben().toString());
//
//		// WAARDE VRAGEN VAN BOVENSTE COMPOSITE
//		System.out.println(hoofddoel1.getBerekendewaarde());
//		
//		    
//		em.getTransaction().commit();
//		em.close();
//		emf.close();

	}
	


}