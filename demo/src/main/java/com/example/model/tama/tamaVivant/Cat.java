package com.example.model.tama.tamaVivant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import org.json.simple.JSONObject;


import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

/*
 * This class is the model for the Cat tamagotchi
 * 
 */
public class Cat extends Vivant {

    public Cat(){}

    /*
     * This method initializes a new Cat tamagotchi
     * 
     * It calls the Vivant init_new_tamagothi method
     * It adds the Cat attributes
     * It loads the Cat actions
     * 
     */
    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.CAT;
        this.weight = ActionConstant.CAT_WEIGHT; // Attribut Vivant
        
        super.init_new_tamagothi();
        super.addAttributes();
        loadAction();
    }

    /*
     * This method loads a Cat tamagotchi from the database
     * 
     * It calls the Vivant loadTamaFromDatabase method
     * It adds the Cat attributes
     * It loads the Cat actions
     * It updates the Cat state from the last connexion
     * 
     * @param tama : the Cat tamagotchi to load
     * 
     */
    public void loadTamaFromDatabase(JSONObject tama){
        // attribute of the cat 
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Cat state from the last connexion
     * 
     * It calls the Vivant updateState method
     * It updates the Cat state for each time unit passed since the last connexion
     * It prints the Cat attributes
     * 
     */
    private void updateFromLastConnexion(){
        long last_connexion = getMaSessions().getDateDerniereConnexion();
        for(int i=0;i<(((LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond()-last_connexion)/ActionConstant.DELTA_TIME));i++){
            System.out.println("GIRDIM");
            updateState();
            printAttributes(true);
        }

    }

    /*
     * This method updates the Cat state
     * 
     * It calls the Vivant updateState method
     * It updates the Cat state
     */
    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_CAT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_CAT;  
        delta_mood = ActionConstant.DELTA_MOOD_CAT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_CAT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_CAT; 
        super.updateState();
    }

    /*  
     * This method loads the Cat actions
     * 
     * It calls the Vivant loadAction method
     * It adds the Cat actions according to the Cat current location
     * 
     */
    public void loadAction(){
        super.loadAction();
        
        switch(getLieuActuel().getNomLieu()){
            case HOME:
                actions.put(AttributeConstant.ACTION_PLAYING_CAT, this::playing);
            break;
            case BATHROOM:
                actions.put(AttributeConstant.ACTION_WASHING_CAT, this::washing);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_DOING_SPORT_CAT, this::doingSport);
            break;
            case KITCHEN:
                actions.put(AttributeConstant.ACTION_EATING_CAT, this::eating);
            break;
            case TOILET:
                actions.put(AttributeConstant.ACTION_USING_TOILET_CAT, this::usingToilet);
            break;
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_SLEEPING_CAT, this::sleeping);
            break;
            default:
                break;
        }
    }

    /*
     * This mehod prints the Cat attributes
     * 
     * It calls the Vivant printAttributes method
     * 
     * @param update_life : boolean to know if the Cat life must be updated
     * @return res : the Cat attributes
     */
    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }
    
}
