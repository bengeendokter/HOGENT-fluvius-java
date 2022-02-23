package domein;


import java.util.Date;
import java.util.List;

import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;
import exceptions.VerkeerdeRolException;
import repository.AanmeldPogingDaoJpa;
import repository.GenericDaoJpa;

public class DomeinController {
    private Gebruiker aangemeldeGebruiker;

    public void meldAan(String gebruikersnaam, String wachtwoord) 
    {
        
        GenericDaoJpa<Gebruiker> gebruikerDao = new GenericDaoJpa<>(Gebruiker.class);
        GenericDaoJpa.startTransaction();
        
        //Haal gebruiker op
        Gebruiker gebruiker = gebruikerDao.get(gebruikersnaam);
        
        try {
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
            aangemeldeGebruiker = gebruiker;
            //insert geslaagde aanmeldpoging
            GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
            aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), true, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 0));
            GenericDaoJpa.commitTransaction();
            System.out.println(gebruiker.toString());

        }  catch (GebruikerBestaatNietException e) {
            
            GenericDaoJpa.commitTransaction();
            System.out.println("bestaat niet");
            throw new GebruikerBestaatNietException();
            
        }  catch (VerkeerdeRolException e) {
        	
        	registreerVerkeerdeAanmeldPoging(gebruiker);
            System.out.println("verkeerde rol");
            throw new VerkeerdeRolException();
            
        }catch (GebruikerGeblokkeerdException e) {
        	
        	registreerVerkeerdeAanmeldPoging(gebruiker);
        	System.out.println("geblokkeerd");	
        	throw new GebruikerGeblokkeerdException();
        	
        } catch (OngeldigeWachtwoordException e) {

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
    		
    	if(aanmeldPogingnummer == 3)//blokkeer gebruiker
    	{
    			gebruiker.setStatus("GEBLOKKEERD");
    	}
    	
    	aanmeldpogingDao.insert(new AanmeldPoging(gebruiker, new Date(), false, gebruiker.getRol(), gebruiker.getStatus(), aanmeldPogingnummer));
    	GenericDaoJpa.commitTransaction();
    }
    
    public Gebruiker getAangemeldeGebruiker() {
    	return this.aangemeldeGebruiker;
    }
    

}