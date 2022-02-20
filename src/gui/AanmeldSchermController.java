package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AanmeldSchermController extends Pane {

	
	@FXML
	private Button btnMeldAan;

	@FXML
	private TextField txtGebruikersnaam;
	
	@FXML
	private PasswordField pssWachtwoord;

	


	public AanmeldSchermController() {
		

		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
			txtGebruikersnaam.setPromptText("Gebruikersnaam");
			pssWachtwoord.setPromptText("Wachtwoord");
			btnMeldAan.setText("Meld aan");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}


	@FXML
	public void meldAan(ActionEvent event) {
		try {

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
