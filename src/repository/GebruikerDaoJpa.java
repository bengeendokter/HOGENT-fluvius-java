package repository;

import javax.persistence.NoResultException;

import domein.Gebruiker;

public class GebruikerDaoJpa extends GenericDaoJpa<Gebruiker> implements GebruikerDao {

	public GebruikerDaoJpa() {
		super(Gebruiker.class);
	}

	@Override
	public Gebruiker getByName(String naam) {
		try {
			Gebruiker g = em.createNamedQuery("gebruiker.findByNaam", Gebruiker.class).setParameter("naam", naam).getSingleResult();
			return g;
		} catch (NoResultException e) {
			return null;
		}	
	}

	@Override
	public Gebruiker getById(int id) {
		return em.createNamedQuery("gebruiker.findById", Gebruiker.class).setParameter("id", id).getSingleResult();
	}
}
