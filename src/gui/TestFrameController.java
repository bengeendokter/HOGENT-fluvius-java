package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class TestFrameController extends BorderPane {
	@FXML
	private TabPane tabPane;
	
	public TestFrameController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TestFrame.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			tabPane.widthProperty().addListener((observable, oldValue, newValue) -> {
				tabPane.setTabMinWidth(tabPane.getWidth() / tabPane.getTabs().size());
				tabPane.setTabMaxWidth(tabPane.getWidth() / tabPane.getTabs().size());
			});
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
