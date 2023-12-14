package com.example.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.model.JsonDatabase;
import com.example.model.Session;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ContinuePartyController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Session> tableView;

    ArrayList<Session> allSessions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allSessions = JsonDatabase.getAllSession();
        // Assuming `allSessions` is populated with data
        for (Session session : allSessions) {
            TableColumn<Session, String> tableColumn = new TableColumn<>(session.getNom_donner_tamagotchi());

  

            tableColumn.setCellValueFactory(data -> {
                return new SimpleStringProperty("MON TAMA");
            });
            

            tableView.getColumns().add(tableColumn);
            tableView.getItems().add(session);
        }
        //tableView.getItems().addAll(allSessions);
    }
    
}

