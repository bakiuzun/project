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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private AnchorPane rootLayout;
    
    @FXML
    private Label actualPlaceLabel;

    private ArrayList<Label> labels = new ArrayList<>();
    
    private Timeline timeline;

    private Timeline autoSaveTimeLine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpActualPlaceLabel();
        changeBackgroundImage(JsonDatabase.currentTamagotchi.getLieuActuel().getImgpath());
        setCenterImage(JsonDatabase.currentTamagotchi.getSession().getTamagotchi_img_path());
    
        setUpActions();
        setUpAttribute();
        setUpAvailablePlace();

        startFunctionCall();

    }


    

    private void setUpActualPlaceLabel(){
        actualPlaceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        actualPlaceLabel.setText(JsonDatabase.currentTamagotchi.getLieuActuel().getNomLieu().name());
    }
    private void setUpAvailablePlace(){
        ArrayList<NomLieu> availablePlaces = JsonDatabase.currentTamagotchi.getLieuActuel().getVoisins();
        for(NomLieu place: availablePlaces){
            Button button = new Button(place.name());
            button.setOnAction(e-> {
                placesVBox.getChildren().clear();
                buttonHBox.getChildren().clear();
                JsonDatabase.currentTamagotchi.clearAction();
                JsonDatabase.currentTamagotchi.setLieuActuel(new Lieu(place));
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
        ArrayList<String> Attributekeys = JsonDatabase.currentTamagotchi.printAttributes();
        for (String key : Attributekeys) {
            Label label = new Label(key);
            //label.setAlignment(Pos.CENTER);
            // add styles
            attributeVBox.getChildren().add(label);
            labels.add(label); // Store labels in a map with their corresponding key
        }

    }

    private void setUpActions(){
        Set<String> Actionkeys = JsonDatabase.currentTamagotchi.getActions().keySet();
        for (String key : Actionkeys) {
            Button button = new Button(key);
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
