package domein;

import repository.GebruikerDao;
import repository.GebruikerDaoJpa;

public class PopulateDB
{
	public void run()
	{
		GebruikerDao gebruikerRepo = new GebruikerDaoJpa();
		GebruikerDaoJpa.startTransaction();
		
		gebruikerRepo.insert(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
		gebruikerRepo.insert(new Gebruiker("block", "123456789", "MVO coördinator", "GEBLOKKEERD"));
		
		GebruikerDaoJpa.commitTransaction();
	}
}
