package main;


import domein.AanmeldController;
import gui.AanmeldenResponsiveController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.DatabaseSelector;
import repository.GebruikerDaoJpa;


public class StartupGui extends Application{
 
	public void start(Stage primaryStage)
	{
		try
		{
			// Gebruiker: JanJansens, Wachtwoord: 123456798
			AanmeldController aanmeldController = new AanmeldController(DatabaseSelector.ISLOCALHOST, new GebruikerDaoJpa());
						
			AanmeldenResponsiveController root = new AanmeldenResponsiveController(aanmeldController);
			Scene scene = new Scene(root);

			primaryStage.setResizable(true);
			primaryStage.setMaximized(true);
			
			primaryStage.setScene(scene);
			
			scene.getStylesheets().add("theme.css");
			
			Image icon = new Image(getClass().getResource("/images/icon.png").toExternalForm());
			primaryStage.getIcons().add(icon);
			
			primaryStage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	

}
