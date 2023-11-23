package com.example.model;

public abstract class Vivant extends Tamagotchi {

    protected Integer faim;
    protected Integer fatigue;
    protected Integer hygiene;
    protected Integer humeur;
    protected Integer poids;

    protected int delta_faim;
    protected int delta_fatigue;
    protected int delta_hygiene;
    protected int delta_humeur;
    protected int delta_poid;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.faim = 100;
        this.fatigue = 100;
        this.hygiene = 100;
        this.humeur = 100;

        this.delta_faim = 2;
        this.delta_fatigue = 2;
        this.delta_hygiene = 2;
        this.delta_humeur = 2;
        this.delta_poid = 2;
        
        // le poid et init en fonction du Vivant dans les sous-classes
    }


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){

        this.faim -= delta_faim;
        this.fatigue -= delta_fatigue;
        this.humeur -= delta_humeur;
        this.hygiene -= delta_hygiene;

        replace_new_attributes_values();
        
        super.updateState();
    }


    public void replace_new_attributes_values(){
        attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.faim));
        attributes.replace(AttributeConstant.TIREDNESS, String.valueOf(this.fatigue));
        attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        attributes.replace(AttributeConstant.MOOD, String.valueOf(this.humeur));
        attributes.replace(AttributeConstant.WEIGHT, String.valueOf(this.poids));

    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put(AttributeConstant.HUNGER, String.valueOf(this.faim));
        attributes.put(AttributeConstant.TIREDNESS, String.valueOf(this.fatigue));
        attributes.put(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        attributes.put(AttributeConstant.MOOD, String.valueOf(this.humeur));
        attributes.put(AttributeConstant.WEIGHT, String.valueOf(this.poids));
    }
    

    public void seNourrir(){
    	double prendrePoids = this.faim/100;
    	if(prendrePoids>0.6) {
    		this.poids += ActionConstant.KILOMAX; 
    	}
    	else if(prendrePoids>0.4){
    		this.poids += ActionConstant.KILOMAX/2; 
    	}
    	attributes.replace(AttributeConstant.WEIGHT, String.valueOf(this.poids));
    	this.faim += ActionConstant.SENOURRIR;
    	attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.faim));
        super.updateState();
    }

    public void seLaver(){
    	this.hygiene += ActionConstant.SELAVERHYGIENE;
    	attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        super.updateState();

    }

    public void jouer(){
    	this.humeur += ActionConstant.JOUER;
    	attributes.replace(AttributeConstant.MOOD, String.valueOf(this.humeur));
        super.updateState();
        
    }
    
    public void dormir() {
    	this.fatigue += ActionConstant.DORMIR;
    	attributes.replace(AttributeConstant.TIREDNESS, String.valueOf(this.fatigue));
        super.updateState();
    	
    }
    
    public void faireDuSport(){
    	this.faim += ActionConstant.FAIREDUSPORTFAIM;
    	attributes.replace(AttributeConstant.HUNGER, String.valueOf(this.faim));
        super.updateState();
    }
    
    public void faireSesBesoins(){
    	this.hygiene += ActionConstant.FAIRESESBESOINSHYGIENE;
    	attributes.replace(AttributeConstant.HYGIENE, String.valueOf(this.hygiene));
        super.updateState();
    }


}
