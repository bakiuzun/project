package com.example.model.tama.tamaVivant;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;

public class Rabbit extends Vivant {

    public Rabbit(){
        super.loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.RABBIT;
        this.weight = ActionConstant.RABBIT_WEIGHT; // Attribut Vivant
        super.init_new_tamagothi();
        super.addAttributes();

    }

    public void updateState(){
        System.out.println("RABBIT UDPATE HOLD ON ");
        delta_hunger = ActionConstant.DELTA_HUNGER_RABBIT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_RABBIT;  
        delta_mood = ActionConstant.DELTA_MOOD_RABBIT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_RABBIT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_RABBIT; 
        super.updateState();
    }

    public  void loadTamagotchiInfo(){
        
    }

}
