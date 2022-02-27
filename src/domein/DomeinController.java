package domein;

import repository.GenericDaoJpa;

public class DomeinController
{
	private Gebruiker aangemeldeGebruiker;
	
	public DomeinController(Gebruiker aangemeldeGebruiker)
	{
		this.aangemeldeGebruiker = aangemeldeGebruiker;
	}
	
	public Gebruiker getAangemeldeGebruiker()
	{
		return this.aangemeldeGebruiker;
	}
	
	public void sluitPersistentie()
	{
		GenericDaoJpa.closePersistency();
	}
	
}
