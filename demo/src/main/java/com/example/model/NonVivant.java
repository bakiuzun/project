package com.example.model;

public abstract class NonVivant extends Tamagotchi {

    protected int battery;
    protected int oil;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.battery = ActionConstant.BATTERY_MAX;
        this.oil = ActionConstant.OIL_MAX;

    }


    public void loadAction(){
        super.loadAction();
    }


    public void replace_new_attributes_values(){
        attributes.replace(AttributeConstant.BATTERY, String.valueOf(this.battery));
        attributes.replace(AttributeConstant.OIL, String.valueOf(this.oil));

    }

    public void addAttributes(){
        super.addAttributes();
        attributes.put(AttributeConstant.BATTERY, String.valueOf(this.battery));
        attributes.put(AttributeConstant.OIL, String.valueOf(this.oil));
    }
    
    public void battering(){
        this.battery = ActionConstant.BATTERING;
        attributes.replace(AttributeConstant.BATTERY, String.valueOf(this.battery));
    }

    public void oiling(){
        this.oil = ActionConstant.OILING;
        attributes.replace(AttributeConstant.OIL, String.valueOf(this.oil));
    }

    /*
     * Méthode corréspondant au sommeil/repos d'un Tamagochi non vivant 
     * 
     * 
     */
    public void maj(){

    }


}
