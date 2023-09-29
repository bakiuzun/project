package com.example.controller;


import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import com.example.model.JsonDatabase;
import com.example.model.Tamagotchi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


        TimerService service = new TimerService();
        AtomicInteger count = new AtomicInteger(0);
        service.setCount(count.get());

        service.setDelay(Duration.seconds(3));
        service.setPeriod(Duration.seconds(3));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                updateAttributes();
                count.set((int) t.getSource().getValue());
            }
        });
        service.start();
        

    }


    public void updateAttributes(){
       

        JsonDatabase.currentTamagotchi.updateState();

        Map<String,String> attr =   JsonDatabase.currentTamagotchi.getAttributes();

        data.clear(); // Clear the existing data
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            data.add(new AttributeEntry(entry.getKey(), entry.getValue()));
        }

        // Refresh the TableView to reflect the updated data
        tamagotchiTableView.refresh();
        // SHOW THE NEW VALUES HERE 

    }

    private static class TimerService extends ScheduledService<Integer> {
        private IntegerProperty count = new SimpleIntegerProperty();

        public final void setCount(Integer value) {
            count.set(value);
        }

        public final Integer getCount() {
            return count.get();
        }

        protected Task<Integer> createTask() {
            return new Task<Integer>() {
                protected Integer call() {
                    //Adds 1 to the count
                    count.set(getCount() + 1);
                    return getCount();
                }
            };
        }
    }

}
