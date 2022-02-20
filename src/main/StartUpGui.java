package main;


import gui.AanmeldSchermController;
import gui.OverzichtSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class StartUpGui extends Application{

	public void start(Stage primaryStage) {
		try {
			
			AanmeldSchermController root = new AanmeldSchermController();
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
