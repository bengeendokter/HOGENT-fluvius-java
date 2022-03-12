package repository;

import javax.persistence.NoResultException;

import domein.Component;

public class MVODoelstellingDaoJpa extends GenericDaoJpa<Component> implements MVODoelstellingDao{

	public MVODoelstellingDaoJpa() {
		super(Component.class);
	}

	@Override
	public Component getByNaam(String naam)
	{
		try
		{
			Component c = em.createNamedQuery("MVODoelstellingComponent.findByNaam", Component.class).setParameter("naam", naam)
					.getSingleResult();
			return c;
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
}
