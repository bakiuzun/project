package com.example.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.security.auth.callback.Callback;

import com.example.model.JsonDatabase;
import com.example.model.Session;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ContinuePartyController implements Initializable {

    public class SessionListViewCell extends ListCell<Session> {

        private SessionHBox sessionHBox;
        
        @Override
        public void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);
        
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (sessionHBox == null) {
                    sessionHBox = new SessionHBox(session);
                } 
                setGraphic(sessionHBox);
            }
        }
    }

    public class SessionHBox extends HBox {

        Label nameLabel;
        ImageView imageView;
        Button continuePartyButton;

        public SessionHBox(Session session) {
            
            imageView = new ImageView();
            imageView.setImage(new Image(session.getTamagotchi_img_path()));
            imageView.setFitWidth(100); // Set maximum width to 100 pixels
            imageView.setFitHeight(100);

            nameLabel = new Label(session.getNom_donner_tamagotchi());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-family: Arial;"); // Change font size and family
            
            continuePartyButton = new Button("Continue Party");

            this.getChildren().addAll(imageView, nameLabel,continuePartyButton);
            this.setSpacing(10);
            this.setAlignment(Pos.CENTER_LEFT);
        }

    }

    ArrayList<Session> allSessions;

    @FXML
    AnchorPane rootLayout;

    
    ListView<Session> sessionListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       
        allSessions = JsonDatabase.getAllSession();
            // Set up the ListView to display sessions
        sessionListView = new ListView<>();
        
        sessionListView.setCellFactory(listView -> new SessionListViewCell());

        for (Session session : allSessions) {
            // Create a custom cell to display the session image and name
            sessionListView.getItems().add(session);
        }

        sessionListView.prefHeightProperty().bind(rootLayout.prefHeightProperty());
        sessionListView.prefWidthProperty().bind(rootLayout.prefWidthProperty());
        rootLayout.getChildren().add(sessionListView);
       
    }    
}