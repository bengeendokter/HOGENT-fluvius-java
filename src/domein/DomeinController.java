package domein;


import java.util.Date;

import repository.GenericDaoJpa;

public class DomeinController {
	private Gebruiker aangemeldeGebruiker;

	public void meldAan(String gebruikersnaam, String wachtwoord) 
	{
		
		GenericDaoJpa<Gebruiker> gebruikerDao = new GenericDaoJpa<>(Gebruiker.class);
		GenericDaoJpa.startTransaction();
		
		//Haal gebruiker op
		Gebruiker gebruiker = gebruikerDao.get(gebruikersnaam);
		
		
		//Gebruiker niet gevonden
		if(gebruiker == null)
		{
			GenericDaoJpa.closePersistency();
			throw new IllegalArgumentException(String.format("Onbestaande gebruikersnaam opgegeven"));
		}
		
		//status van gebruiker is geblokkeerd
		if(gebruiker.getStatus().equals("ACTIEF") == false)
		{
			GenericDaoJpa.closePersistency();
			throw new IllegalArgumentException(String.format("Deze gebruiker is geblokkeerd"));
		}
		
		//gebruiker is geen mvo ccordinator
		if(gebruiker.getRol().equals("MVO co�rdinator") == false)
		{
			GenericDaoJpa.closePersistency();
			throw new IllegalArgumentException(String.format("Deze gebruiker is geen MVO co�rdinator"));
		}
		
		//wachtwoord fout
		if(gebruiker.controleerWachtwoord(wachtwoord) == false)
		{
			GenericDaoJpa.closePersistency();
			throw new IllegalArgumentException(String.format("Opgegeven wachtwoord is incorrect"));
		}
		
		//aanmelden geslaagd
		aangemeldeGebruiker = gebruiker;
		//insert geslaagde aanmeldpoging
		GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
		aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), true, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 0));
		GenericDaoJpa.commitTransaction();
		System.out.println(gebruiker.toString());
		GenericDaoJpa.closePersistency();
	}
	
}
