package domein;

import repository.GebruikerDao;
import repository.GebruikerDaoJpa;

public class PopulateDB
{
	public void run()
	{
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GebruikerDaoJpa.startTransaction();
		
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789", "MVO co�rdinator", "ACTIEF"));
		
		GebruikerDaoJpa.commitTransaction();
	}
}