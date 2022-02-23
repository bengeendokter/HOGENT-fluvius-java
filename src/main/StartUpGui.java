package main;


import domein.DomeinController;
import gui.AanmeldSchermController;
import gui.OverzichtSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class StartUpGui extends Application{

	public void start(Stage primaryStage) {
		try {
			DomeinController dc = new DomeinController();
			AanmeldSchermController root = new AanmeldSchermController(dc);
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
