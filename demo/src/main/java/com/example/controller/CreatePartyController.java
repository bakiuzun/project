package com.example.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.model.Dog;
import com.example.model.*;
import com.example.model.JsonDatabase;
import com.example.model.Session;
import com.example.model.TypeTamagotchi;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreatePartyController {
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
    public void initialize() {
    
        tamagotchiType.setItems(FXCollections.observableArrayList(Arrays.stream(TypeTamagotchi.values())
            .map(type -> type.name())
            .collect(Collectors.toList())));

        tamagotchiType.setValue(TypeTamagotchi.CAT.name());
        selectedType = TypeTamagotchi.CAT;
        
        addListener();
        
    }

    private void addListener(){
        tamagotchiType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {selectedType = TypeTamagotchi.valueOf(newValue);});

    }

    @FXML
    private void createParty() {

        Session new_tama_session = Session.init_new_session(tamagotchiName.getText(), Integer.valueOf(this.pinCode.getText()));
        switch (selectedType){
            case CAT:
                Cat cat = new Cat();
                cat.init_new_tamagothi();
                cat.setSession(new_tama_session);
                JsonDatabase.create_new_session(cat, new_tama_session);
            case DOG:
                Dog dog = new Dog();
                dog.init_new_tamagothi();
                dog.setSession(new_tama_session);
                JsonDatabase.create_new_session(dog,new_tama_session);
            case LAPIN:
                Lapin lapin = new Lapin();
                lapin.init_new_tamagothi();
                lapin.setSession(new_tama_session);
                JsonDatabase.create_new_session(lapin, new_tama_session);
            case ROBOT:
                Robot robot = new Robot();
                robot.init_new_tamagothi();
                robot.setSession(new_tama_session);
                JsonDatabase.create_new_session(robot, new_tama_session);
            default:
                break;
            
        }
        System.out.println("New Party");
    }
}