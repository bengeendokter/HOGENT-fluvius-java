package gui;

import domein.DomeinController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Navigatiebalk<E> extends HBox {
	
	private DomeinController dc;

	public Navigatiebalk(DomeinController dc) {
		this.dc = dc;
		initGui();
	}

	private void initGui() {
		//this.setStyle("-fx-background-color: #B2D234;");
		this.setStyle("-fx-background-color: #B2D234;");
		
		// Logo aanmaken
		ImageView imgLogo = new ImageView(new Image(getClass().getResourceAsStream("/images/logo.png")));
		
		// Hoogte en breedte instellen van het logo
		imgLogo.setFitWidth(140);
		imgLogo.setFitHeight(40);
		
		// Vrije ruimte instellen
		Region r = new Region();
		r.setPrefWidth(300);
		
		// Knoppen aanmaken
		Button btnCategorie = new Button("Categorie beheren");
		Button btnDatasource = new Button("Datasource beheren");
		Button btnDoelstelling = new Button("Doelstelling beheren");
		

		// Knoppen stijlen
		btnCategorie.setStyle("-fx-background-color: white;-fx-text-fill: black;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
		btnDatasource.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
		btnDoelstelling.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

		Region r2 = new Region();
		r2.setPrefWidth(300);
		
		// Naam en functie weergeven
		Label lblNaamFunctie = new Label(dc.getAangemeldeGebruiker().getRol() + "\n" + dc.getAangemeldeGebruiker().getGebruikersnaam());
		lblNaamFunctie.setStyle("-fx-text-fill: white;  -fx-font-size: 16;");
		
		// Mannetje weergeven
		ImageView imgVentje = new ImageView(new Image(getClass().getResourceAsStream("/images/ventje.png")));
		
		// Hoogte en breedte instellen van het ventje
		imgVentje.setFitWidth(40);
		imgVentje.setFitHeight(40);
		
		// Toevoegen aan scherm
		this.getChildren().addAll(imgLogo, r2, btnCategorie, btnDatasource, btnDoelstelling,r, lblNaamFunctie);

		// Ruimte instellen
		//this.setPadding(new Insets(5));
		//this.setSpacing(5);
		//this.setAlignment(Pos.TOP_LEFT);

		// Als er op een knop geklikt wordt
		btnCategorie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnCategorie.setStyle("-fx-background-color: white;-fx-text-fill: black;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnDatasource.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDoelstelling.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

				refreshOverzicht(dc.getCategorien(), "categorieën");
			}
		});
		btnDatasource.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDatasource.setStyle("-fx-background-color: white;-fx-text-fill: black;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnCategorie.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDoelstelling.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

				
				refreshOverzicht(dc.getDatasources(), "datasources");
			}
		});
		btnDoelstelling.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDoelstelling.setStyle("-fx-background-color: white;-fx-text-fill: black;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnCategorie.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDatasource.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				Parent hoofdScherm = Navigatiebalk.this.getParent();
					((BorderPane) hoofdScherm).setLeft(null);
					PanelOverzichtTreeview p = new PanelOverzichtTreeview();
					((BorderPane) hoofdScherm).setLeft(p);
					p.initGui(dc.getDoelstellingen(), "doelstellingen", dc);
			}
		});

	}
	
	private <E> void refreshOverzicht(ObservableList<E> lijst, String soort) {
		
		
		PanelOverzicht po = new PanelOverzicht();
		// Eerst het hoofdscherm opvragen adhv dit scherm
		Parent hoofdScherm = Navigatiebalk.this.getParent();
		
		if (hoofdScherm instanceof BorderPane) {
			// Overzicht opvragen adhv het hoofdScherm
			Node overzicht = ((VBox) ((BorderPane) hoofdScherm).getLeft());
			if (overzicht instanceof PanelOverzicht) {
				
				((PanelOverzicht) overzicht).initGui(lijst, soort, dc);
			}
			if(overzicht instanceof PanelOverzichtTreeview) {
				((BorderPane) hoofdScherm).setLeft(null);
				((BorderPane) hoofdScherm).setLeft(po);
				overzicht = ((VBox) ((BorderPane) hoofdScherm).getLeft());
				((PanelOverzicht) overzicht).initGui(lijst, soort, dc);
			}
			
			Node details = ((Pane) ((BorderPane) hoofdScherm).getCenter());
			((BorderPane) hoofdScherm).setCenter(null);
			//((CategorieDetailPanel) details).maakLeeg();
		}
		
		
	}

}
