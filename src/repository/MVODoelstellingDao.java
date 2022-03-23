package repository;

import domein.Component;

public interface MVODoelstellingDao extends GenericDao<Component>{

	public Component getByNaam(String naam);

}
