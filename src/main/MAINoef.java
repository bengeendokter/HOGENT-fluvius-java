package main;


import java.math.BigDecimal;
import javax.persistence.EntityManager;

import domein.Test;
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
        
        ////persisteer de objecten
        entityManager.persist(test1);
        entityManager.persist(test2);
        
        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }
}
