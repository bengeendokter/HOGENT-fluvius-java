package main;

import java.io.IOException;

import domein.DomeinController;
import domein.Gebruiker;
import domein.PopulateDB;
import gui.CategorieResponsiveController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.DatabaseSelector;

public class CategorieGUI extends Application
{
	
	public void start(Stage primaryStage)
	{
		try
		{
			if(DatabaseSelector.ISLOCALHOST)
			{
				try
				{
					PopulateDB.run();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				} 
			}
			DomeinController domeinController = new DomeinController(new Gebruiker("JanJansens", "123456789", "MVO co�rdinator", "ACTIEF"));
			
			//AanmeldController aanmeldController = new AanmeldController();
			//CategorieFrameController root = new CategorieFrameController(aanmeldController);
			
			
			CategorieResponsiveController root = new CategorieResponsiveController(domeinController);
			//CategorieFrameController root = new CategorieFrameController();
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