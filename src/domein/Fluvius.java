package domein;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.RollbackException;

import org.eclipse.persistence.exceptions.DatabaseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.CategorieDao;
import repository.MVODatasourceDao;
import repository.MVODoelstellingDao;
import repository.SdGoalDao;

public class Fluvius {
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

	// CONSTRUCTOR
	// ______________________________________________________________________________________________

	public Fluvius(CategorieDao categorieDaoJpa, SdGoalDao sdGoalDaoJpa, MVODoelstellingDao mvoDoelstellingDaoJpa,
			MVODatasourceDao mvoDatasourceDaoJpa) {
		setCategorieRepo(categorieDaoJpa);
		setSdGoalRepo(sdGoalDaoJpa);
		setMVODoelstellingenRepo(mvoDoelstellingDaoJpa);
		setMVODatasourceRepo(mvoDatasourceDaoJpa);

		setCategorien();
		setSdGoals();
		setDoelstellingen();
		setDatasources();

	}

	// SDG
	// ______________________________________________________________________________________________
	public void setSdGoalRepo(SdGoalDao mock) {
		sdGoalsRepo = mock;
	}

	public void setSdGoals() {
		sdGoals.clear();
		sdGoals.addAll(sdGoalsRepo.findAll());
	}

	public ObservableList<SdGoal> getBeschikbareSdgs() {
		categorien.forEach(c -> {
			List<SdGoal> goals = c.getSdGoals();
			for (int i = 0; i < goals.size(); i++) {
				sdGoals.remove(goals.get(i));
			}
		});

		List<SdGoal> goalsToRemove = new ArrayList<>();
		sdGoals.forEach(g -> {
			if (g.getParentSDG() != null) {
				if (sdGoals.stream().filter(g2 -> {
					return g.getParentSDG().getId() == g2.getId();
				}).findFirst().orElse(null) == null) {
					goalsToRemove.add(g);
				}
			}
		});
		goalsToRemove.forEach(g -> sdGoals.remove(g));
		System.out.println("Beschikbare SdGoals ophalen");
		return FXCollections.unmodifiableObservableList(sdGoals);
	}

	public ObservableList<SdGoal> getSdgs() {
		setSdGoals();
		System.out.println("Alle SdGoals ophalen");
		return FXCollections.unmodifiableObservableList(sdGoals);
	}

	// CATEGORIE BEHEREN
	// ______________________________________________________________________________________________

	public void setCategorieRepo(CategorieDao mock) {
		categorieRepo = mock;
	}

	private void setCategorien() {
		categorien.clear();
		categorien.addAll(categorieRepo.findAll());
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Categorie> getCategorien() {
		setCategorien();
		System.out.println("Alle Categorie?n ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Categorie>) (Object) categorien);
	}

	public Categorie getCurrentCategorie() {
		return currentCategorie;
	}

	public void setCurrentCategorie(Categorie categorie) {
		currentCategorie = categorie;
	}

	public void voegCategorieToe(DTOCategorie categorie) {
		try {
			SDGCategorie nieuweCategorie = categorieRepo.getByNaam(categorie.naam);
			if (nieuweCategorie != null) {
				throw new IllegalArgumentException("Er bestaat al een categorie met deze naam");
			}

			System.out.printf("Categorie %s inserten in databank%n", categorie.toString());
			categorieRepo.startTransaction();
			categorieRepo.insert(new SDGCategorie(categorie));
			categorieRepo.commitTransaction();
		} catch (DatabaseException e) {
			throw new IllegalArgumentException(String.format("Categorie met naam %s bestaat al", categorie.toString()));
		} catch (IllegalArgumentException e) {

			if (categorie.naam.equals("") || categorie.naam == null) {
				if (categorieRepo.isActive()) {
					categorieRepo.rollbackTransaction();
				}
				throw new IllegalArgumentException(String.format("Naam mag niet leeg zijn", categorie.naam));
			}
			if (e.getMessage().equals("Er bestaat al een categorie met deze naam")) {
				if (categorieRepo.isActive()) {
					categorieRepo.rollbackTransaction();
				}
				throw new IllegalArgumentException(String.format("Categorie met naam %s bestaat al", categorie.naam));
			}
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
			if (categorie.sdgoals.isEmpty() || categorie.sdgoals == null) {
				throw new IllegalArgumentException(String.format("SDG's mogen niet leeg zijn", categorie.naam));
			}

		} catch (Exception e) {
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
			System.out.println(e.getMessage());
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een Categorie");
		}

		setCategorien();
	}

	public void verwijderCategorie() {
		if (currentCategorie == null)
			throw new IllegalArgumentException("Er is geen categorie geselecteerd");

		try {
			List<SDGCategorie> categorien = categorieRepo.findAll();
			if (categorien.size() == 1) {
				throw new IllegalArgumentException("Kan enigste categorie niet verwijderen");
			}

			System.out.printf("Categorie %s verwijderen uit databank%n", currentCategorie.toString());
			categorieRepo.startTransaction();
			categorieRepo.delete((SDGCategorie) currentCategorie);
			categorieRepo.commitTransaction();
		} catch (IllegalArgumentException e) {
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
			throw e;
		} catch (Exception e) {
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het verwijderen van een Categorie");
		}

		setCategorien();

	}

	public void wijzigCategorie(DTOCategorie categorie) {

		Categorie c = currentCategorie;
		SDGCategorie nieuweCategorie = categorieRepo.getByNaam(categorie.naam);

		if (nieuweCategorie != null && nieuweCategorie.getCategorieID() != currentCategorie.getCategorieID()) {
			throw new IllegalArgumentException("Er bestaat al een categorie met deze naam");
		}
		for (Categorie cat : getCategorien()) {
			setCurrentCategorie(c);
			for (SdGoal sdg : categorie.sdgoals) {
				if (currentCategorie != null) {
					if (cat.getSdGoals().contains(sdg) && cat.getNaam() != currentCategorie.getNaam()) {
						throw new IllegalArgumentException("Een meegegeven SdGoal zit al in een andere Categorie");
					}
				}
			}
		}

		updateCategorie(categorie);
	}

	private void updateCategorie(DTOCategorie categorie) {
		if (currentCategorie == null)
			throw new IllegalArgumentException("Er is geen categorie geselecteerd");
		try {
			categorieRepo.startTransaction();
			SDGCategorie cat = new SDGCategorie(categorie);
			cat.setCategorieID(currentCategorie.getCategorieID());
			categorieRepo.update(cat);
			categorieRepo.commitTransaction();
		} catch (IllegalArgumentException e) {
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
			setCurrentCategorie(currentCategorie);
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			if (categorieRepo.isActive()) {
				categorieRepo.rollbackTransaction();
			}
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
	public ObservableList<Doelstelling> getDoelstellingen() {
		setDoelstellingen();
		System.out.println("Alle doelstellingen ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Doelstelling>) (Object) doelstellingen);
	}

	public List<Doelstelling> geefDoelstellingenDieGeenSubsHebben() {
		List<Doelstelling> doelZonderSubs = new ArrayList<>();
		for (Doelstelling d : doelstellingen) {
			if (d.getParentComponent() == null && d.getComponents().isEmpty()) {
				doelZonderSubs.add(d);
			}
		}

		return doelZonderSubs;
	}

	public List<Doelstelling> geefDoelstellingenDieSubsHebben() {
		List<Doelstelling> doelMetSubs = new ArrayList<>();
		doelstellingen.forEach(d -> {
			Iterator<Component> iterator = new CompositeIterator(Arrays.asList(d).iterator());
			while (iterator.hasNext()) {
				Component component = iterator.next();

				if (!component.isLeaf()) {
					doelMetSubs.add(component);
				}
			}
		});
		return doelMetSubs;
	}

	private void setDoelstellingen() {
		doelstellingen.clear();
		doelstellingen.addAll(mvoDoelstellingRepo.findAll());
	}

	public void voegMVODoelstellingToeZonderSubs(DTOMVODoelstelling doelstelling) {
		try {
			System.out.printf("Doelstelling %s inserten in databank%n", doelstelling.naam);
			mvoDoelstellingRepo.startTransaction();

			Component doelstellingInRepo = mvoDoelstellingRepo.getByNaam(doelstelling.naam);
			if (doelstellingInRepo != null) {
				throw new IllegalArgumentException(
						String.format("MVO Doelstelling met naam %s bestaat al", doelstelling.naam));
			}
			// historiek
			Component c = new Leaf(doelstelling);

			System.out.println("adding leaf");

			mvoDoelstellingRepo.insert(c);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			System.out.printf("%s", e.getMessage());
			throw new IllegalArgumentException(
					"Er is een probleem opgetreden bij het toevoegen van een MVO Doelstelling");
		} finally {
			mvoDoelstellingRepo.commitTransaction();
		}

		setDoelstellingen();
	}

	public void voegMVODoelstellingToeMetSubs(DTOMVODoelstelling doelstelling) {
		try {
			System.out.printf("Doelstelling %s inserten in databank%n", doelstelling.naam);
			mvoDoelstellingRepo.startTransaction();

			Component doelstellingInRepo = mvoDoelstellingRepo.getByNaam(doelstelling.naam);
			if (doelstellingInRepo != null) {
				throw new IllegalArgumentException(
						String.format("MVO Doelstelling met naam %s bestaat al", doelstelling.naam));
			}

			Component c = new Composite(doelstelling);

			System.out.println("adding value");

			mvoDoelstellingRepo.insert(c);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			System.out.printf("%s", e.getMessage());
			throw new IllegalArgumentException(
					"Er is een probleem opgetreden bij het toevoegen van een MVO Doelstelling");
		} finally {
			mvoDoelstellingRepo.commitTransaction();
		}

		setDoelstellingen();
	}

	public void verwijderMVODoelstelling() {
		if (currentDoelstelling == null)
			throw new IllegalArgumentException("Er is geen MVO doelstelling geselecteerd");

		try {
			List<Component> doelstellingen = (List<Component>) mvoDoelstellingRepo.findAll();
			if (doelstellingen.size() == 1) {
				throw new IllegalArgumentException("Kan enigste MVO doelstelling niet verwijderen");
			}

			System.out.printf("MVO Doelstelling %s verwijderen uit databank%n", currentDoelstelling.toString());
			mvoDoelstellingRepo.startTransaction();

			// hoogste niveau
			Component parent = null;

			if (currentDoelstelling.getParentComponent() == null) {
				System.out.println("test");
				mvoDoelstellingRepo.delete((Component) currentDoelstelling);

			}

			// bevat hoofd
			else {
				System.out.println("test1");
				// verwijderen en alles weer updaten tot hoogste niveau
				parent = currentDoelstelling.getParentComponent();

				mvoDoelstellingRepo.delete((Component) currentDoelstelling);
				parent.getComponents().remove(currentDoelstelling);

				while (parent.getParentComponent() != null) {
					parent = parent.getParentComponent();
				}

				parent.getBerekendewaarde();
				mvoDoelstellingRepo.update(parent);

			}

			mvoDoelstellingRepo.commitTransaction();
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(
					"Er is een probleem opgetreden bij het verwijderen van een MVO Doelstelling");
		}

		setDoelstellingen();

	}

	public void wijzigMVODoelstelling(DTOMVODoelstelling doelstelling) {
		Component doelstellingInRepo = mvoDoelstellingRepo.getByNaam(doelstelling.naam);

		if (doelstellingInRepo != null
				&& doelstellingInRepo.getDoelstellingID() != currentDoelstelling.getDoelstellingID()) {
			throw new IllegalArgumentException("Er bestaat al een MVO Doelstelling met deze naam");
		}

		updateMVODoelstelling(doelstelling);
	}

	private void updateMVODoelstelling(DTOMVODoelstelling doelstelling) {

		if (currentDoelstelling == null)
			throw new IllegalArgumentException("Er is geen MVO Doelstelling geselecteerd");
		try {

			mvoDoelstellingRepo.startTransaction();

			Component comp;
			if (doelstelling.datasource == null) {
				comp = new Composite(doelstelling);
			} else {
				comp = new Leaf(doelstelling);
			}

			Component original = mvoDoelstellingRepo.get(currentDoelstelling.getDoelstellingID());
			if (original != null) {
				comp.setValues(original.getValues());
			}

			// delete

			comp.setParentComponent(currentDoelstelling.getParentComponent());
			comp.setDoelstellingID(currentDoelstelling.getDoelstellingID());

			mvoDoelstellingRepo.update(comp);
			mvoDoelstellingRepo.commitTransaction();

			Component parent = comp;

			while (parent.getParentComponent() != null) {
				parent = parent.getParentComponent();
			}

			System.out.printf("Dit is de naam van de parent : %s", parent.getNaam());

			mvoDoelstellingRepo.startTransaction();
			parent.getBerekendewaarde();
			mvoDoelstellingRepo.update(parent);
			mvoDoelstellingRepo.commitTransaction();

		} catch (IllegalArgumentException e) {
			if (mvoDoelstellingRepo.isActive()) {
				mvoDoelstellingRepo.rollbackTransaction();
			}
			throw new IllegalArgumentException(e.getMessage());
		}
		
		catch (Exception e) {
			if (mvoDoelstellingRepo.isActive()) {
				mvoDoelstellingRepo.rollbackTransaction();
			}

			System.out.println(e.getMessage());
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een doelstelling update");
		}

		setDoelstellingen();

	}

	// DATASOURCES BEHEREN
	// ______________________________________________________________________________________________

	public void setMVODatasourceRepo(MVODatasourceDao mock) {
		mvoDatasourceRepo = mock;
	}

	public Datasource getCurrentDatasource() {
		return currentDatasource;
	}

	public void setCurrentDatasource(Datasource datasource) {
		currentDatasource = datasource;
	}

	private void setDatasources() {
		datasources.clear();
		datasources.addAll(mvoDatasourceRepo.findAll());
	}

	public void voegMVODatasourceToe(DTODatasource datasource)
			throws SQLIntegrityConstraintViolationException, IllegalStateException {
		try {
			// controle op unieke naam
			MVODatasource nieuweDatasource = mvoDatasourceRepo.getByNaam(datasource.naam);
			if (nieuweDatasource != null) {
				throw new IllegalArgumentException("Er bestaat al een datasource met deze naam");
			}

			System.out.printf("Datasource %s inserten in databank%n", datasource.toString());
			mvoDatasourceRepo.startTransaction();
			mvoDatasourceRepo.insert(new MVODatasource(datasource));
			mvoDatasourceRepo.commitTransaction();
		}

		catch (IllegalArgumentException e) {
			if (mvoDatasourceRepo.isActive()) {
				mvoDatasourceRepo.rollbackTransaction();
			}

			throw new IllegalArgumentException(e.getMessage());
		} catch (RollbackException e) {
			throw new IllegalArgumentException(
					String.format("Datasource met naam '%s' bestaat al", datasource.toString()));
		} catch (DatabaseException e) {
			if (mvoDatasourceRepo.isActive()) {
				mvoDatasourceRepo.rollbackTransaction();
			}
			throw new IllegalArgumentException(
					String.format("Datasource met naam '%s' bestaat al", datasource.toString()));
		}

		catch (Exception e) {
			if (mvoDatasourceRepo.isActive()) {
				mvoDatasourceRepo.rollbackTransaction();
			}
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een Datasource");
		}

		setDatasources();
	}

	public void verwijderMVODatasource() {
		if (currentDatasource == null)
			throw new IllegalArgumentException("Er is geen datasource geselecteerd");

		try {
			List<MVODatasource> datasources = mvoDatasourceRepo.findAll();
			if (datasources.size() == 1) {
				throw new IllegalArgumentException("Kan enigste datasource niet verwijderen");
			}

			System.out.printf("Datasource %s verwijderen uit databank%n", currentDatasource.toString());
			mvoDatasourceRepo.startTransaction();
			mvoDatasourceRepo.delete((MVODatasource) currentDatasource);
			mvoDatasourceRepo.commitTransaction();
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException("Een datasource die gebruikt wordt kan niet verwijderd worden.");
		}

		setDatasources();

	}

	public void wijzigMVODatasource(DTODatasource datasource) {
		MVODatasource datasourceInRepo = mvoDatasourceRepo.getByNaam(datasource.naam);
		if (datasourceInRepo != null && datasourceInRepo.getDatasourceID() != currentDatasource.getDatasourceID()) {
			throw new IllegalArgumentException("Er bestaat al een datasource met deze naam");
		}

		updateDatasource(datasource);
	}

	private void updateDatasource(DTODatasource datasource) {
		if (currentDatasource == null)
			throw new IllegalArgumentException("Er is geen datasource geselecteerd");
		try {
			mvoDatasourceRepo.startTransaction();
			MVODatasource datas = new MVODatasource(datasource);
			datas.setDatasourceID(currentDatasource.getDatasourceID());
			mvoDatasourceRepo.update(datas);
			mvoDatasourceRepo.commitTransaction();
		} catch (IllegalArgumentException e) {
			if (mvoDatasourceRepo.isActive()) {
				mvoDatasourceRepo.rollbackTransaction();
			}
			throw e;
		} catch (Exception e) {
			if (mvoDatasourceRepo.isActive()) {
				mvoDatasourceRepo.rollbackTransaction();
			}
			throw new IllegalArgumentException("Er is een probleem opgetreden bij een Datasource update");
		}

		setDatasources();
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Datasource> getDatasources() {
		setDatasources();
		System.out.println("Alle Categorie?n ophalen");
		return FXCollections.unmodifiableObservableList((ObservableList<Datasource>) (Object) datasources);
	}

}