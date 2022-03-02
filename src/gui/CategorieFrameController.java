package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DomeinController;
import domein.SdGoal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;


public class CategorieFrameController extends Pane {
	@FXML
	private Label catError;
	
	@FXML
	private Label vartextCat;
	
	
	@FXML
	private Button catBewerken;
	
	@FXML
	private Button catVerwijderen;
	
	@FXML
	private TextField naamCategorie;
	
	@FXML
	private Region groeneBalk;
	@FXML
	private ImageView logo;
	@FXML
	private ImageView ventje;
	@FXML
	private Label lblNaamIngelogdeGebruiker;
	@FXML
	private Label lblFunctieIngelogdeGebruiker;
	
	//Categorie of String?
	@FXML
	private ListView<Categorie> listCategorieen;
	
	/*//MvoDoelstelling of String?
	@FXML
	private ListView<String> listSdGoal;*/
		
	@FXML
	private Button btnAddCategorie;
	
	private DomeinController dc;
	//private AanmeldController dc;
	
	@FXML
	private Label labelIcoon;
	
	@FXML
	private ImageView catIcoon;
	
	@FXML
	private Label kiesIcoon;
	
	@FXML
	//ImageView of String?
	private ListView<String> listIcoon;


	@FXML
	private Label labelSdGoal;
	
	@FXML
	//SdGoal of String?
	private ListView<SdGoal> listSdGoal;
	
	@FXML
	private Label labelKiesSdGoal;
	
	@FXML
	//SdGoal of String?
	private ListView<SdGoal> listKiesSdGoal;
	
	@FXML
	private Button btnRemoveSdGoal;
	
	@FXML
	private Button catOpslaan;
	
	@FXML
	private Button catAnnuleer;
	
	@FXML
	private TabPane tabPane;
	
	//lijst met namen/paths van iconen
	private List<String> iconen = (List<String>) Arrays.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png", "file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	
	public CategorieFrameController(DomeinController dc)
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieFrame.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			this.dc = dc;
						
			// Elementen naar de voorgrond plaatsen
			groeneBalk.toFront();
			lblNaamIngelogdeGebruiker.toFront();
			lblNaamIngelogdeGebruiker.setText(dc.getAangemeldeGebruiker().getGebruikersnaam());
			lblFunctieIngelogdeGebruiker.toFront();
			lblFunctieIngelogdeGebruiker.setText(dc.getAangemeldeGebruiker().getRol());
			// Dit wil nog niet
			ventje.toFront();
			logo.toFront();
			catError.setVisible(false);
			
			listCategorieen.toFront();
			listKiesSdGoal.setVisible(false);
			listIcoon.setVisible(false);
			btnRemoveSdGoal.setVisible(false);
			kiesIcoon.setVisible(false);
			labelKiesSdGoal.setVisible(false);
			
			catOpslaan.setVisible(false);
			catAnnuleer.setVisible(false);
			
			tabPane.getTabs().forEach(t -> t.setGraphic(new ImageView(new Image("file:src/images/tab.png", 25, 25, true, true))));
			
			tabPane.widthProperty().addListener((observable, oldValue, newValue) ->
		    {
		        tabPane.setTabMinWidth(tabPane.getWidth() / tabPane.getTabs().size());
		        tabPane.setTabMaxWidth(tabPane.getWidth() / tabPane.getTabs().size());      
		    });
			

			

		} catch(IOException e){
			throw new RuntimeException(e);
		}
		
		listCategorieen.setItems(dc.getCategorien());
		
		
		listCategorieen.setCellFactory(param -> new ListCell<Categorie>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Categorie name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	setText(name.getNaam());
                	imageView.setImage(new Image(name.getIcon(), 50, 50, true, true));
                    
                    setGraphic(imageView);
                }
            }
        });
		
		//eerste categorie in listview selecteren
		//als je bij het initeel starten direct op de verwijder knop klikt, dan wordt de eerste verwijderd
		listCategorieen.getSelectionModel().selectFirst();
		
		listKiesSdGoal.setItems(dc.getSdGoals().filtered(s -> s.getIcon() != null).sorted(Comparator.comparing(SdGoal::getAfbeeldingNaamAlsInt)));
		//FXCollections.observableList(
		
		
		listKiesSdGoal.setCellFactory(param -> new ListCell<SdGoal>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(SdGoal sdg, boolean empty) {
                super.updateItem(sdg, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	setText(sdg.getNaam());
                	imageView.setImage(new Image(sdg.getIcon(), 50, 50, true, true));
                    
                    setGraphic(imageView);
                }
            }
        });
		

		
		//overzicht van eerste categorie tonen
		naamCategorie.setText(dc.getCategorien().stream().findFirst().get().getNaam());
		
		listSdGoal.setItems(FXCollections.observableList(dc.getCategorien().stream().findFirst().get().getDoelstellingen().stream().collect(Collectors.toList())));
		
		//eerste keer overzicht tonen moet ook iconen tonen van SdGoal's van de categorie
		listSdGoal.setCellFactory(param -> new ListCell<SdGoal>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(SdGoal name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	setText(name.getNaam());
                	imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
                    
                    setGraphic(imageView);
                }
            }
        });
		
		catIcoon.setImage(new Image(dc.getCategorien().stream().findFirst().get().getIcon(), 50, 50, true, true));
		
		listKiesSdGoal.getSelectionModel().selectFirst();
		
		listKiesSdGoal.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oldValue, newValue) -> {
           if (newValue != null) {

                   if (newValue.getIcon() != null) {
                	   SdGoal SdGoal = listKiesSdGoal.getSelectionModel().getSelectedItem();
                       System.out.printf("%s  - %s\n", SdGoal.getClass().getSimpleName(), SdGoal.getNaam());
                       
                       listSdGoal.getItems().add(SdGoal);
                       
                       //voeg SdGoal to aan listview en categorie zelf bij aanmaken
                       listSdGoal.setItems(listSdGoal.getItems());
                       
                       listSdGoal.setCellFactory(param -> new ListCell<SdGoal>() {
                           private ImageView imageView = new ImageView();
                           @Override
                           public void updateItem(SdGoal name, boolean empty) {
                               super.updateItem(name, empty);
                               if (empty) {
                                   setText(null);
                                   setGraphic(null);
                               } else {
                               	setText(name.getNaam());
                               	imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
                                   
                                   setGraphic(imageView);
                               }
                           }
                       });
                   
                   
        	   }
                
           }
        });
		
		listCategorieen.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oldValue, newValue) -> {
           if (newValue != null) {
                Categorie cat = listCategorieen.getSelectionModel().getSelectedItem();
                //theVariable.getClass().getSimpleName()
                System.out.printf("%s  - %s\n", cat.getClass().getSimpleName(), cat.getNaam());
                
                //info tonen van geselecteerde categorie
                	//naam
                naamCategorie.setText(cat.getNaam());
                
                catIcoon.setImage(new Image(cat.getIcon(), 50, 50, true, true));
               
                	//doelstellingen
                if (!(cat.getDoelstellingen() == null)) {
                	//arrayList
                	//listSdGoal.setItems(FXCollections.observableList(cat.getDoelstellingen().stream().map(d -> d.getNaam()).collect(Collectors.toList())));
                	listSdGoal.setItems(FXCollections.observableList(cat.getDoelstellingen().stream().collect(Collectors.toList())));
                	
//moet dit gebruikt worden
                	listSdGoal.getSelectionModel().selectFirst();
                	
                	listSdGoal.setCellFactory(param -> new ListCell<SdGoal>() {
                        private ImageView imageView = new ImageView();
                        @Override
                        public void updateItem(SdGoal name, boolean empty) {
                            super.updateItem(name, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                            	setText(name.getNaam());
                            	imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
                                
                                setGraphic(imageView);
                            }
                        }
                	});
                } else {
                	//listSdGoal.setItems(FXCollections.observableArrayList(cat.getDoelstellingen()));
                	System.out.printf("geen doelstellingen voor %s", cat.getNaam());
                	//niet zo doen
                	//listSdGoal.setVisible(false);
                }   
           }
        });
		
		//---------------
		
//hoeft niet meer
		//listKiesSdGoal.setItems(SdGoals);
		
//hoeft niet meer
		//iconen toevoegen bij elk element van de lijst van SdGoal's
//		listKiesSdGoal.setCellFactory(param -> new ListCell<SdGoal>() {
//            private ImageView imageView = new ImageView();
//            @Override
//            public void updateItem(SdGoal name, boolean empty) {
//                super.updateItem(name, empty);
//                if (empty) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                	setText(name.getNaam());
//                	imageView.setImage(new Image(name.getIcoon(), 25, 25, true, true));
//                    
//                    setGraphic(imageView);
//                }
//            }
//        });
		
		
//al gedaan, is dit overbodig?
		//eerste element in listview selecteren omdat anders "oldvalue" null wordt
		/*listKiesSdGoal.getSelectionModel().selectFirst();*/
		
		//listIcoon opvullen met iconen
		listIcoon.setItems(FXCollections.observableList(iconen));
		listIcoon.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	setText(null);
                	imageView.setImage(new Image(name, 25, 25, true, true));
                    
                    setGraphic(imageView);
                }
            }
        });
		
		//eerste element in listview selecteren omdat anders "oldvalue" null wordt
		listIcoon.getSelectionModel().selectFirst();
		
		//icoon verandert als je op een icoon klikt van de lijst
		listIcoon.getSelectionModel().selectedItemProperty().
        addListener((observableValue, oldValue, newValue) -> {
           if (newValue != null) {
        	   
                	   String icoonPath = listIcoon.getSelectionModel().getSelectedItem();
                       
                       catIcoon.setImage(new Image(icoonPath, 25, 25, true, true));
           
           }
        });
		
		
	}


	// Event Listener on Button[#btnAddCategorie].onAction
		@FXML
		public void addCategorie(ActionEvent event) {
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
//			
			listSdGoal.getItems().clear();
//			
			//placeholder als er geen SdGoal(') is/zijn geselecteerd
			listSdGoal.setPlaceholder(new Label("Geen SdGoal('s)"));
		}
		
		@FXML
		public void catOpslaan(ActionEvent event) {
			
			try {
				//maak nieuwe categorie bij Aanmaken
				if (vartextCat.getText().equals("Maak nieuwe categorie")) {

					dc.voegCategorieToe(naamCategorie.getText(), new ArrayList<SdGoal>(listSdGoal.getItems().stream().collect(Collectors.toList())), catIcoon.getImage().getUrl());

					//alles terug goed zetten
					naamCategorie.setStyle("-fx-border-color:none");
					
					vartextCat.setText("Overzicht categorie");
					
					catBewerken.setDisable(false);
					catBewerken.setVisible(true);
					catVerwijderen.setDisable(false);
					catVerwijderen.setVisible(true);
					
					listSdGoal.setVisible(true);
					
					catError.setVisible(false);
					catError.setStyle("-fx-text-fill: black");
					
					//laatste toegevoegde categorie gegevens tonen
					listCategorieen.getSelectionModel().selectLast();
					Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
					naamCategorie.setText(c.getNaam());
					catIcoon.setImage(new Image(c.getIcon(), 50, 50, true, true));
					listSdGoal.setItems(FXCollections.observableList(c.getDoelstellingen().stream().collect(Collectors.toList())));
					
					kiesIcoon.setVisible(false);
					labelKiesSdGoal.setVisible(false);
					
					listIcoon.setVisible(false);
					listKiesSdGoal.setVisible(false);
					
					btnRemoveSdGoal.setVisible(false);
					catAnnuleer.setVisible(false);
					catOpslaan.setVisible(false);
											
					catError.setVisible(false);
								
					//gegevens wijzigen bij Wijzigen
				} else if (vartextCat.getText().equals("Wijzig categorie")) {	
					Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
					dc.setCategorieIcon(c.getNaam(), catIcoon.getImage().getUrl());
					//beter manier?
					dc.wijzigCategorieDoelstellingen(c.getNaam(), listSdGoal.getItems().stream().collect(Collectors.toList()).stream().map(s -> s.getNaam()).collect(Collectors.toList()));
					dc.wijzigCategorieNaam(c.getNaam(), naamCategorie.getText());
					
					
					//alles terug goed zetten
					vartextCat.setText("Overzicht categorie");
					catBewerken.setDisable(false);
					
					kiesIcoon.setVisible(false);
					labelKiesSdGoal.setVisible(false);
					
					listIcoon.setVisible(false);
					listKiesSdGoal.setVisible(false);
					
					btnRemoveSdGoal.setVisible(false);
					
					catBewerken.setDisable(false);
					catBewerken.setVisible(true);
					catVerwijderen.setDisable(false);
					catVerwijderen.setVisible(true);
					//listSDG.setVisible(true);
					
					catAnnuleer.setVisible(false);
					catOpslaan.setVisible(false);
					
					catError.setVisible(false);
					//catError.setStyle("-fx-text-fill: black");
				}
			} catch(IllegalArgumentException e) {
				catError.setText(e.getMessage());
				catError.setVisible(true);
			}
				
		}
		
		@FXML
		public void catAnnuleer(ActionEvent event) {
			
			try {
				naamCategorie.setStyle("-fx-border-color:none");
				
				//toon overzicht van eerste of geselecteerde categorie
				if (vartextCat.getText().equals("Maak nieuwe categorie")) {
					vartextCat.setText("Overzicht categorie");
					
					listCategorieen.getSelectionModel().selectFirst();
					Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
					naamCategorie.setText(c.getNaam());
					catIcoon.setImage(new Image(c.getIcon(), 50, 50, true, true));
					listSdGoal.setItems(FXCollections.observableList(c.getDoelstellingen().stream().collect(Collectors.toList())));
					
					kiesIcoon.setVisible(false);
					labelKiesSdGoal.setVisible(false);
					
					listIcoon.setVisible(false);
					listKiesSdGoal.setVisible(false);
					
					btnRemoveSdGoal.setVisible(false);
					catAnnuleer.setVisible(false);
					catOpslaan.setVisible(false);
					
					catBewerken.setDisable(false);
					catBewerken.setVisible(true);
					catVerwijderen.setDisable(false);
					catVerwijderen.setVisible(true);
					
					catError.setVisible(false);
					
				} else if (vartextCat.getText().equals("Wijzig categorie")) {
					//wijzigen
		//TODO			
					//alles terug goed zetten
					vartextCat.setText("Overzicht categorie");
					catBewerken.setDisable(false);
					
					kiesIcoon.setVisible(false);
					labelKiesSdGoal.setVisible(false);
					
					listIcoon.setVisible(false);
					listKiesSdGoal.setVisible(false);
					
					btnRemoveSdGoal.setVisible(false);
					
					catBewerken.setDisable(false);
					catBewerken.setVisible(true);
					catVerwijderen.setDisable(false);
					catVerwijderen.setVisible(true);
					
					catAnnuleer.setVisible(false);
					catOpslaan.setVisible(false);
					
					//listSDG.setVisible(true);
					
					catError.setVisible(false);
					catError.setStyle("-fx-text-fill: black");
					
					catError.setVisible(false);
					
				}
			} catch(IllegalArgumentException e) {
				catError.setText(e.getMessage());
				catError.setVisible(true);
			}
		}
		
		@FXML
		public void removeSdGoal(ActionEvent event) {	
			listSdGoal.getItems().clear();
		}
		
		@FXML
		public void catBewerken(ActionEvent event) {	
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
		}
		
		@FXML
		public void catVerwijderen(ActionEvent event) {	
//			//controle dat minstens 1 categorie in de lijst blijft
//		
			
			//vragen of de gebruiker zeker is
//TODO POPUP
	//		
//TODO probleem oplossen bij deleten
			//verwijderen
			
			try {
				Categorie c = listCategorieen.getSelectionModel().getSelectedItem();
				dc.verwijderCategorie(c.getNaam());
			} catch(IllegalArgumentException e) {
				catError.setText(e.getMessage());
				catError.setVisible(true);
			}
			
		}
	
}