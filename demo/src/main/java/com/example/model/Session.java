package com.example.model;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Session {
    private int id;
    private Time tempJeu;
    private Time dateCreation;
    private Time dateDerniereConnexion; 


    public Session(int id,Time tempJeu,Time dateCreation,Time dateDerniereConnexion){
        this.id = id;
        this.tempJeu = tempJeu;
        this.dateCreation = dateCreation;
        this.dateDerniereConnexion = dateDerniereConnexion;
    }
    
    
    public static Session init_new_session(){

        return null;
    }
    public void getMaxID(){
        // get max from json file 
    }


    public Map<String,String>  getAttributes(){

        Map<String,String> attr = new HashMap<>();

        attr.put("id", String.valueOf(id));
        attr.put("time", tempJeu.toString());
        attr.put("dateCreation", dateCreation.toString());
        attr.put("dateDerniereConnexion", dateDerniereConnexion.toString());

        return attr;
    }

    
}
