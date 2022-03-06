package repository;

import domein.MVODatasource;

public interface MVODatasourceDao extends GenericDao<MVODatasource>
{
	public MVODatasource getByNaam(String naam);
}
