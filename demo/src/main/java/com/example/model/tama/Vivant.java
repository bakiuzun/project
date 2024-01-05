package com.example.model.tama;



import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public abstract class Vivant extends Tamagotchi {

    protected Integer hunger;
    protected Integer tiredness;
    protected Integer hygiene;
    protected Integer mood;
    protected Integer weight;

    protected int delta_hunger;
    protected int delta_tiredness;
    protected int delta_hygiene;
    protected int delta_mood;
    protected int delta_weight;




    public void init_new_tamagothi(){
        this.lieuActuel = new Lieu(NomLieu.HOME);
        super.init_new_tamagothi();
        
        this.hunger = ActionConstant.HUNGER_MAX;
        this.tiredness = ActionConstant.TIREDNESS_MAX;
        this.hygiene = ActionConstant.HYGIENE_MAX;
        this.mood = ActionConstant.MOOD_MAX;

        this.delta_hunger = ActionConstant.BASE_DELTA;
        this.delta_tiredness = ActionConstant.BASE_DELTA;
        this.delta_hygiene = ActionConstant.BASE_DELTA;
        this.delta_mood = ActionConstant.BASE_DELTA;
        this.delta_weight = ActionConstant.BASE_DELTA;

        // le poid et init en fonction du Vivant dans les sous-classes
    }

    public void addNeighbord(){
        switch (this.lieuActuel.getNomLieu()) {
            case HOME:
                this.lieuActuel.addVoisin(NomLieu.GARDEN);
                this.lieuActuel.addVoisin(NomLieu.BATHROOM);
                this.lieuActuel.addVoisin(NomLieu.KITCHEN);
                this.lieuActuel.addVoisin(NomLieu.BEDROOM);
                break;
            case BATHROOM:
                this.lieuActuel.addVoisin(NomLieu.HOME);
                this.lieuActuel.addVoisin(NomLieu.TOILET);
                break;
            case GARDEN:
                this.lieuActuel.addVoisin(NomLieu.HOME);
                break;
            case KITCHEN:
                this.lieuActuel.addVoisin(NomLieu.HOME);
                this.lieuActuel.addVoisin(NomLieu.BEDROOM);
                break;
            case TOILET:
                this.lieuActuel.addVoisin(NomLieu.BATHROOM);
                break;
            case BEDROOM:
                this.lieuActuel.addVoisin(NomLieu.HOME);
                this.lieuActuel.addVoisin(NomLieu.KITCHEN);
                break;
            default:
                break;
        }
    }


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){
        updateHunger();
        updateWeight();
        updateTiredness();
        updateMood();
        updateHygiene();

        replace_new_attributes_values();

    }

    private void updateWeight(){

        double hunger_diff_max = (double) this.hunger / ActionConstant.HUNGER_MAX;
        int reduce_weight = 0;

        if (hunger_diff_max < 0.1){reduce_weight = 5; }
        else if (hunger_diff_max < 0.2){reduce_weight = 3; }
        else if (hunger_diff_max < 0.3){reduce_weight = 1; }
        

        this.weight = Math.max(this.weight - reduce_weight,0);
    }
    private void updateHunger(){this.hunger = Math.max(this.hunger-delta_hunger,0);}

    private void updateTiredness(){this.tiredness =  Math.max(this.tiredness-delta_tiredness,0);}

    private void updateMood(){this.mood =  Math.max(this.mood-delta_mood,0);}

    private void updateHygiene(){this.hygiene =  Math.max(this.hygiene-delta_hygiene,0);}


    public void replace_new_attributes_values(){
        attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.hunger));
        attributes.replace(AttributeConstant.TIREDNESS, String.valueOf(this.tiredness));
        attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        attributes.replace(AttributeConstant.MOOD, String.valueOf(this.mood));
        attributes.replace(AttributeConstant.WEIGHT, String.valueOf(this.weight));

    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put(AttributeConstant.HUNGER, String.valueOf(this.hunger));
        attributes.put(AttributeConstant.TIREDNESS, String.valueOf(this.tiredness));
        attributes.put(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        attributes.put(AttributeConstant.MOOD, String.valueOf(this.mood));
        attributes.put(AttributeConstant.WEIGHT, String.valueOf(this.weight));
    }
    

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        
        this.weight  = Integer.parseInt((String) tama.get(AttributeConstant.WEIGHT));
        this.hygiene = Integer.parseInt((String) tama.get(AttributeConstant.HYGIENE));
        this.tiredness  = Integer.parseInt((String) tama.get(AttributeConstant.TIREDNESS));
        this.hunger = Integer.parseInt((String) tama.get(AttributeConstant.HUNGER));
        this.mood = Integer.parseInt((String) tama.get(AttributeConstant.MOOD));
        

    }

    public void eating(){
    	double prendreWeight = this.hunger/100.0;
    	if(prendreWeight>0.6) {
    		this.weight += ActionConstant.KILOMAX; 
    	}
    	else if(prendreWeight>0.4){
    		this.weight += ActionConstant.KILOMAX/2; 
    	}
        this.hunger = Math.min(this.hunger + ActionConstant.EATING, ActionConstant.HUNGER_MAX);
    	attributes.replace(AttributeConstant.WEIGHT, String.valueOf(this.weight));
    	attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.hunger));

    }

    public void washing(){
        this.hygiene = Math.min(this.hygiene + ActionConstant.WASHING_HYGIENE, ActionConstant.HYGIENE_MAX);
    }

    public void playing(){
        this.mood = Math.min(this.mood + ActionConstant.PLAYING, ActionConstant.MOOD_MAX);
    }
    
    public void sleeping() {
        this.tiredness = Math.min(this.tiredness+ ActionConstant.SLEEPING, ActionConstant.TIREDNESS_MAX);
    }
    
    public void doingSport(){
    	this.hunger = Math.max(this.hunger + ActionConstant.DOING_SPORT_HUNGER, 0);
        //this.weight = Math.max(this.weight + ActionConstant.DOING_SPORT_WEIGHT, 0);
        this.mood = Math.min(this.mood + ActionConstant.DOING_SPORT_MOOD, ActionConstant.MOOD_MAX);
        this.tiredness = Math.max(this.tiredness + ActionConstant.DOING_SPORT_TIREDNESS, 0);
        updateWeight();

    }
    
    public void usingToilet(){
        this.mood = Math.min(this.mood + ActionConstant.USING_TOILET_MOOD, ActionConstant.MOOD_MAX);
        this.hygiene = Math.max(this.hygiene + ActionConstant.USING_TOILET_HYGIENE, 0);
    	attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        attributes.replace(AttributeConstant.MOOD, String.valueOf(this.mood));
    }


    public Integer getHunger() {return hunger;}
    public void setHunger(Integer hunger) {this.hunger = hunger;}


    public Integer getTiredness() {return tiredness;}
    public void setTiredness(Integer tiredness) {this.tiredness = tiredness;}


    public Integer getHygiene() {return hygiene;}
    public void setHygiene(Integer hygiene) {this.hygiene = hygiene;}


    public Integer getMood() {return mood;}
    public void setMood(Integer mood) {this.mood = mood;}


    public Integer getWeight() {return weight;}
    public void setWeight(Integer weight) {this.weight = weight;}


    private String printHunger(){
        double res = (double) this.hunger / ActionConstant.HUNGER_MAX;
        
        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.VIVANT_HUNGER_80;
        }
        if (res>= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.VIVANT_HUNGER_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.VIVANT_HUNGER_40;
        }
        if (res >= 0.2) {
            this.mood = Math.max(this.mood - 15, 0);
            this.tiredness = Math.max(this.tiredness - 5, 0);
            this.reduce_life_by += -10;
            return AttributeConstant.VIVANT_HUNGER_20;
        }
        this.mood =  Math.max(this.mood - 20, 0);
        this.tiredness =  Math.max(this.tiredness - 20, 0);
        this.reduce_life_by += -15;
        return AttributeConstant.VIVANT_HUNGER_0;
    }


    private String printTiredness(){

        double res = (double) this.tiredness / ActionConstant.TIREDNESS_MAX;
        
        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.VIVANT_TIREDNESS_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.VIVANT_TIREDNESS_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.VIVANT_TIREDNESS_40;
        }
        if (res >= 0.2) {
            this.mood = Math.max(this.mood - 10, 0);
            this.reduce_life_by += -3;
            return AttributeConstant.VIVANT_TIREDNESS_20;
        }
        this.mood = Math.max(this.mood - 20, 0);
        this.reduce_life_by += -5;
        return AttributeConstant.VIVANT_TIREDNESS_0;
        
    }

    private String printMood(){
        double res = (double) this.mood / ActionConstant.MOOD_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.VIVANT_MOOD_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.VIVANT_MOOD_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.VIVANT_MOOD_40;
        }
        if (res >= 0.2) {
            this.tiredness = Math.max(this.tiredness - 10, 0);
            this.reduce_life_by += -3;
            return AttributeConstant.VIVANT_MOOD_20;
        }
        this.tiredness = Math.max(this.tiredness - 20, 0);
        this.reduce_life_by += -5;
        return AttributeConstant.VIVANT_MOOD_0;
        
    }

    private String printHygiene(){
        double res = (double) this.hygiene  / ActionConstant.HYGIENE_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.VIVANT_HYGIENE_80;
        }
        if (res>= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.VIVANT_HYGIENE_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.VIVANT_HYGIENE_40;
        }
        if (res >= 0.2) {
            this.reduce_life_by += -5;
            return AttributeConstant.VIVANT_HYGIENE_20;
        }
        this.reduce_life_by += -10;
        return AttributeConstant.VIVANT_HYGIENE_0;
    }

    public HashMap<String,String> printAttributes(boolean update_life){
        
        
        HashMap<String,String> res =  new HashMap<>();
        
        res.put(AttributeConstant.HUNGER, printHunger());
        res.put(AttributeConstant.WEIGHT,this.weight + "kg");
        res.put(AttributeConstant.TIREDNESS,printTiredness());
        res.put(AttributeConstant.MOOD,printMood());
        res.put(AttributeConstant.HYGIENE,printHygiene());

        if (update_life){super.updateState(); } // this will change the life of the tamagotchi
        else {this.reduce_life_by = 0;}
        res.putAll(super.printAttributes(update_life));
        this.replace_new_attributes_values();

        return res;
    }


}
