package gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import domein.Categorie;
import domein.ListViewInterface;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelOverzicht<E> extends VBox {
	
	private ObservableList<E> items;

	public PanelOverzicht(ObservableList<E> items) {
		this.items = items;
		initGui();
	}

	private void initGui() {
		
		this.setStyle("-fx-background-color: white;");
		
		// Label aanmaken
		Label lblOverzicht = new Label("Overzicht");
		lblOverzicht.setStyle("-fx-text-fill: #004C69;  -fx-font-size: 20;-fx-font-weight: bold; -fx-underline:true");
		
		// ListView aanmaken
		ListView<E> list = new ListView<E>();
		//ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
		list.setItems(items);
		
		list.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, oldValue, newValue) -> {
			if(newValue != null)
			{
				CategorieDetailPanel cdp = new CategorieDetailPanel();
				// Eerst het hoofdscherm opvragen adhv dit scherm
				Parent hoofdScherm = PanelOverzicht.this.getParent();
				if (hoofdScherm instanceof BorderPane) {
					// DetailsScherm opvragen adhv het hoofdScherm
					Node details = ((VBox) ((BorderPane) hoofdScherm).getCenter());
					if (details instanceof CategorieDetailPanel) {
						((CategorieDetailPanel) details).initGui(newValue);
					}
				}
			}
		});
		
		// Butten aanmaken
		Button btnMaakNieuwe = new Button("+");
		btnMaakNieuwe.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;");

		// Toevoegen aan het scherm
		this.getChildren().addAll(lblOverzicht, list, btnMaakNieuwe);

		// Ruimte instellen
		this.setPadding(new Insets(10));
		this.setSpacing(5);
		this.setAlignment(Pos.TOP_CENTER);

	}

}
