package domein;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.RollbackException;

import org.eclipse.persistence.exceptions.DatabaseException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GenericDaoJpa;
import repository.MVODatasourceDao;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDao;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDao;
import repository.SdGoalDaoJpa;

public class Fluvius
{
	// ATTRIBUTEN
	// ______________________________________________________________________________________________
	
	private ObservableList<SDGCategorie> categorien = FXCollections.observableArrayList();
	private ObservableList<SdGoal> sdGoals = FXCollections.observableArrayList();
	private ObservableList<Component> doelstellingen = FXCollections.observableArrayList();
	private ObservableList<MVODatasource> datasources = FXCollections.observableArrayList();
	
	private CategorieDao categorieRepo;
	private SdGoalDao sdGoalsRepo;
	private MVODoelstellingDao mvoDoelstellingRepo;
	private MVODatasourceDao mvoDatasourceRepo;
	
	private Categorie currentCategorie;
	private Doelstelling currentDoelstelling;
	private Datasource currentDatasource;
	
	private Component allComponents;
	
	// CONSTRUCTOR
	// ______________________________________________________________________________________________
	
	public Fluvius()
	{
		setCategorieRepo(new CategorieDaoJpa());
		setSdGoalRepo(new SdGoalDaoJpa());
		setMVODoelstellingenRepo(new MVODoelstellingDaoJpa());
		setMVODatasourceRepo(new MVODatasourceDaoJpa());
		
		setCategorien();
		setSdGoals();
		setDoelstellingen();
		setDatasources();
		
	}
	
	// NOG EEN VRAAG HIEROVER
	public Fluvius(Component allComponents) {
		this.allComponents = allComponents;
	}
	
	
	public List<Component> geefDoelstellingenDieGeenSubsHebben(){
		Iterator<Component> iterator = new CompositeIterator(Arrays.asList(allComponents).iterator());
		List<Component> doelZonderSubs = new ArrayList<>();
		while (iterator.hasNext()) {
            Component component = iterator.next();

            if(component.isLeaf() ) {
            	doelZonderSubs.add(component);
            }
        }
		return doelZonderSubs;
	}
	
	public List<Component> geefDoelstellingenDieSubsHebben(){
		Iterator<Component> iterator = new CompositeIterator(Arrays.asList(allComponents).iterator());
		List<Component> doelMetSubs = new ArrayList<>();
		while (iterator.hasNext()) {
            Component component = iterator.next();

            if(!component.isLeaf() ) {
            	doelMetSubs.add(component);
            }
        }
		return doelMetSubs;
	}
	
	// SDG
	// ______________________________________________________________________________________________
	public void setSdGoalRepo(SdGoalDao mock)
	{
		sdGoalsRepo = mock;
	}
	
	public void setSdGoals()
	{
		sdGoals.clear();
		sdGoals.addAll(sdGoalsRepo.findAll());
	}
	
	public ObservableList<SdGoal> getBeschikbareSdgs()
	{
		//TODO filter op beschikbaar
		categorien.forEach(c -> {
			List<SdGoal> goals = c.getSdGoals();
			for(int i = 0; i < goals.size(); i++) {
				sdGoals.remove(goals.get(i));
			}
		});
		//setSdGoals();
		System.out.println("Alle SdGoals ophalen");
		return FXCollections.unmodifiableObservableList(sdGoals);
	}
	
	public void voegSdGoalObserverToe(ListChangeListener<SdGoal> listener)
	{
		sdGoals.addListener(listener);
	}
	
	// CATEGORIE BEHEREN
	// ______________________________________________________________________________________________
	
	public void setCategorieRepo(CategorieDao mock)
	{
		categorieRepo = mock;
	}
	
	private void setCategorien()
	{
		categorien.clear();
		categorien.addAll(categorieRepo.findAll());
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<Categorie> getCategorien()
	{
		setCategorien();
		System.out.println("Alle Categoriën ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Categorie>)(Object)categorien);
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		categorien.addListener(listener);
	}
	
	public void voegCategorieToe(DTOCategorie categorie)
	{
		try
		{
			System.out.printf("Categorie %s inserten in databank%n", categorie.toString());
			GenericDaoJpa.startTransaction();
			categorieRepo.insert(new SDGCategorie(categorie));
			GenericDaoJpa.commitTransaction();
		}
		catch(DatabaseException e)
		{
			throw new IllegalArgumentException(String.format("Categorie met naam %s bestaat al", categorie.toString()));
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een Categorie");
		}
		
		setCategorien();
	}
	
	public void verwijderCategorie()
	{
		if(currentCategorie == null) throw new IllegalArgumentException("Er is geen categorie geselecteerd");
		
		try
		{	
			List<SDGCategorie> categorien = categorieRepo.findAll();
			if( categorien.size() == 1)
			{
				throw new IllegalArgumentException("Kan enigste categorie niet verwijderen");
			}
			
			System.out.printf("Categorie %s verwijderen uit databank%n", currentCategorie.toString());
			GenericDaoJpa.startTransaction();
			categorieRepo.delete( (SDGCategorie)currentCategorie);
			GenericDaoJpa.commitTransaction();	
		}
		catch(IllegalArgumentException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het verwijderen van een Categorie");
		}
		
		setCategorien();
		
	}
	
	public void wijzigCategorie(DTOCategorie categorie)
	{
		SDGCategorie categorieInRepo = categorieRepo.getByNaam(currentCategorie.getNaam()); 
		System.out.printf("categorieInRepo = %s, currentCategorie = %s", categorieInRepo.getCategorieID(), currentCategorie.getCategorieID());
		if(categorieInRepo != null && categorieInRepo.getCategorieID() != currentCategorie.getCategorieID())
		{
			throw new IllegalArgumentException("Er bestaat al een categorie met deze naam");
		}
		for(Categorie cat : getCategorien())
		{
			for(SdGoal sdg : categorie.sdgoals)
			{
			
				if(cat.getSdGoals().contains(sdg) && cat.getNaam() != currentCategorie.getNaam())
				{
					throw new IllegalArgumentException("Een meegegeven SdGoal zit al in een andere Categorie");
				}
			}
		}
		
		updateCategorie(categorie);
	}

	public void setCurrentCategorie(Categorie categorie)
	{
		currentCategorie = categorie;
	}

	public Categorie getCurrentCategorie()
	{
		return currentCategorie;
	}
	
	private void updateCategorie(DTOCategorie categorie)
	{
		if(currentCategorie == null) throw new IllegalArgumentException("Er is geen categorie geselecteerd");
		try
		{
			GenericDaoJpa.startTransaction();
			SDGCategorie cat = new SDGCategorie(categorie);
			cat.setCategorieID(currentCategorie.getCategorieID());
			categorieRepo.update(cat);
			GenericDaoJpa.commitTransaction();
		}
		catch(Exception e)
		{
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een Categorie update");
		}
		
		setCategorien();
	}
	
	// MVO DOELSTELLING BEHEREN
	// ______________________________________________________________________________________________
	
	public void setMVODoelstellingenRepo(MVODoelstellingDao mock) {
		this.mvoDoelstellingRepo = mock;
	}
	
	public Doelstelling getCurrentDoelstelling() {
		return currentDoelstelling;
	}
	
	public void setCurrentDoelstelling(Doelstelling doelstelling) {
		this.currentDoelstelling = doelstelling;
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<Doelstelling> getDoelstellingen(){
		setDoelstellingen();
		System.out.println("Alle doelstellingen ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Doelstelling>)(Object)doelstellingen);
	}
	
	private void setDoelstellingen() {
		doelstellingen.clear();
		doelstellingen.addAll(mvoDoelstellingRepo.findAll());
	}
	
	public void voegMVODoelstellingToe(DTOMVODoelstelling doelstelling) {
		try
		{
			System.out.printf("Doelstelling %s inserten in databank%n", doelstelling.toString());
			GenericDaoJpa.startTransaction();
			mvoDoelstellingRepo.insert(new Composite(doelstelling));
			GenericDaoJpa.commitTransaction();
		}
		catch(DatabaseException e)
		{
			throw new IllegalArgumentException(String.format("MVO Doelstelling met naam %s bestaat al", doelstelling.toString()));
		}
		catch(Exception e)
		{
			System.out.printf("%s", e.getMessage());
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een MVO Doelstelling");
		}
		
		setDoelstellingen();
	}
	
	public void verwijderMVODoelstelling()
	{
		if(currentDoelstelling == null) throw new IllegalArgumentException("Er is geen MVO doelstelling geselecteerd");
		
		try
		{	
			List<Component> doelstellingen = (List<Component>) mvoDoelstellingRepo.findAll();
			if( doelstellingen.size() == 1)
			{
				throw new IllegalArgumentException("Kan enigste MVO doelstelling niet verwijderen");
			}
			
			System.out.printf("MVO Doelstelling %s verwijderen uit databank%n", currentDoelstelling.toString());
			GenericDaoJpa.startTransaction();
			mvoDoelstellingRepo.delete( (Component)currentDoelstelling);
			GenericDaoJpa.commitTransaction();	
		}
		catch(IllegalArgumentException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het verwijderen van een MVO Doelstelling");
		}
		
		setDoelstellingen();
		
	}
	
	public void wijzigMVODoelstelling(DTOMVODoelstelling doelstelling)
	{
		Component doelstellingInRepo = mvoDoelstellingRepo.getByNaam(doelstelling.naam);
		
		if(doelstellingInRepo != null && doelstellingInRepo.getDoelstellingID() != currentDoelstelling.getDoelstellingID())
		{
			throw new IllegalArgumentException("Er bestaat al een MVO Doelstelling met deze naam");
		}

		updateMVODoelstelling(doelstelling);
	}
	
	private void updateMVODoelstelling(DTOMVODoelstelling doelstelling)
	{
		if(currentDoelstelling == null) throw new IllegalArgumentException("Er is geen MVO Doelstelling geselecteerd");
		try
		{
			GenericDaoJpa.startTransaction();
			Composite d = new Composite(doelstelling);
			d.setDoelstellingID(currentDoelstelling.getDoelstellingID());
			mvoDoelstellingRepo.update(d);
			GenericDaoJpa.commitTransaction();
		}
		catch(Exception e)
		{
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een doelstelling update");
		}
		
		setDoelstellingen();
	}
	
	
	// DATASOURCES BEHEREN
	// ______________________________________________________________________________________________
	
	public void setMVODatasourceRepo(MVODatasourceDao mock)
	{
		mvoDatasourceRepo = mock;
	}
	
	public Datasource getCurrentDatasource()
	{
		return currentDatasource;
	}
	
	public void setCurrentDatasource(Datasource datasource)
	{
		currentDatasource = datasource;
	}
	
	private void setDatasources()
	{
		datasources.clear();
		datasources.addAll(mvoDatasourceRepo.findAll());
	}
	
	public void voegMVODatasourceToe(DTODatasource datasource) throws SQLIntegrityConstraintViolationException, IllegalStateException
	{
		try
		{
			System.out.printf("Datasource %s inserten in databank%n", datasource.toString());
			GenericDaoJpa.startTransaction();
			mvoDatasourceRepo.insert(new MVODatasource(datasource));
			GenericDaoJpa.commitTransaction();
		}
		catch(IllegalArgumentException e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
		catch(RollbackException e) {
			throw new IllegalArgumentException(String.format("Datasource met naam '%s' bestaat al", datasource.toString()));
		}
		catch(DatabaseException e)
		{
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(String.format("Datasource met naam '%s' bestaat al", datasource.toString()));
		}
		catch(Exception e)
		{
			System.out.println(e);
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een Datasource");
		}
		
		setDatasources();
	}
	
	public void verwijderMVODatasource()
	{
		if(currentDatasource == null) throw new IllegalArgumentException("Er is geen datasource geselecteerd");
		
		try
		{	
			List<MVODatasource> datasources = mvoDatasourceRepo.findAll();
			if(datasources.size() == 1)
			{
				throw new IllegalArgumentException("Kan enigste datasource niet verwijderen");
			}
			
			System.out.printf("Datasource %s verwijderen uit databank%n", currentDatasource.toString());
			GenericDaoJpa.startTransaction();
			mvoDatasourceRepo.delete( (MVODatasource)currentDatasource);
			GenericDaoJpa.commitTransaction();	
		}
		catch(IllegalArgumentException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het verwijderen van een Datasource");
		}
		
		setDatasources();
		
	}
	
	public void wijzigMVODatasource(DTODatasource datasource)
	{
		MVODatasource datasourceInRepo = mvoDatasourceRepo.getByNaam(datasource.naam);
		if(datasourceInRepo != null && datasourceInRepo.getDatasourceID() != currentDatasource.getDatasourceID())
		{
			throw new IllegalArgumentException("Er bestaat al een datasource met deze naam");
		}
		
		updateDatasource(datasource);
	}
	
	private void updateDatasource(DTODatasource datasource)
	{
		if(currentDatasource == null) throw new IllegalArgumentException("Er is geen datasource geselecteerd");
		try
		{
			GenericDaoJpa.startTransaction();
			MVODatasource datas = new MVODatasource(datasource);
			datas.setDatasourceID(currentDatasource.getDatasourceID());
			mvoDatasourceRepo.update(datas);
			GenericDaoJpa.commitTransaction();
		}
		catch(Exception e)
		{
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een Datasource update");
		}
		
		setDatasources();
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<Datasource> getDatasources()
	{
		setDatasources();
		System.out.println("Alle Categoriën ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Datasource>)(Object)datasources);
	}
	
	
	
}