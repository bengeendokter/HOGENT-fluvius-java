package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CategorieResponsiveController extends BorderPane {
	
	@FXML
	private BorderPane groeneBalk;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab tabMVO;
	
	@FXML
	private Tab tabCat;
	
	@FXML
	private Tab tabData;
	
	
	public CategorieResponsiveController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieResponsive.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			
			tabPane.widthProperty().addListener((observable, oldValue, newValue) ->
		    {
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
	


