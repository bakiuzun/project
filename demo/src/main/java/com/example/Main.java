package com.example;
import java.io.IOException;

import com.example.model.JsonDatabase;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Main extends Application {

    public static void main(String[] args) {
        JsonDatabase.initializeDatabase();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //JsonDatabase.getAllSession();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        /* 
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setTitle("JavaFX Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    @FXML
    private void createNewParty(ActionEvent event) {
        // Implement the logic for creating a new party here

        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/new_party.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("/new_party.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void continueParty(ActionEvent event) {
        
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/continue_party.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Contparty.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quit() {
        System.out.println("Quit");
        Platform.exit();
        
        // Implement the logic for quitting the application here
    }
}

