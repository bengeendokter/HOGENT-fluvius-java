package repository;

import domein.MVODoelstellingComponent;
import domein.SDGCategorie;

public interface MVODoelstellingDao extends GenericDao<MVODoelstellingComponent>{

	public MVODoelstellingComponent getByNaam(String naam);

}
