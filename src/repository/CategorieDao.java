package repository;

import domein.Categorie;

public interface CategorieDao extends GenericDao<Categorie>
{
	public Categorie getByNaam(String naam);
}
