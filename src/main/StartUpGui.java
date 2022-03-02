package main;

import domein.AanmeldController;
import gui.AanmeldSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
	
	public void start(Stage primaryStage)
	{
		try
		{
//			boolean populateTheDB = true;
			boolean populateTheDB = false;
			AanmeldController aanmeldController = new AanmeldController(populateTheDB);
			AanmeldSchermController root = new AanmeldSchermController(aanmeldController);
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
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
