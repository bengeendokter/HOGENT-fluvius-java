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
import domein.Rol;
import domein.SdGoal;
import domein.Som;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpdateOrCreateDoelstelling<E> extends Pane {
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
	private TextField txtEenheid;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private ListView<String> listIconen;
	@FXML
	private ChoiceBox<SdGoal> choiceSdg;
	@FXML
	private Label lblKiesSub;
	@FXML
	private Label lblSubs;
	@FXML
	private TreeView<Doelstelling> treeKiesSubs;
	@FXML
	private TreeView<Doelstelling> treeGekozenSubs;
	@FXML
	private Label lblDatasource;
	@FXML
	private ChoiceBox<Datasource> choiceDatasource;
	@FXML
	private Label lblErrorMessage;
	
//	private DomeinController dc;
//	private E object;
	private List<Bewerking> doelTypes = new ArrayList<>(Arrays.asList(new Som(), new Average()));
	private List<String> iconen = (List<String>) Arrays
			.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png",
					"file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	TreeItem<Doelstelling> rootNode2 = 
	        new TreeItem<Doelstelling>(null);
	
	Map<String, Doelstelling> map = new HashMap<String, Doelstelling>();
	
	public UpdateOrCreateDoelstelling(DomeinController dc, E object, String wijzigMaak){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrCreateDoelstelling.fxml"));
		loader.setController(this);
//		this.dc = dc;
//		this.object = object;

		try
		{
			loader.load();
			lblMaakOfWijzig.setText(wijzigMaak);
			
			if(object != null) {
				txtnaam.setText(((Doelstelling)object).getNaam());
				choiceBewerking.setValue(((Doelstelling)object).getFormule());
				//txtDoelwaarde.setText(((Doelstelling)object).getDoelwaarde());
				String pad2 = ((Doelstelling) object).getIcon();
				int index2 = pad2.indexOf("c");
				pad2 = pad2.substring(index2+1);
				imgIcoon.setImage(new Image(((Doelstelling) object).getIcon(), 25,25,true, true));
				choiceSdg.setValue(((Doelstelling) object).getSdGoal());
				choiceDatasource.setValue(((Doelstelling) object).getDatasource());
				
				List<Rol> rollen = ((Doelstelling) object).getRollen();
				
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
				
				for (Doelstelling s : ((Doelstelling)object).getComponents()) {
					System.out.println(s.getNaam());
//		            TreeItem<Doelstelling> empLeaf = new TreeItem<Doelstelling>(s);
		            boolean found = false;
//		            for (TreeItem<Doelstelling> depNode : rootNode.getChildren()) {
//		            	if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG_id()) {
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
		                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
		                );
		                
		                rootNode2.getChildren().add(depNode);
		            }
		        }
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
			
			//icoon verandert als je op een icoon klikt van de lijst
			listIconen.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					
					String icoonPath = (String) listIconen.getSelectionModel().getSelectedItem();
					
					imgIcoon.setImage(new Image(icoonPath, 25, 25, true, true));
					
				}
			});
			
			choiceBewerking.setItems(FXCollections.observableList(doelTypes));
			choiceSdg.setItems(FXCollections.observableList(dc.getSdgs()));
			choiceDatasource.setItems(FXCollections.observableList(dc.getDatasources()));
			
			treeGekozenSubs.setRoot(rootNode2);
			treeGekozenSubs.setShowRoot(false);

			
			TreeItem<Doelstelling> rootNode = 
			        new TreeItem<Doelstelling>(null);

			for (Doelstelling s : dc.getDoelstellingen()) {
				System.out.println(s.getNaam());
//	            TreeItem<Doelstelling> empLeaf = new TreeItem<Doelstelling>(s);
	            boolean found = false;
//	            for (TreeItem<Doelstelling> depNode : rootNode.getChildren()) {
//	            	if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG_id()) {
//	            		depNode.getChildren().add(empLeaf);
//	                  found = true;
//	                  break;
//	            	}
//	            }
	            String pad = s.getIcon();
				int index = pad.indexOf("c");
				pad = pad.substring(index+1);

	            if (!found) {
	                TreeItem<Doelstelling> depNode = new TreeItem<Doelstelling>(
	                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
	                );
	                
	                rootNode.getChildren().add(depNode);
	            }
	        }
			treeKiesSubs.setRoot(rootNode);
			
			treeKiesSubs.setShowRoot(false);
			
			EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
			    handleMouseClicked(event);
			};
			
			EventHandler<MouseEvent> mouseEventHandle2 = (MouseEvent event) -> {
			    handleMouseClickedBack(event);
			};

			treeKiesSubs.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
			treeGekozenSubs.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle2);
			lblErrorMessage.setVisible(false);
			this.getChildren().addAll(lblErrorMessage, imgIcoon, lblBewerking, lblDoelwaarde, lblIcoon, lblKiesIcoon, lblKiesSub, lblMaakOfWijzig, lblNaam, lblRollen, lblSdg, lblSubs, txtDoelwaarde, txtEenheid, txtnaam, treeKiesSubs, treeGekozenSubs, btnAnnuleer, btnOpslaan, choiceBewerking, choiceSdg, listIconen, kiesCoordinator, kiesDirectie, kiesManager, kiesStakeholder, lblDatasource, choiceDatasource);
			
			btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					//lblErrorMessage.setVisible(false);
					try {
						lblErrorMessage.setVisible(false);
					populateMap(rootNode2);
					
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
						throw new IllegalArgumentException("De doelwaarde is geen geldig kommagetal gescheiden door een punt");
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
					
					if(object != null) {
						dc.setCurrentDoelstelling((Doelstelling)object);
						dc.wijzigMVODoelstelling(doel);

					}else {
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
			
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void maakLeeg() {
		this.getChildren().clear();
		
	}

	
	private void handleMouseClicked(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    if (node instanceof Text || (node instanceof TreeCell && ((Labeled) node).getText() != null)) {
	    	TreeItem<Doelstelling> c = treeKiesSubs.getSelectionModel().getSelectedItem();
            c.getParent().getChildren().remove(c);
            treeGekozenSubs.getSelectionModel().selectFirst();
            TreeItem<Doelstelling> b = treeGekozenSubs.getSelectionModel().getSelectedItem();
            if(b == null || b.getParent() == null) {
            	treeGekozenSubs.getRoot().getChildren().add(c);
            } else {
                b.getParent().getChildren().add(c);
            }
            
	    	
	    }
	}
	
	private void handleMouseClickedBack(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    if (node instanceof Text || (node instanceof TreeCell && ((Labeled) node).getText() != null)) {
	    	TreeItem<Doelstelling> c = treeGekozenSubs.getSelectionModel().getSelectedItem();
            c.getParent().getChildren().remove(c);
            treeKiesSubs.getSelectionModel().selectFirst();
        	TreeItem<Doelstelling> b = treeKiesSubs.getSelectionModel().getSelectedItem();
            if(b.getParent() == null) {
            	treeKiesSubs.getRoot().getChildren().add(c);
            } else {
            	
                b.getParent().getChildren().add(c);
            }
            
	    	
	    }
	}
	
	// METHODE OM DE ELEMENTEN UIT DE TREEVIEW TE HALEN EN DAARNA OM TE ZETTEN NAAR EN LIST
		private void populateMap(TreeItem<Doelstelling> item){

	        if(item.getChildren().size() > 0){
	            for(TreeItem<Doelstelling> subItem : item.getChildren()){
	                populateMap(subItem);
	            }
	        }
	        else {
	        	Doelstelling node = (Doelstelling) item.getValue();     
	            	if(node != null) {
	            		map.put(node.toString(), node);
	            	}
	                
	        }
	    }
		
		private void refreshScherm() {
//			PanelOverzicht po = new PanelOverzicht();
//			// Eerst het hoofdscherm opvragen adhv dit scherm
//			Parent hoofdScherm = UpdateOrCreateDoelstelling.this.getParent();
//			if (hoofdScherm instanceof BorderPane) {
//				Node details = ((Pane) ((BorderPane) hoofdScherm).getCenter());
//				((UpdateOrCreateDoelstelling) details).maakLeeg();
//			}
			maakLeeg();
			
		}

}
