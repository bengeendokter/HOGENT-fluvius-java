package gui;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DTOCategorie;
import domein.DTODatasource;
import domein.Datasource;
import domein.Doelstelling;
import domein.Composite;
import domein.DomeinController;
import domein.Rol;
import domein.SdGoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CategorieResponsiveController extends BorderPane
{
	
	private DomeinController dc;
	
	@FXML
	private BorderPane groeneBalk;
	
	@FXML
	private ImageView ventje;
	@FXML
	private Label lblNaamIngelogdeGebruiker;
	@FXML
	private Label lblFunctieIngelogdeGebruiker;
	
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabMVO;
	@FXML
	private Tab tabCat;
	@FXML
	private Tab tabData;
	
	@FXML
	private ListView<Categorie> listCategorieen;
	@FXML
	private Button btnAddCategorie;
	@FXML
	private Label vartextCat;
	@FXML
	private Button catBewerken;
	@FXML
	private Button catVerwijderen;
	@FXML
	private TextField naamCategorie;
	@FXML
	private ListView<String> listIcoon;
	@FXML
	private Button btnRemoveSdGoal;
	@FXML
	private Button catOpslaan;
	@FXML
	private Button catAnnuleer;
	@FXML
	private Label kiesIcoon;
	@FXML
	private Label labelKiesSdGoal;
	@FXML
	private ListView<SdGoal> listSdGoal;
	@FXML
	private ListView<SdGoal> listKiesSdGoal;
	@FXML
	private Label catError;
	@FXML
	private ImageView catIcoon;
	//lijst met namen/paths van iconen
	private List<String> iconen = (List<String>) Arrays
			.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png",
					"file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	@FXML
	private ImageView arrow1;
	@FXML
	private ImageView arrow2;
	@FXML
	private ListView<Datasource> listDatasources1;
	@FXML
	private Button btnAddDatasource1;
	@FXML
	private Label vartextData;
	@FXML
	private TextField naamDatasource;
	@FXML
	private TextArea datasourceLink;
	@FXML
	private ChoiceBox<String> datasourceType;
	@FXML
	private Button btnDataBewerken;
	@FXML
	private Button btnDataVerwijderen;
	@FXML
	private Button btnDataOpslaan;
	@FXML
	private Button btnDataAnnuleer;
	@FXML
	private Label dataError;
	
	// MVO Doelstelling attributen
	@FXML
	private ListView<Doelstelling> listDoelen;
	
	@FXML
	private TextField naamDoel;
	
	@FXML
	private ChoiceBox<String> selectionDoelType;
	
	@FXML
	private TextField doelDoelwaarde;
	
	@FXML
	private ImageView doelIcoon;
	
	@FXML
	private ListView<String> listDoelIcoon;
	
	@FXML
	private ChoiceBox<SdGoal> selectionDoelHoofdSDG;
	
	@FXML
	private ChoiceBox<SdGoal> selectionDoelSubSDG;
	
	@FXML
	private ListView<Doelstelling> listDoelKiesSubDoel;
	
	@FXML
	private ListView<Doelstelling> listDoelSubDoelen;
	
	@FXML
	private CheckBox checkboxMVORol;
	
	@FXML
	private CheckBox checkboxManagerRol;
	
	@FXML
	private CheckBox checkboxDirectieRol;
	
	@FXML
	private CheckBox checkboxStakeholderRol;
	
	@FXML
	private ListView<Datasource> listDoelKiesData;
	
	@FXML
	private ListView<Datasource> listDoelDatasources;
	
	@FXML
	private Button btnAddDoel;
	
	@FXML
	private Button btnDoelWijzig;
	
	@FXML
	private Button btnDoelVerwijder;
	
	@FXML
	private Button btnOplaanDoel;
	
	@FXML
	private Button btnAnuleerDoel;
	
	@FXML
	private VBox vboxListSubDoelen;
	
	@FXML
	private VBox vboxPijlenSubDoelen;
	
	@FXML
	private VBox vboxListDatasources;
	
	@FXML
	private VBox vboxPijlenDatasources;
	
	@FXML
	private VBox vboxListIcons;
	
	@FXML
	private Label doelError;
	
	private List<String> doelTypes = new ArrayList<>(Arrays.asList("huidige waarde", "gewogen gemiddelde", "historische waarde"));
	
	public CategorieResponsiveController(DomeinController dc)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieResponsive.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			this.dc = dc;
			
			lblNaamIngelogdeGebruiker.setText(dc.getAangemeldeGebruiker().getGebruikersnaam());
			lblFunctieIngelogdeGebruiker.setText(dc.getAangemeldeGebruiker().getRol());
			
			catError.setVisible(false);
			dataError.setVisible(false);
			
			listKiesSdGoal.setVisible(false);
			arrow1.setVisible(false);
			arrow2.setVisible(false);
			listIcoon.setVisible(false);
			btnRemoveSdGoal.setVisible(false);
			kiesIcoon.setVisible(false);
			labelKiesSdGoal.setVisible(false);
			
			catOpslaan.setVisible(false);
			catAnnuleer.setVisible(false);
			btnDataOpslaan.setVisible(false);
			btnDataAnnuleer.setVisible(false);
			
			naamDatasource.setEditable(false);
			datasourceLink.setEditable(false);
			datasourceType.setDisable(true);
			
			datasourceType.getItems().addAll("csv", "excel", "databank");
			
			// MVO Doelstellingen
			// -----------------------------------------------------------------------------------
//			showDoelMinimal();
//			vulDoelList();
//			onTabChange();
//			listDoelen.getSelectionModel().selectFirst();
			
			// ICONEN TABBLADEN INSTELLEN
			///////////////////////////////////////////////////////////////////////////////////
			tabPane.getTabs().forEach(e -> {
				if(e.getText().equals("MVO Doelstelling beheren"))
					e.setGraphic(new ImageView(new Image("file:src/images/doelstelling.png", 25, 25, true, true)));
				else if(e.getText().equals("Categorie beheren"))
					e.setGraphic(new ImageView(new Image("file:src/images/category.png", 25, 25, true, true)));
				else if(e.getText().equals("Datasource beheren"))
					e.setGraphic(new ImageView(new Image("file:src/images/data.png", 25, 25, true, true)));
			});
			
			tabPane.widthProperty().addListener((observable, oldValue, newValue) -> {
				tabPane.setTabMinWidth(tabPane.getWidth() / tabPane.getTabs().size());
				tabPane.setTabMaxWidth(tabPane.getWidth() / tabPane.getTabs().size());
			});
			
			listCategorieen.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
					newValue) -> dc.setCurrentCategorie(listCategorieen.getSelectionModel().getSelectedItem()));
			listDatasources1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue,
					newValue) -> dc.setCurrentDatasource(listDatasources1.getSelectionModel().getSelectedItem()));
			
			listCategorieen.setItems(dc.getCategorien());
			listDatasources1.setItems(dc.getDatasources());
			// TODO end
			
			listCategorieen.setCellFactory(param -> new ListCell<Categorie>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(Categorie name, boolean empty)
				{
					super.updateItem(name, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(name.getNaam());
						imageView.setImage(new Image(name.getIcon(), 50, 50, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			//eerste categorie in listview selecteren
			//als je bij het initeel starten direct op de verwijder knop klikt, dan wordt de eerste verwijderd
			listCategorieen.getSelectionModel().selectFirst();
			listDatasources1.getSelectionModel().selectFirst();
			
			listKiesSdGoal.setItems(dc.getBeschikbareSdgs().filtered(s -> s.getIcon() != null)
					.sorted(Comparator.comparing(SdGoal::getAfbeeldingNaamAlsInt)));
			//FXCollections.observableList(
			
			listKiesSdGoal.setCellFactory(param -> new ListCell<SdGoal>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(SdGoal sdg, boolean empty)
				{
					super.updateItem(sdg, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(sdg.getNaam());
						imageView.setImage(new Image(sdg.getIcon(), 50, 50, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			//overzicht van eerste categorie tonen
			naamCategorie.setText(dc.getCategorien().stream().findFirst().get().getNaam());
			
			listSdGoal.setItems(FXCollections.observableList(
					dc.getCategorien().stream().findFirst().get().getSdGoals().stream().collect(Collectors.toList())));
			
			//eerste keer overzicht tonen moet ook iconen tonen van SdGoal's van de categorie
			listSdGoal.setCellFactory(param -> new ListCell<SdGoal>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(SdGoal name, boolean empty)
				{
					super.updateItem(name, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(name.getNaam());
						imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			catIcoon.setImage(new Image(dc.getCategorien().stream().findFirst().get().getIcon(), 50, 50, true, true));
			
			listKiesSdGoal.getSelectionModel().selectFirst();
			
			listKiesSdGoal.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if(newValue != null)
						{
							
							if(newValue.getIcon() != null)
							{
								
								SdGoal SdGoal = listKiesSdGoal.getSelectionModel().getSelectedItem();
								System.out.printf("%s  - %s\n", SdGoal.getClass().getSimpleName(), SdGoal.getNaam());
								
								listSdGoal.getItems().add(SdGoal);
								
								//voeg SdGoal to aan listview en categorie zelf bij aanmaken
								listSdGoal.setItems(listSdGoal.getItems());
								
								listSdGoal.setCellFactory(param -> new ListCell<SdGoal>()
								{
									private ImageView imageView = new ImageView();
									
									@Override
									public void updateItem(SdGoal name, boolean empty)
									{
										super.updateItem(name, empty);
										if(empty)
										{
											setText(null);
											setGraphic(null);
										}
										else
										{
											setText(name.getNaam());
											imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
											
											setGraphic(imageView);
										}
									}
								});
								
							}
							
						}
					});
			
			ObservableList<Datasource> datasources = dc.getDatasources();
			if (datasources.size() > 0) {
				naamDatasource.setText(dc.getDatasources().stream().findFirst().get().getNaam());
				datasourceType.setValue(dc.getDatasources().stream().findFirst().get().getTypeDatasource().toString());
				datasourceLink.setText(dc.getDatasources().stream().findFirst().get().getLink());
				
			}
			
			listDatasources1.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if(newValue != null)
						{
							Datasource dataS = listDatasources1.getSelectionModel().getSelectedItem();
							
							naamDatasource.setText(dataS.getNaam());
							datasourceType.setValue(dataS.getTypeDatasource().toString());
							datasourceLink.setText(dataS.getLink());
							
						}
					});
			
			listCategorieen.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if(newValue != null)
						{
							Categorie cat = listCategorieen.getSelectionModel().getSelectedItem();
							//theVariable.getClass().getSimpleName()
							System.out.printf("%s  - %s\n", cat.getClass().getSimpleName(), cat.getNaam());
							
							//info tonen van geselecteerde categorie
							//naam
							naamCategorie.setText(cat.getNaam());
							
							catIcoon.setImage(new Image(cat.getIcon(), 50, 50, true, true));
							
							//doelstellingen
							if(!(cat.getSdGoals() == null))
							{
								//arrayList
								//listSdGoal.setItems(FXCollections.observableList(cat.getDoelstellingen().stream().map(d -> d.getNaam()).collect(Collectors.toList())));
								listSdGoal.setItems(FXCollections
										.observableList(cat.getSdGoals().stream().collect(Collectors.toList())));
								
								//moet dit gebruikt worden
								listSdGoal.getSelectionModel().selectFirst();
								
								listSdGoal.setCellFactory(param -> new ListCell<SdGoal>()
								{
									private ImageView imageView = new ImageView();
									
									@Override
									public void updateItem(SdGoal name, boolean empty)
									{
										super.updateItem(name, empty);
										if(empty)
										{
											setText(null);
											setGraphic(null);
										}
										else
										{
											setText(name.getNaam());
											imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
											
											setGraphic(imageView);
										}
									}
								});
							}
							else
							{
								//listSdGoal.setItems(FXCollections.observableArrayList(cat.getDoelstellingen()));
								System.out.printf("geen sdGoals voor %s", cat.getNaam());
								//niet zo doen
								//listSdGoal.setVisible(false);
							}
						}
					});
			
			//listIcoon opvullen met iconen
			listIcoon.setItems(FXCollections.observableList(iconen));
			listIcoon.setCellFactory(param -> new ListCell<String>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(String name, boolean empty)
				{
					super.updateItem(name, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(null);
						imageView.setImage(new Image(name, 25, 25, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			//eerste element in listview selecteren omdat anders "oldvalue" null wordt
			listIcoon.getSelectionModel().selectFirst();
			
			//icoon verandert als je op een icoon klikt van de lijst
			listIcoon.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					
					String icoonPath = listIcoon.getSelectionModel().getSelectedItem();
					
					catIcoon.setImage(new Image(icoonPath, 25, 25, true, true));
					
				}
			});
		}
		
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	// Event Listener on Button[#btnAddCategorie].onAction
	@FXML
	public void addCategorie(ActionEvent event)
	{
		vartextCat.setText("Maak nieuwe categorie");
		naamCategorie.clear();
		
//			
		catBewerken.setDisable(true);
		catBewerken.setVisible(false);
		catVerwijderen.setDisable(true);
		catVerwijderen.setVisible(false);
		
		listIcoon.setVisible(true);
		btnRemoveSdGoal.setVisible(true);
//			
		catAnnuleer.setVisible(true);
		catOpslaan.setVisible(true);
//			
		kiesIcoon.setVisible(true);
		labelKiesSdGoal.setVisible(true);
		listSdGoal.setVisible(true);
//			
		listKiesSdGoal.setVisible(true);
		arrow1.setVisible(true);
		arrow2.setVisible(true);
//			
		listSdGoal.getItems().clear();
//			
		//placeholder als er geen SdGoal(') is/zijn geselecteerd
		listSdGoal.setPlaceholder(new Label("Geen SdGoal('s)"));
	}
	
	@FXML
	public void catOpslaan(ActionEvent event)
	{
		
		try
		{
			//maak nieuwe categorie bij Aanmaken
			if(vartextCat.getText().equals("Maak nieuwe categorie"))
			{
				
				DTOCategorie nieuweCategorie = new DTOCategorie(naamCategorie.getText(), catIcoon.getImage().getUrl(),
						new ArrayList<SdGoal>(listSdGoal.getItems().stream().collect(Collectors.toList())));
				dc.voegCategorieToe(nieuweCategorie);
				
				//alles terug goed zetten
				naamCategorie.setStyle("-fx-border-color:none");
				
				vartextCat.setText("Details categorie");
				
				catBewerken.setDisable(false);
				catBewerken.setVisible(true);
				catVerwijderen.setDisable(false);
				catVerwijderen.setVisible(true);
				
				listSdGoal.setVisible(true);
				
				catError.setVisible(false);
				
				//laatste toegevoegde categorie gegevens tonen
				listCategorieen.getSelectionModel().selectLast();
				Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
				naamCategorie.setText(c.getNaam());
				catIcoon.setImage(new Image(c.getIcon(), 50, 50, true, true));
				listSdGoal.setItems(FXCollections.observableList(c.getSdGoals().stream().collect(Collectors.toList())));
				
				kiesIcoon.setVisible(false);
				labelKiesSdGoal.setVisible(false);
				
				listIcoon.setVisible(false);
				listKiesSdGoal.setVisible(false);
				arrow1.setVisible(false);
				arrow2.setVisible(false);
				
				btnRemoveSdGoal.setVisible(false);
				catAnnuleer.setVisible(false);
				catOpslaan.setVisible(false);
				
				catError.setVisible(false);
				
				listKiesSdGoal.setItems(dc.getBeschikbareSdgs().filtered(s -> s.getIcon() != null)
						.sorted(Comparator.comparing(SdGoal::getAfbeeldingNaamAlsInt)));
				
				//gegevens wijzigen bij Wijzigen
			}
			else if(vartextCat.getText().equals("Wijzig categorie"))
			{
				Categorie huidigeCategorie = listCategorieen.getSelectionModel().getSelectedItem();
				dc.setCurrentCategorie(huidigeCategorie);
				
				DTOCategorie nieuweCategorie = new DTOCategorie(naamCategorie.getText(), catIcoon.getImage().getUrl(),
						new ArrayList<SdGoal>(listSdGoal.getItems().stream().collect(Collectors.toList())));
				dc.wijzigCategorie(nieuweCategorie);
				
				//alles terug goed zetten
				vartextCat.setText("Details categorie");
				catBewerken.setDisable(false);
				
				kiesIcoon.setVisible(false);
				labelKiesSdGoal.setVisible(false);
				
				listIcoon.setVisible(false);
				listKiesSdGoal.setVisible(false);
				arrow1.setVisible(false);
				arrow2.setVisible(false);
				
				btnRemoveSdGoal.setVisible(false);
				
				catBewerken.setDisable(false);
				catBewerken.setVisible(true);
				catVerwijderen.setDisable(false);
				catVerwijderen.setVisible(true);
				//listSDG.setVisible(true);
				
				catAnnuleer.setVisible(false);
				catOpslaan.setVisible(false);
				
				catError.setVisible(false);
				//catError.setStyle("-fx-text-fill: ");
				
				listKiesSdGoal.setItems(dc.getBeschikbareSdgs().filtered(s -> s.getIcon() != null)
						.sorted(Comparator.comparing(SdGoal::getAfbeeldingNaamAlsInt)));
			}
		}
		catch(IllegalArgumentException e)
		{
			catError.setText(e.getMessage());
			catError.setVisible(true);
		}
		
	}
	
	@FXML
	public void catAnnuleer(ActionEvent event)
	{
		
		try
		{
			naamCategorie.setStyle("-fx-border-color:none");
			
			//toon overzicht van eerste of geselecteerde categorie
			if(vartextCat.getText().equals("Maak nieuwe categorie"))
			{
				vartextCat.setText("Details categorie");
				
				listCategorieen.getSelectionModel().selectFirst();
				Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
				naamCategorie.setText(c.getNaam());
				catIcoon.setImage(new Image(c.getIcon(), 50, 50, true, true));
				listSdGoal.setItems(FXCollections.observableList(c.getSdGoals().stream().collect(Collectors.toList())));
				
				kiesIcoon.setVisible(false);
				labelKiesSdGoal.setVisible(false);
				
				listIcoon.setVisible(false);
				listKiesSdGoal.setVisible(false);
				arrow1.setVisible(false);
				arrow2.setVisible(false);
				
				btnRemoveSdGoal.setVisible(false);
				catAnnuleer.setVisible(false);
				catOpslaan.setVisible(false);
				
				catBewerken.setDisable(false);
				catBewerken.setVisible(true);
				catVerwijderen.setDisable(false);
				catVerwijderen.setVisible(true);
				
				catError.setVisible(false);
				
			}
			else if(vartextCat.getText().equals("Wijzig categorie"))
			{
				//wijzigen
				//TODO			
				//alles terug goed zetten
				vartextCat.setText("Details categorie");
				catBewerken.setDisable(false);
				
				kiesIcoon.setVisible(false);
				labelKiesSdGoal.setVisible(false);
				
				listIcoon.setVisible(false);
				listKiesSdGoal.setVisible(false);
				arrow1.setVisible(false);
				arrow2.setVisible(false);
				
				btnRemoveSdGoal.setVisible(false);
				
				catBewerken.setDisable(false);
				catBewerken.setVisible(true);
				catVerwijderen.setDisable(false);
				catVerwijderen.setVisible(true);
				
				catAnnuleer.setVisible(false);
				catOpslaan.setVisible(false);
				
				//listSDG.setVisible(true);
				
				catError.setVisible(false);
				
				catError.setVisible(false);
				
			}
		}
		catch(IllegalArgumentException e)
		{
			catError.setText(e.getMessage());
			catError.setVisible(true);
		}
	}
	
	@FXML
	public void removeSdGoal(ActionEvent event)
	{
		listSdGoal.getItems().clear();
	}
	
	@FXML
	public void catBewerken(ActionEvent event)
	{
		vartextCat.setText("Wijzig categorie");
		listSdGoal.setVisible(true);
		
		catBewerken.setDisable(true);
		
		listIcoon.setVisible(true);
		btnRemoveSdGoal.setVisible(true);
		
		catAnnuleer.setVisible(true);
		catOpslaan.setVisible(true);
		
		kiesIcoon.setVisible(true);
		labelKiesSdGoal.setVisible(true);
		
		listKiesSdGoal.setVisible(true);
		arrow1.setVisible(true);
		arrow2.setVisible(true);
	}
	
	@FXML
	public void catVerwijderen(ActionEvent event)
	{
		try
		{
			
			//vragen of de gebruiker zeker is
			Alert boodschap = new Alert(AlertType.CONFIRMATION);
			boodschap.setTitle("Verwijderen");
			
			boodschap.setContentText("Bent u zeker dat u deze categorie wilt verwijderen?");
			
			boodschap.showAndWait().ifPresent(response -> {
				if(response != ButtonType.CANCEL)
				{
					Categorie huidigeCategorie = listCategorieen.getSelectionModel().getSelectedItem();
					dc.verwijderCategorie();
					
					//alles terug goed zetten
					listCategorieen.getSelectionModel().selectFirst();
					Categorie cate = listCategorieen.getSelectionModel().getSelectedItem();
					naamCategorie.setText(cate.getNaam());
					catIcoon.setImage(new Image(cate.getIcon(), 50, 50, true, true));
					listSdGoal.setItems(
							FXCollections.observableList(cate.getSdGoals().stream().collect(Collectors.toList())));
				}
			});
			
		}
		catch(IllegalArgumentException e)
		{
			catError.setText(e.getMessage());
			catError.setVisible(true);
		}
	}
	
	@FXML
	public void addDatasource(ActionEvent event)
	{
		vartextData.setText("Maak nieuwe datasource");
		
		naamDatasource.clear();
		datasourceLink.clear();
		datasourceType.getSelectionModel().clearSelection();
		naamDatasource.setEditable(true);
		datasourceLink.setEditable(true);
		datasourceType.setDisable(false);
		
		btnDataBewerken.setDisable(true);
		btnDataBewerken.setVisible(false);
		btnDataVerwijderen.setDisable(true);
		btnDataVerwijderen.setVisible(false);
		
		btnDataOpslaan.setVisible(true);
		btnDataAnnuleer.setVisible(true);
	}
	
	@FXML
	public void datasourceBewerken(ActionEvent event)
	{
		
	}
	
	@FXML
	public void datasourceVerwijderen(ActionEvent event)
	{
		
	}
	
	@FXML
	public void datasourceOpslaan(ActionEvent event)
			throws SQLIntegrityConstraintViolationException, IllegalStateException
	{
		try
		{
			
			//toon overzicht van eerste of geselecteerde categorie
			if(vartextData.getText().equals("Maak nieuwe datasource"))
			{
				DTODatasource newDatasource = new DTODatasource(naamDatasource.getText(), datasourceType.getValue(),
						datasourceLink.getText(), null, null, null);
				
				dc.voegMVODatasourceToe(newDatasource);
				
				vartextData.setText("Details datasource");
				
				listDatasources1.getSelectionModel().selectFirst();
				Datasource d = listDatasources1.getSelectionModel().getSelectedItem();
				naamDatasource.setText(d.getNaam());
				datasourceLink.setText(d.getLink());
				datasourceType.setValue(d.getTypeDatasource().toString());
				
				naamDatasource.setEditable(false);
				datasourceLink.setEditable(false);
				datasourceType.setDisable(true);
				
				btnDataAnnuleer.setVisible(false);
				btnDataOpslaan.setVisible(false);
				
				btnDataBewerken.setDisable(false);
				btnDataBewerken.setVisible(true);
				btnDataVerwijderen.setDisable(false);
				btnDataVerwijderen.setVisible(true);
				
				dataError.setVisible(false);
				
			}
			else if(vartextCat.getText().equals("Wijzig datasource"))
			{
				// TODO
				// Datasource opslaan na bewerken
				///////////////////////////////////////////////////////////////////////////////////////////////////
			}
		}
		catch(IllegalArgumentException e)
		{
			dataError.setText(e.getMessage());
			dataError.setVisible(true);
		}
	}
	
	@FXML
	public void datasourceAnnuleer(ActionEvent event)
	{
		try
		{
			naamCategorie.setStyle("-fx-border-color:none");
			
			//toon overzicht van eerste of geselecteerde categorie
			if(vartextData.getText().equals("Maak nieuwe datasource"))
			{
				vartextData.setText("Details datasource");
				
				listDatasources1.getSelectionModel().selectFirst();
				Datasource d = listDatasources1.getSelectionModel().getSelectedItem();
				naamDatasource.setText(d.getNaam());
				datasourceLink.setText(d.getLink());
				datasourceType.setValue(d.getTypeDatasource().toString());
				
				naamDatasource.setEditable(false);
				datasourceLink.setEditable(false);
				datasourceType.setDisable(true);
				
				btnDataAnnuleer.setVisible(false);
				btnDataOpslaan.setVisible(false);
				
				btnDataBewerken.setDisable(false);
				btnDataBewerken.setVisible(true);
				btnDataVerwijderen.setDisable(false);
				btnDataVerwijderen.setVisible(true);
				
				dataError.setVisible(false);
				
			}
			else if(vartextData.getText().equals("Wijzig datasource"))
			{
				
				// TODO
				// Datasource annuleren na bewerken
				///////////////////////////////////////////////////////////////////////////////////////////////////
				
			}
		}
		catch(IllegalArgumentException e)
		{
			dataError.setText(e.getMessage());
			dataError.setVisible(true);
		}
	}
	
	// Doelstelling methodes
	// ------------------------------------------------------------------------------------------------------------
//	private void onTabChange()
//	{
//		tabPane.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//			if(newValue != null)
//			{
//				if(oldValue != newValue)
//				{
//					listDoelen.getSelectionModel().selectFirst();
//				}
//			}
//		});
//	}
//	
//	private void showDoelMinimal()
//	{
//		// visibility
//		vboxListIcons.setVisible(false);
//		vboxListIcons.setManaged(false);
//		
//		vboxListSubDoelen.setVisible(false);
//		vboxListSubDoelen.setManaged(false);
//		vboxPijlenSubDoelen.setVisible(false);
//		vboxPijlenSubDoelen.setManaged(false);
//		
//		vboxPijlenDatasources.setVisible(false);
//		vboxPijlenDatasources.setManaged(false);
//		vboxListDatasources.setVisible(false);
//		vboxListDatasources.setManaged(false);
//		
//		doelError.setVisible(false);
//		
//		btnOplaanDoel.setVisible(false);
//		btnAnuleerDoel.setVisible(false);
//		
//		btnDoelWijzig.setVisible(true);
//		btnDoelVerwijder.setVisible(true);
//		
//		// editable
//		naamDoel.setEditable(false);
//		selectionDoelType.setDisable(true);
//		doelDoelwaarde.setEditable(false);
//		selectionDoelHoofdSDG.setDisable(true);
//		selectionDoelSubSDG.setDisable(true);
//		checkboxMVORol.setDisable(true);
//		checkboxManagerRol.setDisable(true);
//		checkboxDirectieRol.setDisable(true);
//		checkboxStakeholderRol.setDisable(true);
//	}
//	
//	private void vulDoelList()
//	{
//		listDoelen.setItems(dc.getDoelstellingen());
//		
//		// vul ListView
//		listDoelen.setCellFactory(param -> new ListCell<Doelstelling>()
//		{
//			private ImageView imageView = new ImageView();
//			
//			@Override
//			public void updateItem(Doelstelling doel, boolean empty)
//			{
//				super.updateItem(doel, empty);
//				if(empty)
//				{
//					setText(null);
//					setGraphic(null);
//				}
//				else
//				{
//					setText(doel.getNaam());
//					//imageView.setImage(new Image(doel.getIcon(), 50, 50, true, true));
//					
//					setGraphic(imageView);
//				}
//			}
//		});
//		
//		// onSelect Doel
//		listDoelen.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//			if(newValue != null)
//			{
////				Doelstelling doel = newValue;
////				
////				naamDoel.setText(doel.getNaam());
////				selectionDoelType.setValue(doel.getDoelstellingsType());
////				doelDoelwaarde.setText(String.valueOf(doel.getDoelwaarde()));
////				doelIcoon.setImage(new Image(doel.getIcon(), 250, 250, true, true));
////				selectionDoelHoofdSDG.setValue(doel.getHoofdSdg());
////				SdGoal subSdg = doel.getSubSdg();
////				selectionDoelSubSDG.setValue(subSdg != null ? subSdg : new SdGoal("---"));
//				
//				// TODO subdoelstellingen, datasources en rollen
//				
//				// indien hiervoor aan het bewerken was, sluit bewerkingsview
//				if(oldValue != newValue)
//				{
//					showDoelMinimal();
//				}
//			}
//		});
//		
//		// vul type choicebox
//		selectionDoelType.setItems(FXCollections.observableList(doelTypes));
//		
//		// vul hoofd SDG choicebox
//		// TODO
//		
//		// onDoelHoofdSDGSelect
//		selectionDoelHoofdSDG.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//			if(newValue != null)
//			{
//				SdGoal sdGoal = newValue;
//				
//				// vul sub SDG choicebox
//				// TODO
//			}
//		});
//		
//		listDoelen.getSelectionModel().selectFirst();
//		
//		onSelectDoelIcon(listDoelIcoon, doelIcoon);
//		
//		onSelectKiesSubDoel();
//		onSelectSubDoel();
//
//		onSelectKiesDatasource();	
//		onSelectDatasource();
//	}
//
//	private void onSelectDoelIcon(ListView<String> iconenLijst, ImageView icoon)
//	{
//		//listIcoon opvullen met iconen
//		iconenLijst.setItems(FXCollections.observableList(iconen));
//		iconenLijst.setCellFactory(param -> new ListCell<String>()
//		{
//			private ImageView imageView = new ImageView();
//			
//			@Override
//			public void updateItem(String name, boolean empty)
//			{
//				super.updateItem(name, empty);
//				if(empty)
//				{
//					setText(null);
//					setGraphic(null);
//				}
//				else
//				{
//					setText(null);
//					imageView.setImage(new Image(name, 25, 25, true, true));
//					
//					setGraphic(imageView);
//				}
//			}
//		});
//		
//		//icoon verandert als je op een icoon klikt van de lijst
//		iconenLijst.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//			if(newValue != null)
//			{
//				String icoonPath = newValue;
//				
//				icoon.setImage(new Image(icoonPath, 250, 250, true, true));
//				
//			}
//		});
//	}
//	
//	private void onSelectKiesSubDoel()
//	{
//		// TODO filter op mogelijke subdoelen
//		listDoelKiesSubDoel.setItems(FXCollections.observableList(dc.getDoelstellingen()));
//		
//		// view
//		listDoelKiesSubDoel.setCellFactory(param -> new ListCell<Doelstelling>()
//		{
//			private ImageView imageView = new ImageView();
//			
//			@Override
//			public void updateItem(Doelstelling subDoel, boolean empty)
//			{
//				super.updateItem(subDoel, empty);
//				if(empty)
//				{
//					setText(null);
//					setGraphic(null);
//				}
//				else
//				{
//					setText(subDoel.getNaam());
//					//imageView.setImage(new Image(subDoel.getIcon(), 25, 25, true, true));
//					
//					setGraphic(imageView);
//				}
//			}
//		});
//		
//		// onSelect
//		listDoelKiesSubDoel.getSelectionModel().selectedItemProperty()
//				.addListener((observableValue, oldValue, newValue) -> {
//					if(newValue != null)
//					{
//						Doelstelling subDoel = newValue;
//						
//						ObservableList<Doelstelling> huidigeSubDoelen = listDoelSubDoelen.getItems();
//						
//						if(huidigeSubDoelen == null)
//						{
//							huidigeSubDoelen = FXCollections.observableList(new ArrayList<>());
//						}
//						huidigeSubDoelen.add(subDoel);
//						listDoelSubDoelen.setItems(FXCollections.observableList(new ArrayList<>(new HashSet<>(huidigeSubDoelen))));
//						
//						// TODO verwijder newValue via listDoelKiesSubDoel. getItems/setItems
//					}
//				});
//	}
//
//	private void onSelectSubDoel()
//	{
//		// TODO Auto-generated method stub
//		
//	}
//	
//	private void onSelectKiesDatasource()
//	{
//		// TODO Auto-generated method stub
//		listDoelKiesData.setItems(FXCollections.observableList(dc.getDatasources()));
//		
//		// onSelect
//		listDoelKiesData.getSelectionModel().selectedItemProperty()
//				.addListener((observableValue, oldValue, newValue) -> {
//					if(newValue != null)
//					{
//						Datasource data = newValue;
//						
//						ObservableList<Datasource> huidigeDatasources = listDoelDatasources.getItems();
//						
//						if(huidigeDatasources == null)
//						{
//							huidigeDatasources = FXCollections.observableList(new ArrayList<>());
//						}
//						huidigeDatasources.add(data);
//						listDoelDatasources.setItems(FXCollections.observableList(new ArrayList<>(new HashSet<>(huidigeDatasources))));
//						
//						// TODO verwijder newValue via listDoelKiesData. getItems/setItems
//					}
//				});
//	}
//	
//	private void onSelectDatasource()
//	{
//		// TODO Auto-generated method stub
//		
//	}
//	
//	private void leegDoelVelden()
//	{
//		naamDoel.setText("");
//		selectionDoelType.setValue("");
//		doelDoelwaarde.setText("");
//		doelIcoon.setImage(null);
//		selectionDoelHoofdSDG.setValue(new SdGoal(""));
//		selectionDoelSubSDG.setValue(new SdGoal(""));
//		listDoelSubDoelen.setItems(null);
//		checkboxMVORol.setSelected(false);
//		checkboxManagerRol.setSelected(false);
//		checkboxDirectieRol.setSelected(false);
//		checkboxStakeholderRol.setSelected(false);
//		listDoelDatasources.setItems(null);
//	}
//	
	@FXML
	private void addDoel(ActionEvent event)
	{
//		listDoelen.getSelectionModel().clearSelection();
//		
//		// visibility
//		vboxListIcons.setVisible(true);
//		vboxListIcons.setManaged(true);
//		
//		vboxListSubDoelen.setVisible(true);
//		vboxListSubDoelen.setManaged(true);
//		vboxPijlenSubDoelen.setVisible(true);
//		vboxPijlenSubDoelen.setManaged(true);
//		
//		vboxPijlenDatasources.setVisible(true);
//		vboxPijlenDatasources.setManaged(true);
//		vboxListDatasources.setVisible(true);
//		vboxListDatasources.setManaged(true);
//		
//		doelError.setVisible(false);
//		
//		btnOplaanDoel.setVisible(true);
//		btnAnuleerDoel.setVisible(true);
//		
//		btnDoelWijzig.setVisible(false);
//		btnDoelVerwijder.setVisible(false);
//		
//		// editable
//		naamDoel.setEditable(true);
//		selectionDoelType.setDisable(false);
//		doelDoelwaarde.setEditable(true);
//		selectionDoelHoofdSDG.setDisable(false);
//		selectionDoelSubSDG.setDisable(false);
//		checkboxMVORol.setDisable(false);
//		checkboxManagerRol.setDisable(false);
//		checkboxDirectieRol.setDisable(false);
//		checkboxStakeholderRol.setDisable(false);
//		
//		leegDoelVelden();
		
		// TODO vul kies listviews in
	}
	
	@FXML
	private void doelOpslaan(ActionEvent event)
	{
		// TODO
	}
	
	@FXML
	private void doelAnnuleer(ActionEvent event)
	{
//		leegDoelVelden();
//		showDoelMinimal();
//		listDoelen.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void doelBewerken(ActionEvent event)
	{
		// TODO
	}
	
	@FXML
	private void doelVerwijderen(ActionEvent event)
	{
		// TODO
	}
	
	// TODO toon errors
	// TODO verander label detailscherm bij elke actie
}
