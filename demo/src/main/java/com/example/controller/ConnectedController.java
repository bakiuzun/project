package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;


import com.example.model.JsonDatabase;
import com.example.model.Tamagotchi;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConnectedController {

    public class AttributeEntry {
        private final String attributeName;
        private final String attributeValue;
    
        public AttributeEntry(String attributeName, String attributeValue) {
            this.attributeName = attributeName;
            this.attributeValue = attributeValue;
        }
    
        public String getAttributeName() {return attributeName;}
        public String getAttributeValue() {return attributeValue;}
    }
    
    @FXML
    private TableView<AttributeEntry> tamagotchiTableView;

    @FXML
    private TableColumn<AttributeEntry, String> attributeNameColumn;

    @FXML
    private TableColumn<AttributeEntry, String> attributeValueColumn;


    private ObservableList<AttributeEntry> data;

   
    

    public void initialize() {

        Tamagotchi tama =  JsonDatabase.currentTamagotchi;
        Map<String,String> attr =  tama.getAttributes();

         // Create an ObservableList to hold the data for the TableView
        data = FXCollections.observableArrayList();

        // Populate the ObservableList with data from the attributes map
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            data.add(new AttributeEntry(entry.getKey(), entry.getValue()));
        }

        // Set the cell value factories for the TableView columns
        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        // Set the data for the TableView
        tamagotchiTableView.setItems(data);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateAttributes();
            }
        }, 3000, 3000);

    }


    public void updateAttributes(){
        System.out.println("GIRDIMMMM");

        JsonDatabase.currentTamagotchi.updateState();

        Map<String,String> attr =   JsonDatabase.currentTamagotchi.getAttributes();

        System.out.println("ATTR KEY = " + attr.get("faim"));
        data.clear(); // Clear the existing data
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            data.add(new AttributeEntry(entry.getKey(), entry.getValue()));
        }

        // Refresh the TableView to reflect the updated data
        tamagotchiTableView.refresh();
        // SHOW THE NEW VALUES HERE 

    }

}
