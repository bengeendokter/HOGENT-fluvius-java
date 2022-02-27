package repository;

import domein.AanmeldPoging;
import domein.Gebruiker;

public interface AanmeldPogingDao extends GenericDao<AanmeldPoging>
{
	public AanmeldPoging getLaatsteAanmeldPogingByGebruikersnaam(Gebruiker gebruiker);
}
