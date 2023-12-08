package com.example.model;

public class Dog extends Vivant {
    

    public Dog(){       
        super.loadAction();
    }



    public void init_new_tamagothi(){
        
        this.typeTamagotchi = TypeTamagotchi.DOG;
        
        this.weight = ActionConstant.DOG_WEIGHT; // Attribut Vivant
        super.init_new_tamagothi();
        super.addAttributes();

        
    }

    public void updateState(){
        System.out.println("DOG UDPATE HOLD ON ");
        delta_hunger = ActionConstant.DELTA_HUNGER_DOG; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_DOG;  
        delta_mood = ActionConstant.DELTA_MOOD_DOG; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_DOG;  
        delta_weight = ActionConstant.DELTA_WEIGHT_DOG; 
        super.updateState();
    }


    public  void loadTamagotchiInfo(){
        
    }
}
