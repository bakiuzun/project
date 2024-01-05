package com.example.model.tama.tamaNonVivant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Voiture extends NonVivant {

    public Voiture(){
        
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.VOITURE;

        super.init_new_tamagothi();
        super.addAttributes();
        loadAction();
    }
    
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    private void updateFromLastConnexion(){
        long last_connexion = getMaSessions().getDateDerniereConnexion();
        for(int i=0;i<(((LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond()-last_connexion)/ActionConstant.DELTA_TIME));i++){
            updateState();
            printAttributes(true);
        }

    }

    public void updateState(){
        delta_battery = ActionConstant.DELTA_BATTERY_CAR;
        delta_oil = ActionConstant.DELTA_OIL_CAR;
        delta_temperature = ActionConstant.DELTA_TEMPERATURE_CAR;
        delta_rust = ActionConstant.DELTA_RUST_CAR;
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        
        switch(getLieuActuel().getNomLieu()){
            case ROAD:
                actions.put(AttributeConstant.ACTION_BATTERING_CAR, this::battering);
            break;
            case GAS_STATION:
                actions.put(AttributeConstant.ACTION_OILING_CAR, this::oiling);
            break;
            case GARAGE:
                actions.put(AttributeConstant.ACTION_COOLING_CAR, this::cooling);
            break;
            case WASHING_STATION:
                actions.put(AttributeConstant.ACTION_CLEANING_CAR, this::cleaning);
            break;
            default:
                break;
        } 
    }

    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}