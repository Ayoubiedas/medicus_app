package application;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Add_atcd extends Stage {
	
	private Integer id_patient_detailed;
	
	public Add_atcd(Integer id_pt) {
		try {
			
			id_patient_detailed = id_pt;
			
			GridPane grid = new GridPane();
	        
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(0, 10, 0, 10));
	       
	        
	        Label lbs[];
	        TextField input = new TextField();
	        ComboBox<String> atcd_type = new ComboBox<String>();
	        DatePicker atcd_date = new DatePicker(LocalDate.now());
	        TextArea atcd_content = new TextArea();
	        Button save_button = new Button("Enregistrer");
	        
	        save_button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                	Atcd nw_atcd = new Atcd();
            		nw_atcd.setId_patient(id_patient_detailed);
            		nw_atcd.setLabel(input.getText());
            		nw_atcd.setType(atcd_type.getValue());
            		// covert datepicker to date
            		LocalDate localDate = atcd_date.getValue();
            		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            		//System.out.println(date);
            		// end conversion
            		nw_atcd.setDate(date);
            		nw_atcd.setContent(atcd_content.getText());
            		
            		nw_atcd.save_antcd();
                }

            });
	        
	        String types[]=new String[]{
	    	        "Medical",
	    	        "Chirurgical",
	    	        "Obstétrical",
	    	        "Familial",
	    	};
	        
	        atcd_type.getItems().addAll(types);
	             
	  
	        String labels_list[]=new String[]{
	        "Libellé",
	        "Type",
	        "Date",
	        "Antécédent",
	        };
	        
	        int labels_list_lenght = labels_list.length; 
	        
	        lbs=new Label[labels_list_lenght];
	        
	        
	        
	        for (int i = 0; i < labels_list_lenght; i++) {
	            lbs[i]=new Label(labels_list[i]);
	            grid.add(lbs[i], 0, i);
	        }
	        
	        
	        grid.add(input, 1, 0);
	        grid.add(atcd_type, 1, 1);
	        grid.add(atcd_date, 1, 2);
	        grid.add(atcd_content, 1, 3);
	        grid.add(save_button, 1, 4);
	        
	        // styling items
	        grid.setStyle("-fx-padding:10;");
			// end styling items
	        
	        
	        
			Scene scene = new Scene(grid,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			setScene(scene);
			show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
