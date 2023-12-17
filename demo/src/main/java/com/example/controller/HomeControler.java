package com.example.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.model.JsonDatabase;
import com.example.model.Session;
import com.example.model.tama.Tamagotchi;
import com.example.model.utils.AttributeConstant;

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
import javafx.event.ActionEvent;
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

        if (JsonDatabase.currentTamagotchi.getLife() == 0){
            /// DONE END GAME
        }
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

 public void setActionsTable() {
    actionsTableView.getItems().clear();

    // Create a TableColumn for the buttons
    TableColumn<Map.Entry<String, Runnable>, Void> buttonColumn = new TableColumn<>("Actions");
    buttonColumn.setMinWidth(200);

    // Set up the cell factory for the TableColumn
    Callback<TableColumn<Map.Entry<String, Runnable>, Void>, TableCell<Map.Entry<String, Runnable>, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<Map.Entry<String, Runnable>, Void> call(final TableColumn<Map.Entry<String, Runnable>, Void> param) {
            final TableCell<Map.Entry<String, Runnable>, Void> cell = new TableCell<>() {
                private final Button btn = new Button();

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Map.Entry<String, Runnable> action = getTableView().getItems().get(getIndex());
                        action.getValue().run();
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        btn.setText(getTableView().getItems().get(getIndex()).getKey());
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    buttonColumn.setCellFactory(cellFactory);

    // Add the TableColumn to the TableView
    actionsTableView.getColumns().add(buttonColumn);

    // Add the actions to the TableView
    ObservableList<Map.Entry<String, Runnable>> actions = FXCollections.observableArrayList(tama.getActions().entrySet());
    actionsTableView.setItems(actions);

    actionsTableView.refresh();
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
