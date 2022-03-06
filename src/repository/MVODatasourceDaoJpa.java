package repository;

import javax.persistence.NoResultException;

import domein.MVODatasource;


public class MVODatasourceDaoJpa extends GenericDaoJpa<MVODatasource> implements MVODatasourceDao
{
	
	public MVODatasourceDaoJpa()
	{
		super(MVODatasource.class);
	}
	
	@Override
	public MVODatasource getByNaam(String naam)
	{
		try
		{
			MVODatasource d = em.createNamedQuery("datasource.findByNaam", MVODatasource.class).setParameter("naam", naam)
					.getSingleResult();
			return d;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
