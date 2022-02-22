package domein;


import repository.GenericDaoJpa;

public class DomeinController {
	private Gebruiker aangemeldeGebruiker;

	public void meldAan(String gebruikersnaam, String wachtwoord) 
	{
		
		GenericDaoJpa<Gebruiker> gebruikerDao = new GenericDaoJpa<>(Gebruiker.class);
		GenericDaoJpa.startTransaction();
		
		//Haal gebruiker op
		Gebruiker gebruiker = gebruikerDao.get(gebruikersnaam);
		GenericDaoJpa.commitTransaction();
		
		//Gebruiker niet gevonden
		if(gebruiker == null)
		{
			throw new IllegalArgumentException(String.format("Onbestaande gebruikersnaam opgegeven"));
		}
		
		//status van gebruiker is geblokkeerd
		if(gebruiker.getStatus().equals("ACTIEF") == false)
		{
			throw new IllegalArgumentException(String.format("Deze gebruiker is geblokkeerd"));
		}
		
		//wachtwoord fout
		if(gebruiker.controleerWachtwoord(wachtwoord) == false)
		{
			throw new IllegalArgumentException(String.format("Opgegeven wachtwoord is incorrect"));
		}
		
		//aanmelden geslaagd
		aangemeldeGebruiker = gebruiker;
		System.out.println(gebruiker.toString());
	}
	
	public void close() {
        GenericDaoJpa.closePersistency();
    }
}
