package domein;

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
	public static void run()
	{
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GenericDao<SdGoal> sdGoalRepo = new GenericDaoJpa<SdGoal>(SdGoal.class);
		CategorieDao categorieRepo = new CategorieDaoJpa();
		MVODoelstellingDao doelstellingenRepo = new MVODoelstellingDaoJpa();
		MVODatasourceDao datasourceRepo = new MVODatasourceDaoJpa();
		GebruikerDaoJpa.startTransaction();
		
		// Gebruikers
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
		gebruikerRepo.insert(new Gebruiker("block", "123456789", "MVO coördinator", "GEBLOKKEERD"));
		
		// SdGoals
		SdGoal goal1 = new SdGoal("1", "Geen armoede");
		SdGoal goal2 = new SdGoal("2", "Geen honger");
		SdGoal goal3 = new SdGoal("3", "Goede gezondheid en welzijn");
		sdGoalRepo.insert(goal1);
		sdGoalRepo.insert(goal2);
		sdGoalRepo.insert(goal3);
		sdGoalRepo.insert(new SdGoal("4", "Kwaliteitsonderwijs"));
		sdGoalRepo.insert(new SdGoal("5", "Gendergelijkheid"));
		sdGoalRepo.insert(new SdGoal("6", "Schoon water en sanitair"));
		sdGoalRepo.insert(new SdGoal("7", "Betaalbare en duurzame energie"));
		sdGoalRepo.insert(new SdGoal("8", "Waardig werk en economische groei"));
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
		categorieRepo.insert(new SDGCategorie(new DTOCategorie("Economie", "file:src/images/peace.png", new ArrayList<>(Arrays.asList(goal1)))));
		
		// Rollen
		List<Rol> rollen = new ArrayList<>();
		Rol rol = new Rol("MVO Coördinator");
		rollen.add(rol);
		
		// Datasources
		/*datasourceRepo.insert(new MVODatasource(new DTODatasource("aantal vrouwen", "excel", "fluvius.com/qra/abi")));
		
		List<MVODatasource> datasources = new ArrayList<>();
		MVODatasource mvd = new MVODatasource(new DTODatasource("aantal kinderen", "excel", "fluvius.com/qra/abi"));
		datasources.add(mvd);*/
		
		// Doelstellingen
		/*doelstellingenRepo.insert(new DoelstellingMVO(new DTOMVODoelstelling("doelstelling1", "file:src/images/peace.png", 20, "gewogen gemiddelde", rollen, datasources, goal1, null)));
		DoelstellingMVO d = new DoelstellingMVO(new DTOMVODoelstelling("doelstelling2", "file:src/images/planet.jpg", 20, "gewogen gemiddelde", rollen, datasources, goal2, null));
		d.add(new DoelstellingMVO(new DTOMVODoelstelling("doelstelling21", "file:src/images/peace.png", 20, "gewogen gemiddelde", rollen, datasources, goal3, null)));
		doelstellingenRepo.insert(d); */
		GebruikerDaoJpa.commitTransaction();
	}
}
