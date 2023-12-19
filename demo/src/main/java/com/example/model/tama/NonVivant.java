package com.example.model.tama;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public abstract class NonVivant extends Tamagotchi {

    protected Integer battery;
    protected Integer oil;
    protected Integer temperature;
    protected Integer rust;

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

        updateBattery();
        updateOil();
        updateTemperature();
        updateRust();

        replace_new_attributes_values();
        
        super.updateState();
    }

    private void updateBattery(){
        this.battery = Math.max(this.battery-delta_battery,0);
    }

    private void updateOil(){
        this.oil = Math.max(this.oil-delta_oil,0);
    }
    
    private void updateTemperature(){
        this.temperature = Math.max(this.temperature-delta_temperature,0);
    }
    
    private void updateRust(){
        this.rust = Math.max(this.rust-delta_rust,0);
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

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        this.battery  = Integer.parseInt((String) tama.get(AttributeConstant.BATTERY));
        this.oil = Integer.parseInt((String) tama.get(AttributeConstant.OIL));
        this.temperature  = Integer.parseInt((String) tama.get(AttributeConstant.TEMPERATURE));
        this.rust = Integer.parseInt((String) tama.get(AttributeConstant.RUST));
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
        
        if (res > 0.8) {
            this.reduce_life_by += 5;
            return "BATTERIE PLEINE";
        }
        if (res > 0.6) {
            this.reduce_life_by += 3;
            return "BATTERIE ÉLEVÉ";
        }
        if (res > 0.4) {
            return "BATTERIE NORMAL";
        }
        if (res > 0.2) {
            this.reduce_life_by += -3;
            return "BATTERIE FAIBLE";
        }

        this.reduce_life_by += -5;
        return "BATTERIE VIDE";
        
    }

    private String printOil(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return "CARBURANT PLEIN";
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return "HEUREUX";
        }
        if (res >= 0.4) {
            return "NORMAL";
        }
        if (res >= 0.2) {
            this.reduce_life_by += -3;
            return "TRISTE";
        }
        this.reduce_life_by += -5;
        return "TRÈS TRISTE";
        
    }


    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        res.add(AttributeConstant.BATTERY + " " + this.battery + "%");
        res.add(AttributeConstant.OIL + " " + this.oil + "%");
        res.add(AttributeConstant.TEMPERATURE + " " + this.temperature + "%");
        res.add(AttributeConstant.RUST + " " + this.rust + "%");

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
