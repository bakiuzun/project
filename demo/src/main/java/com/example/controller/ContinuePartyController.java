package com.example.controller;

import java.util.ArrayList;

import com.example.model.JsonDatabase;
import com.example.model.Session;

import javafx.fxml.FXML;

public class ContinuePartyController {


    ArrayList<Session> allSessions;


    @FXML
    public void initialize() {
        
        allSessions = JsonDatabase.getAllSession();
        for (Session x: allSessions){
            System.out.println("TIME " + x.getTamagotchi_img_path());
        }
    }
    
}
