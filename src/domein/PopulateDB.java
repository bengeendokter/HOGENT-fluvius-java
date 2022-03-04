package domein;

import java.util.ArrayList;
import java.util.Arrays;

import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class PopulateDB
{
	public static void run()
	{
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GenericDao<SdGoal> sdGoalRepo = new GenericDaoJpa<SdGoal>(SdGoal.class);
		CategorieDao categorieRepo = new CategorieDaoJpa();
		GebruikerDaoJpa.startTransaction();
		
		// Gebruikers
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
		gebruikerRepo.insert(new Gebruiker("block", "123456789", "MVO coördinator", "GEBLOKKEERD"));
		
		// SdGoals
		SdGoal goal1 = new SdGoal("1", "Geen armoede");
		sdGoalRepo.insert(goal1);
		sdGoalRepo.insert(new SdGoal("2", "Geen honger"));
		sdGoalRepo.insert(new SdGoal("3", "Goede gezondheid en welzijn"));
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
		categorieRepo.insert(new Categorie("Economie", new ArrayList<>(Arrays.asList(goal1)), "file:src/images/peace.png"));
		
		GebruikerDaoJpa.commitTransaction();
	}
}
