package com.example.controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.model.JsonDatabase;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class HomeController2  implements Initializable  {
    
    @FXML
    private HBox buttonHBox;
    @FXML
    private VBox attributeVBox;

    @FXML
    private Label actualPlaceLabel;

    private ArrayList<Label> labels = new ArrayList<>();
    
    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        actualPlaceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        actualPlaceLabel.setText(JsonDatabase.currentTamagotchi.getLieuActuel().getNomLieu().name());

    
        Set<String> Actionkeys = JsonDatabase.currentTamagotchi.getActions().keySet();
        for (String key : Actionkeys) {
            Button button = new Button(key);
            button.setOnAction(this::handleButtonClick);
            buttonHBox.getChildren().add(button);
        }

        ArrayList<String> Attributekeys = JsonDatabase.currentTamagotchi.printAttributes();

        for (String key : Attributekeys) {
            Label label = new Label(key);
            attributeVBox.getChildren().add(label);
            labels.add(label); // Store labels in a map with their corresponding key
        }

        startFunctionCall();

    }

    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        JsonDatabase.currentTamagotchi.doAction(clickedButton.getText());
    }

     // Function that you want to call repeatedly
    private void updateLabels() {
        ArrayList<String> updatedKeys = JsonDatabase.currentTamagotchi.printAttributes();

        for (int i = 0; i < updatedKeys.size(); i++) {
            String updatedKey = updatedKeys.get(i);
            labels.get(i).setText(updatedKey);
            
        }

        checkLife();
    }

    private void checkLife(){

        if (JsonDatabase.currentTamagotchi.getLife() == 0){
            timeline.stop();
        }
    }

    // Function to start calling yourFunctionToCall every 5 seconds
    private void startFunctionCall() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            JsonDatabase.currentTamagotchi.updateState();
            updateLabels();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Set to repeat indefinitely
        timeline.play();
        
    }

    private void showErrorAlert(){
         Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("FIN");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Votre Tamagotchi est mort");
        errorAlert.showAndWait();


    }
    
}
