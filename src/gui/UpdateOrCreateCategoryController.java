package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DTOCategorie;
import domein.DomeinController;
import domein.ListViewInterface;
import domein.SdGoal;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UpdateOrCreateCategoryController<E> extends Pane {
	@FXML
	private Label lblUpdateOrCreate;
	@FXML
	private Label lblNaam;
	@FXML
	private TextField txtFNaam;
	@FXML
	private Label lblIcoon;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private Label lblKiesIcoon;
	@FXML
	private ListView listIcoon;
	@FXML
	private Label lblKiesSdgs;
	@FXML
	private Label lblGeselecteerdeSdgs;
	@FXML
	private TreeView treeviewSdgs;
	@FXML
	private TreeView treeviewGesSdgs;
	@FXML
	private Button btnSlaOp;
	@FXML
	private Button btnAnnuleer;
	
	private DomeinController dc;
	
	private List<String> iconen = (List<String>) Arrays
			.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png",
					"file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	
	@SuppressWarnings("unchecked")
	public UpdateOrCreateCategoryController(DomeinController dc,E object, String wijzigMaak ) {
		
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrCreateCategory.fxml"));
		loader.setController(this);
		//loader.setRoot(this);
		try
		{
			loader.load();
			if(object != null) {
				lblUpdateOrCreate.setText(wijzigMaak);
				txtFNaam.setText(((Categorie)object).getNaam());
				
				String pad2 = ((Categorie) object).getIcon();
				int index2 = pad2.indexOf("c");
				pad2 = pad2.substring(index2+1);
				imgIcoon.setImage(new Image(getClass().getResourceAsStream(pad2)));

				TreeItem<SdGoal> rootNode2 = 
				        new TreeItem<SdGoal>(null);

				for (SdGoal s : ((Categorie)object).getSdGoals()) {
					System.out.println(s.getNaam());
		            TreeItem<SdGoal> empLeaf2 = new TreeItem<SdGoal>(s);
		            boolean found2 = false;
		            for (TreeItem<SdGoal> depNode2 : rootNode2.getChildren()) {
		            	if(depNode2.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG_id()) {
		            		depNode2.getChildren().add(empLeaf2);
		                  found2 = true;
		                  break;
		            	}
		            }
		            String pad3 = s.getIcon();
					int index3 = pad3.indexOf("c");
					pad3 = pad3.substring(index3+1);

		            if (!found2) {
		                TreeItem<SdGoal> depNode4 = new TreeItem<SdGoal>(
		                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
		                );
		                
		                rootNode2.getChildren().add(depNode4);
		            }
		        }
				treeviewGesSdgs.setRoot(rootNode2);
				treeviewGesSdgs.setShowRoot(false);
				
				//onSelectListView(treeviewSdgs, treeviewGesSdgs);
				//onSelectListView(treeviewGesSdgs, treeviewSdgs);
			}
			else { // nieuwe aanmaken
				lblUpdateOrCreate.setText(wijzigMaak);
//				DTOCategorie nieuweCategorie = new DTOCategorie(txtFNaam.getText(), imgIcoon.getImage().getUrl(),
//						new ArrayList<SdGoal>(treeviewGesSdgs.getItems().stream().collect(Collectors.toList())));
//				dc.voegCategorieToe(nieuweCategorie);
			}
			// MOET SOWIESO GEBEUREN
			
			//listIcoon opvullen met iconen
			listIcoon.setItems(FXCollections.observableList(iconen));
			listIcoon.setCellFactory(param -> new ListCell<String>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(String name, boolean empty)
				{
					super.updateItem(name, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(null);
						imageView.setImage(new Image(name, 25, 25, true, true));
						
						setGraphic(imageView);
					}
				}
			});
			
			//eerste element in listview selecteren omdat anders "oldvalue" null wordt
			listIcoon.getSelectionModel().selectFirst();
			
			//icoon verandert als je op een icoon klikt van de lijst
			listIcoon.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					
					String icoonPath = (String) listIcoon.getSelectionModel().getSelectedItem();
					
					imgIcoon.setImage(new Image(icoonPath, 25, 25, true, true));
					
				}
			});
			
			TreeItem<SdGoal> rootNode = 
			        new TreeItem<SdGoal>(null);

			for (SdGoal s : dc.getBeschikbareSdgs()) {
				System.out.println(s.getNaam());
	            TreeItem<SdGoal> empLeaf = new TreeItem<SdGoal>(s);
	            boolean found = false;
	            for (TreeItem<SdGoal> depNode : rootNode.getChildren()) {
	            	if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG_id()) {
	            		depNode.getChildren().add(empLeaf);
	                  found = true;
	                  break;
	            	}
	            }
	            String pad = s.getIcon();
				int index = pad.indexOf("c");
				pad = pad.substring(index+1);

	            if (!found) {
	                TreeItem<SdGoal> depNode = new TreeItem<SdGoal>(
	                    s, new ImageView(new Image(s.getIcon(), 30, 30, true, true))
	                );
	                
	                rootNode.getChildren().add(depNode);
	            }
	        }
			treeviewSdgs.setRoot(rootNode);
			treeviewSdgs.setShowRoot(false);
			
			this.getChildren().addAll(lblUpdateOrCreate, lblGeselecteerdeSdgs, lblIcoon, lblKiesIcoon, lblKiesSdgs, lblNaam, listIcoon, treeviewGesSdgs, treeviewSdgs, imgIcoon, txtFNaam, btnSlaOp, btnAnnuleer);
			

		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
//	private <E extends ListViewInterface> void onSelectListView( TreeView<E> oorsprong, TreeView<E> bestemming)
//	{
//		// view
//		oorsprong.setCellFactory(param -> new ListCell<E>()
//		{
//			private ImageView imageView = new ImageView();
//			
//			@Override
//			public void updateItem(E type2, boolean empty)
//			{
//				super.updateItem(type2, empty);
//				if(empty)
//				{
//					setText(null);
//					setGraphic(null);
//				}
//				else
//				{
//					setText(type2.getNaam());
//					imageView.setImage(new Image(type2.getIcon(), 25, 25, true, true));
//					
//					setGraphic(imageView);
//				}
//			}
//		});
//		
//		// onSelect
//		oorsprong.getSelectionModel().selectedItemProperty()
//				.addListener((observableValue, oldValue, newValue) -> {
//					if(newValue != null)
//					{
//						E type3 = newValue;
//						
//						List<E> huidige = bestemming.getItems();
//						
//						if(huidige == null)
//						{
//							huidige = FXCollections.observableList(new ArrayList<>());
//						}
//						huidige.add(type3);
//						huidige = new ArrayList<>(new HashSet<>(huidige));
//						bestemming.setItems(FXCollections.observableList(huidige));
//						
//						// verwijder newValue uit listDoelKiesSubDoel
//						// verpak dit in runLater want gui updaten in een lambda functie geeft error
//						Platform.runLater(new Runnable()
//						{
//							@Override
//							public void run()
//							{
//								List<E> nieuweLijst = new ArrayList<>(
//										oorsprong.getItems());
//								nieuweLijst.remove(newValue);
//								oorsprong.setItems(FXCollections.observableList(nieuweLijst));
//							}
//						});
//						
//					}
//				});
//	}

}
