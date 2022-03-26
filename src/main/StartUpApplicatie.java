package main;

import domein.AanmeldController;
import gui.AanmeldenResponsiveController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.DatabaseSelector;

public class StartUpApplicatie extends Application{

	public void start(Stage primaryStage)
	{
		try
		{
	
			// ga naar de repository.DatabaseSelector Class om een lokale databank te gebruiken
			AanmeldController aanmeldController = new AanmeldController(DatabaseSelector.ISLOCALHOST);
			
			//AanmeldSchermController root = new AanmeldSchermController(aanmeldController);
			AanmeldenResponsiveController root = new AanmeldenResponsiveController(aanmeldController);
			Scene scene = new Scene(root);
			
			//primaryStage.setResizable(false);
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
