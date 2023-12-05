package com.example.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.model.*;

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
        init_tamagotchiComboBox();
        
        // allow only digit
        pinCode.setTextFormatter(pinCode_helper());

        // set create button to false at the start 
        updateCreatePartyState(null);
        
    }

    private void init_tamagotchiComboBox(){
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
    private void createParty(ActionEvent event) {

        Integer codePin = this.pinCode.getText().length() != 0 ? Integer.valueOf(this.pinCode.getText()): -1;
        
        Session new_tama_session = Session.init_new_session(tamagotchiName.getText(),codePin);
        Tamagotchi new_tama = null;

        switch (selectedType){
            case CAT:
                Cat cat = new Cat();
                cat.init_new_tamagothi();
                new_tama = cat;
                break;
            case DOG:
                Dog dog = new Dog();
                dog.init_new_tamagothi();
                new_tama = dog;
                break;
            case RABBIT:
                Rabbit lapin = new Rabbit();
                lapin.init_new_tamagothi();
                new_tama = lapin;
                break;
            case TURTLE:
                Turtle turtle = new Turtle();
                turtle.init_new_tamagothi();
                new_tama = turtle;
                break;
            case ROBOT:
                Robot robot = new Robot();
                robot.init_new_tamagothi();
                new_tama = robot;
                break;
            case VOITURE:
                Voiture voiture = new Voiture();
                voiture.init_new_tamagothi();
                new_tama = voiture;
                break;
            default:
                break;
            
        }

        new_tama.setSession(new_tama_session);
        JsonDatabase.create_new_session(new_tama);


        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/connected.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}