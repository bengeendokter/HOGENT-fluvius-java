package gui;

import java.io.IOException;

import domein.Categorie;
import domein.DomeinController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class VolledigPanelController extends BorderPane{
	
//	private DomeinController dc;
	
	public VolledigPanelController(DomeinController dc)
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VolledigPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
//		this.dc = dc;
		try
		{
			loader.load();
			this.setStyle("-fx-background-color: white;");
			
		    Navigatiebalk nav = new Navigatiebalk(dc);
		    this.setTop(nav);
		   
		    
		    PanelOverzicht<Categorie> ov = new PanelOverzicht<>();
		    this.setLeft(ov);
		    ov.initGui(dc.getCategorien(), "categorieën", dc);

			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}

}