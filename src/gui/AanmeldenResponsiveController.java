package gui;


import java.io.IOException;

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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class AanmeldenResponsiveController extends BorderPane
{
	
	@FXML
	private BorderPane borderPaneEen;
	@FXML
	private Pane paneEen;
	
	//private AanmeldController aanmeldController;
	
	public AanmeldenResponsiveController()
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldenResponsive.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			
			
			/*this.aanmeldController = aanmeldController;
			
			// Veldjes invullen
			txtGebruikersnaam.setPromptText("Gebruikersnaam");
			pssWachtwoord.setPromptText("Wachtwoord");
			btnMeldAan.setText("Meld aan");*/
			
			// Achtergrond maken
			for(int r = -20; r < 10000; r = r + 100)
			{
				for(int i = 0; i < 10000; i = i + 80)
				{
					Circle circle = new Circle();
					circle.setCenterX(i);
					circle.setCenterY(r);
					circle.setRadius(100);
					circle.setStroke(Color.web("0xB2D235"));
					circle.setFill(Color.WHITE);
					this.getChildren().add(circle);
					circle.setTranslateZ(1);
				}
			}
			
			paneEen.toFront();
			borderPaneEen.toBack();
			paneEen.setTranslateZ(Integer.MAX_VALUE);
			
			/*witVlak.setEffect(new DropShadow(10, Color.LIGHTGRAY));
			witVlak.toFront();
			lblGebruiker.toFront();
			lblMeldAan.toFront();
			logo.toFront();
			btnMeldAan.toFront();
			txtGebruikersnaam.toFront();
			pssWachtwoord.toFront();
			foutmelding.setVisible(false);
			circle.setVisible(false);
			uitroepteken.setVisible(false);*/
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	/*@FXML
	public void meldAan(ActionEvent event)
	{
		
		try
		{
			
			btnMeldAan.setVisible(false);
			DomeinController domeinController = aanmeldController.meldAan(txtGebruikersnaam.getText(),
					pssWachtwoord.getText());
			
			//OverzichtSchermController vs = new OverzichtSchermController(domeinController);
			CategorieResponsiveController vs = new CategorieResponsiveController(domeinController);
			Scene scene = new Scene(vs);
			scene.getStylesheets().add("theme.css");
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setResizable(true);
			stage.setMaximized(true);
			stage.setScene(scene);
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
	}*/
	
}

