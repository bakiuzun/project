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
 * This class is the model for the Robot tamagotchi
 * 
 */
public class Robot extends NonVivant {

    public Robot(){}

    /*
     * This method initializes a new Robot tamagotchi
     * 
     * It calls the NonVivant init_new_tamagothi method
     * It adds the Robot attributes
     * It loads the Robot actions
     * 
     */
    public void initNewTamagotchi(){

        this.typeTamagotchi = TypeTamagotchi.ROBOT;

        super.initNewTamagotchi();
        super.addAttributes();
        loadAction();
    }

    /*
     * This method loads a Robot tamagotchi from the database
     * 
     * It calls the NonVivant loadTamaFromDatabase method
     * It adds the Robot attributes
     * It loads the Robot actions
     * It updates the Robot state from the last connexion
     * 
     * @param tama : the Robot tamagotchi to load
     * 
     */
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Robot state from the last connexion
     * 
     * It calls the NonVivant updateState method
     * It updates the Robot state for each time unit passed since the last connexion
     * It prints the Robot attributes
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
     * This method updates the Robot state
     * 
     * It calls the NonVivant updateState method
     * It updates the Robot state
     */
    public void updateState(){
        deltaBattery = ActionConstant.DELTA_BATTERY_ROBOT;
        deltaOil = ActionConstant.DELTA_OIL_ROBOT;
        deltaTemperature = ActionConstant.DELTA_TEMPERATURE_ROBOT;
        deltaRust = ActionConstant.DELTA_RUST_ROBOT;
        super.updateState();
    }

    /*  
     * This method loads the Robot actions
     * 
     * It calls the NonVivant loadAction method
     * It adds the Robot actions according to the Robot current location
     * 
     */
    public void loadAction(){
        super.loadAction();
        
        switch(getCurrentPlace().getNomLieu()){
            case GAS_STATION:
                actions.put(AttributeConstant.ACTION_OILING_ROBOT, this::oiling);
            break;
            case GARAGE:
                actions.put(AttributeConstant.ACTION_BATTERING_ROBOT, this::battering);
                actions.put(AttributeConstant.ACTION_COOLING_ROBOT, this::cooling);
            break;
            case WASHING_STATION:
                actions.put(AttributeConstant.ACTION_CLEANING_ROBOT, this::cleaning);
            break;
            default:
                break;
        }   
    }

    /*
     * This mehod prints the Robot attributes
     * 
     * It calls the NonVivant printAttributes method
     * 
     * @param update_life : boolean to know if the Robot life must be updated
     * @return res : the Robot attributes
     */
    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}
    
