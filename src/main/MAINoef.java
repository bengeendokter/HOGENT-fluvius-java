package main;

import javax.persistence.EntityManager;

import domein.Gebruiker;
import domein.Test;
import util.JPAUtil;

public class MAINoef
{
	
	public static void main(String args[])
	{
		// objecten aanmaken
		@SuppressWarnings("unused")
		Test test1 = new Test("test1");
		@SuppressWarnings("unused")
		Test test2 = new Test("test2");
		
		//vraag aan de factory een entityManager
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		////start een transactie
		entityManager.getTransaction().begin();
		
		/*
		GebruikerDaoJpa gebruikerDao = new GebruikerDaoJpa();
		Gebruiker g = gebruikerDao.getByName("JanJansens");
		
		AanmeldPoging aPoging = new AanmeldPoging(g, new Date() , false, g.getRol(), g.getStatus(), 0);*/
		
		Gebruiker gebruiker2 = new Gebruiker("block", "123456789", "MVO coördinator", "GEBKLOKKEERD");
		
		////persisteer de objecten
		entityManager.persist(gebruiker2);
		
		//commit
		entityManager.getTransaction().commit();
		
		//sluit de entityManager
		entityManager.close();
		
		//sluit de factory
		JPAUtil.getEntityManagerFactory().close();
	}
}
