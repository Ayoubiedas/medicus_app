package application;

import java.time.chrono.HijrahChronology;
import java.util.ArrayList;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.AutoCompletionBinding.AutoCompletionEvent;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Search_scene extends Application {

	public static void main(String[] args) {
		 Application.launch(args);
	}

	
	public void start(Stage primaryStage) throws Exception {
		
		// db connection
		
		Db_manage.make_connection();
		
		/*final ImageView imageView = new ImageView(
			      new Image("search.png")
			    );
		imageView.fitWidthProperty().set(50);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setCache(true);*/
		
		
		Label search_label = new Label("Trouver un patient");
		
		ArrayList<Patient> patients = Db_manage.load_patients("patient");

		
		TextField search_patient = new TextField();
		search_patient.getStyleClass().add("search-field");
		search_patient.promptTextProperty().set("Inserer le nom du patient");
		
		Button search_button = new Button("Chercher");
		search_button.setContentDisplay(ContentDisplay.TOP);
		
		AutoCompletionBinding<Patient> bind = TextFields.bindAutoCompletion(search_patient,patients);
        
		bind.setOnAutoCompleted(new EventHandler<AutoCompletionEvent<Patient>>() {
            @Override
            public void handle(AutoCompletionEvent<Patient> event) {
                Patient searched_patient = (Patient)event.getCompletion();
                load_patient(searched_patient);
                
                search_button.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent ev) {
                    	Patient searched_patient = (Patient)event.getCompletion();
                    	load_patient(searched_patient);
                    }
                });
            }
        });
		
		
		
		
		// create table
		
		TableView<Patient> table_patients = new TableView<Patient>();
		
		ObservableList<Patient> list_patient = FXCollections.observableArrayList(patients);
		
		table_patients.setItems(list_patient);
		//System.out.println(list_patient);
		
		TableColumn firstNameColumn = new TableColumn("Nom");
		TableColumn lastNameColumn = new TableColumn("Prenom");
		TableColumn ageColumn = new TableColumn("Age");
		
		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastname"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));

		table_patients.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn);
		table_patients.setPrefWidth(450);
		table_patients.setPrefHeight(300);
		table_patients.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table_patients.setRowFactory( tv -> {
		    TableRow<Patient> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Patient patient_selected = row.getItem();
		            load_patient(patient_selected);
		        }
		    });
		    return row ;
		});
		// end create table
		
		VBox vbox_test = new VBox();
		HBox search = new HBox();
		VBox table_pan = new VBox();
		
		// layout
		vbox_test.setPadding(new Insets(15, 12, 15, 12));
		vbox_test.setSpacing(10);
		
		search.setPadding(new Insets(15, 12, 15, 12));
		search.setSpacing(10);
		
		table_pan.getChildren().addAll(table_patients);
		
		search.getChildren().addAll(search_patient, search_button);
		vbox_test.getChildren().addAll(search_label, search, table_pan);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		Tab tab = new Tab();
		tab.setText("Mes patients");
		tab.setContent(vbox_test);
		
		tabPane.getTabs().add(tab);
		
		Scene scene_search = new Scene(tabPane);
		scene_search.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		
		primaryStage.setScene(scene_search);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	private void load_patient(Patient patient)
	{
		
		new Patient_detail(patient);
		
	}
	
	public static void add_new_atcd(Integer id_pt)
	{
		new Add_atcd(id_pt);
	}

	public static void load_atcd(Atcd atcd)
	{
		new Add_atcd(atcd);
	}

}