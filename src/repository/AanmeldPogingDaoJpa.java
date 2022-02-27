package repository;

import domein.AanmeldPoging;
import domein.Gebruiker;

public class AanmeldPogingDaoJpa extends GenericDaoJpa<AanmeldPoging> implements AanmeldPogingDao  {
    public AanmeldPogingDaoJpa() {
        super(AanmeldPoging.class);
    }

    @Override
    public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(Gebruiker gebruiker){
        return em.createNamedQuery("aanmeldpoging.findByGebruiker", AanmeldPoging.class).setParameter("gebruiker", gebruiker).getResultList().stream().findFirst().orElse(null);//
    }
}


