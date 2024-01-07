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
    private long gameTime;
    private long creationDate;
    private long lastConnectionDate; 
    private String nameGivenToTamagotchi; 
    private Integer codePin; 
    private String tamagotchiImgPath;

    public Session(Integer id,long gameTime,long creationDate,long lastConnectionDate,Integer codePin,String nomTama){
        this.id = id;
        this.gameTime = gameTime;
        this.creationDate = creationDate;
        this.lastConnectionDate = lastConnectionDate;
        this.codePin = codePin;
        this.nameGivenToTamagotchi = nomTama;
    }
    
    
    public static Session initNewSession(String nom_tama,Integer codePin){
        

           // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the seconds since epoch (1970-01-01T00:00:00Z)
        long secondsSinceEpoch = LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond();//currentDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();

        Integer new_id = JsonDatabase.getFreeSessionID();
        
        long newCreationDate = secondsSinceEpoch;
        long newLastConnectionDate = secondsSinceEpoch;
        long differenceSeconds = newLastConnectionDate - newCreationDate;
        long differenceHours = differenceSeconds / 3600;
        
        Session new_session = new Session(new_id,differenceHours,newCreationDate,newLastConnectionDate,codePin,nom_tama);
        return new_session;
    }

    public Map<String,String>  getAttributes(){

        Map<String,String> attr = new HashMap<>();

        attr.put(AttributeConstant.ID, String.valueOf(id));
        attr.put(AttributeConstant.TOTAL_GAME_TIME,String.valueOf(gameTime));
        attr.put(AttributeConstant.CREATION_DATE, String.valueOf(creationDate));
        attr.put(AttributeConstant.LAST_CONNECTION, String.valueOf(lastConnectionDate));
        attr.put(AttributeConstant.TAMAGOTCHI_NAME, this.nameGivenToTamagotchi);
        attr.put(AttributeConstant.PIN, String.valueOf(codePin));
        attr.put(AttributeConstant.TAMAGOTCHI_IMG_PATH,tamagotchiImgPath);

        return attr;
    }


    public Integer getId() {
        return id;
    }


    public long getGameTime() {
        return gameTime;
    }


    public long getCreationDate() {
        return creationDate;
    }


    public long getLastConnectionDate() {
        return lastConnectionDate;
    }


    public String getNameGivenToTamagotchi() {
        return nameGivenToTamagotchi;
    }


    public int getCodePin() {
        return codePin;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }


    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }


    public void setLastConnectionDate(long lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }


    public void setNameGivenToTamagotchi(String nameGivenToTamagotchi) {
        this.nameGivenToTamagotchi = nameGivenToTamagotchi;
    }


    public void setCodePin(int codePin) {
        this.codePin = codePin;
    }


    public void setCodePin(Integer codePin) {
        this.codePin = codePin;
    }


    public String getTamagotchiImgPath() {
        return tamagotchiImgPath;
    }


    public void setTamagotchiImgPath(String tamagotchiImgPath) {
        this.tamagotchiImgPath = tamagotchiImgPath;
    }



    
}
