package com.example.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.model.*;
import com.example.model.tama.Tamagotchi;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.tama.tamaNonVivant.Voiture;
import com.example.model.tama.tamaVivant.Cat;
import com.example.model.tama.tamaVivant.Dog;
import com.example.model.tama.tamaVivant.Rabbit;
import com.example.model.tama.tamaVivant.Turtle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class CreatePartyController {

    private final int MAX_TAMAGOTCHI_NAME_LENGTH = 15;
    private final int PIN_CODE_LENGTH = 4;
    
    @FXML
    private ComboBox<String> tamagotchiType;
    @FXML
    private ImageView tamagotchiImage;

    @FXML
    private TextField tamagotchiName;


    @FXML
    private TextField pinCode;
    
    private TypeTamagotchi selectedType;

    @FXML
    private Button createPartyButton;

    private boolean disableCreatePartyButton;

    @FXML
    public void initialize() {
        
        // dynamic way of settings available element in the combo box
        initTamagotchiComboBox();
        
        // allow only digit
        pinCode.setTextFormatter(pinCode_helper());

        // set create button to false at the start 
        updateCreatePartyState(null);
        
    }

    private void initTamagotchiComboBox(){
         tamagotchiType.setItems(FXCollections.observableArrayList(Arrays.stream(TypeTamagotchi.values())
            .map(type -> type.name())
            .collect(Collectors.toList())));

        tamagotchiType.setValue(TypeTamagotchi.CAT.name());
        selectedType = TypeTamagotchi.CAT;


        tamagotchiType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> 
        {
            selectedType = TypeTamagotchi.valueOf(newValue);
        });
        

    }

    private TextFormatter<Integer> pinCode_helper(){

         TextFormatter<Integer> textFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                null,
                change -> {
                    String newText = change.getControlNewText();

                    // Use a regular expression to allow only numeric input
                    if (newText.matches("\\d*")) {
                        return change;
                    } else {
                        return null;
                    }
                }
        );
        return textFormatter;
    }

    @FXML
    private void updateCreatePartyState(KeyEvent event){

        if (pinCode.getText().length() == PIN_CODE_LENGTH || pinCode.getText().length() == 0
            && (tamagotchiName.getText().trim().isEmpty() == false && tamagotchiName.getText().length() <= MAX_TAMAGOTCHI_NAME_LENGTH)){

                disableCreatePartyButton = false;
        }         
        else {
            disableCreatePartyButton = true;
        }

        createPartyButton.setDisable(disableCreatePartyButton);
    }
    @FXML
    private void goToHomePage(ActionEvent event) {

        String homePageFXML = "menu.fxml";
        String homePageTitle = "MainPage";


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Access the Stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(homePageTitle);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void createParty(ActionEvent event) {

        Integer codePin = this.pinCode.getText().length() != 0 ? Integer.valueOf(this.pinCode.getText()): -1;
        
        Session new_tama_session = Session.initNewSession(tamagotchiName.getText(),codePin);
        Tamagotchi new_tama = null;
        String tamagotchi_img_path = null;

        switch (selectedType){
            case CAT:
                Cat cat = new Cat();
                cat.initNewTamagotchi();
                new_tama = cat;
                tamagotchi_img_path = "images/CAT.png";
                break;
            case DOG:
                Dog dog = new Dog();
                dog.initNewTamagotchi();
                new_tama = dog;
                tamagotchi_img_path = "images/DOG.png";
                break;
            case RABBIT:
                Rabbit rabbit = new Rabbit();
                rabbit.initNewTamagotchi();
                new_tama = rabbit;
                tamagotchi_img_path = "images/RABBIT.png";
                break;
            case TURTLE:
                Turtle turtle = new Turtle();
                turtle.initNewTamagotchi();
                new_tama = turtle;
                tamagotchi_img_path = "images/TURTLE.png";
                break;
            case ROBOT:
                Robot robot = new Robot();
                robot.initNewTamagotchi();
                new_tama = robot;
                tamagotchi_img_path = "images/ROBOT.png";
                break;
            case VOITURE:
                Voiture voiture = new Voiture();
                voiture.initNewTamagotchi();
                new_tama = voiture;
                tamagotchi_img_path = "images/VOITURE.png";
                break;
            default:
                break;
            
        }
        
        new_tama_session.setTamagotchiImgPath(tamagotchi_img_path);
        new_tama.setSession(new_tama_session);
        JsonDatabase.createNewSession(new_tama);

        // change screen
        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/home2.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(e ->{JsonDatabase.saveExistingSession();});
            stage.show();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}