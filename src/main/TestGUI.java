package main;

import domein.AanmeldController;
import gui.AanmeldenResponsiveController;
import gui.TestFrameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.DatabaseSelector;

public class TestGUI extends  Application {
	public void start(Stage primaryStage)
	{
		try
		{

			//TestFrameController root = new TestFrameController();
			
			//AanmeldenResponsiveController root  = new AanmeldenResponsiveController();
//			Scene scene = new Scene(root);
//			primaryStage.setResizable(true);
//			primaryStage.setMaximized(true);
//			primaryStage.setScene(scene);
//			
//			scene.getStylesheets().add("theme.css");
//			
//			Image icon = new Image(getClass().getResource("/images/icon.png").toExternalForm());
//			primaryStage.getIcons().add(icon);
//			
//			primaryStage.show();
			
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
