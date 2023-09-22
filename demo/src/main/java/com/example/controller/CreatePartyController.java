package com.example.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.model.TypeEspece;

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

    @FXML TextField tamagotchiName;

    @FXML
    public void initialize() {
    
        tamagotchiType.setItems(FXCollections.observableArrayList(Arrays.stream(TypeEspece.values())
            .map(type -> type.name())
            .collect(Collectors.toList())));

        tamagotchiType.setValue(TypeEspece.CAT.name());
        
        addListener();
        //tamagotchiType.setItems(FXCollections.observableArrayList("Cat", "Robot", "Dog"));
    }

    private void addListener(){
        tamagotchiType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            TypeEspece selectedType = TypeEspece.valueOf(newValue);
            switch (selectedType) {
                case CAT:
                    System.out.println("CATTTTTTT");
                    //tamagotchiImage.setImage(new Image("/path/to/cat/image.png"));
                case ROBOT:
                    System.out.println("ROBOT");
                    //tamagotchiImage.setImage(new Image("/path/to/robot/image.png"));
                case DOG:
                    System.out.println("DOG");
                    //tamagotchiImage.setImage(new Image("/path/to/dog/image.png"));

                case LAPIN:
                    System.out.println("LAPIN");
                // Add more cases here for other types
            }
        });

    }

    @FXML
    private void createParty() {
        System.out.println("New Party");
    }
}