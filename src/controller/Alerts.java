package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public class Alerts {

	public static void emptyFieldsAlert() {
		
		Alert emptyFields = new Alert(Alert.AlertType.WARNING, "Ostrze¿enie", ButtonType.OK);
		emptyFields.setHeaderText("Ostrze¿enie");
		emptyFields.setContentText("Przed dodaniem pracownika proszê wpe³niæ wszystkie pola poprawnymi danymi");
		emptyFields.initModality(Modality.APPLICATION_MODAL);
		emptyFields.showAndWait();
		if (emptyFields.getResult() == ButtonType.OK) {
			emptyFields.close();
		}
	}
	public static void saveInfoAlert() {
		
		Alert saveInfo = new Alert(Alert.AlertType.INFORMATION, "Informacja", ButtonType.OK);
		saveInfo.setHeaderText("Informacja");
		saveInfo.setContentText("Dokument zosta³ pomyœlnie zapisany");
		saveInfo.initModality(Modality.APPLICATION_MODAL);
		saveInfo.showAndWait();
		if (saveInfo.getResult() == ButtonType.OK) {
			saveInfo.close();
		}
	}
}
