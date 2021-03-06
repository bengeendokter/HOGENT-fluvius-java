package gui;

import java.io.IOException;

import domein.Categorie;
import domein.DomeinController;
import domein.SdGoal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class CategorieDetailsController<E> extends BorderPane{
	@FXML
	private Label lblDetailsDatasource;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblNaamIngevuld;
	@FXML
	private Label lblIcoon;
	@FXML
	private Button btnOpslaan;
	@FXML
	private Button btnAnnuleer;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private TreeView<SdGoal>treeviewSdgs;
	@FXML
	private Label lblSdgs;
	@FXML
	private Label lblErrorMessage;

	
	public CategorieDetailsController(DomeinController dc, E object){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieDetails.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		try
		{
			loader.load();
			this.setStyle("-fx-background-color: white;");
			lblErrorMessage.setVisible(false);
			if(object instanceof Categorie) {
				lblNaamIngevuld.setText(((Categorie) object).getNaam());
			}
			
			if(object instanceof Categorie) {
				String pad = ((Categorie) object).getIcon();
				int indexcdubbelpunt = pad.indexOf("C:");
				if(indexcdubbelpunt == -1) {
					int index = pad.indexOf("c");
					pad = pad.substring(index+1);

					imgIcoon.setImage(new Image(getClass().getResourceAsStream(pad)));
				}else {
					int index = pad.indexOf("bin");
	            	pad = pad.substring(index + 3);
	            	pad = pad.replace("\\", "/");
	            	index = pad.indexOf("/images");
	            	pad = pad.substring(index);
	            	System.out.println(pad);
					Image m = new Image(getClass().getResource(pad).toExternalForm());
					imgIcoon.setImage(m);
				}

			}
			
			TreeItem<SdGoal> rootNode = 
			        new TreeItem<SdGoal>(null);

			for (SdGoal s : ((Categorie) object).getSdGoals()) {
				
	            TreeItem<SdGoal> empLeaf = new TreeItem<SdGoal>(s);
	            boolean found = false;
	            boolean toe = false;
	            for (TreeItem<SdGoal> depNode : rootNode.getChildren()) {
	            	if(s.getParentSDG() != null && depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG().getAfbeeldingNaamAlsInt()) {
	            		depNode.getChildren().add(empLeaf);
	                  found = true;
	                  break;
	            	}
	            	else if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getAfbeeldingNaamAlsInt()) {
	            		if(s.getParentSDG() == null && toe == false) {
	            			TreeItem<SdGoal> kind = depNode;
	                        rootNode.getChildren().remove(depNode);
	                        TreeItem<SdGoal> ob = new TreeItem<SdGoal>(
	                                s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
	                                );
	                        ob.getChildren().add(kind);
	                        rootNode.getChildren().add(ob);
	                        found = true;
	                        toe = true;
	                          break;
	                          
	            		} 
	            		else {
	            			TreeItem<SdGoal> kind2 = depNode;
	                        rootNode.getChildren().remove(depNode);
	                        @SuppressWarnings("unlikely-arg-type")
							int index = rootNode.getChildren().indexOf(s);
	                        rootNode.getChildren().get(index).getChildren().add(kind2);
	            		}
                        
                    }
	            }
	            String pad = s.getIcon();
				int index = pad.indexOf("c");
				pad = pad.substring(index+1);

	            if (!found) {
	            	TreeItem<SdGoal> depNode;
	            	if(s.getParentSDG() == null) {
	            		depNode = new TreeItem<SdGoal>(
	    	                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
	    	                );
	            	} else {
	            		depNode = new TreeItem<SdGoal>(
	    	                    s
	    	                );
	            	}
	                
	                
	                rootNode.getChildren().add(depNode);
	            }
	        }
	 
			treeviewSdgs.setRoot(rootNode);
			treeviewSdgs.setShowRoot(false);
			

			btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					//vragen of de gebruiker zeker is
					Alert boodschap = new Alert(AlertType.CONFIRMATION);
					boodschap.setTitle("Verwijderen");
					
					boodschap.setContentText("Bent u zeker dat u deze categorie wilt verwijderen?");
					
					boodschap.showAndWait().ifPresent(response -> {
						if(response != ButtonType.CANCEL)
						{
							try {
								lblErrorMessage.setVisible(false);
								dc.setCurrentCategorie((Categorie)object);
								dc.verwijderCategorie();
								maakLeeg();
							}
							catch(IllegalArgumentException e)
							{
								lblErrorMessage.setText(e.getMessage());
								lblErrorMessage.setVisible(true);
							}
						}
					});
					
				}
			});
			
			 btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent evt) {

						UpdateOrCreateCategoryController<E> vs = new UpdateOrCreateCategoryController<>(dc, object, "Wijzig categorie");
						// Eerst het hoofdscherm opvragen adhv dit scherm
						Parent hoofdScherm = CategorieDetailsController.this.getParent();
						if (hoofdScherm instanceof BorderPane) {
							// DetailsScherm opvragen adhv het hoofdScherm
							((BorderPane) hoofdScherm).setCenter(vs);
						}
						
					}
				});
		
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	public void maakLeeg() {
		this.getChildren().clear();
		
	}
	

}
