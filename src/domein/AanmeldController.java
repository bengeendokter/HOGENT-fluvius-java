package domein;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;
import exceptions.VerkeerdeRolException;
import repository.AanmeldPogingDaoJpa;
import repository.GebruikerDaoJpa;
import repository.GenericDaoJpa;

public class AanmeldController
{
	private GebruikerDaoJpa gJpa;
	public AanmeldController() throws SQLIntegrityConstraintViolationException
	{
		this(false);
	}
	
	public AanmeldController(boolean withInit) throws SQLIntegrityConstraintViolationException
	{
		if(withInit)
		{
			try
			{
				PopulateDB.run();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public DomeinController meldAan(String gebruikersnaam, String wachtwoord) throws ExceptionInInitializerError, SQLIntegrityConstraintViolationException
	{
		
		
		try
		{
			gJpa = new GebruikerDaoJpa();
			gJpa.startTransaction();
		}
		catch(ExceptionInInitializerError e)
		{
			System.out.print(e.getCause());
			throw new ExceptionInInitializerError("Kan niet connecteren met de databank");
			
		}
		
		
		//Haal gebruiker op
		Gebruiker gebruiker = gJpa.getByName(gebruikersnaam);
		
		try
		{
			//Gebruiker niet gevonden
			if(gebruiker == null)
			{
				throw new GebruikerBestaatNietException();
			}
			
			//status van gebruiker is geblokkeerd
			if(gebruiker.getStatus().equals("ACTIEF") == false)
			{
				throw new GebruikerGeblokkeerdException();
			}
			
			//rol van gebruiker is onjuist
			if(gebruiker.getRol().equals("MVO coördinator") == false)
			{
				throw new VerkeerdeRolException();
			}
			
			//wachtwoord fout
			if(gebruiker.controleerWachtwoord(wachtwoord) == false)
			{
				throw new OngeldigeWachtwoordException();
			}
			
			//aanmelden geslaagd
			
			//insert geslaagde aanmeld poging
			GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
			aanmeldPogingDao.insert(
					new AanmeldPoging(gebruiker, new Date(), true, gebruiker.getRol(), gebruiker.getStatus(), 0));
			gJpa.commitTransaction();
			System.out.println(gebruiker.toString());
			
			return new DomeinController(gebruiker);
		}
		catch(GebruikerBestaatNietException e)
		{
			
			gJpa.commitTransaction();
			System.out.println("bestaat niet");
			throw new GebruikerBestaatNietException();
			
		}
		catch(VerkeerdeRolException e)
		{
			
			registreerVerkeerdeAanmeldPoging(gebruiker);
			System.out.println("verkeerde rol");
			throw new VerkeerdeRolException();
			
		}
		catch(GebruikerGeblokkeerdException e)
		{
			
			registreerVerkeerdeAanmeldPoging(gebruiker);
			System.out.println("geblokkeerd");
			throw new GebruikerGeblokkeerdException();
			
		}
		catch(OngeldigeWachtwoordException e)
		{
			
			registreerVerkeerdeAanmeldPoging(gebruiker);
			System.out.println("ww is fout");
			throw new OngeldigeWachtwoordException();
		}
	}
	
	private void registreerVerkeerdeAanmeldPoging(Gebruiker gebruiker) throws SQLIntegrityConstraintViolationException
	{
		AanmeldPogingDaoJpa aanmeldpogingDao = new AanmeldPogingDaoJpa();
		AanmeldPoging ap = aanmeldpogingDao.getLaatsteAanmeldPogingByGebruikersnaam(gebruiker);
		
		int aanmeldPogingnummer = 1;
		if(ap != null)
		{
			aanmeldPogingnummer += ap.getPoging();
		}
		
		if(aanmeldPogingnummer >= 3)//blokkeer gebruiker
		{
			gebruiker.setStatus("GEBLOKKEERD");
		}
		
		aanmeldpogingDao.insert(new AanmeldPoging(gebruiker, new Date(), false, gebruiker.getRol(),
				gebruiker.getStatus(), aanmeldPogingnummer));
		gJpa.commitTransaction();
	}
	
	public void sluitPersistentie()
	{
		gJpa.closePersistency();
	}
}
