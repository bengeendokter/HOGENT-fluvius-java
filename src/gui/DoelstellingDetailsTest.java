package gui;

import java.io.IOException;

import domein.Categorie;
import domein.Doelstelling;
import domein.DomeinController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DoelstellingDetailsTest<E> extends Pane{
	@FXML
	private Button btnWijzigen;
	@FXML
	private Button btnVerwijderen;
	@FXML
	private Label lblDetailsDoelstelling1;
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
	private TreeView treeViewSubDoelstellingen;
	@FXML
	private Label lblNaamIngevuld;
	@FXML
	private Label lblBewerkingIngevuld;
	@FXML
	private Label lblDoelWaardeIngevuld;
	@FXML
	private Label lblEenheidIngevuld;
	@FXML
	private Label lblSdgIngevuld;
	@FXML
	private Label lblDatasourceIngevuld;
	@FXML
	private ListView listRollenIngevuld;
	private DomeinController dc;
	private E object;
	
	public DoelstellingDetailsTest(DomeinController dc, E object){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DoelstellingDetails.fxml"));
		loader.setController(this);
		this.dc = dc;
		this.object = object;

		try
		{
			loader.load();
			if(object instanceof Doelstelling) {
				lblNaamIngevuld.setText(((Doelstelling) object).getNaam());
				lblBewerkingIngevuld.setText(((Doelstelling)object).getFormule().toString());
				//lblDatasourceIngevuld.setText(((Doelstelling) object).getDatasource().getNaam());
				lblSdgIngevuld.setText(((Doelstelling) object).getSdGoal().getNaam());
				//lblDoelWaardeIngevuld.setText(((Doelstelling) object).getDoelwaarde());
				lblEenheidIngevuld.setText("hier moet nog iets komen");
				//listRollenIngevuld.setItems((ObservableList) ((Doelstelling)object).getRollen());
			}
			
			
			this.getChildren().addAll(btnWijzigen, btnVerwijderen, lblBewerking, lblDatasource, lblDetailsDoelstelling1, lblDoelwaarde, lblIcoon, lblNaam, lblRollen, lblSdg, lblSubDoelstellingen, treeViewSubDoelstellingen, lblDoelWaardeIngevuld, lblEenheidIngevuld, lblNaamIngevuld, lblBewerkingIngevuld, lblDatasourceIngevuld, lblSdgIngevuld, listRollenIngevuld);
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

}
