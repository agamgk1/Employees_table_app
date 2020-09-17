package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Employee;

public class MainViewController {

	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
	private PrintWriter save = null;

	@FXML
	private TextField roomNumberTextField;

	@FXML
	private Label roomLabel;

	@FXML
	private Button reportButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField ednTimeTextField;

	@FXML
	private Button addButton;

	@FXML
	private TableColumn<Employee, String> surnameColumn;

	@FXML
	private TextField surnameTextField;

	@FXML
	private Button readButton;

	@FXML
	private TableColumn<Employee, String> nameColumn;

	@FXML
	private TextField startTimeTextField;

	@FXML
	private Button saveButton;
	
	@FXML
	private TableColumn<Employee, String> roomColumn;
	@FXML
	private TableColumn<Employee, String> startTime;

	@FXML
	private TableColumn<Employee, String> endTime;

	@FXML
	private TableView<Employee> table;

	@FXML
	void addButtonAction(ActionEvent event) {

		if ((nameTextField.getText().trim().isEmpty()) || (surnameTextField.getText().trim().isEmpty())
				|| (roomNumberTextField.getText().trim().isEmpty()) || (endTime.getText().trim().isEmpty())
				|| startTimeTextField.getText().trim().isEmpty()) {

			Alerts.emptyFieldsAlert();
		}
		
		if(isInteger(startTimeTextField.getText()))  {
			if(isInteger(ednTimeTextField.getText())) {
		
			employeeList.add(new Employee(nameTextField.getText(), surnameTextField.getText(),

					roomNumberTextField.getText(), startTimeTextField.getText(), ednTimeTextField.getText()));
			nameTextField.clear();
			surnameTextField.clear();
			startTimeTextField.clear();
			ednTimeTextField.clear();
			roomNumberTextField.clear();
		}
		}
	}

	@FXML
	void saveButtonAction() throws IOException {
		saveFile();
		Alerts.saveInfoAlert();
	}

	@FXML
	void reportButtonAction() throws IOException {
		saveReportFile();
	}

	@FXML
	public void initialize() throws IOException {
		readFile();
		setTable();
		
		//Zmiana wygl¹du
		saveButton.getStyleClass().add("my-button");
		roomLabel.getStyleClass().add("my-label");
		
	}
	//wczytnie pliku tekstowego 
	private void readFile() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("file.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (br != null) {
			String st;
			while ((st = br.readLine()) != null) {
				
				addEmployeeToList(st.split(" "));
			}
			br.close();
		}
	}
	//zapis pliku 
	private void saveFile() throws IOException {
		save = new PrintWriter("file.txt");
		for (Employee e : employeeList) {
			save.printf("%s %s %s %s %s %n", e.getName(), e.getSurmame(), e.getRoom(), e.getStartHour(),
					e.getEndHour());
		}
		if (save != null)
			save.close();
	}
	//generacja i zapis raportu
	private void saveReportFile() throws IOException {
		Stage secondaryStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz raport procowniczy");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"));
		File file = fileChooser.showSaveDialog(secondaryStage);
		if (file != null) {
			save = new PrintWriter(new FileWriter(file));

			sortListByWorkTime();
			for (Employee e : employeeList) {
				save.printf("%s %s %s %s %s %n", e.getName(), e.getSurmame(), e.getRoom(), e.getStartHour(),
						e.getEndHour());
			}
			if (save != null)
				save.close();
		}
	}
	//Dodanie pracownika do listy
	private void addEmployeeToList(String[] employee) {
	
			employeeList.add(new Employee(employee[0], employee[1], employee[2], employee[3], employee[4]));
		
	}
	//Sortowanie Listy pracowników
	private void sortListByWorkTime() {
		Comparator<Employee> comparator = Comparator.comparingInt(Employee::getWorkTime);
		Collections.sort(employeeList, comparator);
	}
	//Ustawienie danych w tabeli 
	private void setTable() {

		nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("surmame"));
		roomColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("room"));
		startTime.setCellValueFactory(new PropertyValueFactory<Employee, String>("startHour"));
		endTime.setCellValueFactory(new PropertyValueFactory<Employee, String>("endHour"));

		table.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			nameTextField.setText(newVal.getName());
			surnameTextField.setText(newVal.getSurmame());
			startTimeTextField.setText(newVal.getStartHour());
			ednTimeTextField.setText(newVal.getEndHour());
			roomNumberTextField.setText(newVal.getRoom());
			roomLabel.setVisible(true);
			roomLabel.setText("Pracownik: " + newVal.getName() + " " + "pracuje w pokoju nr: " + newVal.getRoom());
	
		});
		table.setItems(employeeList);
	}
	//metoda sprawdzajaca czy string mozna zamienic na Integer
	public boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	    	Alerts.emptyFieldsAlert();
	        return false;
	    }
	}
}