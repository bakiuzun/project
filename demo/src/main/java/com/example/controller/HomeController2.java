package com.example.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;



import com.example.model.JsonDatabase;
import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.utils.ActionConstant;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Region;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

public class HomeController2  implements Initializable  {
    
    @FXML
    private HBox buttonHBox;

    @FXML
    private HBox placesVBox;

    @FXML
    private VBox attributeVBox;

    @FXML
    private ImageView centerImage;

    @FXML
    private Label moveLabel;


    @FXML
    private AnchorPane rootLayout;
    
    @FXML
    private Label actualPlaceLabel;

    private ArrayList<Label> labels = new ArrayList<>();
    
    private Timeline timeline;

    private Timeline autoSaveTimeLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JsonDatabase.currentTamagotchi.addNeighbord();
        setUpActualPlaceLabel();
        changeBackgroundImage(JsonDatabase.currentTamagotchi.getLieuActuel().getImgpath());
        setCenterImage(JsonDatabase.currentTamagotchi.getSession().getTamagotchi_img_path());
    
        rootLayout.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        setUpActions();
        setUpAttribute();
        setUpMoveLabel();
        setUpAvailablePlace();

        startFunctionCall();
        checkLife();

    }

    private void setUpMoveLabel(){


    CornerRadii cornerRadii = new CornerRadii(10);
    Color transparentLightGray = Color.rgb(250, 250, 250, 0.6); // Adjust alpha (last parameter) for transparency
    BackgroundFill backgroundFill = new BackgroundFill(transparentLightGray, cornerRadii, Insets.EMPTY);
    Background background = new Background(backgroundFill);

    // Apply styling for the Label
    moveLabel.setBackground(background);
    moveLabel.setPadding(new Insets(20));

    // Set the shadow effect
    DropShadow shadow = new DropShadow();
    shadow.setColor(Color.WHITESMOKE);
    shadow.setRadius(10);
    moveLabel.setEffect(shadow);

    }

    

    private void setUpActualPlaceLabel(){
        actualPlaceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        actualPlaceLabel.setText(JsonDatabase.currentTamagotchi.getLieuActuel().getNomLieu().name());
    }
    private void setUpAvailablePlace(){
        ArrayList<NomLieu> availablePlaces = JsonDatabase.currentTamagotchi.getLieuActuel().getVoisins();
        for(NomLieu place: availablePlaces){
            Button button = new Button(place.name());
            button.getStyleClass().add("button-hover");
            //button.setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
            button.setOnAction(e-> {
                placesVBox.getChildren().clear();
                buttonHBox.getChildren().clear();
                JsonDatabase.currentTamagotchi.clearAction();
                JsonDatabase.currentTamagotchi.setLieuActuel(new Lieu(place));
                JsonDatabase.currentTamagotchi.addNeighbord();
                JsonDatabase.currentTamagotchi.loadAction();
                setUpActions();
                setUpAvailablePlace();
                changeBackgroundImage(JsonDatabase.currentTamagotchi.getLieuActuel().getImgpath());
                setUpActualPlaceLabel();
            });
            placesVBox.getChildren().add(button);

        }


    }
    private void setUpAttribute(){
          // set up attributes 
        CornerRadii cornerRadii = new CornerRadii(10);
        // Set the background with light gray color and a transparent background
        Color transparentLightGray = Color.rgb(250, 250, 250, 0.6); // Adjust alpha (last parameter) for transparency
        BackgroundFill backgroundFill = new BackgroundFill(transparentLightGray, cornerRadii, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        // Apply styling for the VBox
        attributeVBox.setBackground(background);
        attributeVBox.setPadding(new Insets(20));

        // Set the shadow effect
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.WHITESMOKE);
        shadow.setRadius(10);
        attributeVBox.setEffect(shadow);

        //attributeVBox.setPadding(new Insets(20));

        for (Map.Entry<String, String> entry : JsonDatabase.currentTamagotchi.printAttributes(false).entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Label label = new Label(key);
            label.setStyle("-fx-font-weight: bold;-fx-font-family: Arial;"); // Set bold style
            attributeVBox.getChildren().add(label);

            Region spacer = new Region();
            spacer.setPrefHeight(2.5); // Set the desired height for the spacer
            attributeVBox.getChildren().add(spacer);
            
            Label val = new Label(value);
            attributeVBox.getChildren().add(val);
            
            Region spacer2 = new Region();
            spacer2.setPrefHeight(10); // Set the desired height for the spacer
        
            attributeVBox.getChildren().add(spacer2);
            labels.add(val);


        }
       
        AnchorPane.setRightAnchor(attributeVBox, 30.0);
        AnchorPane.setTopAnchor(attributeVBox, 30.0);
        attributeVBox.setAlignment(Pos.CENTER);
    }

    private void setUpActions(){
        Set<String> Actionkeys = JsonDatabase.currentTamagotchi.getActions().keySet();
        for (String key : Actionkeys) {
            Button button = new Button(key);
            button.getStyleClass().add("button-hover");

            button.setOnAction(this::handleButtonClick);

            buttonHBox.getChildren().add(button);
        }
    }

    public void changeBackgroundImage(String imagePath) {
        Image image = new Image(imagePath);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        rootLayout.setBackground(background);

    }

    public void setCenterImage(String imagePath) {
        Image image = new Image(imagePath);
        centerImage.setImage(image);

        centerImage.setFitWidth(image.getWidth() / 2);
        centerImage.setFitHeight(image.getHeight() / 2);
        

        
        // Position the image in the center of the AnchorPane
        //AnchorPane.setTopAnchor(centerImage, (rootLayout.getHeight() - image.getHeight()) / 2);
        //AnchorPane.setLeftAnchor(centerImage, (rootLayout.getWidth() - image.getWidth()) / 2);
    }


    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        JsonDatabase.currentTamagotchi.doAction(clickedButton.getText());

        clickedButton.setDisable(true);

        ObservableList<Node> children = placesVBox.getChildren();

        // il peut pas se déplacer pendant qu'une acction se passe
        for (Node node : children) {node.setDisable(true);}
        

        Timeline buttonTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            for (Node node : children) {node.setDisable(false);}
            clickedButton.setDisable(false);
            updateLabels();

        }));
        buttonTimer.setCycleCount(1); // Run once
        buttonTimer.play();

        
    }

     // Function that you want to call repeatedly
    private void updateLabels() {
        HashMap<String,String> updatedKeys = JsonDatabase.currentTamagotchi.printAttributes(true);

        int index = 0;

       for (String key : updatedKeys.keySet()) {
            String updatedKey = updatedKeys.get(key); // Retrieve the updated value for the key
            labels.get(index).setText(updatedKey);

            index++; // Move to the next index
        }

        checkLife();
    }

    private void checkLife(){

        if (JsonDatabase.currentTamagotchi.getLife() == 0){
            timeline.stop();
            autoSaveTimeLine.stop();
            scheduleErrorAlert();
        }
    }

    private void scheduleErrorAlert() {
        Platform.runLater(this::showErrorAlert);
    }

    // Function to start calling yourFunctionToCall every 5 seconds
    private void startFunctionCall() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(ActionConstant.UPDATE_STATE_TIME), event -> {
            JsonDatabase.currentTamagotchi.updateState();
            updateLabels();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Set to repeat indefinitely
        timeline.play();


        autoSaveTimeLine = new Timeline(new KeyFrame(Duration.seconds(5*60), event -> {
            JsonDatabase.save_existing_session();
        }));
        autoSaveTimeLine.setCycleCount(Timeline.INDEFINITE); // Set to repeat indefinitely
        autoSaveTimeLine.play();
    }

    private void showErrorAlert(){

        JsonDatabase.delete_existing_session();

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("FIN");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Votre Tamagotchi est mort");
        errorAlert.setOnCloseRequest(event -> {
            Stage currentStage = (Stage) rootLayout.getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            });
        errorAlert.showAndWait();
    }
    
}
