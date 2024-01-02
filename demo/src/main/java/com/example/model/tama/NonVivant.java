package com.example.model.tama;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    }

    public void oiling(){
        this.oil = Math.min(this.oil + ActionConstant.OILING, ActionConstant.OIL_MAX);
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
            this.reduce_life_by += 5;
            return AttributeConstant.NON_VIVANT_BATTERY_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.NON_VIVANT_BATTERY_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_BATTERY_40;
        }
        if (res >= 0.2) {
            this.reduce_life_by += -3;
            return AttributeConstant.NON_VIVANT_BATTERY_20;
        }
        this.reduce_life_by += -15;
        return AttributeConstant.NON_VIVANT_BATTERY_0;
        
    }

    private String printOil(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.NON_VIVANT_OIL_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.NON_VIVANT_OIL_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_OIL_40;
        }
        if (res >= 0.2) {
            this.reduce_life_by += -3;
            return AttributeConstant.NON_VIVANT_OIL_20;
        }
        this.battery += -5;
        this.reduce_life_by += -10;
        return AttributeConstant.NON_VIVANT_OIL_0;
        
    }

    private String printTemperature(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_TEMPERATURE_40;
        }
        if (res >= 0.2) {
            this.reduce_life_by += -3;
            return AttributeConstant.NON_VIVANT_TEMPERATURE_20;
        }
        this.reduce_life_by += -10;
        return AttributeConstant.NON_VIVANT_TEMPERATURE_0;
        
    }

    private String printRust(){
        double res = (double) this.oil / ActionConstant.OIL_MAX;

        if (res >= 0.8) {
            this.reduce_life_by += 5;
            return AttributeConstant.NON_VIVANT_RUST_80;
        }
        if (res >= 0.6) {
            this.reduce_life_by += 3;
            return AttributeConstant.NON_VIVANT_RUST_60;
        }
        if (res >= 0.4) {
            return AttributeConstant.NON_VIVANT_RUST_40;
        }
        if (res >= 0.2) {
            this.reduce_life_by += -3;
            return AttributeConstant.NON_VIVANT_RUST_20;
        }
        this.temperature += -5;
        this.reduce_life_by += -5;
        return AttributeConstant.NON_VIVANT_RUST_0;
        
    }


    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        res.add(AttributeConstant.BATTERY + ": " + printBattery());
        res.add(AttributeConstant.OIL + ": " + printOil());
        res.add(AttributeConstant.TEMPERATURE + ": " + printTemperature());
        res.add(AttributeConstant.RUST + ": " + printRust());
    
        super.updateState(); // this will change the life of the tamagotchi
    
        res.addAll(super.printAttributes());

        this.replace_new_attributes_values();

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
