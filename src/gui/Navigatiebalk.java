package gui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Navigatiebalk extends HBox {

	public Navigatiebalk() {
		initGui();
	}

	private void initGui() {
		this.setStyle("-fx-background-color: #B2D234;");

		// Knoppen aanmaken
		Button btnCategorie = new Button("Categorie beheren");
		Button btnDatasource = new Button("Datasource beheren");
		Button btnDoelstelling = new Button("Doelstelling beheren");

		// Knoppen stijlen
		btnCategorie.setStyle("-fx-background-color: #B2D234;-fx-text-fill: #004C69;  -fx-font-size: 20;");
		btnDatasource.setStyle("-fx-background-color: #B2D234;-fx-text-fill: #004C69;  -fx-font-size: 20;");
		btnDoelstelling.setStyle("-fx-background-color: #B2D234;-fx-text-fill: #004C69;  -fx-font-size: 20;");

		// Toevoegen aan scherm
		this.getChildren().addAll(btnCategorie, btnDatasource, btnDoelstelling);

		// Ruimte instellen
		this.setPadding(new Insets(5));
		this.setSpacing(5);
		this.setAlignment(Pos.TOP_CENTER);

		// Als er op een knop geklikt wordt
		btnCategorie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnCategorie.setStyle("-fx-background-color: white;-fx-text-fill: #004C69;  -fx-font-size: 20;");
			}
		});
		btnDatasource.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDatasource.setStyle("-fx-background-color: white;-fx-text-fill: #004C69;  -fx-font-size: 20;");
			}
		});
		btnDoelstelling.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				btnDoelstelling.setStyle("-fx-background-color: white;-fx-text-fill: #004C69;  -fx-font-size: 20;");
			}
		});

	}

}
