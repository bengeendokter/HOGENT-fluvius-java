package repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.AanmeldPoging;

public class AanmeldPogingDaoJpa extends GenericDaoJpa<AanmeldPoging> implements AanmeldPogingDao  {
    public AanmeldPogingDaoJpa() {
        super(AanmeldPoging.class);
    }

    @Override
    public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Aanmeldpoging.findByGebruikersnaam", AanmeldPoging.class)
                 .setParameter("gebruikersnaam", gebruikersnaam)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
}


