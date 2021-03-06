package repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDaoJpa<T> implements GenericDao<T>
{
	private static final String PU_NAME = DatabaseSelector.ISLOCALHOST ? "local2" : "fluvius";
	private  final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU_NAME);
	protected   final EntityManager em = emf.createEntityManager();
	private final Class<T> type;
	
	public GenericDaoJpa(Class<T> type)
	{
		this.type = type;
	}
	
	public   void closePersistency()
	{
		em.close();
		emf.close();
	}
	
	public   void startTransaction()
	{
		em.getTransaction().begin();
	}
	
	public  void commitTransaction()
	{
		em.getTransaction().commit();
		System.out.println("Transaction commited");
	}
	
	public  void rollbackTransaction()
	{
		em.getTransaction().rollback();
	}
	
	public  boolean isActive() {
		return em.getTransaction().isActive();
	}
	
	@Override
	public List<T> findAll()
	{
		//return em.createNamedQuery(type.getName()+".findAll", type).getResultList();
		System.out.println("select entity from " + type.getName() + " entity");
		return em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
	}
	
	@Override
	public <U> T get(U id)
	{
		T entity = em.find(type, id);
		return entity;
	}
	
	@Override
	public T update(T object)
	{
		return em.merge(object);
	}
	
	@Override
	public void delete(T object)
	{
		em.remove(em.merge(object));
	}
	
	@Override
	public void insert(T object) throws SQLIntegrityConstraintViolationException
	{
		em.persist(em.merge(object));
	}
	
	@Override
	public <U> boolean exists(U id)
	{
		T entity = em.find(type, id);
		return entity != null;
	}
	
}
