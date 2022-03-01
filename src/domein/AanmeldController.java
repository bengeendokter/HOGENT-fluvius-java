package domein;

import java.net.UnknownHostException;
import java.util.Date;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;
import exceptions.VerkeerdeRolException;
import repository.AanmeldPogingDaoJpa;
import repository.GebruikerDaoJpa;
import repository.GenericDaoJpa;

public class AanmeldController
{
	
	public AanmeldController()
	{
		this(false);
	}
	
	public AanmeldController(boolean withInit)
	{
		if(withInit)
		{
			new PopulateDB().run();
		}
	}
	
	public DomeinController meldAan(String gebruikersnaam, String wachtwoord) throws ExceptionInInitializerError
	{
		
		GebruikerDaoJpa gJpa;
		try
		{
			gJpa = new GebruikerDaoJpa();
			GenericDaoJpa.startTransaction();
		}
		catch(ExceptionInInitializerError e)
		{
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
			if(gebruiker.getRol().equals("MVO co�rdinator") == false)
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
			GenericDaoJpa.commitTransaction();
			System.out.println(gebruiker.toString());
			
			return new DomeinController(gebruiker);
		}
		catch(GebruikerBestaatNietException e)
		{
			
			GenericDaoJpa.commitTransaction();
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
	
	private void registreerVerkeerdeAanmeldPoging(Gebruiker gebruiker)
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
		GenericDaoJpa.commitTransaction();
	}
	
	public void sluitPersistentie()
	{
		GenericDaoJpa.closePersistency();
	}
}
