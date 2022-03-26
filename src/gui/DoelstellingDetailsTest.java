package gui;

import java.io.IOException;

import domein.Doelstelling;
import domein.DomeinController;
import domein.Rol;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class DoelstellingDetailsTest extends BorderPane{
	@FXML
	private Button btnWijzigen;
	@FXML
	private Button btnVerwijderen;
	@FXML
	private Label lblDetailsDoelstelling;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblBewerking;
	@FXML
	private Label lblDoelwaarde;
	@FXML
	private Label lblIcoon;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private Label lblSdg;
	@FXML
	private Label lblDatasource;
	@FXML
	private Label lblRollen;
	@FXML
	private Label lblSubDoelstellingen;
	@FXML
	private TreeView<Doelstelling> treeViewSubDoelstellingen;
	@FXML
	private Label lblNaamIngevuld;
	@FXML
	private Label lblBewerkingIngevuld;
	@FXML
	private Label lblDoelWaardeIngevuld;
	@FXML
	private Label lblEenheidIngevuld;
	@FXML
	private Label lblSdgIngevuld;
	@FXML
	private Label lblDatasourceIngevuld;
	@FXML
	private ListView<Rol> listRollenIngevuld;
	@FXML
	private Label lblErrorMessage;
//	private DomeinController dc;
//	private E object;
	

	public DoelstellingDetailsTest(DomeinController dc, Doelstelling object){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DoelstellingDetails.fxml"));
		loader.setController(this);
		loader.setRoot(this);
//		this.dc = dc;
//		this.object = object;

		try
		{
			loader.load();
			lblErrorMessage.setVisible(false);
			if(object instanceof Doelstelling) {
				lblNaamIngevuld.setText(((Doelstelling) object).getNaam());
				lblBewerkingIngevuld.setText(((Doelstelling)object).getFormule().toString());
				if(((Doelstelling) object).getDatasource() != null) {
					lblDatasourceIngevuld.setText(((Doelstelling) object).getDatasource().getNaam());
				} else {
					lblDatasourceIngevuld.setText("");
				}
				
				lblSdgIngevuld.setText(((Doelstelling) object).getSdGoal().getNaam());
				//lblDoelWaardeIngevuld.setText(((Doelstelling) object).getDoelwaarde());
				lblEenheidIngevuld.setText("hier moet nog iets komen");
				listRollenIngevuld.setItems(FXCollections.observableList(((Doelstelling)object).getRollen()));
				
				listRollenIngevuld.setCellFactory(param -> new ListCell<Rol>()
				{
					
					@Override
					public void updateItem(Rol doel, boolean empty)
					{
						super.updateItem(doel, empty);
						if(empty)
						{
							setText(null);
							setGraphic(null);
						}
						else
						{
							setText(doel.getRol());
							

						}
					}
				});
				
				
				TreeItem<Doelstelling> rootNode = 
				        new TreeItem<Doelstelling>(null);

				
				
				for (Doelstelling s : object.getComponents()) {
					System.out.println(s.getNaam());
//		            TreeItem<Doelstelling> empLeaf = new TreeItem<Doelstelling>(s);
		            boolean found = false;
//		            for (TreeItem<Doelstelling> depNode : rootNode.getChildren()) {
//		            	if(depNode.getValue().g == s.getParentSDG_id()) {
//		            		depNode.getChildren().add(empLeaf);
//		                  found = true;
//		                  break;
//		            	}
//		            }
		            String pad = s.getIcon();
					int index = pad.indexOf("c");
					pad = pad.substring(index+1);

		            if (!found) {
		                TreeItem<Doelstelling> depNode = new TreeItem<Doelstelling>(
		                    s
		                );
		                
		                rootNode.getChildren().add(depNode);
		            }
		        }
		 
				treeViewSubDoelstellingen.setRoot(rootNode);
		        treeViewSubDoelstellingen.setShowRoot(false);
		        
		        
		        String pad = ((Doelstelling) object).getIcon();
				int index = pad.indexOf("c");
				pad = pad.substring(index+1);
				// Mannetje weergeven
				System.out.println(pad);
				//imgIcoon.setImage(new Image(getClass().getResourceAsStream(pad)));
				
			}
			
			
//			this.getChildren().addAll(btnWijzigen, btnVerwijderen, lblBewerking, lblDatasource, lblDetailsDoelstelling1, lblDoelwaarde, lblIcoon, lblNaam, lblRollen, lblSdg, lblSubDoelstellingen, treeViewSubDoelstellingen, lblDoelWaardeIngevuld, lblEenheidIngevuld, lblNaamIngevuld, lblBewerkingIngevuld, lblDatasourceIngevuld, lblSdgIngevuld, listRollenIngevuld, imgIcoon, lblErrorMessage);
		
			btnVerwijderen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					//vragen of de gebruiker zeker is
					Alert boodschap = new Alert(AlertType.CONFIRMATION);
					boodschap.setTitle("Verwijderen");
					
					boodschap.setContentText("Bent u zeker dat u deze doelstelling wilt verwijderen?");
					
					boodschap.showAndWait().ifPresent(response -> {
						if(response != ButtonType.CANCEL)
						{
							try {
								lblErrorMessage.setVisible(false);
								dc.setCurrentDoelstelling((Doelstelling)object);
								dc.verwijderMVODoelstelling();
								maakLeeg();
							}
							catch(IllegalArgumentException e)
							{
								lblErrorMessage.setText(e.getMessage());
								lblErrorMessage.setVisible(true);
							}
							
						}
					});
					
				}
			});
			
			btnWijzigen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {

					UpdateOrCreateDoelstelling<Doelstelling> vs = new UpdateOrCreateDoelstelling<>(dc, object, "Wijzig doelstelling");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = DoelstellingDetailsTest.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
					
				}
			});
		
		
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void maakLeeg() {
		this.getChildren().clear();
		
	}

}
