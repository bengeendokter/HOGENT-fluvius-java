package main;

import domein.DomeinController;
import domein.Gebruiker;
import gui.CategorieFrameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CategorieGUI extends Application
{
	
	public void start(Stage primaryStage)
	{
		try
		{
			DomeinController domeinController = new DomeinController(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
			
			//AanmeldController aanmeldController = new AanmeldController();
			//CategorieFrameController root = new CategorieFrameController(aanmeldController);
			
			
			CategorieFrameController root = new CategorieFrameController(domeinController);
			//CategorieFrameController root = new CategorieFrameController();
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			
			/*Image icon = new Image(getClass().getResource("/images/icon.png").toExternalForm());
			primaryStage.getIcons().add(icon);*/
			
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