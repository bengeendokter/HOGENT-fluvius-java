package repository;

import domein.Gebruiker;

public interface GebruikerDao extends GenericDao<Gebruiker>
{
	public Gebruiker getByName(String naam);
	public Gebruiker getById(int id);
}
