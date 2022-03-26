package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domein.AanmeldController;
import domein.Average;
import domein.Component;
import domein.Composite;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.Datasource;
import domein.Doelstelling;
import domein.DomeinController;
import domein.Leaf;
import domein.MVODatasource;
import domein.Rol;
import domein.SdGoal;
import domein.Som;
import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.MVODatasourceDao;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDao;
import repository.MVODoelstellingDaoJpa;

public class HistoriekStartUp {
	public static void main(String[] args) throws IOException, SQLIntegrityConstraintViolationException, InterruptedException {
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GenericDao<SdGoal> sdGoalRepo = new GenericDaoJpa<SdGoal>(SdGoal.class);
		CategorieDao categorieRepo = new CategorieDaoJpa();
		MVODoelstellingDao doelstellingenRepo = new MVODoelstellingDaoJpa();
		MVODatasourceDao datasourceRepo = new MVODatasourceDaoJpa();
		//gebruikerRepo.startTransaction();
		//sdGoalRepo.startTransaction();
		//categorieRepo.startTransaction();
		doelstellingenRepo.startTransaction();
		datasourceRepo.startTransaction();
		
		// Rollen
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		
		//SDG
		/*SdGoal goalnul = new SdGoal("0", "TestSDG");
		SdGoal goalnul1= new SdGoal("0",
		"0.1 TestSubSDG");*/
		
		/*MVODatasource ds = new MVODatasource(new DTODatasource("testtest", "csv", "src/data/csvDouble.csv", null,
				null, null, false, "snel", "uitstoot", 1));
		
		datasourceRepo.insert(ds);*/
		
		
		AanmeldController aanmeldController = new AanmeldController(true);
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789"); 
		
		MVODatasource d = (MVODatasource) dc.getDatasources().stream().sorted(Comparator.comparing(e -> e.getKolom())).collect(Collectors.toList()).get(0);
		MVODatasource d1 = (MVODatasource) dc.getDatasources().stream().sorted(Comparator.comparing(e -> e.getKolom())).collect(Collectors.toList()).get(1);
		
		SdGoal sg = dc.getBeschikbareSdgs().get(1);
		
		List<Doelstelling> lijst = new ArrayList<>();
		List<Doelstelling> lijst1 = new ArrayList<>();
		
		/*Component m1 = new Leaf(new DTOMVODoelstelling("Leaf0", "file:src/images/peace.png", 20,
				rollen, sg, d, null, new Som(), 2022));
		
		Component m2 = new Leaf(new DTOMVODoelstelling("Leaf1", "file:src/images/peace.png", 20,
				rollen, sg, d1, null, new Som(), 2022));*/
		
		Component m3 = new Leaf(new DTOMVODoelstelling("LeafMeAlone", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Average(), 2021));
		
		Component m10 = new Leaf(new DTOMVODoelstelling("LeafLeaf", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Average(), 2023));
		
		/*Component m4 = new Leaf(new DTOMVODoelstelling("Leaf02020", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Average(), 2020));
		
		Component m5 = new Leaf(new DTOMVODoelstelling("Leaf12020", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Average(), 2020));
		
		Component m6 = new Leaf(new DTOMVODoelstelling("Leaf22020", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Average(), 2020));*/
		
		/*lijst.add(m1);
		lijst.add(m2);
		
		lijst1.add(m4);
		lijst1.add(m5);
		lijst1.add(m6);*/
		
		//doelstelling met 2 subs (2022)
		/*Component head = new Composite(new DTOMVODoelstelling("Head2022", "file:src/images/peace.png", 20,
				rollen, sg, d, lijst, new Som(), 2022));
		doelstellingenRepo.insert(head);*/
		
		//subdoelstelling (2021)
		doelstellingenRepo.insert(m3);
		doelstellingenRepo.insert(m10);
		
		//doelstelling met 2 subs (2023)
		/*doelstellingenRepo.insert(new Composite(new DTOMVODoelstelling("Head2020", "file:src/images/peace.png", 20,
				rollen, sg, d, lijst1, new Som(), 2020)));*/
		
		/*Thread.sleep(10000);
		
		dc.setCurrentDoelstelling(m3);
		//dc.verwijderMVODoelstelling();
		DTOMVODoelstelling dto = new DTOMVODoelstelling("LeafMeAlone1", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Som(), 2021);
		dc.wijzigMVODoelstelling(dto);*/
		
		
		
		
		
		/*doelstellingenRepo.insert(new Leaf(new DTOMVODoelstelling("Leaf0", "file:src/images/peace.png", 20,
				rollen, sg, d, null, new Som(), 2022)));*/
		
		
		/*Component d = new Composite(new DTOMVODoelstelling("CO2Neutraal", "file:src/images/planet.jpg", 20, rollen,
				goal2, mvd, new ArrayList<>(), new Som()));
		d.add(new Composite(new DTOMVODoelstelling("CO2Transport", "file:src/images/planet.jpg", 20, rollen, goal2, mvd,
				new ArrayList<>(), new Som())));
		doelstellingenRepo.insert(d);*/
		
		//gebruikerRepo.commitTransaction();
		//sdGoalRepo.commitTransaction();
		//categorieRepo.commitTransaction();
		doelstellingenRepo.commitTransaction();
		datasourceRepo.commitTransaction();
		
		/*Thread.sleep(5000);
		
		doelstellingenRepo.startTransaction();
		dc.setCurrentDoelstelling(m3);
		//dc.verwijderMVODoelstelling();
		System.out.println(dc.getCurrentDoelstelling().getNaam());
		DTOMVODoelstelling dto = new DTOMVODoelstelling("LeafMeAlonezz", "file:src/images/peace.png", 10,
				rollen, sg, d1, null, new Som(), 2021);
		dc.wijzigMVODoelstelling(dto);
		//dc.verwijderMVODoelstelling();
		
		
		doelstellingenRepo.commitTransaction();*/
		
		//System.out.println(head.getComponentValue(2022, head.getDoelstellingID()).getValue().toString());
	}
}
