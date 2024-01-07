package com.example.model.tama;



import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.model.Lieu;
import com.example.model.NomLieu;

import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

/*
 * This class is the model for the NonVivant tamagotchi
 * 
 */
public abstract class NonVivant extends Tamagotchi {

    protected Integer battery;
    protected Integer oil;
    protected Integer temperature;
    protected Integer rust;

    protected int deltaBattery;
    protected int deltaOil;
    protected int deltaTemperature;
    protected int deltaRust;

    /*
     * This method initializes a new NonVivant tamagotchi
     * 
     * It calls the Tamagotchi init_new_tamagothi method
     * It adds the NonVivant attributes
     * It adds the NonVivant neighbors
     * 
     */
    public void initNewTamagotchi(){
        this.currentPlace = new Lieu(NomLieu.GARAGE);
        super.initNewTamagotchi();

        this.battery = ActionConstant.BATTERY_MAX;
        this.oil = ActionConstant.OIL_MAX;
        this.temperature = ActionConstant.TEMPERATURE_MAX;
        this.rust = ActionConstant.RUST_MAX;

    }

        
    public void addNeighbor(){
        switch (this.currentPlace.getNomLieu()) {
            case GARAGE:
                this.currentPlace.addVoisin(NomLieu.ROAD);
                break;
            case ROAD:
                this.currentPlace.addVoisin(NomLieu.GARAGE);
                this.currentPlace.addVoisin(NomLieu.GAS_STATION);
                this.currentPlace.addVoisin(NomLieu.WASHING_STATION);
                break;
            case GAS_STATION:
                this.currentPlace.addVoisin(NomLieu.ROAD);
                this.currentPlace.addVoisin(NomLieu.WASHING_STATION);
                break;
            case WASHING_STATION:
                this.currentPlace.addVoisin(NomLieu.ROAD);
                this.currentPlace.addVoisin(NomLieu.GAS_STATION);
                break;
        default:
            break;
        }
    }


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){

        updateBattery();
        updateOil();
        updateTemperature();
        updateRust();

        replaceNewAttributesValues();
        
    }

    private void updateBattery(){
        this.battery = Math.max(this.battery-deltaBattery,0);
    }

    private void updateOil(){
        this.oil = Math.max(this.oil-deltaOil,0);
    }
    
    private void updateTemperature(){
        this.temperature = Math.max(this.temperature-deltaTemperature,0);
    }
    
    private void updateRust(){
        this.rust = Math.max(this.rust-deltaRust,0);
    }


    public void replaceNewAttributesValues(){
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

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        this.battery  = Integer.parseInt((String) tama.get(AttributeConstant.BATTERY));
        this.oil = Integer.parseInt((String) tama.get(AttributeConstant.OIL));
        this.temperature  = Integer.parseInt((String) tama.get(AttributeConstant.TEMPERATURE));
        this.rust = Integer.parseInt((String) tama.get(AttributeConstant.RUST));
    }
    
    public void battering(){
        this.battery = Math.min(this.battery + ActionConstant.BATTERING, ActionConstant.BATTERY_MAX);
        this.temperature = Math.max(this.temperature + ActionConstant.BATTERING_TEMPERATURE, 0);
    }

    public void oiling(){
        this.oil = Math.min(this.oil + ActionConstant.OILING, ActionConstant.OIL_MAX);
        this.rust = Math.max(this.rust + ActionConstant.OILING_RUST, 0);
    }

    public void cooling(){
        this.temperature = Math.min(this.temperature + ActionConstant.COOLING, ActionConstant.TEMPERATURE_MAX);
    }

    public void cleaning(){
        this.rust = Math.min(this.rust + ActionConstant.CLEANING, ActionConstant.RUST_MAX);
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getOil() {
        return oil;
    }

    public void setOil(Integer oil) {
        this.oil = oil;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getRust() {
        return rust;
    }

    public void setRust(Integer rust) {
        this.rust = rust;
    }



    private String printBattery(){

        double res = (double) this.battery / ActionConstant.BATTERY_MAX;
        
        if (res >= 0.8) {
            this.reduceLifeBy+= 5;
            return AttributeConstant.NON_VIVANT_BATTERY_80;
        }
        if (res >= 0.6) {
            this.reduceLifeBy+= 3;
            return AttributeConstant.NON_VIVANT_BATTERY_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_BATTERY_40;
        }
        if (res >= 0.2) {
            this.reduceLifeBy+= -3;
            return AttributeConstant.NON_VIVANT_BATTERY_20;
        }
        this.reduceLifeBy+= -15;
        return AttributeConstant.NON_VIVANT_BATTERY_0;
        
    }

    private String printOil(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduceLifeBy+= 5;
            return AttributeConstant.NON_VIVANT_OIL_80;
        }
        if (res >= 0.6) {
            this.reduceLifeBy+= 3;
            return AttributeConstant.NON_VIVANT_OIL_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_OIL_40;
        }
        if (res >= 0.2) {
            this.reduceLifeBy+= -3;
            return AttributeConstant.NON_VIVANT_OIL_20;
        }
        this.battery += -5;
        this.reduceLifeBy+= -10;
        return AttributeConstant.NON_VIVANT_OIL_0;
        
    }

    private String printTemperature(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduceLifeBy+= 5;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_80;
        }
        if (res >= 0.6) {
            this.reduceLifeBy+= 3;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_TEMPERATURE_40;
        }
        if (res >= 0.2) {
            this.reduceLifeBy+= -3;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_20;
        }
        this.reduceLifeBy+= -10;
        return AttributeConstant.NON_VIVANT_TEMPERATURE_0;
        
    }

    private String printRust(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduceLifeBy+= 5;
            return AttributeConstant.NON_VIVANT_RUST_80;
        }
        if (res >= 0.6) {
            this.reduceLifeBy+= 3;
            return AttributeConstant.NON_VIVANT_RUST_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_RUST_40;
        }
        if (res >= 0.2) {
            this.reduceLifeBy+= -3;
            return AttributeConstant.NON_VIVANT_RUST_20;
        }
        this.temperature += -5;
        this.reduceLifeBy+= -5;
        return AttributeConstant.NON_VIVANT_RUST_0;
        
    }


    public HashMap<String,String> printAttributes(boolean update_life){
        
        HashMap<String,String> res =  new HashMap<String,String>();
        
        res.put(AttributeConstant.BATTERY, printBattery());
        res.put(AttributeConstant.OIL, printOil());
        res.put(AttributeConstant.TEMPERATURE, printTemperature());
        res.put(AttributeConstant.RUST, printRust());
    
        if (update_life){super.updateState(); } // this will change the life of the tamagotchi
        else {this.reduceLifeBy= 0;}
        
        res.putAll(super.printAttributes(update_life));

        this.replaceNewAttributesValues();

        return res;
    }

    /*
     * Méthode corréspondant au sommeil/repos d'un Tamagochi non vivant 
     * 
     * 
     */
    public void maj(){

    }


}
