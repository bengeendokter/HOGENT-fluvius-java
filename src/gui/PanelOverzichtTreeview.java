package gui;

import java.util.List;
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

public class PanelOverzichtTreeview extends VBox
{
	TreeView<Doelstelling> treeview = new TreeView<>();
	
	public PanelOverzichtTreeview()
	{
		
	}
	
	public void initGui(ObservableList<Doelstelling> doelstellingen, String soort, DomeinController dc)
	{		
		this.setStyle("-fx-background-color: #495973;");
		
		// Label aanmaken
		Label lblOverzicht = new Label("Overzicht " + soort);
		lblOverzicht.setStyle("-fx-text-fill: white;  -fx-font-size: 20;-fx-font-weight: bold; ");
		
		// maak rootNode
		TreeItem<Doelstelling> rootNode = new TreeItem<Doelstelling>(null);
		
		// vul rootNode recursief op met doelstellingen
		List<Doelstelling> rootDoelstellingen = doelstellingen.stream()
				.filter(doel -> doel.getParentComponent() == null).toList();
		addToTreeItem(rootNode, rootDoelstellingen);
		
		treeview.setRoot(rootNode);
		treeview.setShowRoot(false);
		
		// stel iconen en namen van TreeCellen in
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
		
		// on click
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
		
		btnMaakNieuwe.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent evt)
			{
				if(soort.equals("doelstellingen"))
				{
					UpdateOrCreateDoelstelling vs = new UpdateOrCreateDoelstelling(dc, null,
							"Maak nieuwe doelstelling");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = PanelOverzichtTreeview.this.getParent();
					if(hoofdScherm instanceof BorderPane)
					{
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
	
	private void addToTreeItem(TreeItem<Doelstelling> rootDoelstelling, List<Doelstelling> doelstellingen)
	{
		for(Doelstelling doelstelling : doelstellingen)
		{
			TreeItem<Doelstelling> parentDoelstelling = new TreeItem<>(doelstelling);
			
			addToTreeItem(parentDoelstelling,
					doelstelling.getComponents().stream().map(component -> (Doelstelling) component).toList());
			
			rootDoelstelling.getChildren().add(parentDoelstelling);
		}
	}
}
