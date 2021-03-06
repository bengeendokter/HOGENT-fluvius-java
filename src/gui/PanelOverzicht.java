package gui;

import domein.Categorie;
import domein.DomeinController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelOverzicht<E> extends VBox {
	
	private ObservableList<E> items;
	private DomeinController dc;
	

	public PanelOverzicht() {
		
		initGui(items, "categorieŽn", dc);
	}

	public void initGui(ObservableList<E> items, String soort, DomeinController dc) {
		this.items = items;
		this.dc = dc;
		
		this.getChildren().clear();
		
		this.setStyle("-fx-background-color: #495973;");
		
		// Label aanmaken
		Label lblOverzicht = new Label("Overzicht " + soort);
		lblOverzicht.setStyle("-fx-text-fill: white;  -fx-font-size: 20;-fx-font-weight: bold; ");
		
		// ListView aanmaken
		ListView<E> list = new ListView<E>();

		list.setItems(items);
		
		
		if(soort.equals("categorieŽn")) {
			list.setCellFactory(param -> new ListCell<E>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(E name, boolean empty)
				{
					super.updateItem(name, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(((Categorie)name).getNaam());
						imageView.setImage(new Image(((Categorie)name).getIcon(), 40, 40, true, true));
						setGraphic(imageView);
						
					}
				}
			});
		}
		
		

		
		list.getSelectionModel().selectedItemProperty()
		.addListener((observableValue, oldValue, newValue) -> {
			if(newValue != null)
			{

				
				list.setStyle("-fx-background-color: white");
				list.setStyle("-fx-selection-bar:#D3D3D3; -fx-background-insets: 0 ;");


				// Eerst het hoofdscherm opvragen adhv dit scherm
				Parent hoofdScherm = PanelOverzicht.this.getParent();
				
				if (hoofdScherm instanceof BorderPane) {

					 //DetailsScherm opvragen adhv het hoofdScherm

					if(soort.equals("categorieŽn")) {

						((BorderPane) hoofdScherm).setCenter(null);
						CategorieDetailsController<E> cdp = new CategorieDetailsController<>(dc, newValue);
						
						
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								((BorderPane) hoofdScherm).setCenter(cdp);
							}
						});
					}
					
					if(soort.equals("datasources")) {
						DatasourceDetailsController<E> d = new DatasourceDetailsController<>(dc, newValue);
						
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								((BorderPane) hoofdScherm).setCenter(d);
							}
						});
					}
					
				}
			}
		});
		
		// Butten aanmaken #B2D234
		Button btnMaakNieuwe = new Button("+");
		btnMaakNieuwe.setStyle("-fx-background-color: #B2D234;-fx-text-fill: white;  -fx-font-size: 20;");

		btnMaakNieuwe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				if(soort.equals("categorieŽn")) {				
					UpdateOrCreateCategoryController<E> vs = new UpdateOrCreateCategoryController<>(dc, null, "Wijzig categorie");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = PanelOverzicht.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
					
				}
				if(soort.equals("doelstellingen")) {
					UpdateOrCreateDoelstelling vs = new UpdateOrCreateDoelstelling(dc, null, "Maak nieuwe doelstelling");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = PanelOverzicht.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
				}
				if(soort.equals("datasources")) {
					UpdateOrCreateDatasourceController<E> vs = new UpdateOrCreateDatasourceController<>(dc, null, "Maak nieuw datasource");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = PanelOverzicht.this.getParent();
					if (hoofdScherm instanceof BorderPane) {
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
				}
				
				
			}
		});
		
		// Toevoegen aan het scherm
		this.getChildren().addAll(lblOverzicht, list, btnMaakNieuwe);

		// Ruimte instellen
		this.setPadding(new Insets(10));
		this.setSpacing(5);
		this.setAlignment(Pos.TOP_CENTER);
		this.setMinWidth(USE_PREF_SIZE);

		// Eerste item in ListView selecteren en weergeven
		list.getSelectionModel().selectFirst();
		
	}
	


}
