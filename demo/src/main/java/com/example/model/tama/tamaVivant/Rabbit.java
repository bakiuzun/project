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
 * This class is the model for the Rabbit tamagotchi
 * 
 */
public class Rabbit extends Vivant {

    public Rabbit(){}

    /*
     * This method initializes a new Rabbit tamagotchi
     * 
     * It calls the Vivant init_new_tamagothi method
     * It adds the Rabbit attributes
     * It loads the Rabbit actions
     * 
     */
    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.RABBIT;
        this.weight = ActionConstant.RABBIT_WEIGHT; // Attribut Vivant

        super.init_new_tamagothi();
        super.addAttributes();
        loadAction();
    }

    /*
     * This method loads a Rabbit tamagotchi from the database
     * 
     * It calls the Vivant loadTamaFromDatabase method
     * It adds the Rabbit attributes
     * It loads the Rabbit actions
     * It updates the Rabbit state from the last connexion
     * 
     * @param tama : the Rabbit tamagotchi to load
     * 
     */
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Rabbit state from the last connexion
     * 
     * It calls the Vivant updateState method
     * It updates the Rabbit state for each time unit passed since the last connexion
     * It prints the Rabbit attributes
     * 
     */
    private void updateFromLastConnexion(){
        long last_connexion = getMaSessions().getDateDerniereConnexion();
        for(int i=0;i<(((LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond()-last_connexion)/ActionConstant.DELTA_TIME));i++){
            updateState();
            printAttributes(true);
        }

    }

    /*
     * This method updates the Rabbit state
     * 
     * It calls the Vivant updateState method
     * It updates the Rabbit state
     */
    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_RABBIT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_RABBIT;  
        delta_mood = ActionConstant.DELTA_MOOD_RABBIT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_RABBIT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_RABBIT; 
        super.updateState();
    }

    /*  
     * This method loads the Rabbit actions
     * 
     * It calls the Vivant loadAction method
     * It adds the Rabbit actions according to the Rabbit current lorabbition
     * 
     */
    public void loadAction(){
        super.loadAction();
        
        switch(getLieuActuel().getNomLieu()){
            case HOME:
                actions.put(AttributeConstant.ACTION_PLAYING_RABBIT, this::playing);
            break;
            case BATHROOM:
                actions.put(AttributeConstant.ACTION_WASHING_RABBIT, this::washing);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_DOING_SPORT_RABBIT, this::doingSport);
            break;
            case KITCHEN:
                actions.put(AttributeConstant.ACTION_EATING_RABBIT, this::eating);
            break;
            case TOILET:
                actions.put(AttributeConstant.ACTION_USING_TOILET_RABBIT, this::usingToilet); 
            break;
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_SLEEPING_RABBIT, this::sleeping);
            break;
            default:
                break;
        }        
    }

    /*
     * This mehod prints the Rabbit attributes
     * 
     * It calls the Vivant printAttributes method
     * 
     * @param update_life : boolean to know if the Rabbit life must be updated
     * @return res : the Rabbit attributes
     */
    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }


}
