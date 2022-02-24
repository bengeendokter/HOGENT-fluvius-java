package main;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;

import domein.AanmeldPoging;
import domein.Gebruiker;
import domein.Test;
import repository.AanmeldPogingDaoJpa;
import repository.GebruikerDao;
import repository.GebruikerDaoJpa;
import util.JPAUtil;

public class MAINoef {

    public static void main(String args[]) {
        // objecten aanmaken
       Test test1 = new Test("test1");
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
