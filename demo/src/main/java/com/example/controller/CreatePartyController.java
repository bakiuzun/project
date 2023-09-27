package com.example.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.model.Dog;
import com.example.model.JsonDatabase;
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
        switch (selectedType){
            case CAT:
                break;
            case DOG:
                Dog dog = new Dog();
                dog.init_new_tamagothi();
                JsonDatabase.create_new_session(dog,TypeTamagotchi.DOG);
            case LAPIN:
                break;
            case ROBOT:
                break;
            default:
                break;
            
        }
        System.out.println("New Party");
    }
}