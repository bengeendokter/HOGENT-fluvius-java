package repository;

import javax.persistence.EntityNotFoundException;

import domein.AanmeldPoging;

public interface AanmeldPogingDao extends GenericDao<AanmeldPoging>  {
    public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;   
}

