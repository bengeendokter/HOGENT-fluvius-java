package repository;

import javax.persistence.NoResultException;

import domein.Categorie;

public class CategorieDaoJpa extends GenericDaoJpa<Categorie> implements CategorieDao
{
	
	public CategorieDaoJpa()
	{
		super(Categorie.class);
	}
	
	@Override
	public Categorie getByNaam(String naam)
	{
		try
		{
			Categorie c = em.createNamedQuery("categorie.findByNaam", Categorie.class).setParameter("naam", naam)
					.getSingleResult();
			return c;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
