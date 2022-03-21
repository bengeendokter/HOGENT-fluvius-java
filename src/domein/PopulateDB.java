package domein;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class PopulateDB
{
	public static void run() throws IOException, SQLIntegrityConstraintViolationException
	{
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GenericDao<SdGoal> sdGoalRepo = new GenericDaoJpa<SdGoal>(SdGoal.class);
		CategorieDao categorieRepo = new CategorieDaoJpa();
		MVODoelstellingDao doelstellingenRepo = new MVODoelstellingDaoJpa();
		MVODatasourceDao datasourceRepo = new MVODatasourceDaoJpa();
		gebruikerRepo.startTransaction();
		sdGoalRepo.startTransaction();
		categorieRepo.startTransaction();
		doelstellingenRepo.startTransaction();
		datasourceRepo.startTransaction();
		
		// Gebruikers
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
		gebruikerRepo.insert(new Gebruiker("block", "123456789", "MVO coördinator", "GEBLOKKEERD"));
		
		// SdGoals
		SdGoal goal1 = new SdGoal("1", "Geen armoede");
		SdGoal goaltest = new SdGoal("1111", "test");
		goaltest.setParentSDG_id(1);
		SdGoal goal2 = new SdGoal("2", "Geen honger");
		SdGoal goal3 = new SdGoal("3", "Goede gezondheid en welzijn");
		SdGoal goal4 = new SdGoal("8", "Waardig werk en economische groei");
		sdGoalRepo.insert(goal1);
		sdGoalRepo.insert(goal2);
		sdGoalRepo.insert(goal3);
		sdGoalRepo.insert(goaltest);
		sdGoalRepo.insert(new SdGoal("4", "Kwaliteitsonderwijs"));
		sdGoalRepo.insert(new SdGoal("5", "Gendergelijkheid"));
		sdGoalRepo.insert(new SdGoal("6", "Schoon water en sanitair"));
		sdGoalRepo.insert(new SdGoal("7", "Betaalbare en duurzame energie"));
		sdGoalRepo.insert(goal4);
		sdGoalRepo.insert(new SdGoal("9", "Industrie, innovatie en infrastructuur"));
		sdGoalRepo.insert(new SdGoal("10", "Ongelijkheid verminderen"));
		sdGoalRepo.insert(new SdGoal("11", "Duurzame steden en gemeenschappen"));
		sdGoalRepo.insert(new SdGoal("12", "Verantwoorde consumptie en productie"));
		sdGoalRepo.insert(new SdGoal("13", "Klimaatactie"));
		sdGoalRepo.insert(new SdGoal("14", "Leven in het water"));
		sdGoalRepo.insert(new SdGoal("15", "Leven op het land"));
		sdGoalRepo.insert(new SdGoal("16", "Vrede, veiligheid en sterke publieke diensten"));
		sdGoalRepo.insert(new SdGoal("17", "Partnershap om doelstellingen te bereiken"));
		
		// Categorien
		categorieRepo.insert(new SDGCategorie(new DTOCategorie("Economie", "file:src/images/people.png", new ArrayList<>(Arrays.asList(goal1, goaltest)))));
		categorieRepo.insert(new SDGCategorie(new DTOCategorie("Sociaal", "file:src/images/peace.png", new ArrayList<>(Arrays.asList(goal2)))));
		categorieRepo.insert(new SDGCategorie(new DTOCategorie("Omgeving", "file:src/images/planet.jpg", new ArrayList<>(Arrays.asList(goal3)))));
		// Rollen
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		
		// Datasources
		datasourceRepo.insert(new MVODatasource(new DTODatasource("Aantal vrouwen", "databank", null,  "localhost", "test", "test123", true, "traag", "personen", 1)));
		
		datasourceRepo.insert(new MVODatasource(new DTODatasource("co2 mercedes", "csv", "src/data/csvDouble.csv",  null, null, null, true, "snel", "uitstoot", 1)));
		datasourceRepo.insert(new MVODatasource(new DTODatasource("co2 audi", "excel", "src/data/xlsDouble.xls",  null, null, null, true, "traag", "uitstoot", 2)));
		datasourceRepo.insert(new MVODatasource(new DTODatasource("co2 bmw", "excel", "src/data/xlsxDouble.xlsx",  null, null, null, true, "traag", "uitstoot", 3)));
		
		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd = new MVODatasource(new DTODatasource("Aantal kinderen", "csv", "src/data/csvDouble.csv", null, null, null, true, "snel", "kinderen", 2));
		datasources.add(mvd);
		
		// Doelstellingen
		doelstellingenRepo.insert(new Composite(new DTOMVODoelstelling("Doelstelling1", "file:src/images/peace.png", 20, rollen, goal1, mvd, new ArrayList<>(), new Average())));
		Component d = new Composite(new DTOMVODoelstelling("Doelstelling2", "file:src/images/planet.jpg", 20, rollen, goal2, mvd, new ArrayList<>(), new Som()));
		d.add(new Composite(new DTOMVODoelstelling("Doelstelling2.1", "file:src/images/planet.jpg", 20, rollen, goal2, mvd, new ArrayList<>(), new Som())));
		doelstellingenRepo.insert(d);
		gebruikerRepo.commitTransaction();
		sdGoalRepo.commitTransaction();
		categorieRepo.commitTransaction();
		doelstellingenRepo.commitTransaction();
		datasourceRepo.commitTransaction();

	}
}