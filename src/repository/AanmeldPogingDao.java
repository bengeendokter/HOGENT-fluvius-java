package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.AanmeldPoging;
import domein.Gebruiker;

public interface AanmeldPogingDao extends GenericDao<AanmeldPoging>  {
    public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(Gebruiker gebruiker);
}

