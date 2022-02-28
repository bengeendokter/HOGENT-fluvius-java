package repository;

import javax.persistence.NoResultException;

import domein.SdGoal;

public class SdGoalDaoJpa extends GenericDaoJpa<SdGoal> implements SdGoalDao
{
	
	public SdGoalDaoJpa()
	{
		super(SdGoal.class);
	}
	
	@Override
	public SdGoal getByNaam(String naam)
	{
		try
		{
			SdGoal s = em.createNamedQuery("sdGoal.findByNaam", SdGoal.class).setParameter("naam", naam)
					.getSingleResult();
			return s;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
