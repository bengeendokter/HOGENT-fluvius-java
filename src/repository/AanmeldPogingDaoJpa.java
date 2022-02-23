package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.AanmeldPoging;
import domein.Gebruiker;

public class AanmeldPogingDaoJpa extends GenericDaoJpa<AanmeldPoging> implements AanmeldPogingDao  {
    public AanmeldPogingDaoJpa() {
        super(AanmeldPoging.class);
    }

    @Override
    public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(Gebruiker gebruiker) throws EntityNotFoundException {
        return em.createNamedQuery("aanmeldpoging.findByGebruiker", AanmeldPoging.class).setParameter("gebruiker", gebruiker).getResultList().stream().findFirst().orElse(null);//
    }
}


