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
        
        
        try {
            //Gebruiker niet gevonden
            if(gebruiker == null)
            {
                throw new IllegalArgumentException(String.format("Onbestaande gebruikersnaam opgegeven"));
            }
            
            aangemeldeGebruiker = gebruiker;
            
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
            
            //insert geslaagde aanmeldpoging
            GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
            aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), true, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 0));
            GenericDaoJpa.commitTransaction();
            System.out.println(gebruiker.toString());
            GenericDaoJpa.closePersistency();
        } catch (IllegalArgumentException e) {
            GenericDaoJpa<AanmeldPoging> aanmeldPogingDao = new GenericDaoJpa<>(AanmeldPoging.class);
            if (aangemeldeGebruiker != null) {
                aanmeldPogingDao.insert(new AanmeldPoging(aangemeldeGebruiker, new Date(), false, aangemeldeGebruiker.getRol(), aangemeldeGebruiker.getStatus(), 1));
            }
            GenericDaoJpa.commitTransaction();
            
            throw new IllegalArgumentException();
        }
    }
    
}