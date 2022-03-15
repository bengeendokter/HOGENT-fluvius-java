package repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface GenericDao<T>
{
	public List<T> findAll();
	public <U> T get(U id);
	public T update(T object);
	public void delete(T object);
	public void insert(T object) throws SQLIntegrityConstraintViolationException;
	public <U> boolean exists(U id);
//	public void closePersistency();
//	public void startTransaction();
//	public void commitTransaction();
//	public void rollbackTransaction();
	//public boolean isActive();

}
