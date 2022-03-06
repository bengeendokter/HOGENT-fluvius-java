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
	private ObservableList<MVODoelstellingComponent> doelstellingen = FXCollections.observableArrayList();
	
	private CategorieDao categorieRepo;
	private SdGoalDao sdGoalsRepo;
	private MVODoelstellingDao mvoDoelstellingRepo;
	
	private Categorie currentCategorie;
	private MVODoelstellingComponent currentDoelstelling;
	
	// CONSTRUCTOR
	// ______________________________________________________________________________________________
	
	public Fluvius()
	{
		setCategorieRepo(new CategorieDaoJpa());
		setSdGoalRepo(new SdGoalDaoJpa());
		setMVODoelstellingenRepo(new MVODoelstellingDaoJpa());
		
		setCategorien();
		setSdGoals();
		setDoelstellingen();
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
		setSdGoals();
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
		return (Doelstelling) this.currentDoelstelling;
	}
	
	// TODO
	public void setCurrentDoelstelling(DTOMVODoelstelling doelstelling) {
		//this.currentDoelstelling = doelstelling;
	}
	
	public ObservableList<Doelstelling> getDoelstellingen(){
		return null;
		//return FXCollections.unmodifiableObservableList(doelstellingen);
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
			mvoDoelstellingRepo.insert(new DoelstellingMVO(doelstelling));
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
			List<MVODoelstellingComponent> doelstellingen = (List<MVODoelstellingComponent>) mvoDoelstellingRepo.findAll();
			if( doelstellingen.size() == 1)
			{
				throw new IllegalArgumentException("Kan enigste MVO doelstelling niet verwijderen");
			}
			
			System.out.printf("MVO Doelstelling %s verwijderen uit databank%n", currentDoelstelling.toString());
			GenericDaoJpa.startTransaction();
			mvoDoelstellingRepo.delete( (MVODoelstellingComponent)currentDoelstelling);
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
		MVODoelstellingComponent doelstellingInRepo = mvoDoelstellingRepo.getByNaam(doelstelling.naam);

		updateMVODoelstelling(doelstelling);
	}
	
	private void updateMVODoelstelling(DTOMVODoelstelling doelstelling)
	{
//		if(currentDoelstelling == null) throw new IllegalArgumentException("Er is geen MVO Doelstelling geselecteerd");
//		try
//		{
//			GenericDaoJpa.startTransaction();
//			SDGCategorie cat = new SDGCategorie(categorie);
//			cat.setCategorieID(currentCategorie.getCategorieID());
//			categorieRepo.update(cat);
//			GenericDaoJpa.commitTransaction();
//		}
//		catch(Exception e)
//		{
//			throw new IllegalArgumentException("Er is een probleem opgetreden bij een Categorie update");
//		}
//		
//		setCategorien();
	}
	
	
	
	
}