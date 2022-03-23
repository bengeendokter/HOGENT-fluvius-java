package gui;

import domein.Doelstelling;
import domein.DomeinController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelOverzichtTreeview extends VBox {
	
//	private ObservableList<Doelstelling> items;
//	private DomeinController dc;
	TreeView<Doelstelling> treeview = new TreeView<>();

	public PanelOverzichtTreeview() {
		
		//initGui(items, "doelstellingen", dc);
	}

	public void initGui(ObservableList<Doelstelling> items, String soort, DomeinController dc) {
//		this.items = items;
//		this.dc = dc;
		
		this.getChildren().clear();
		
		this.setStyle("-fx-background-color: #495973;");
		
		// Label aanmaken
		Label lblOverzicht = new Label("Overzicht " + soort);
		lblOverzicht.setStyle("-fx-text-fill: white;  -fx-font-size: 20;-fx-font-weight: bold; ");
		
		
		
		TreeItem<Doelstelling> rootNode = 
		        new TreeItem<Doelstelling>(null);

		for (Doelstelling s : items) {
			System.out.println(s.getNaam());
            TreeItem<Doelstelling> empLeaf = new TreeItem<Doelstelling>(s);
            boolean found = false;
            for (TreeItem<Doelstelling> depNode : rootNode.getChildren()) {
            	if(s.getParentComponent() != null) {
            		if(depNode.getValue().getDoelstellingID() == s.getParentComponent().getDoelstellingID()) {
                		depNode.getChildren().add(empLeaf);
                      found = true;
                      break;
                	}
            	}	
            }
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
 
		treeview.setRoot(rootNode);
		treeview.setShowRoot(false);
		

		
		treeview.setCellFactory(param -> new TreeCell<Doelstelling>()
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
		
		// Eventhandler vervangen met Listenen zodat eerste doelstelling direct kan worden weergegeven
//		EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
//		    handleMouseClicked(event);
//		};
//
//		treeview.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
		treeview.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
			if(newValue != null)
			{
				// Eerst het hoofdscherm opvragen adhv dit scherm
				Parent hoofdScherm = PanelOverzichtTreeview.this.getParent();
				
				if(hoofdScherm instanceof BorderPane)
				{
					DoelstellingDetailsTest test = new DoelstellingDetailsTest(dc, newValue.getValue());

						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								((BorderPane) hoofdScherm).setCenter(test);
							}
						});

				}
			}
		});
		
		// Butten aanmaken #B2D234
		Button btnMaakNieuwe = new Button("+");
		btnMaakNieuwe.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;");

		btnMaakNieuwe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {

				if(soort.equals("doelstellingen")) {
					UpdateOrCreateDoelstelling<Doelstelling> vs = new UpdateOrCreateDoelstelling<>(dc, null, "Maak nieuwe doelstelling");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = PanelOverzichtTreeview.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
				}
				
				
			}
		});
		
		// Toevoegen aan het scherm
		this.getChildren().addAll(lblOverzicht, treeview, btnMaakNieuwe);

		// Ruimte instellen
		this.setPadding(new Insets(10));
		this.setSpacing(5);
		this.setAlignment(Pos.TOP_CENTER);
		this.setMinWidth(USE_PREF_SIZE);
		
		// Eerste item in TreeView selecteren en weergeven
		treeview.getSelectionModel().selectFirst();
	}
	
//	private void handleMouseClicked(MouseEvent event) {
//	    Node node = event.getPickResult().getIntersectedNode();
//	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
//	    	TreeItem c = (TreeItem)treeview.getSelectionModel().getSelectedItem();
//	    	
//	    	
//	    	Parent hoofdScherm = PanelOverzichtTreeview.this.getParent();
//			
//			if (hoofdScherm instanceof BorderPane) {
//
//				 //DetailsScherm opvragen adhv het hoofdScherm
//				Node details = ( ((BorderPane) hoofdScherm).getCenter());
//
//					//DoelstellingDetailPanel dd = new DoelstellingDetailPanel(dc, newValue);
//					DoelstellingDetailsTest test = new DoelstellingDetailsTest(dc, c.getValue());						//DoelstellingDetailPanelController2 d2 = new DoelstellingDetailPanelController2(dc, newValue);
//						((BorderPane) hoofdScherm).setCenter(test);
//
//	    	
//	    	
//	    	
//          
//	    	
//	    }
//	} 
//	}


}
