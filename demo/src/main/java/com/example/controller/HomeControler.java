package com.example.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.model.JsonDatabase;
import com.example.model.Session;
import com.example.model.tama.Tamagotchi;

import javafx.util.Duration;
import javafx.util.Pair;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;


public class HomeControler implements Initializable {

    @FXML
    private ImageView tamaImage;

    @FXML
    private TableView attribTableView;

    @FXML
    private TableColumn attributeNameColumn;

    @FXML
    private TableColumn attributeValueColumn;

    @FXML
    private TableColumn buttonColumn;

    @FXML
    private TableView actionsTableView;

    private Session session;

    private Tamagotchi tama;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
            tama = JsonDatabase.currentTamagotchi;

            session = tama.getSession();
            setImage();
            setActionsTable();
            setAttributesTable();
            TimerService service = new TimerService();
            AtomicInteger count = new AtomicInteger(0);
            service.setCount(count.get());

            service.setDelay(Duration.seconds(3));
            service.setPeriod(Duration.seconds(3));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                setAttributesTable();
                
                count.set((int) t.getSource().getValue());
            }
        });
        service.start();






        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
    
    public void setImage(){

        String tamagotchiImagePath = session.getTamagotchi_img_path();
        Image tamagotchiImage = new Image(tamagotchiImagePath);
        tamaImage.setImage(tamagotchiImage);

    }

    public void setAttributesTable(){

        JsonDatabase.currentTamagotchi.updateState();

        attribTableView.getItems().clear();



        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    
        ObservableList<Pair<String, String>> attributes = FXCollections.observableArrayList();
    
        for (Map.Entry<String, String> entry : tama.getAttributes().entrySet()) {
            attributes.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
    
        attribTableView.setItems(attributes);

        attribTableView.refresh();

    }

    public void setActionsTable(){

        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("key"));

        buttonColumn.setCellFactory(new Callback<TableColumn<Map.Entry<String, Runnable>, String>, TableCell<Map.Entry<String, Runnable>, String>>() {
            @Override
            public TableCell<Map.Entry<String, Runnable>, String> call(TableColumn<Map.Entry<String, Runnable>, String> param) {
                return new TableCell<>() {
                    final Button btn = new Button();
    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setText(item);
                            btn.setOnAction(event -> getTableView().getItems().get(getIndex()).getValue().run());
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        });
    
        ObservableList<Map.Entry<String, Runnable>> actions = FXCollections.observableArrayList(tama.getActions().entrySet());
        actionsTableView.setItems(actions);


      

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
