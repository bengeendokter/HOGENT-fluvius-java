package gui;

import java.sql.SQLIntegrityConstraintViolationException;

import domein.DTODatasource;
import domein.Datasource;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UpdateOrCreateDatasourceController<E> extends BorderPane {
	
	@FXML
	private Label lblUpdateOrCreate;
	@FXML
	private Button btnOpslaan;
	@FXML
	private Button btnAnnuleer;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private	TextField txtFieldNaam;
	@FXML
	private ChoiceBox<String> cBoxType;
	@FXML
	private ChoiceBox<String> cBoxWijzigsnelheid;
	@FXML
	private TextField txtFieldMaat;
	@FXML
	private TextField txtFieldKolom;
	@FXML
	private TextArea txtAreaLink;
	@FXML
	private TextField txtFieldHostnaam;
	@FXML
	private TextField txtFieldGebruikersnaam;
	@FXML
	private TextField txtFieldWachtwoord;
	@FXML
	private CheckBox chkBoxCorrupt;
	@FXML
	private VBox vBoxLink;
	@FXML
	private HBox hboxHostsnaam;
	@FXML
	private HBox hBoxGebruikersnaam;
	@FXML
	private HBox hBoxWachtwoord;
	
	public UpdateOrCreateDatasourceController(DomeinController dc, E object, String wijzigMaak) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrCreateDatasource.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		
		try {
			loader.load();
			
			lblUpdateOrCreate.setText(wijzigMaak);
			cBoxType.getItems().addAll("csv", "excel", "databank");
			cBoxWijzigsnelheid.getItems().addAll("traag", "normaal", "snel");
			
			vBoxLink.setVisible(false);
			hboxHostsnaam.setVisible(false);
			hBoxGebruikersnaam.setVisible(false);
			hBoxWachtwoord.setVisible(false);
			
			vBoxLink.setManaged(false);
			hboxHostsnaam.setManaged(false);
			hBoxGebruikersnaam.setManaged(false);
			hBoxWachtwoord.setManaged(false);
			
			lblErrorMessage.setText("");
			lblErrorMessage.setVisible(false);
			
			txtAreaLink.setWrapText(true);
		
			
			cBoxType.getSelectionModel().selectedItemProperty()
			.addListener(((observableValue, oldValue, newValue) -> {
				if(newValue == "databank")
				{
					vBoxLink.setVisible(false);
					hboxHostsnaam.setVisible(true);
					hBoxGebruikersnaam.setVisible(true);
					hBoxWachtwoord.setVisible(true);
					
					vBoxLink.setManaged(false);
					hboxHostsnaam.setManaged(true);
					hBoxGebruikersnaam.setManaged(true);
					hBoxWachtwoord.setManaged(true);
				}
				else
				{
					vBoxLink.setVisible(true);
					hboxHostsnaam.setVisible(false);
					hBoxGebruikersnaam.setVisible(false);
					hBoxWachtwoord.setVisible(false);
					
					vBoxLink.setManaged(true);
					hboxHostsnaam.setManaged(false);
					hBoxGebruikersnaam.setManaged(false);
					hBoxWachtwoord.setManaged(false);
				}
			}));
			
			if(object != null) {
				wijzigBestaandeCategorie(object);
			}
			
			btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					lblErrorMessage.setText("");
					lblErrorMessage.setVisible(false);
					
					try {
						
						int kolom;
						
						try {
							kolom = Integer.parseInt(txtFieldKolom.getText());
						} catch (NumberFormatException e) {
							kolom = -1;
						}
					
						DTODatasource data = new DTODatasource(
								txtFieldNaam.getText(),
								cBoxType.getSelectionModel().getSelectedItem(),
								txtAreaLink.getText(),
								txtFieldHostnaam.getText(),
								txtFieldGebruikersnaam.getText(),
								txtFieldWachtwoord.getText(),
								chkBoxCorrupt.isSelected(),
								cBoxWijzigsnelheid.getSelectionModel().getSelectedItem(),
								txtFieldMaat.getText(),
								kolom
						);
						
						if (object != null) {
							dc.setCurrentDatasource((Datasource) object);
							dc.wijzigMVODatasource(data);
						} else {
							dc.voegMVODatasourceToe(data);
						}
						
						refreshScherm();
						
					} catch (IllegalArgumentException | SQLIntegrityConstraintViolationException | IllegalStateException e) {
						lblErrorMessage.setText(e.getMessage());
						lblErrorMessage.setVisible(true);
					}
					
				}
			});
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				maakLeeg();
			}
		});
	}
	
	private void wijzigBestaandeCategorie(E object) {
		
		Datasource huidigeDatasource = ((Datasource) object);
		
		txtFieldNaam.setText(huidigeDatasource.getNaam());
		cBoxType.setValue(huidigeDatasource.getTypeDatasource().toString());
		cBoxWijzigsnelheid.setValue(huidigeDatasource.getWijzigbaarheid());
		txtFieldMaat.setText(huidigeDatasource.getMaat());
		txtFieldKolom.setText(Integer.toString(huidigeDatasource.getKolom()));
		chkBoxCorrupt.setSelected(huidigeDatasource.getCorrupt());
		
		if (huidigeDatasource.getTypeDatasource().toString() == "databank") {
			txtFieldHostnaam.setText(huidigeDatasource.getHostname());
			txtFieldGebruikersnaam.setText(huidigeDatasource.getUsername());
			txtFieldWachtwoord.setText(huidigeDatasource.getPassword());
		} else {
			txtAreaLink.setText(huidigeDatasource.getLink());
		}
		
		
	}

	private void refreshScherm() {
		maakLeeg();
		
	}
	public void maakLeeg() {
		this.getChildren().clear();
		
	}

}
