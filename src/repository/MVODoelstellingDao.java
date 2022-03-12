package repository;

import domein.Component;
import domein.SDGCategorie;

public interface MVODoelstellingDao extends GenericDao<Component>{

	public Component getByNaam(String naam);

}
