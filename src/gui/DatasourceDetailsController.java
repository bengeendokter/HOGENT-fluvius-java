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
	private Button btnAnnuleer;
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
			
			lblNaamIngevuld.setText(((Datasource)object).getNaam());
			areaLink.setText(((Datasource)object).getLink());
			lblHostnaamIngevuld.setText(((Datasource)object).getHostname());
			lblGebruikersnaamIngevuld.setText(((Datasource)object).getUsername());
			lblWachtwoordIngevuld.setText(((Datasource)object).getPassword());
			lblTypeIngevuld.setText(((Datasource)object).getTypeDatasource().toString());
			System.out.println(((Datasource)object).getTypeDatasource().toString());
			if(((Datasource)object).getTypeDatasource().toString().equals("databank")) {
				lblLink.setVisible(false);
				areaLink.setVisible(false);
			}
			if(((Datasource)object).getTypeDatasource().toString().equals("excel")) {
				lblWachtwoord.setVisible(false);
				lblGebruikersnaamIngevuld.setVisible(false);
				lblWachtwoordIngevuld.setVisible(false);
				lblHostnaam.setVisible(false);
				lblHostnaamIngevuld.setVisible(false);
				lblGebruikersnaam.setVisible(false);
			}
			
			//this.getChildren().addAll(lblWachtwoordIngevuld, lblGebruikersnaam1,lblDetailsDatasource, lblGebruikersnaam, lblGebruikersnaamIngevuld, lblHostnaam, lblHostnaamIngevuld, lblLink, lblNaam, lblNaamIngevuld, lblType, btnAnnuleer, btnOpslaan, lblTypeIngevuld, errorMessage);
		
			btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
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
								dc.setCurrentDatasource((Datasource)object);
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
