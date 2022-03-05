package repository;

import javax.persistence.NoResultException;

import domein.SDGCategorie;

public class CategorieDaoJpa extends GenericDaoJpa<SDGCategorie> implements CategorieDao
{
	
	public CategorieDaoJpa()
	{
		super(SDGCategorie.class);
	}
	
	@Override
	public SDGCategorie getByNaam(String naam)
	{
		try
		{
			SDGCategorie c = em.createNamedQuery("categorie.findByNaam", SDGCategorie.class).setParameter("naam", naam)
					.getSingleResult();
			return c;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
