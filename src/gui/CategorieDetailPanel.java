package gui;

import domein.Categorie;
import domein.ListViewInterface;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CategorieDetailPanel<E> extends VBox{
	private E object;
	
	public CategorieDetailPanel() {
		this.getChildren().add(new Label("test"));
		
		//initGui();
	}

	public void initGui(E object) {
		this.object = object;
		// Scherm leegmaken
		this.getChildren().clear();
		
		Label lblDetails = new Label("Details categorie");
		Label lblNaam = new Label();

		if(object instanceof Categorie) {
			lblNaam.setText(((Categorie) object).getNaam());
		}
		
		
		this.getChildren().addAll(lblDetails, lblNaam);
		
	}

}
