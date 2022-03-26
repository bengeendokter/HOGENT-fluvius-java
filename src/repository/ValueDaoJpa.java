package repository;

import javax.persistence.NoResultException;

import domein.ComponentValue;

public class ValueDaoJpa extends GenericDaoJpa<ComponentValue> implements ValueDao
{
	
	public ValueDaoJpa()
	{
		super(ComponentValue.class);
	}
	
	@Override
	public ComponentValue getByID(int id)
	{
		try
		{
			ComponentValue d = em.createNamedQuery("cvalue.findByID", ComponentValue.class).setParameter("id", id)
					.getSingleResult();
			return d;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}