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
 * This class is the model for the Dog tamagotchi
 * 
 */
public class Dog extends Vivant {
    

    public Dog(){}

    /*
     * This method initializes a new Dog tamagotchi
     * 
     * It calls the Vivant init_new_tamagothi method
     * It adds the Dog attributes
     * It loads the Dog actions
     * 
     */
    public void initNewTamagotchi(){
        this.typeTamagotchi = TypeTamagotchi.DOG;
        this.weight = ActionConstant.DOG_WEIGHT; // Attribut Vivant

        super.initNewTamagotchi();
        super.addAttributes();
        loadAction();
    }

    /*
     * This method loads a Dog tamagotchi from the database
     * 
     * It calls the Vivant loadTamaFromDatabase method
     * It adds the Dog attributes
     * It loads the Dog actions
     * It updates the Dog state from the last connexion
     * 
     * @param tama : the Dog tamagotchi to load
     * 
     */
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Dog state from the last connexion
     * 
     * It calls the Vivant updateState method
     * It updates the Dog state for each time unit passed since the last connexion
     * It prints the Dog attributes
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
     * This method updates the Dog state
     * 
     * It calls the Vivant updateState method
     * It updates the Dog state
     */
    public void updateState(){
        deltaHunger = ActionConstant.DELTA_HUNGER_DOG; 
        deltaHygiene = ActionConstant.DELTA_HYGIENE_DOG;  
        deltaMood = ActionConstant.DELTA_MOOD_DOG; 
        deltaTiredness = ActionConstant.DELTA_TIREDNESS_DOG;  
        deltaWeight = ActionConstant.DELTA_WEIGHT_DOG; 
        super.updateState();
    }

    /*  
     * This method loads the Dog actions
     * 
     * It calls the Vivant loadAction method
     * It adds the Dog actions according to the Dog current lodogion
     * 
     */
    public void loadAction(){
        super.loadAction();

        switch(getCurrentPlace().getNomLieu()){
            case HOME:
                actions.put(AttributeConstant.ACTION_PLAYING_DOG, this::playing);
            break;
            case BATHROOM:
                actions.put(AttributeConstant.ACTION_WASHING_DOG, this::washing);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_DOING_SPORT_DOG, this::doingSport);
            break;
            case KITCHEN:
                actions.put(AttributeConstant.ACTION_EATING_DOG, this::eating);
            break;
            case TOILET:
                actions.put(AttributeConstant.ACTION_USING_TOILET_DOG, this::usingToilet);
            break;
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_SLEEPING_DOG, this::sleeping);
            break;
            default:
                break;
        }   
    }

    /*
     * This mehod prints the Dog attributes
     * 
     * It calls the Vivant printAttributes method
     * 
     * @param update_life : boolean to know if the Dog life must be updated
     * @return res : the Dog attributes
     */
    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}
