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
 * This class is the model for the Turtle tamagotchi
 * 
 */
public class Turtle extends Vivant {
    

    public Turtle(){}

    /*
     * This method initializes a new Turtle tamagotchi
     * 
     * It calls the Vivant init_new_tamagothi method
     * It adds the Turtle attributes
     * It loads the Turtle actions
     * 
     */
    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.TURTLE;
        this.weight = ActionConstant.TURTLE_WEIGHT; // Attribut Vivant

        super.init_new_tamagothi();
        super.addAttributes();  
        loadAction();
    }

    /*
     * This method loads a Turtle tamagotchi from the database
     * 
     * It calls the Vivant loadTamaFromDatabase method
     * It adds the Turtle attributes
     * It loads the Turtle actions
     * It updates the Turtle state from the last connexion
     * 
     * @param tama : the Turtle tamagotchi to load
     * 
     */
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();
        updateFromLastConnexion();
    }

    /*
     * This method updates the Turtle state from the last connexion
     * 
     * It calls the Vivant updateState method
     * It updates the Turtle state for each time unit passed since the last connexion
     * It prints the Turtle attributes
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
     * This method updates the Turtle state
     * 
     * It calls the Vivant updateState method
     * It updates the Turtle state
     */
    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_TURTLE; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_TURTLE;  
        delta_mood = ActionConstant.DELTA_MOOD_TURTLE; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_TURTLE;  
        delta_weight = ActionConstant.DELTA_WEIGHT_TURTLE; 
        super.updateState();
    }

    /*  
     * This method loads the Turtle actions
     * 
     * It calls the Vivant loadAction method
     * It adds the Turtle actions according to the Turtle current loturtleion
     * 
     */
    public void loadAction(){
        super.loadAction();
        switch(getLieuActuel().getNomLieu()){
            case HOME:
                actions.put(AttributeConstant.ACTION_PLAYING_TURTLE, this::playing);
            break;
            case BATHROOM:
                actions.put(AttributeConstant.ACTION_WASHING_TURTLE, this::washing);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_DOING_SPORT_TURTLE, this::doingSport);
            break;
            case KITCHEN:
                actions.put(AttributeConstant.ACTION_EATING_TURTLE, this::eating);
            break;
            case TOILET:
                actions.put(AttributeConstant.ACTION_USING_TOILET_TURTLE, this::usingToilet);
            break;
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_SLEEPING_TURTLE, this::sleeping);
            break;
            default:
                break;
        }       
    }

    /*
     * This mehod prints the Turtle attributes
     * 
     * It calls the Vivant printAttributes method
     * 
     * @param update_life : boolean to know if the Turtle life must be updated
     * @return res : the Turtle attributes
     */
   public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}
