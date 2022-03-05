package domein;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.persistence.exceptions.DatabaseException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GenericDaoJpa;
import repository.SdGoalDao;
import repository.SdGoalDaoJpa;

public class Fluvius
{
	private ObservableList<SDGCategorie> categorien = FXCollections.observableArrayList();
	private ObservableList<SdGoal> sdGoals = FXCollections.observableArrayList();
	
	private CategorieDao categorieRepo;
	private SdGoalDao sdGoalsRepo;
	
	private Categorie currentCategorie;
	
	public Fluvius()
	{
		setCategorieRepo(new CategorieDaoJpa());
		setSdGoalRepo(new SdGoalDaoJpa());
		
		setCategorien();
		setSdGoals();
	}
	
	public void setCategorieRepo(CategorieDao mock)
	{
		categorieRepo = mock;
	}
	
	public void setSdGoalRepo(SdGoalDao mock)
	{
		sdGoalsRepo = mock;
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
	
	public void setSdGoals()
	{
		sdGoals.clear();
		sdGoals.addAll(sdGoalsRepo.findAll());
	}
	
	public ObservableList<SdGoal> getBeschikbareSdgs()
	{
		//TODO filter op beschikbaar
		setSdGoals();
		System.out.println("Alle SdGoals ophalen");
		return FXCollections.unmodifiableObservableList(sdGoals);
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		categorien.addListener(listener);
	}
	
	public void voegSdGoalObserverToe(ListChangeListener<SdGoal> listener)
	{
		sdGoals.addListener(listener);
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
		SDGCategorie categorieInRepo = categorieRepo.getByNaam(categorie.naam);
		if(categorieInRepo != null && categorieInRepo.getCategorieID() != currentCategorie.getCategorieID())
		{
			throw new IllegalArgumentException("Er bestaat al een categorie met deze naam");
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
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een Categorie update");
		}
		
		setCategorien();
	}
}
