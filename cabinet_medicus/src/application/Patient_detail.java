package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.chrono.HijrahChronology;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Patient_detail extends Stage {

	private Integer id_patient_detailed;
	
	@SuppressWarnings("unchecked")
	public Patient_detail(Patient searched_patient) {
		try {
			// helloo github
			
			TabPane tabPane = new TabPane();
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
			
			id_patient_detailed = searched_patient.getId_patient();
						
			// onglet info perso
			Tab tab = new Tab();
			tab.setText("Informations personnelles");
			
			Desktop desktop = Desktop.getDesktop();
			//tab.setContent();
			
			final FileChooser fileChooser = new FileChooser();
	        final Button openButton = new Button("Photo");
	        
	        openButton.setOnAction(
	                new EventHandler<ActionEvent>() {
	                    @Override
	                    public void handle(final ActionEvent e) {
	                    	fileChooser.setTitle("Photo du patient");
	                        fileChooser.setInitialDirectory(
	                            new File(System.getProperty("user.home"))
	                        );                 
	                        fileChooser.getExtensionFilters().addAll(
	                            new FileChooser.ExtensionFilter("All Images", "*.*"),
	                            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                            new FileChooser.ExtensionFilter("PNG", "*.png")
	                        );
	                        
	                        Window window = null;
							File file = fileChooser.showOpenDialog(window);
	                        if (file != null) {
	                        	try {
	                               
	                                System.out.println(file);
	                                desktop.open(file);
	                            } catch (IOException ex) {
	                                Logger.getLogger(Patient_detail.class.getName()).log(
	                                    Level.SEVERE, null, ex
	                                );
	                            }
	                        }
	                    }
	                });
	        
	     // identity tpane
	        GridPane grid = new GridPane();
	        
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(0, 10, 0, 10));
	       
	        grid.add(openButton, 9, 0);
	        
	        Label lbs[];
	        TextField inputs[];

	        String labels_list[]=new String[]{
	        "Nom",
	        "Prenom",
	        "Autre nom",
	        "Date de naissance",
	        "Genre",
	        "Adresse",
	        "Region",
	        "Ville",
	        "Code postal",
	        "Pays"
	        };
	        
	        int labels_list_lenght = labels_list.length; 
	        
	        lbs=new Label[labels_list_lenght];
	        inputs = new TextField[labels_list_lenght];
	        
	        
	        for (int i = 0; i < labels_list_lenght; i++) {
	            lbs[i]=new Label(labels_list[i]);
	            inputs[i]=new TextField(labels_list[i]);
	            
	            grid.add(lbs[i], 0, i+1);
	            grid.add(inputs[i], 1, i+1);
	            

	        }
	        
	       
	      
	      TitledPane tp_identity = new TitledPane();
	      tp_identity.setText("Identité");
	      tp_identity.setContent(grid);
	      
	      // contact tp
	      
	      GridPane contact_grid = new GridPane();  
	      
	      contact_grid.setHgap(10);
	      contact_grid.setVgap(10);
	      contact_grid.setPadding(new Insets(0, 10, 0, 10));
	      
	      Label lbs_contact[];
	      TextField inputs_contact[];

	      String labels_contact_list[]=new String[]{
	        "Numéro de Téléphone",
	        "Numéro de mobile",
	        "Numéro de fax",
	        "Numéro tel. professionel",
	        "E-mail",
	        "Site",
	        };
	      
	      int lbs_c_lst_lgth = labels_contact_list.length; 
	        
	      lbs_contact = new Label[lbs_c_lst_lgth];
	      inputs_contact = new TextField[lbs_c_lst_lgth];
	        
	        
	        for (int i = 0; i < lbs_c_lst_lgth; i++) {
	        	lbs_contact[i]=new Label(labels_contact_list[i]);
	        	inputs_contact[i]=new TextField(labels_contact_list[i]);
	            
	        	contact_grid.add(lbs_contact[i], 0, i+1);
	        	contact_grid.add(inputs_contact[i], 1, i+1);
	            

	        }
	      
	      TitledPane tp_contact = new TitledPane();
	      tp_contact.setText("Contact");
	      tp_contact.setContent(contact_grid);
	      
	      
            VBox container = new VBox();
            container.getChildren().addAll(tp_identity, tp_contact);
            container.getStyleClass().add("vbox");
            
            ScrollPane root_info_perso = new ScrollPane();
            root_info_perso.setFitToWidth(true);
  	        root_info_perso.setContent(container);
            
            tab.setContent(root_info_perso);
			// end onglet info perso
			
			Tab tab_2 = new Tab();
			tab_2.setText("Antecedent");
			
			// add tree view
			HBox atcd_pan = new HBox();
			VBox tree_pan = new VBox();
			VBox detail_atcd_pan = new VBox();
			
			TreeItem<String> tree_nodes[] = null; 
			
			String tree_items[]=new String[]{
			        "Antecedent",
			        "Facteurs de risque",
			        "Personnels",
			        "Familiaux",
			        "Cardio-vasculaire",
			        "Medicaux",
			        "Chirurgicaux",
			        "Obstétricaux"
			        };
			
			int tree_items_lgth = tree_items.length;
			tree_nodes =  new TreeItem[tree_items_lgth];
			
			for (int i = 0; i < tree_items_lgth; i++) 
				tree_nodes[i]=new TreeItem<String>(tree_items[i]);
	      
	      
			
			tree_nodes[0].setExpanded(true);
			tree_nodes[1].setExpanded(false);
			tree_nodes[2].setExpanded(false);
			
			tree_nodes[1].getChildren().add(tree_nodes[4]);
			
			tree_nodes[2].getChildren().add(tree_nodes[5]);
			tree_nodes[2].getChildren().add(tree_nodes[6]);
			tree_nodes[2].getChildren().add(tree_nodes[7]);
			
			tree_nodes[0].getChildren().add(tree_nodes[1]);
			tree_nodes[0].getChildren().add(tree_nodes[2]);
			tree_nodes[0].getChildren().add(tree_nodes[3]);
			
			TreeView<String> treeView = new TreeView<String>(tree_nodes[0]);
			
			tree_pan.getChildren().add(treeView);
			
			atcd_pan.getChildren().add(tree_pan);
			atcd_pan.getChildren().add(detail_atcd_pan);
			
			ScrollPane root_atcd = new ScrollPane();
			
			root_atcd.setContent(atcd_pan);
			
			tab_2.setContent(root_atcd);
			
			// end tree view
			
			// create table
			
			TableView<Atcd> table_atcd = new TableView<Atcd>();
			
			Atcd new_atcd = new Atcd(id_patient_detailed);
			ObservableList<Atcd> list_atcd = FXCollections.observableArrayList(new_atcd.load_antcdent());
			
			table_atcd.setItems(list_atcd);
			System.out.println(list_atcd);
			
			TableColumn<Atcd, String> labelColumn = new TableColumn<Atcd, String>("Libellé");
			TableColumn<Atcd, Date> dateColumn = new TableColumn<Atcd, Date>("date");
			
			labelColumn.setCellValueFactory(new PropertyValueFactory<Atcd, String>("label"));
			dateColumn.setCellValueFactory(new PropertyValueFactory<Atcd, Date>("date"));
		

			table_atcd.getColumns().addAll(labelColumn, dateColumn);
			table_atcd.setPrefWidth(450);
			table_atcd.setPrefHeight(300);
			table_atcd.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			// end create table
			
			Button new_atcd_button = new Button("Ajouter un antécédent");
			
			new_atcd_button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                	Search_scene.add_new_atcd(id_patient_detailed);
                }
            });
			
			detail_atcd_pan.getChildren().add(new_atcd_button);
			detail_atcd_pan.getChildren().add(table_atcd);
			
			Tab tab_3 = new Tab();
			tab_3.setText("Consultations");
			
			Tab tab_4 = new Tab();
			tab_4.setText("Prescriptions");
		
			
			tabPane.getTabs().add(tab);
			tabPane.getTabs().add(tab_2);
			
			Scene scene = new Scene(tabPane,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			setScene(scene);
			show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}