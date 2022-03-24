package gui;

import java.io.IOException;

import domein.Datasource;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DatasourceDetailsController<E> extends BorderPane {
	@FXML
	private Label lblDetailsDatasource;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblNaamIngevuld;
	@FXML
	private Label lblType;
	@FXML
	private Label lblLink;
	@FXML
	private TextArea areaLink;
	@FXML
	private Label lblHostnaam;
	@FXML
	private Label lblHostnaamIngevuld;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblGebruikersnaamIngevuld;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private Label lblWachtwoordIngevuld;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private Label lblTypeIngevuld;
	@FXML
	private Button btnOpslaan;
	@FXML
	private Button btnVerwijder;
	@FXML
	private VBox vBoxLink;
	@FXML
	private HBox hBoxHostnaam;
	@FXML
	private HBox hBoxGebruikersnaam;
	@FXML
	private HBox hBoxWachtwoord;
	@FXML
	private Label lblWijzigSnelheid;
	@FXML
	private Label lblMaat;
	@FXML
	private Label lblKolom;
	@FXML
	private HBox hBoxKolomNummer;
	@FXML
	private Label lblStatus;
//	private DomeinController dc;
//	private E object;

	public DatasourceDetailsController(DomeinController dc, E object) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DatasourceDetails.fxml"));
		loader.setController(this);
		loader.setRoot(this);
//		this.dc = dc;
//		this.object = object;
		
		
		try {
			loader.load();
			
			Datasource huideDatasource = ((Datasource) object);
			
			lblNaamIngevuld.setText(huideDatasource.getNaam());
			lblTypeIngevuld.setText(huideDatasource.getTypeDatasource().toString());
			lblWijzigSnelheid.setText(huideDatasource.getWijzigbaarheid());
			lblMaat.setText(huideDatasource.getMaat());
			lblKolom.setText(Integer.toString(huideDatasource.getKolom()));
			
			areaLink.setText(huideDatasource.getLink());
			areaLink.setEditable(false);
			lblHostnaamIngevuld.setText(huideDatasource.getHostname());
			lblGebruikersnaamIngevuld.setText(huideDatasource.getUsername());
			lblWachtwoordIngevuld.setText(huideDatasource.getPassword());
			
			//System.out.println(((Datasource)object).getTypeDatasource().toString());
			if(huideDatasource.getTypeDatasource().toString().equals("databank")) {
				vBoxLink.setVisible(false);
				vBoxLink.setManaged(false);
				
				hBoxGebruikersnaam.setVisible(true);
				hBoxGebruikersnaam.setManaged(true);
				
				hBoxHostnaam.setVisible(true);
				hBoxHostnaam.setVisible(true);
				
				hBoxWachtwoord.setVisible(true);
				hBoxWachtwoord.setVisible(true);
			} else {
				vBoxLink.setVisible(true);
				vBoxLink.setManaged(true);
				
				hBoxGebruikersnaam.setVisible(false);
				hBoxGebruikersnaam.setManaged(false);
				
				hBoxHostnaam.setVisible(false);
				hBoxHostnaam.setVisible(false);
				
				hBoxWachtwoord.setVisible(false);
				hBoxWachtwoord.setVisible(false);
			}
			
			lblErrorMessage.setText("");
			lblErrorMessage.setVisible(false);
			
			lblStatus.setText("IN ORDE");
			lblStatus.setTextFill(Color.GREEN);
			
			Boolean isCorrupt = huideDatasource.getCorrupt();
			Boolean updateNodig = huideDatasource.wijzigNood();
			
			if (isCorrupt) {
				lblStatus.setText("CORRUPT");
				lblStatus.setTextFill(Color.RED);
			}
			
			if (updateNodig) {
				lblStatus.setText("VEROUDERD");
				lblStatus.setTextFill(Color.RED);
			}
			
			//this.getChildren().addAll(lblWachtwoordIngevuld, lblGebruikersnaam1,lblDetailsDatasource, lblGebruikersnaam, lblGebruikersnaamIngevuld, lblHostnaam, lblHostnaamIngevuld, lblLink, lblNaam, lblNaamIngevuld, lblType, btnAnnuleer, btnOpslaan, lblTypeIngevuld, errorMessage);
		
			btnVerwijder.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					//vragen of de gebruiker zeker is
					Alert boodschap = new Alert(AlertType.CONFIRMATION);
					boodschap.setTitle("Verwijderen");
					
					boodschap.setContentText("Bent u zeker dat u deze doelstelling wilt verwijderen?");
					
					boodschap.showAndWait().ifPresent(response -> {
						if(response != ButtonType.CANCEL)
						{
							try {
								lblErrorMessage.setVisible(false);
								dc.setCurrentDatasource(huideDatasource);
								dc.verwijderMVODatasource();
								maakLeeg();
							}
							catch(IllegalArgumentException e)
							{
								lblErrorMessage.setText(e.getMessage());
								lblErrorMessage.setVisible(true);
							}
							
						}
					});
					
				}
			});
			
			// EIGENLIJK OM TE WIJZIGEN OEPS
			btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {

					UpdateOrCreateDatasourceController<E> vs = new UpdateOrCreateDatasourceController<>(dc, object, "Wijzig datasource");
					
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = DatasourceDetailsController.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
					
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void maakLeeg() {
		this.getChildren().clear();
		
	}

}
