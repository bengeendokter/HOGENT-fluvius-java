package gui;

import java.util.Comparator;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DomeinController;
import domein.SdGoal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CategorieDetailPanel<E> extends GridPane{
	private E object;
	private DomeinController dc;
	
	private final Image depIcon = 
	        new Image(getClass().getResourceAsStream("/images/people.png"));
	
	
	public CategorieDetailPanel() {
		
		//initGui();
	}

	public void initGui(E object, DomeinController dc) {
		this.object = object;
		this.dc = dc;
		
		this.setStyle("-fx-background-color: white;");
		
		// Scherm leegmaken
		this.getChildren().clear();
		
		Label lblDetails = new Label("Details categorie");
		lblDetails.setStyle("-fx-text-fill: #004C69;  -fx-font-size: 20; -fx-underline:true");
		this.add(lblDetails, 0, 0);
		
		Label lblNaam = new Label("Naam: ");
		lblNaam.setStyle("-fx-text-fill: black;  -fx-font-size: 20; ");
		this.add(lblNaam, 0, 1);
		
		Label lblNaamIngevuld = new Label();
		lblNaamIngevuld.setStyle("-fx-background-color: white; -fx-text-fill: black;  -fx-font-size: 20; ");
		if(object instanceof Categorie) {
			lblNaamIngevuld.setText(((Categorie) object).getNaam());
		}
		this.add(lblNaamIngevuld, 1, 1);
		
		Label lblIcoon = new Label("Icoon: ");
		lblIcoon.setStyle("-fx-text-fill: black;  -fx-font-size: 20; ");
		this.add(lblIcoon, 0, 2);
		
		Label lblIcoonIngevuld = new Label();
		lblIcoonIngevuld.setStyle("-fx-background-color: white; -fx-text-fill: black;  -fx-font-size: 20; ");
		
		if(object instanceof Categorie) {
			String pad = ((Categorie) object).getIcon();
			int index = pad.indexOf("c");
			pad = pad.substring(index+1);
			// Mannetje weergeven
			ImageView imgIcoon2 = new ImageView(new Image(getClass().getResourceAsStream(pad)));
			
			// Hoogte en breedte instellen van het logo
			imgIcoon2.setFitWidth(40);
			imgIcoon2.setFitHeight(40);
			this.add(imgIcoon2, 1, 2);
			
		}

		
		// Sdg's weergeven en aanmaken in een treeview
		Label lblSdgs = new Label("SDG's: ");
		lblSdgs.setStyle("-fx-text-fill: black;  -fx-font-size: 20; ");
		this.add(lblSdgs, 0, 3);
		
		TreeItem<SdGoal> rootNode = 
		        new TreeItem<SdGoal>(null);

		
		
		for (SdGoal s : ((Categorie) object).getSdGoals()) {
			System.out.println(s.getNaam());
            TreeItem<SdGoal> empLeaf = new TreeItem<SdGoal>(s);
            boolean found = false;
            for (TreeItem<SdGoal> depNode : rootNode.getChildren()) {
            	if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG_id()) {
            		depNode.getChildren().add(empLeaf);
                  found = true;
                  break;
            	}
            }
            String pad = s.getIcon();
			int index = pad.indexOf("c");
			pad = pad.substring(index+1);

            if (!found) {
                TreeItem<SdGoal> depNode = new TreeItem<SdGoal>(
                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
                );
                
                rootNode.getChildren().add(depNode);
            }
        }
 
 
        TreeView<SdGoal> treeView = new TreeView<SdGoal>(rootNode);
        treeView.setShowRoot(false);
        this.add(treeView, 0,10);
		
        
        // knop om te verwijderen
        Button btnDelete = new Button("Verwijder");
        btnDelete.setStyle("-fx-background-color: #004C69;-fx-text-fill: white;  -fx-font-size: 20;");
        this.add(btnDelete, 0,15);
        
        // Als er op de verwijderknop geklikt wordt moet de categorie verwijderd worden
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				//vragen of de gebruiker zeker is
				Alert boodschap = new Alert(AlertType.CONFIRMATION);
				boodschap.setTitle("Verwijderen");
				
				boodschap.setContentText("Bent u zeker dat u deze categorie wilt verwijderen?");
				
				boodschap.showAndWait().ifPresent(response -> {
					if(response != ButtonType.CANCEL)
					{
						dc.setCurrentCategorie((Categorie)object);
						dc.verwijderCategorie();
						maakLeeg();
					}
				});
				
			}
		});
        
        
        // Knop om te wijzigen 
        Button btnUpdate = new Button("Wijzig");
        btnUpdate.setStyle("-fx-background-color: #004C69;-fx-text-fill: white;  -fx-font-size: 20;");
        this.add(btnUpdate, 0,16);
        

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {

				UpdateOrCreateCategoryController vs = new UpdateOrCreateCategoryController(dc, object, "Wijzig categorie");
				// Eerst het hoofdscherm opvragen adhv dit scherm
				Parent hoofdScherm = CategorieDetailPanel.this.getParent();
				if (hoofdScherm instanceof BorderPane) {
					// DetailsScherm opvragen adhv het hoofdScherm
					((BorderPane) hoofdScherm).setCenter(vs);
				}
				
			}
		});
		
		// Ruimte instellen
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.TOP_LEFT);
		
	}

	public void maakLeeg() {
		this.getChildren().clear();
		
	}

}
