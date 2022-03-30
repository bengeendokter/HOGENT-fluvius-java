package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import domein.PopulateDB;

public class RunPopulateDB {

	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IOException {
		// Indien het niet werkt om op te starten,
		// voeg onderstaande toe aan run configuration - vm arguments
		// --module-path "\path\to\javafx-sdk-12.0.1\lib" --add-modulesjavafx.controls,javafx.fxml
		PopulateDB.run();
	}

}
