package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import domein.AanmeldController;
import domein.Average;
import domein.Component;
import domein.Composite;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.Datasource;
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
	public static void main(String[] args) throws IOException, SQLIntegrityConstraintViolationException {
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
		
		MVODatasource ds = new MVODatasource(new DTODatasource("testtest", "csv", "src/data/csvDouble.csv", null,
				null, null, false, "snel", "uitstoot", 1));
		
		datasourceRepo.insert(ds);
		
		AanmeldController aanmeldController = new AanmeldController(true);
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789"); 
		
		SdGoal sg = dc.getBeschikbareSdgs().get(1);
		
		doelstellingenRepo.insert(new Leaf(new DTOMVODoelstelling("TestTest", "file:src/images/peace.png", 20,
				rollen, sg, ds, null, new Som())));
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
	}
}
