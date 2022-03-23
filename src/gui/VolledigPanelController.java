package gui;

import java.io.IOException;

import domein.Categorie;
import domein.DomeinController;
import domein.Gebruiker;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class VolledigPanelController extends BorderPane{
	
	public VolledigPanelController()
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VolledigPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			this.setStyle("-fx-background-color: white;");
			DomeinController dc = new DomeinController(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
//		    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		    Navigatiebalk nav = new Navigatiebalk(dc);
		    this.setTop(nav);
		   
		    
		    PanelOverzicht<Categorie> ov = new PanelOverzicht<>();
		    this.setLeft(ov);
		    ov.initGui(dc.getCategorien(), "categorieën", dc);
		    
		    
//		    CategorieDetailPanel<E> detail = new CategorieDetailPanel<>();
//		    this.setCenter(detail);
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}

}
