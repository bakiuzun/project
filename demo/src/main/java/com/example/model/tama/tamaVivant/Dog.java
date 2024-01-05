package com.example.model.tama.tamaVivant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Dog extends Vivant {
    

    public Dog(){       
    }

    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.DOG;
        this.weight = ActionConstant.DOG_WEIGHT; // Attribut Vivant

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
        delta_hunger = ActionConstant.DELTA_HUNGER_DOG; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_DOG;  
        delta_mood = ActionConstant.DELTA_MOOD_DOG; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_DOG;  
        delta_weight = ActionConstant.DELTA_WEIGHT_DOG; 
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();

        switch(getLieuActuel().getNomLieu()){
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

    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  super.printAttributes(update_life);
        
        return res;
    }

}
