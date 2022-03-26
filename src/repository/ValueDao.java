package repository;

import domein.ComponentValue;

public interface ValueDao extends GenericDao<ComponentValue>
{
	public ComponentValue getByID(int id);
	
	//public TypeDatasource getByNaam(String naam);
}