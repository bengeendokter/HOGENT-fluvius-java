package gui;


import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import domein.AanmeldController;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AanmeldenResponsiveController extends BorderPane
{
	
	@FXML
	private BorderPane borderPaneEen;
	@FXML
	private Pane paneEen;
	
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
	
	@FXML
	private Label foutmelding;
	
	@FXML
	private Circle circle;
	@FXML
	private Label uitroepteken;
	
	private AanmeldController aanmeldController;
	
	public AanmeldenResponsiveController(AanmeldController aanmeldController)
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldenResponsive.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			Image image = new Image("file:src/images/groen.PNG");

		    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

		    borderPaneEen.setBackground(new Background(new BackgroundImage(image,
		            BackgroundRepeat.REPEAT,
		            BackgroundRepeat.REPEAT,
		            BackgroundPosition.CENTER,
		            bSize)));
		    
		    logo.setImage(new Image("file:src/images/logo.PNG"));
			

			
			
			
			this.aanmeldController = aanmeldController;
				
			paneEen.toFront();
			lblMeldAan.toFront();
			logo.toFront();
			btnMeldAan.toFront();
			txtGebruikersnaam.toFront();
			pssWachtwoord.toFront();
			foutmelding.setVisible(false);
			circle.setVisible(false);
			uitroepteken.setVisible(false);
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	@FXML
	public void meldAan(ActionEvent event) throws SQLIntegrityConstraintViolationException, ExceptionInInitializerError
	{
		
		try
		{
			
			btnMeldAan.setVisible(false);
			DomeinController domeinController = aanmeldController.meldAan(txtGebruikersnaam.getText(),
					pssWachtwoord.getText());
			VolledigPanelController vs = new VolledigPanelController(domeinController);
			

			Scene scene = new Scene(vs);
			scene.getStylesheets().add("theme.css");
			Stage stage = (Stage) this.getScene().getWindow();
			stage.hide();
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			
		}
		catch(IllegalArgumentException e)
		{
			
			foutmelding.setText("Wachtwoord of gebruikersnaam is onjuist.");
			foutmelding.setVisible(true);
			foutmelding.toFront();
			circle.setVisible(true);
			uitroepteken.setVisible(true);
			circle.toFront();
			uitroepteken.toFront();
			
		}
		finally
		{
			try
			{
				Thread.sleep(1000);
				btnMeldAan.setVisible(true);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
}