package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OverzichtSchermController extends Pane {

	@FXML
	private Region groeneBalk;
	@FXML
	private ImageView imgCD;
	@FXML
	private ImageView imgUD;
	@FXML
	private ImageView imgDD;
	@FXML
	private ImageView imgRD;
	@FXML
	private ImageView imgCC;
	@FXML
	private ImageView imgUC;
	@FXML
	private ImageView imgDC;
	@FXML
	private ImageView imgRC;
	@FXML
	private ImageView imgCS;
	@FXML
	private ImageView imgUS;
	@FXML
	private ImageView imgDS;
	@FXML
	private ImageView imgRS;
	@FXML
	private Label lblNaamIngelogdeGebruiker;
	@FXML
	private Label lblFunctieIngelogdeGebruiker;
	@FXML
	private ImageView logo;
	@FXML
	private ImageView ventje;

	public OverzichtSchermController() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();

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
			
			// Elementen naar de voorgrond plaatsen
			groeneBalk.toFront();
			imgCD.toFront();
			imgCC.toFront();
			imgUD.toFront();
			imgDD.toFront();
			imgRD.toFront();
			imgCC.toFront();
			imgUC.toFront();
			imgDC.toFront();
			imgRC.toFront();
			imgCS.toFront();
			imgUS.toFront();
			imgDS.toFront();
			imgRS.toFront();
			lblNaamIngelogdeGebruiker.toFront();
			lblFunctieIngelogdeGebruiker.toFront();
			// Dit wil nog niet
			ventje.toFront();
			logo.toFront();
			

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
