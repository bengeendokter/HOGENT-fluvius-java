package repository;

import domein.SDGCategorie;

public interface CategorieDao extends GenericDao<SDGCategorie>
{
	public SDGCategorie getByNaam(String naam);
}
