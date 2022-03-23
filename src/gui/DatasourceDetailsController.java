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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
			
			lblNaamIngevuld.setText(((Datasource) object).getNaam());
			lblTypeIngevuld.setText(((Datasource) object).getTypeDatasource().toString());
			lblWijzigSnelheid.setText(((Datasource) object).getWijzigbaarheid());
			lblMaat.setText(((Datasource) object).getMaat());
			
			areaLink.setText(((Datasource) object).getLink());
			areaLink.setEditable(false);
			lblHostnaamIngevuld.setText(((Datasource) object).getHostname());
			lblGebruikersnaamIngevuld.setText(((Datasource) object).getUsername());
			lblWachtwoordIngevuld.setText(((Datasource) object).getPassword());
			
			//System.out.println(((Datasource)object).getTypeDatasource().toString());
			if(((Datasource) object).getTypeDatasource().toString().equals("databank")) {
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
			
			Boolean isCorrupt = ((Datasource) object).getCorrupt();
			Boolean updateNodig = ((Datasource) object).wijzigNood();
			
			if (isCorrupt) {
				lblErrorMessage.setVisible(true);
				lblErrorMessage.setText("Deze datasource is corrupt. Bewerk of verwijder het aub.");
			}
			
			if (updateNodig) {
				lblErrorMessage.setVisible(true);
				lblErrorMessage.setText("Deze datasource bevat verouderde data. Update het aub.");
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
								dc.setCurrentDatasource((Datasource) object);
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
					// TODO: hier moet het wijzigscherm aangemaakt worden
					
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = DatasourceDetailsController.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						//((BorderPane) hoofdScherm).setCenter(vs);
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
