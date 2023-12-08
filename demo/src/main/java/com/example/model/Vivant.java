package com.example.model;

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


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){

        this.hunger -= delta_hunger;
        this.tiredness -= delta_tiredness;
        this.mood -= delta_mood;
        this.hygiene -= delta_hygiene;

        replace_new_attributes_values();
        
        super.updateState();
    }


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
    

    public void eating(){
    	double prendreWeight = this.hunger/100;
    	if(prendreWeight>0.6) {
    		this.weight += ActionConstant.KILOMAX; 
    	}
    	else if(prendreWeight>0.4){
    		this.weight += ActionConstant.KILOMAX/2; 
    	}
    	attributes.replace(AttributeConstant.WEIGHT, String.valueOf(this.weight));
    	this.hunger = Math.min(this.hunger + ActionConstant.EATING, ActionConstant.HUNGER_MAX);
    	attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.hunger));
        super.updateState();
    }

    public void washing(){
        this.hygiene = Math.min(this.hygiene + ActionConstant.WASHING_HYGIENE, ActionConstant.HYGIENE_MAX);
    	attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        super.updateState();

    }

    public void playing(){
        this.mood = Math.min(this.mood + ActionConstant.PLAYING, ActionConstant.MOOD_MAX);
    	attributes.replace(AttributeConstant.MOOD, String.valueOf(this.mood));
        super.updateState();
        
    }
    
    public void sleeping() {
        this.tiredness = Math.min(this.tiredness+ ActionConstant.SLEEPING, ActionConstant.TIREDNESS_MAX);
    	attributes.replace(AttributeConstant.TIREDNESS, String.valueOf(this.tiredness));
        super.updateState();
    	
    }
    
    public void doingSport(){
    	this.hunger += ActionConstant.FAIREDUSPORTFAIM;
    	attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.hunger));
        super.updateState();
    }
    
    public void usingToilet(){
        this.hygiene = Math.max(this.hygiene + ActionConstant.USING_TOILET_HYGIENE, 0);
    	attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        super.updateState();
    }


}
