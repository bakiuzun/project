package com.example.model.tama.tamaVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Rabbit extends Vivant {

    public Rabbit(){
        loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.RABBIT;
        this.weight = ActionConstant.RABBIT_WEIGHT; // Attribut Vivant

        super.init_new_tamagothi();
        super.addAttributes();
    }

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
    }

    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_RABBIT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_RABBIT;  
        delta_mood = ActionConstant.DELTA_MOOD_RABBIT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_RABBIT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_RABBIT; 
        super.updateState();
    }

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
        }        
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }

    public  void loadTamagotchiInfo(){
        
    }

}
