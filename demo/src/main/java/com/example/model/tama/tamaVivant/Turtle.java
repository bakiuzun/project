package com.example.model.tama.tamaVivant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Turtle extends Vivant {
    

    public Turtle(){}


    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.TURTLE;
        this.weight = ActionConstant.TURTLE_WEIGHT; // Attribut Vivant

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
            printAttributes();
        }

    }

    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_TURTLE; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_TURTLE;  
        delta_mood = ActionConstant.DELTA_MOOD_TURTLE; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_TURTLE;  
        delta_weight = ActionConstant.DELTA_WEIGHT_TURTLE; 
        super.updateState();
    }

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

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }


}
