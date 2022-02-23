package domein;


import java.util.Date;

import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;
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
            
            aangemeldeGebruiker = gebruiker;
            
            //status van gebruiker is geblokkeerd
            if(gebruiker.getStatus().equals("ACTIEF") == false)
            {
                throw new GebruikerGeblokkeerdException();
            }
            
            //wachtwoord fout
            if(gebruiker.controleerWachtwoord(wachtwoord) == false)
            {
                throw new OngeldigeWachtwoordException();
            }
            
            //aanmelden geslaagd
            
            //insert geslaagde aanmeldpoging
            GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
            aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), true, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 0));
            GenericDaoJpa.commitTransaction();
            System.out.println(gebruiker.toString());

            //GenericDaoJpa.closePersistency();
        }  catch (GebruikerBestaatNietException e) {
            GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
            if (aangemeldeGebruiker != null) {
                aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), false, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 1));
            }

            GenericDaoJpa.commitTransaction();
            
            System.out.println("bestaat niet");
            
            throw new GebruikerBestaatNietException();
        } catch (GebruikerGeblokkeerdException e) {
            // TODO
        	// FoutiefAanmeldPoging klasse maken + in databank zetten
        	
        	 System.out.println("geblokkeerd");
        	
        	GenericDaoJpa.commitTransaction();
        	
        	throw new GebruikerGeblokkeerdException();
        } catch (OngeldigeWachtwoordException e) {
        	// TODO
        	// FoutiefAanmeldPoging klasse maken + in databank zetten
        	
        	System.out.println("ww is fout");
        	
        	GenericDaoJpa.commitTransaction();
        	
        	throw new OngeldigeWachtwoordException();
        }
    }
    
    public Gebruiker getAangemeldeGebruiker() {
    	return this.aangemeldeGebruiker;
    }
    
    public void sluitPersistentie() {
    	GenericDaoJpa.closePersistency();
    }
    
}