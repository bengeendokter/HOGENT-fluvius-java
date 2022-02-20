package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class OverzichtSchermController extends Pane{

public OverzichtSchermController() {
		

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
