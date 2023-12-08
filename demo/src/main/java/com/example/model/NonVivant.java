package com.example.model;

public abstract class NonVivant extends Tamagotchi {

    protected int battery;
    protected int oil;
    protected int temperature;
    protected int rust;

    protected int delta_battery;
    protected int delta_oil;
    protected int delta_temperature;
    protected int delta_rust;

    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.battery = ActionConstant.BATTERY_MAX;
        this.oil = ActionConstant.OIL_MAX;
        this.temperature = ActionConstant.TEMPERATURE_MAX;
        this.rust = ActionConstant.RUST_MAX;

    }


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){

        this.battery -= delta_battery;
        this.oil -= delta_oil;
        this.temperature -= delta_temperature;
        this.rust -= delta_rust;

        replace_new_attributes_values();
        
        super.updateState();
    }

    public void replace_new_attributes_values(){
        attributes.replace(AttributeConstant.BATTERY, String.valueOf(this.battery));
        attributes.replace(AttributeConstant.OIL, String.valueOf(this.oil));
        attributes.replace(AttributeConstant.TEMPERATURE, String.valueOf(this.temperature));
        attributes.replace(AttributeConstant.RUST, String.valueOf(this.rust));
    }

    public void addAttributes(){
        super.addAttributes();
        attributes.put(AttributeConstant.BATTERY, String.valueOf(this.battery));
        attributes.put(AttributeConstant.OIL, String.valueOf(this.oil));
        attributes.put(AttributeConstant.TEMPERATURE, String.valueOf(this.temperature));
        attributes.put(AttributeConstant.RUST, String.valueOf(this.rust));
    }
    
    public void battering(){
        this.battery = Math.min(this.battery + ActionConstant.BATTERING, ActionConstant.BATTERY_MAX);
        attributes.replace(AttributeConstant.BATTERY, String.valueOf(this.battery));
    }

    public void oiling(){
        this.oil = Math.min(this.oil + ActionConstant.OILING, ActionConstant.OIL_MAX);
        attributes.replace(AttributeConstant.OIL, String.valueOf(this.oil));
    }

    public void cooling(){
        this.temperature = Math.min(this.temperature + ActionConstant.COOLING, ActionConstant.TEMPERATURE_MAX);
        attributes.replace(AttributeConstant.TEMPERATURE, String.valueOf(this.temperature));
    }

        public void cleaning(){
        this.rust = Math.min(this.rust + ActionConstant.CLEANING, ActionConstant.RUST_MAX);
        attributes.replace(AttributeConstant.RUST, String.valueOf(this.rust));
    }

    /*
     * Méthode corréspondant au sommeil/repos d'un Tamagochi non vivant 
     * 
     * 
     */
    public void maj(){

    }


}
