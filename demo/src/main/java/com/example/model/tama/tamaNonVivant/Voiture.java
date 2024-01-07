package com.example.model.tama.tamaNonVivant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

/*
 * This class is the model for the Voiture tamagotchi
 * 
 */
public class Voiture extends NonVivant {

    public Voiture(){}

    /*
     * This method initializes a new Voiture tamagotchi
     * 
     * It calls the NonVivant init_new_tamagothi method
     * It adds the Voiture attributes
     * It loads the Voiture actions
     * 
     */
    public void initNewTamagotchi(){

        this.typeTamagotchi = TypeTamagotchi.VOITURE;

        super.initNewTamagotchi();
        super.addAttributes();
        loadAction();
    }

    /*
     * This method loads a Voiture tamagotchi from the database
     * 
     * It calls the NonVivant loadTamaFromDatabase method
     * It adds the Voiture attributes
     * It loads the Voiture actions
     * It updates the Voiture state from the last connexion
     * 
     * @param tama : the Voiture tamagotchi to load
     * 
     */    
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Voiture state from the last connexion
     * 
     * It calls the NonVivant updateState method
     * It updates the Voiture state for each time unit passed since the last connexion
     * It prints the Voiture attributes
     * 
     */
    private void updateFromLastConnexion(){
        long last_connexion = getMySession().getLastConnectionDate();
        for(int i=0;i<(((LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond()-last_connexion)/ActionConstant.DELTA_TIME));i++){
            updateState();
            printAttributes(true);
        }

    }

    /*
     * This method updates the Voiture state
     * 
     * It calls the NonVivant updateState method
     * It updates the Voiture state
     */
    public void updateState(){
        deltaBattery = ActionConstant.DELTA_BATTERY_CAR;
        deltaOil = ActionConstant.DELTA_OIL_CAR;
        deltaTemperature = ActionConstant.DELTA_TEMPERATURE_CAR;
        deltaRust = ActionConstant.DELTA_RUST_CAR;
        super.updateState();
    }

    /*  
     * This method loads the Voiture actions
     * 
     * It calls the NonVivant loadAction method
     * It adds the Voiture actions according to the Voiture current location
     * 
     */
    public void loadAction(){
        super.loadAction();
        
        switch(getCurrentPlace().getNomLieu()){
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

    /*
     * This mehod prints the Voiture attributes
     * 
     * It calls the NonVivant printAttributes method
     * 
     * @param update_life : boolean to know if the Voiture life must be updated
     * @return res : the Voiture attributes
     */
    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}