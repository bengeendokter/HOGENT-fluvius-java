package repository;

import javax.persistence.NoResultException;

import domein.MVODoelstellingComponent;

public class MVODoelstellingDaoJpa extends GenericDaoJpa<MVODoelstellingComponent> implements MVODoelstellingDao{

	public MVODoelstellingDaoJpa() {
		super(MVODoelstellingComponent.class);
	}

	@Override
	public MVODoelstellingComponent getByNaam(String naam)
	{
		try
		{
			MVODoelstellingComponent c = em.createNamedQuery("doelstelling.findByNaam", MVODoelstellingComponent.class).setParameter("naam", naam)
					.getSingleResult();
			return c;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
