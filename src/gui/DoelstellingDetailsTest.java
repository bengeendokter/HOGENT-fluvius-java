package gui;

import java.io.IOException;
import java.util.List;

import domein.Doelstelling;
import domein.DomeinController;
import domein.GeenBewerking;
import domein.Rol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DoelstellingDetailsTest extends BorderPane
{
	@FXML
	private Button btnWijzigen;
	@FXML
	private Button btnVerwijderen;
	@FXML
	private Label lblDetailsDoelstelling;
	@FXML
	private Label lblNaam;
	@FXML
	private Label lblBewerking;
	@FXML
	private Label lblDoelwaarde;
	@FXML
	private Label lblIcoon;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private Label lblSdg;
	@FXML
	private Label lblDatasource;
	@FXML
	private Label lblRollen;
	@FXML
	private Label lblSubDoelstellingen;
	@FXML
	private TreeView<Doelstelling> treeViewSubDoelstellingen;
	@FXML
	private Label lblNaamIngevuld;
	@FXML
	private Label lblBewerkingIngevuld;
	@FXML
	private Label lblDoelWaardeIngevuld;
	@FXML
	private Label lblEenheidIngevuld;
	@FXML
	private TextArea areaSdgIngevuld;
	@FXML
	private Label lblDatasourceIngevuld;
	@FXML
	private ListView<Rol> listRollenIngevuld;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private Label lblBerekendeWaarde;
	@FXML
	private Label lblBerekendeWaardeIngevuld;
	@FXML
	private Label lblEenheidIngevuld2;
	@FXML
	private HBox hBoxBereekendeWaarde;
	@FXML
	private Tooltip tooltipSDG;
	@FXML
	private HBox hBoxDatasource;
	@FXML
	private Label lblJaar;
	@FXML
	private Label datapickerJaar;
	@FXML
	private VBox vBoxSubDoelstellingen;
	
	public DoelstellingDetailsTest(DomeinController dc, Doelstelling huidigeDoelstelling)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DoelstellingDetails.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		
		try
		{
			loader.load();
			
			datapickerJaar.setText(String.format("%d", huidigeDoelstelling.getJaar()));
			lblErrorMessage.setVisible(false);
			lblNaamIngevuld.setText(huidigeDoelstelling.getNaam());
			lblBewerkingIngevuld.setText(huidigeDoelstelling.getFormule().toString());
			if(huidigeDoelstelling.getDatasource() != null)
			{
				lblDatasourceIngevuld.setText(huidigeDoelstelling.getDatasource().getNaam());
			}
			else
			{
				hBoxDatasource.setVisible(false);;
			}
			
			areaSdgIngevuld.setText(huidigeDoelstelling.getSdGoal().getNaam());
			areaSdgIngevuld.setEditable(false);
			areaSdgIngevuld.setWrapText(true);
			areaSdgIngevuld.setOpacity(1);

			lblDoelWaardeIngevuld.setText(Double.toString(huidigeDoelstelling.getDoelwaarde()));
			lblEenheidIngevuld.setText(huidigeDoelstelling.getEenheid());
			
			// toon berekende waarde indien er een bewerking is
			if(!(huidigeDoelstelling.getFormule() instanceof GeenBewerking))
			{
				lblBerekendeWaardeIngevuld.setText(Double.toString(
						huidigeDoelstelling.getBerekendewaarde().entrySet().iterator().next().getValue()));
			}
			else
			{
				hBoxBereekendeWaarde.setVisible(false);
			}
			
			lblEenheidIngevuld2.setText(huidigeDoelstelling.getEenheid());
			listRollenIngevuld.setItems(FXCollections.observableList(huidigeDoelstelling.getRollen()));
			
			// icoon doelstelling
			imgIcoon.setImage(new Image(huidigeDoelstelling.getIcon(), 250, 250, true, true));
			
			// geef de naam van de rollen weer in de ListView
			listRollenIngevuld.setCellFactory(param -> new ListCell<Rol>()
			{
				@Override
				public void updateItem(Rol doel, boolean empty)
				{
					super.updateItem(doel, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(doel.getRol());
					}
				}
			});
			
			// vul subdoelstellingen in
			TreeItem<Doelstelling> rootNode = new TreeItem<Doelstelling>(null);
			addToTreeItem(rootNode, huidigeDoelstelling.getComponents().stream().map(component -> (Doelstelling) component).toList());
			
			// Als doelstelling een leaf is -> Subdoelstellingen niet tonen
			if (huidigeDoelstelling.getDatasource() != null) {
				vBoxSubDoelstellingen.setVisible(false);
				vBoxSubDoelstellingen.setManaged(false);
			}
			
			treeViewSubDoelstellingen.setRoot(rootNode);
			treeViewSubDoelstellingen.setShowRoot(false);
			
			// stel iconen en namen van TreeCellen in
			treeViewSubDoelstellingen.setCellFactory(param -> new TreeCell<Doelstelling>()
			{
				private ImageView imageView = new ImageView();
				
				@Override
				public void updateItem(Doelstelling doel, boolean empty)
				{
					super.updateItem(doel, empty);
					if(empty)
					{
						setText(null);
						setGraphic(null);
					}
					else
					{
						setText(doel.getNaam());
						imageView.setImage(new Image(doel.getIcon(), 30, 30, true, true));
						
						setGraphic(imageView);
						
					}
				}
			});
			
			// verwijder knop
			btnVerwijderen.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent evt)
				{
					//vragen of de gebruiker zeker is
					Alert boodschap = new Alert(AlertType.CONFIRMATION);
					boodschap.setTitle("Verwijderen");
					
					boodschap.setContentText("Bent u zeker dat u deze doelstelling wilt verwijderen?");
					
					boodschap.showAndWait().ifPresent(response -> {
						if(response != ButtonType.CANCEL)
						{
							try
							{
								lblErrorMessage.setVisible(false);
								dc.setCurrentDoelstelling(huidigeDoelstelling);
								dc.verwijderMVODoelstelling();
								
								// refresh scherm
								Parent hoofdScherm = DoelstellingDetailsTest.this.getParent();
								((BorderPane) hoofdScherm).setLeft(null);
								PanelOverzichtTreeview p = new PanelOverzichtTreeview();
								((BorderPane) hoofdScherm).setLeft(p);
								ObservableList<Doelstelling> dcDoelstellingen = dc.getDoelstellingen();
								dcDoelstellingen.forEach(c -> System.out.println(c.getNaam() + " " + c.getComponents()));
								p.initGui(dcDoelstellingen, "doelstellingen", dc);
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
			
			// wijzig knop
			btnWijzigen.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent evt)
				{
					
					UpdateOrCreateDoelstelling vs = new UpdateOrCreateDoelstelling(dc,
							huidigeDoelstelling, "Wijzig doelstelling");
					// Eerst het hoofdscherm opvragen adhv dit scherm
					Parent hoofdScherm = DoelstellingDetailsTest.this.getParent();
					if(hoofdScherm instanceof BorderPane)
					{
						// DetailsScherm opvragen adhv het hoofdScherm
						((BorderPane) hoofdScherm).setCenter(vs);
					}
				}
			});
			
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private void addToTreeItem(TreeItem<Doelstelling> rootDoelstelling, List<Doelstelling> doelstellingen)
	{
		for(Doelstelling doelstelling : doelstellingen)
		{
			TreeItem<Doelstelling> parentDoelstelling = new TreeItem<>(doelstelling);
			
			addToTreeItem(parentDoelstelling,
					doelstelling.getComponents().stream().map(component -> (Doelstelling) component).toList());
			
			rootDoelstelling.getChildren().add(parentDoelstelling);
		}
	}
}
