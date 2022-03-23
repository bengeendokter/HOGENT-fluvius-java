package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil
{
	private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("local");
	
	public static EntityManagerFactory getEntityManagerFactory()
	{
		return entityManagerFactory;
	}
	
	private JPAUtil()
	{
	}
}
