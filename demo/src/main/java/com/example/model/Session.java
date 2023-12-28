package com.example.model;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.model.utils.AttributeConstant;

public class Session {
    private Integer id;
    private long tempJeu;
    private long dateCreation;
    private long dateDerniereConnexion; 
    private String nom_donner_tamagotchi; 
    private Integer codePin; 
    private String tamagotchi_img_path;

    public Session(Integer id,long tempJeu,long dateCreation,long dateDerniereConnexion,Integer codePin,String nomTama){
        this.id = id;
        this.tempJeu = tempJeu;
        this.dateCreation = dateCreation;
        this.dateDerniereConnexion = dateDerniereConnexion;
        this.codePin = codePin;
        this.nom_donner_tamagotchi = nomTama;
    }
    
    
    public static Session init_new_session(String nom_tama,Integer codePin){
        

           // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the seconds since epoch (1970-01-01T00:00:00Z)
        long secondsSinceEpoch = LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond();//currentDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();

        Integer new_id = JsonDatabase.getFreeSessionID();
        
        long new_dateCreation = secondsSinceEpoch;
        long new_dateDerniereConnexion = secondsSinceEpoch;
        long differenceSeconds = new_dateDerniereConnexion - new_dateCreation;
        long differenceHours = differenceSeconds / 3600;
        
        Session new_session = new Session(new_id,differenceHours,new_dateCreation,new_dateDerniereConnexion,codePin,nom_tama);
        return new_session;
    }

    public Map<String,String>  getAttributes(){

        Map<String,String> attr = new HashMap<>();

        attr.put(AttributeConstant.ID, String.valueOf(id));
        attr.put(AttributeConstant.TOTAL_GAME_TIME,String.valueOf(tempJeu));
        attr.put(AttributeConstant.CREATION_DATE, String.valueOf(dateCreation));
        attr.put(AttributeConstant.LAST_CONNECTION, String.valueOf(dateDerniereConnexion));
        attr.put(AttributeConstant.TAMAGOTCHI_NAME, this.nom_donner_tamagotchi);
        attr.put(AttributeConstant.PIN, String.valueOf(codePin));
        attr.put(AttributeConstant.TAMAGOTCHI_IMG_PATH,tamagotchi_img_path);

        return attr;
    }


    public Integer getId() {
        return id;
    }


    public long getTempJeu() {
        return tempJeu;
    }


    public long getDateCreation() {
        return dateCreation;
    }


    public long getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }


    public String getNom_donner_tamagotchi() {
        return nom_donner_tamagotchi;
    }


    public int getCodePin() {
        return codePin;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setTempJeu(long tempJeu) {
        this.tempJeu = tempJeu;
    }


    public void setDateCreation(long dateCreation) {
        this.dateCreation = dateCreation;
    }


    public void setDateDerniereConnexion(long dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }


    public void setNom_donner_tamagotchi(String nom_donner_tamagotchi) {
        this.nom_donner_tamagotchi = nom_donner_tamagotchi;
    }


    public void setCodePin(int codePin) {
        this.codePin = codePin;
    }


    public void setCodePin(Integer codePin) {
        this.codePin = codePin;
    }


    public String getTamagotchi_img_path() {
        return tamagotchi_img_path;
    }


    public void setTamagotchi_img_path(String tamagotchi_img_path) {
        this.tamagotchi_img_path = tamagotchi_img_path;
    }



    
}
