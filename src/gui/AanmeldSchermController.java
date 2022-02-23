package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AanmeldSchermController extends Pane {

	@FXML
	private Region witVlak;
	@FXML
	private Label lblGebruiker;

	@FXML
	private Label lblMeldAan;
	@FXML
	private ImageView logo;
	
	@FXML
	private Button btnMeldAan;

	@FXML
	private TextField txtGebruikersnaam;

	@FXML
	private PasswordField pssWachtwoord;
	
	private DomeinController dc;

	public AanmeldSchermController(DomeinController dc) {

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
			
			this.dc = dc;
			txtGebruikersnaam.setPromptText("Gebruikersnaam");
			pssWachtwoord.setPromptText("Wachtwoord");
			btnMeldAan.setText("Meld aan");

			// Achtergrond maken
			for (int r = -20; r < 860; r = r + 100) {
				for (int i = 0; i < 860; i = i + 80) {
					Circle circle = new Circle();
					circle.setCenterX(i);
					circle.setCenterY(r);
					circle.setRadius(100);
					circle.setStroke(Color.web("0xB2D235"));
					circle.setFill(Color.WHITE);
					this.getChildren().add(circle);
				}
			}
			witVlak.toFront();
			lblGebruiker.toFront();
			lblMeldAan.toFront();
			logo.toFront();
			btnMeldAan.toFront();
			txtGebruikersnaam.toFront();
			pssWachtwoord.toFront();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		

	}

	@FXML
	public void meldAan(ActionEvent event) {
		try {
			
			dc.meldAan(txtGebruikersnaam.getText(), pssWachtwoord.getText());
			
			OverzichtSchermController vs = new OverzichtSchermController();
			Scene scene = new Scene(vs);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		} catch (IllegalArgumentException e) {

			Alert boodschap = new Alert(AlertType.ERROR);
			boodschap.setTitle("Foutmelding");
			boodschap.setContentText(e.getMessage());
			boodschap.showAndWait();
		}
	}

}
