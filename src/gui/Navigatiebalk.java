package gui;

import domein.DomeinController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Navigatiebalk extends BorderPane {
	
	private DomeinController dc;

	public Navigatiebalk(DomeinController dc) {
		this.dc = dc;
		initGui();
	}

	private void initGui() {

		this.setStyle("-fx-background-color: white;");
		
		// Logo aanmaken
		ImageView imgLogo = new ImageView(new Image(getClass().getResourceAsStream("/images/logo.png")));
		
		// Hoogte en breedte instellen van het logo
		imgLogo.setFitWidth(130);
		imgLogo.setFitHeight(40);
		
		
		// Vrije ruimte instellen
		Region r = new Region();
		r.setPrefWidth(15);
		r.setMinWidth(USE_PREF_SIZE);
		
		Region r2 = new Region();
		r2.setPrefWidth(30);
		r2.setMinWidth(USE_PREF_SIZE);
		
		// Knoppen aanmaken
		Button btnCategorie = new Button("Categorie beheren");
		Button btnDatasource = new Button("Datasource beheren");
		Button btnDoelstelling = new Button("Doelstelling beheren");
		

		// Knoppen stijlen
		btnCategorie.setStyle("-fx-background-color: #495973;-fx-text-fill: white;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
		btnDatasource.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
		btnDoelstelling.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

		
		// Naam en functie weergeven
		Label lblNaamFunctie = new Label(dc.getAangemeldeGebruiker().getRol() + "\n" + dc.getAangemeldeGebruiker().getGebruikersnaam());
		lblNaamFunctie.setStyle("-fx-text-fill: #495973;  -fx-font-size: 16;");
		lblNaamFunctie.setMinWidth(USE_PREF_SIZE);
		

		
		// Navigatie centreren met HBox
		HBox navHBox = new HBox(btnCategorie, btnDatasource, btnDoelstelling);
		// zet breed genoeg (bv zo breed als scherm)
		navHBox.setAlignment(Pos.CENTER);
		

		
		HBox imgMetMarginLeft = new HBox();
		imgMetMarginLeft.getChildren().addAll(r, imgLogo);
		this.setLeft(imgMetMarginLeft);
		
		this.setCenter(navHBox);
		BorderPane.setAlignment(navHBox ,Pos.CENTER );
		
		HBox lblNaamMetMarginRight = new HBox();
		lblNaamMetMarginRight.getChildren().addAll(lblNaamFunctie, r2);
		this.setRight(lblNaamMetMarginRight);
		


		// Als er op een knop geklikt wordt
		btnCategorie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnCategorie.setStyle("-fx-background-color: #495973;-fx-text-fill: white;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnDatasource.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDoelstelling.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

				refreshOverzicht(dc.getCategorien(), "categorieën");
			}
		});
		btnDatasource.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDatasource.setStyle("-fx-background-color: #495973;-fx-text-fill: white;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnCategorie.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDoelstelling.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");

				
				refreshOverzicht(dc.getDatasources(), "datasources");
			}
		});
		btnDoelstelling.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDoelstelling.setStyle("-fx-background-color: #495973;-fx-text-fill: white;  -fx-font-size: 20; -fx-padding: 10 10 10 10;");
				btnCategorie.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				btnDatasource.setStyle("-fx-background-color: white;-fx-text-fill: #495973;  -fx-font-size: 20;-fx-padding: 10 10 10 10;");
				Parent hoofdScherm = Navigatiebalk.this.getParent();
					((BorderPane) hoofdScherm).setLeft(null);
					PanelOverzichtTreeview p = new PanelOverzichtTreeview();
					((BorderPane) hoofdScherm).setLeft(p);
					p.initGui(dc.getDoelstellingen(), "doelstellingen", dc);
			}
		});

	}
	
	private <E> void refreshOverzicht(ObservableList<E> lijst, String soort) {
		
		
		PanelOverzicht<E> po = new PanelOverzicht<>();
		// Eerst het hoofdscherm opvragen adhv dit scherm
		Parent hoofdScherm = Navigatiebalk.this.getParent();
		
		if (hoofdScherm instanceof BorderPane) {

				((BorderPane) hoofdScherm).setLeft(po);

				po.initGui(lijst, soort, dc);
		}
		
		
	}

}
