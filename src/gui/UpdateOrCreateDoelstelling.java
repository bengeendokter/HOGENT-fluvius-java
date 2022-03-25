package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domein.Average;
import domein.Bewerking;
import domein.DTOMVODoelstelling;
import domein.Datasource;
import domein.Doelstelling;
import domein.DomeinController;
import domein.GeenBewerking;
import domein.Rol;
import domein.SdGoal;
import domein.Som;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class UpdateOrCreateDoelstelling extends BorderPane
{
	@FXML
	private Label lblMaakOfWijzig;
	@FXML
	private Button btnOpslaan;
	@FXML
	private Button btnAnnuleer;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblBewerking;
	@FXML
	private Label lblDoelwaarde;
	@FXML
	private Label lblIcoon;
	@FXML
	private Label lblKiesIcoon;
	@FXML
	private Label lblSdg;
	@FXML
	private Label lblRollen;
	@FXML
	private CheckBox kiesCoordinator;
	@FXML
	private CheckBox kiesManager;
	@FXML
	private CheckBox kiesDirectie;
	@FXML
	private CheckBox kiesStakeholder;
	@FXML
	private TextField txtnaam;
	@FXML
	private ChoiceBox<Bewerking> choiceBewerking;
	@FXML
	private TextField txtDoelwaarde;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private ListView<String> listIconen;
	@FXML
	private ChoiceBox<SdGoal> choiceSdg;
	@FXML
	private ChoiceBox<SdGoal> choiceSubSdg;
	@FXML
	private Label lblKiesSub;
	@FXML
	private Label lblSubs;
	@FXML
	private TreeView<Doelstelling> treeKiesSubs;
	@FXML
	private Label lblSubDoelstellingen;
	@FXML
	private TreeView<Doelstelling> treeGekozenSubs;
	@FXML
	private Label lblDatasource;
	@FXML
	private ChoiceBox<Datasource> choiceDatasource;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private CheckBox isSubdoelstelling;
	@FXML
	private HBox hboxSubdoelstellingen;
	@FXML
	private HBox hboxDatasource;
	@FXML
	private Label lblEenheid;
	
	private List<Bewerking> doelTypes = new ArrayList<>(Arrays.asList(new Som(), new Average(), new GeenBewerking()));
	private List<String> iconen = (List<String>) Arrays
			.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png",
					"file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	private Map<String, Doelstelling> map = new HashMap<String, Doelstelling>();
	
	public UpdateOrCreateDoelstelling(DomeinController dc, Doelstelling doelstellingToUpdate,
			String titelWijzigOfMaakAan)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrCreateDoelstelling.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		
		try
		{
			loader.load();
			lblMaakOfWijzig.setText(titelWijzigOfMaakAan);
			
			// treeView rootNodes
			TreeItem<Doelstelling> rootNode = new TreeItem<Doelstelling>(null);
			treeGekozenSubs.setRoot(rootNode);
			treeGekozenSubs.setShowRoot(false);
			
			// indien wijzig doelstelling
			if(doelstellingToUpdate != null)
			{
				// toon gepaste velden indien Leaf of Composite
				isSubdoelstelling.setVisible(false);
				if(doelstellingToUpdate.getDatasource() == null)
				{
					hboxDatasource.setVisible(false);
				}
				else
				{
					hboxSubdoelstellingen.setVisible(false);
				}
				
				txtnaam.setText(doelstellingToUpdate.getNaam());
				choiceBewerking.setValue(doelstellingToUpdate.getFormule());
				txtDoelwaarde.setText(Double.toString(doelstellingToUpdate.getDoelwaarde()));
				imgIcoon.setImage(new Image(doelstellingToUpdate.getIcon(), 250, 250, true, true));
				
				// stel hoofd en sub sdGoal velden in
				SdGoal sdg = doelstellingToUpdate.getSdGoal();
				if(sdg.getParentSDG_id() == 0) // sdGoal is een hoofdSdg
				{
					choiceSdg.setValue(sdg);
					
					choiceSubSdg.setItems(FXCollections.observableList(dc.getSdgs().stream()
							.filter(subSdg -> subSdg.getParentSDG_id() == Integer.valueOf(sdg.getAfbeeldingnaam()))
							.toList()));
				}
				else // sdGoal is een subdSdg
				{
					choiceSubSdg.setValue(sdg);
					
					choiceSdg.setValue(dc.getSdgs().stream()
							// get alle hoofd sdg's
							.filter(hoodSdg -> hoodSdg.getParentSDG_id() == 0)
							// zoek de hoofd sdGoal die bij de subSgd hoort
							.filter(hoodSdg -> Integer.valueOf(hoodSdg.getAfbeeldingnaam()) == sdg.getParentSDG_id())
							.findFirst().get());
				}
				
				choiceDatasource.setValue(doelstellingToUpdate.getDatasource());
				lblEenheid.setText(doelstellingToUpdate.getEenheid());
				
				List<Rol> rollen = doelstellingToUpdate.getRollen();
				if(rollen.contains(new Rol("MVO Coördinator")))
				{
					kiesCoordinator.setSelected(true);
				}
				if(rollen.contains(new Rol("Manager")))
				{
					kiesManager.setSelected(true);
				}
				if(rollen.contains(new Rol("Directie")))
				{
					kiesDirectie.setSelected(true);
				}
				if(rollen.contains(new Rol("Stakeholder")))
				{
					kiesStakeholder.setSelected(true);
				}
				
				// subDoelstellingen toevoegen
				rootNode.getChildren().addAll(doelstellingToUpdate.getComponents().stream()
						.map(subDoel -> new TreeItem<>((Doelstelling) subDoel)).toList());
			}
			
			//listIcoon opvullen met iconen
			listIconen.setItems(FXCollections.observableList(iconen));
			listIconen.setCellFactory(param -> new ListCell<String>()
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
						imageView.setImage(new Image(name, 40, 40, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			// stel iconen en namen van TreeCellen gekozenSubs in
			treeGekozenSubs.setCellFactory(param -> new TreeCell<Doelstelling>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(Doelstelling doel, boolean empty)
				{
					super.updateItem(doel, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(doel.getNaam());
						imageView.setImage(new Image(doel.getIcon(), 30, 30, true, true));
						
						setGraphic(imageView);
						
					}
				}
			});
			
			//icoon verandert als je op een icoon klikt van de lijst
			listIconen.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					String icoonPath = (String) listIconen.getSelectionModel().getSelectedItem();
					
					imgIcoon.setImage(new Image(icoonPath, 250, 250, true, true));
				}
			});
			
			choiceBewerking.setItems(FXCollections.observableList(doelTypes));
			choiceSdg.setItems(FXCollections
					.observableList(dc.getSdgs().stream().filter(sdg -> sdg.getParentSDG_id() == 0).toList()));
			
			// subSdGoal
			choiceSdg.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					choiceSubSdg.setItems(FXCollections.observableList(dc.getSdgs().stream()
							.filter(sdg -> sdg.getParentSDG_id() == Integer.valueOf(newValue.getAfbeeldingnaam()))
							.toList()));
				}
			});
			
			choiceDatasource.setItems(FXCollections.observableList(dc.getDatasources()));
			lblErrorMessage.setVisible(false);
			
			// TODO kies SDG's
			
			// Opslaan TODO
			btnOpslaan.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent evt)
				{
					//lblErrorMessage.setVisible(false);
					try
					{
						lblErrorMessage.setVisible(false);
						
						String naam = txtnaam.getText();
						
						Image iconImage = imgIcoon.getImage();
						if(iconImage == null)
						{
							throw new IllegalArgumentException("MVO Doelstelling moet een icoon hebben");
						}
						String icoon = iconImage.getUrl();
						
						// TODO controleer op parse double fout
						double doelwaarde = 0.0;
						try
						{
							doelwaarde = Double.parseDouble(txtDoelwaarde.getText());
						}
						catch(Exception e)
						{
							throw new IllegalArgumentException(
									"De doelwaarde is geen geldig kommagetal gescheiden door een punt");
						}
						List<Rol> rollen = new ArrayList<>();
						if(kiesCoordinator.isSelected())
						{
							rollen.add(new Rol("MVO Coördinator"));
						}
						if(kiesManager.isSelected())
						{
							rollen.add(new Rol("Manager"));
						}
						if(kiesDirectie.isSelected())
						{
							rollen.add(new Rol("Directie"));
						}
						if(kiesStakeholder.isSelected())
						{
							rollen.add(new Rol("Stakeholder"));
						}
						
						SdGoal sdGoal = (SdGoal) choiceSdg.getValue();
						
						Datasource datasource = (Datasource) choiceDatasource.getValue();
						List<Doelstelling> subDoelstellingen = new ArrayList<Doelstelling>(map.values());
						
						Bewerking bewerking = (Bewerking) choiceBewerking.getValue();
						
						DTOMVODoelstelling doel = new DTOMVODoelstelling(naam, icoon, doelwaarde, rollen, sdGoal,
								datasource, subDoelstellingen, bewerking);
						
						if(doelstellingToUpdate != null)
						{
							dc.setCurrentDoelstelling(doelstellingToUpdate);
							dc.wijzigMVODoelstelling(doel);
							
						}
						else
						{
							dc.voegMVODoelstellingToeMetSubs(doel);
							
						}
						
						refreshScherm();
					}
					catch(IllegalArgumentException e)
					{
						lblErrorMessage.setText(e.getMessage());
						lblErrorMessage.setVisible(true);
					}
					
				}
			});
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void maakLeeg()
	{
		this.getChildren().clear();
	}
	
	private void refreshScherm()
	{
		maakLeeg();
	}
	
}
