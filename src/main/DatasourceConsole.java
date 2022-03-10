package main;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domein.AanmeldController;
import domein.DTODatasource;
import domein.DatasourceTest;
import domein.DomeinController;
import domein.MVODatasource;
import repository.DatabaseSelector;
import repository.GenericDaoJpa;

public class DatasourceConsole {
	
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IllegalStateException {
		/*AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");*/
		
		  EntityManagerFactory emf = Persistence.createEntityManagerFactory("fluvius");
		  EntityManager em = emf.createEntityManager();
		
		  em.getTransaction().begin();
		  DatasourceTest dt = new DatasourceTest(new DTODatasource("test3", "csv", "file:src/data/dataProject.csv"));
		  em.persist(dt);
		  em.getTransaction().commit();
		  em.close();
		  emf.close();
		  
		
		/*dc.voegMVODatasourceToe(new DTODatasource("test1", "csv", "file:src/data/dataProject.csv"));
		//dc.setCurrentDatasource(dc.getDatasources().filtered(e-> e.getNaam().equals("test")).get(0));
		List<Double> list = dc.getCurrentDatasource().getData();
		System.out.println(Arrays.toString(list.toArray()));*/
		
		
		//dc.wijzigMVODatasource(new DTODatasource("aantal vrouwen2", "csv", "fluvius.com/1"));
		//dc.verwijderMVODatasource();
	}
	
	
	
	
}