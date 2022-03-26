package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DTOCategorie;
import domein.DomeinController;
import domein.SdGoal;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class UpdateOrCreateCategoryController<E> extends BorderPane {
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
	private ListView<String> listIcoon;
	@FXML
	private Label lblKiesSdgs;
	@FXML
	private Label lblGeselecteerdeSdgs;
	@FXML
	private TreeView<SdGoal> treeviewSdgs;
	@FXML
	private TreeView<SdGoal> treeviewGesSdgs;

	@FXML
	private Button btnOpslaan;
	@FXML
	private Button btnAnnuleer;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private ImageView arrow1;
	@FXML
	private ImageView arrow2;
	@FXML
	private Button btnRemoveSdGoal;
	
	private DomeinController dc;
	
	private List<String> iconen = (List<String>) Arrays
			.asList(new String[] {"file:src/images/people.png", "file:src/images/partnership.png",
					"file:src/images/peace.png", "file:src/images/planet.jpg", "file:src/images/prosperity.jpg"});
	
	TreeItem<SdGoal> rootNode2 = 
	        new TreeItem<SdGoal>(null);
	
	Map<String, SdGoal> map = new HashMap<String, SdGoal>();
	
	public UpdateOrCreateCategoryController(DomeinController dc,E object, String wijzigMaak ) {
		
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrCreateCategory.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		try
		{
			loader.load();
			lblErrorMessage.setVisible(false);
//			this.getChildren().add(lblErrorMessage);
			// label goed zetten
			lblUpdateOrCreate.setText(wijzigMaak);
			
			if(object != null) {
				wijzigBestaandeCategorie(object);
				
			}
			
				
			
			
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
			if(object == null) {
				imgIcoon.setImage(new Image((String) listIcoon.getSelectionModel().getSelectedItem()));
			}
			
			//icoon verandert als je op een icoon klikt van de lijst
			listIcoon.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
				if(newValue != null)
				{
					
					String icoonPath = (String) listIcoon.getSelectionModel().getSelectedItem();
					
					imgIcoon.setImage(new Image(icoonPath, 25, 25, true, true));
					
				}
			});
			
			

			treeviewGesSdgs.setRoot(rootNode2);
			treeviewGesSdgs.setShowRoot(false);

			
			TreeItem<SdGoal> rootNode = 
			        new TreeItem<SdGoal>(null);
	
			for (SdGoal s : dc.getBeschikbareSdgs().stream().sorted(Comparator.comparing(e -> ((SdGoal) e).getParentSDG() == null? -1 : ((SdGoal) e).getParentSDG().getId())).collect(Collectors.toList())) {
				
	            TreeItem<SdGoal> empLeaf = new TreeItem<SdGoal>(s);
	            boolean found = false;
	            for (TreeItem<SdGoal> depNode : rootNode.getChildren()) {
	            	
	            	if(s.getParentSDG() != null && depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG().getAfbeeldingNaamAlsInt()) {
	            		depNode.getChildren().add(empLeaf);
	                  found = true;
	                  break;
	            	}
	            	else if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getAfbeeldingNaamAlsInt()) {
	            		TreeItem<SdGoal> kind = depNode;
	            		rootNode.getChildren().remove(depNode);
	            		TreeItem<SdGoal> ob = new TreeItem<SdGoal>(s);
	            		ob.getChildren().add(kind);
	            		rootNode.getChildren().add(ob);
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
			
			EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
			    handleMouseClicked(event);
			};
			
			EventHandler<MouseEvent> mouseEventHandle2 = (MouseEvent event) -> {
			    handleMouseClickedBack(event);
			};

			treeviewSdgs.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
			treeviewGesSdgs.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle2); 
			
//			this.getChildren().addAll(arrow1, arrow2, btnRemoveSdGoal, lblUpdateOrCreate, lblGeselecteerdeSdgs, lblIcoon, lblKiesIcoon, lblKiesSdgs, lblNaam, listIcoon, treeviewGesSdgs, treeviewSdgs, imgIcoon, txtFNaam, btnSlaOp, btnAnnuleer);
			
			
			btnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					lblErrorMessage.setVisible(false);
					try {
//						TreeItem<SdGoal> rootNode3 = treeviewSdgs.getRoot();
//		            	
//						
//						for (TreeItem<SdGoal> r : rootNode2.getChildren()) {
//			            	for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
//			            	 	if(n.getValue().getAfbeeldingnaam() == r.getValue().getAfbeeldingnaam()) {
//			            	 		n.getChildren().clear();
//			            	 		rootNode2.getChildren().add(n);
//			            	 		rootNode3.getChildren().remove(n);
//			            	 		n.getChildren().addAll(r.getChildren());
//			            	 		rootNode2.getChildren().remove(r);
//			                	}
//			            	}
//						}
						
						
						
					populateMap(rootNode2);
					DTOCategorie cat = new DTOCategorie(txtFNaam.getText(), imgIcoon.getImage().getUrl(),
					new ArrayList<SdGoal>(map.values()));
					
					
		            	
					if(object != null) {
						dc.setCurrentCategorie((Categorie)object);
						
							dc.wijzigCategorie(cat);
						
						
					}else {
						
							dc.voegCategorieToe(cat);
						
						
					}
					
					refreshScherm();
				}
					catch(IllegalArgumentException e)
					{
						lblErrorMessage.setText(e.getMessage());
						lblErrorMessage.setVisible(true);
					}
					
				}
			});
			

		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
		btnAnnuleer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				maakLeeg();
			}
		});
		
	}
	

	private void wijzigBestaandeCategorie(E object) {
		txtFNaam.setText(((Categorie)object).getNaam());

		String pad2 = ((Categorie) object).getIcon();
		int index2 = pad2.indexOf("c");
		pad2 = pad2.substring(index2+1);
		imgIcoon.setImage(new Image(((Categorie) object).getIcon(), 25,25,true, true));
		//imageView.setImage(new Image(name.getIcon(), 25, 25, true, true));
		
		for (SdGoal s : ((Categorie)object).getSdGoals()) {
			System.out.println(s.getNaam());
            TreeItem<SdGoal> empLeaf2 = new TreeItem<SdGoal>(s);
            boolean found2 = false;
            for (TreeItem<SdGoal> depNode2 : rootNode2.getChildren()) {
            	if(s.getParentSDG() != null && depNode2.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG().getAfbeeldingNaamAlsInt()) {
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
	}
	

	
	
	private void handleMouseClicked(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell<?>) node).getText() != null)) {
	    	TreeItem<SdGoal> c = (TreeItem<SdGoal>)treeviewSdgs.getSelectionModel().getSelectedItem();
	    	
	    	boolean isHoofdSdg = ((SdGoal)c.getValue()).getParentSDG() == null;
//	    	if(!isHoofdSdg){
//	    		boolean remove = c.getParent().getChildren().remove(c);
//	    	}
            //boolean remove = c.getParent().getChildren().remove(c);
            treeviewGesSdgs.getSelectionModel().selectFirst();
           // TreeItem b = (TreeItem)treeviewGesSdgs.getSelectionModel().getSelectedItem();

            // OP DE JUISTE PLAATS TOEVOEGEN
            if(!isHoofdSdg)
            {
            	TreeItem<SdGoal> rootNode = treeviewSdgs.getRoot();
             	TreeItem<SdGoal> rootNode3 = treeviewGesSdgs.getRoot();
            	TreeItem<SdGoal> parent = c.getParent();
            	if(c.getParent() != null)
            	{
            		rootNode.getChildren().addAll(parent.getChildren());
            		parent.getChildren().clear();
            		rootNode.getChildren().remove(c);
            		parent.getChildren().add(c);
            		rootNode.getChildren().remove(parent);
            		rootNode3.getChildren().add(parent);
            	}
            	else
            	{
            		
                	for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
                		if(((SdGoal)c.getValue()).getParentSDG() != null && n.getValue().getAfbeeldingNaamAlsInt() == ((SdGoal)c.getValue()).getParentSDG().getAfbeeldingNaamAlsInt() ) {
                			rootNode.getChildren().remove(c);
                			n.getChildren().add(c);
                		}
                		
                	}
            	}
            	
            	//System.out.println(c.getParent());
//            	boolean toegevoegd = false;
//            	TreeItem<SdGoal> rootNode3 = treeviewGesSdgs.getRoot();
//            	
//            	for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
//                	if(n.getValue().getAfbeeldingnaam() == ((SdGoal)c.getValue()).getAfbeeldingnaam()) {
//                		c.getParent().getChildren().remove(c);
//                		n.getChildren().add(c);
//                		toegevoegd = true;
//                		
//                	}
//               	}
//                	
//                if(!toegevoegd)
//                {
//	            	
//	            	SdGoal parent = (SdGoal)c.getParent().getValue();
//	            	SdGoal copy = new SdGoal(parent.getAfbeeldingnaam(), parent.getNaam());
//	            	TreeItem<SdGoal> item = new TreeItem<>(copy);
//	            	c.getParent().getChildren().remove(c);
//	            	rootNode3.getChildren().add(item);
//	            	item.getChildren().add(c);
//	            	
//                }
            }
            else
            {
            	TreeItem<SdGoal> rootNode = treeviewSdgs.getRoot();
            	TreeItem<SdGoal> rootNode3 = treeviewGesSdgs.getRoot();
            	rootNode.getChildren().addAll(c.getChildren());
            	c.getChildren().clear();
            	rootNode.getChildren().remove(c);
            	rootNode3.getChildren().add(c);
            	for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
            		if(n.getValue().getParentSDG() != null && n.getValue().getParentSDG().getAfbeeldingNaamAlsInt() == ((SdGoal)c.getValue()).getAfbeeldingNaamAlsInt() ) {
            			c.getChildren().add(n);
            			
            		}
            		
            	}
            	rootNode3.getChildren().removeAll(c.getChildren());
            }
//            TreeItem<SdGoal> rootNode3 = treeviewGesSdgs.getRoot();
//            boolean toegevoegd = false;
//            for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
//            	if(n.getValue().getAfbeeldingNaamAlsInt() == ((SdGoal)c.getValue()).getParentSDG_id() && !isHoofdSdg) {
//            		n.getChildren().add(c);
//            		toegevoegd = true;
//            	}
//            }
//            
//            if(!toegevoegd && !isHoofdSdg) {
//            	treeviewGesSdgs.getRoot().getChildren().add(c);
//            }
            
	    }
	}
	
	private void handleMouseClickedBack(MouseEvent event) {
	    Node node = event.getPickResult().getIntersectedNode();
	    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell<?>) node).getText() != null)) {
	    	TreeItem<SdGoal> c = (TreeItem<SdGoal>)treeviewGesSdgs.getSelectionModel().getSelectedItem();
	    	
	    	boolean isHoofdSdg = ((SdGoal)c.getValue()).getParentSDG() == null;
	    	
	    	TreeItem<SdGoal> rootNode = treeviewSdgs.getRoot();
	    	TreeItem<SdGoal> rootNode3 = treeviewGesSdgs.getRoot();
	    	TreeItem<SdGoal> parent = c.getParent();
	    	if(!isHoofdSdg)rootNode.getChildren().add(c);
	    	
	    	if(isHoofdSdg)
	    	{
	    		
	    		rootNode3.getChildren().remove(c);
	    		List<TreeItem<SdGoal>> itemsToRemove = new ArrayList<>();
	    		for (TreeItem<SdGoal> r : rootNode.getChildren()) {
            		if(r.getValue().getParentSDG() != null && r.getValue().getParentSDG().getAfbeeldingNaamAlsInt() == ((SdGoal)c.getValue()).getAfbeeldingNaamAlsInt() ) {
            			itemsToRemove.add(r);
            		}
	    		}
	    		
	    		itemsToRemove.forEach(i ->rootNode.getChildren().remove(i));
	    		itemsToRemove.forEach(i -> c.getChildren().add(i));
	    		rootNode.getChildren().add(c);
	    	}
	    	else
	    	{

	    		if(parent != null)
	    			parent.getChildren().remove(c);
	    	}
	    	
	    	
	    	
	    	System.out.println(c.getParent());
	    	
//            boolean remove = c.getParent().getChildren().remove(c);
//            treeviewSdgs.getSelectionModel().selectFirst();
//        	TreeItem b = (TreeItem)treeviewSdgs.getSelectionModel().getSelectedItem();
//        	//treeviewSdgs.getRoot().getChildren().add(c);
//        	
//        	boolean isHoofdSDG = ((SdGoal)c.getValue()).getParentSDG_id() == 0;
//        	// OP DE JUISTE PLAATS TOEVOEGEN
//        	// HIER NOG EENS NAAR KIJKEN
//            TreeItem<SdGoal> rootNode3 = treeviewSdgs.getRoot();
//            boolean toegevoegd = false;
//            for (TreeItem<SdGoal> n : rootNode3.getChildren()) {
//            	if(!isHoofdSDG) {
//            		if(n.getValue().getAfbeeldingNaamAlsInt() == ((SdGoal)c.getValue()).getParentSDG_id()) {
//                		n.getChildren().add(c);
//                		toegevoegd = true;
//                	}
//            	}
//            	
//            }
//            if(!toegevoegd && !isHoofdSDG) {
//            	treeviewSdgs.getRoot().getChildren().add(c);
//            }

	    }
	}
	
	// METHODE OM DE ELEMENTEN UIT DE TREEVIEW TE HALEN EN DAARNA OM TE ZETTEN NAAR EN LIST
	private void populateMap(TreeItem<SdGoal> item){

        if(item.getChildren().size() > 0){
            for(TreeItem<SdGoal> subItem : item.getChildren()){
            	SdGoal node = (SdGoal) item.getValue();  
            	if(node != null) {
            		map.put(node.toString(), node);
            	}
                populateMap(subItem);
            }
        }
        else {
        	SdGoal node = (SdGoal) item.getValue();     
            	if(node != null) {
            		map.put(node.toString(), node);
            	}
                
        }
    }
	
	private void refreshScherm() {
//		PanelOverzicht<Categorie> po = new PanelOverzicht<>();
		// Eerst het hoofdscherm opvragen adhv dit scherm
//		Parent hoofdScherm = UpdateOrCreateCategoryController.this.getParent();
//		if (hoofdScherm instanceof BorderPane) {
//			Node details = ((Pane) ((BorderPane) hoofdScherm).getCenter());
//			((UpdateOrCreateCategoryController) details).maakLeeg();
//		}
		maakLeeg();
		
	}
	public void maakLeeg() {
		this.getChildren().clear();
		
	}
	
	@FXML
	public void resetSdgs(ActionEvent event) {
		treeviewGesSdgs.getRoot().getChildren().clear();
		treeviewSdgs.getRoot().getChildren().clear();
		TreeItem<SdGoal> rootNode = treeviewSdgs.getRoot();
		for (SdGoal s : dc.getBeschikbareSdgs().stream().sorted(Comparator.comparing(e -> ((SdGoal) e).getParentSDG() == null? -1 : ((SdGoal) e).getParentSDG().getId())).collect(Collectors.toList())) {
			
            TreeItem<SdGoal> empLeaf = new TreeItem<SdGoal>(s);
            boolean found = false;
            for (TreeItem<SdGoal> depNode : rootNode.getChildren()) {
            	
            	if(s.getParentSDG() != null && depNode.getValue().getAfbeeldingNaamAlsInt() == s.getParentSDG().getAfbeeldingNaamAlsInt()) {
            		depNode.getChildren().add(empLeaf);
                  found = true;
                  break;
            	}
            	else if(depNode.getValue().getAfbeeldingNaamAlsInt() == s.getAfbeeldingNaamAlsInt()) {
            		TreeItem<SdGoal> kind = depNode;
            		rootNode.getChildren().remove(depNode);
            		TreeItem<SdGoal> ob = new TreeItem<SdGoal>(s);
            		ob.getChildren().add(kind);
            		rootNode.getChildren().add(ob);
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
		
	}

}
