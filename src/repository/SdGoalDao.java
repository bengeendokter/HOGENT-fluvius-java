package repository;

import domein.SdGoal;

public interface SdGoalDao extends GenericDao<SdGoal>
{
	public SdGoal getByNaam(String naam);
}
