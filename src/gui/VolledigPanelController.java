package gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.IOException;

import domein.DomeinController;
import domein.Gebruiker;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class VolledigPanelController<E> extends BorderPane{
	
	public VolledigPanelController()
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VolledigPanel.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try
		{
			loader.load();
			DomeinController dc = new DomeinController(new Gebruiker("JanJansens", "123456789", "MVO coördinator", "ACTIEF"));
		    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		    Navigatiebalk nav = new Navigatiebalk(dc);
		    this.setTop(nav);
		    
		    
		    
		    PanelOverzicht ov = new PanelOverzicht();
		    ov.initGui(dc.getCategorien(), "categorieën", dc);
		    this.setLeft(ov);
		    
		    CategorieDetailPanel<E> detail = new CategorieDetailPanel<>();
		    this.setCenter(detail);
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}

}
