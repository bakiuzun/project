package com.example.model.tama.tamaNonVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Voiture extends NonVivant {

    public Voiture(){
        loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.VOITURE;

        super.init_new_tamagothi();
        super.addAttributes();

    }
    
    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
    }

    public void updateState(){
        delta_battery = ActionConstant.DELTA_BATTERY_CAR;
        delta_oil = ActionConstant.DELTA_OIL_CAR;
        delta_temperature = ActionConstant.DELTA_TEMPERATURE_CAR;
        delta_rust = ActionConstant.DELTA_RUST_CAR;
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        actions.put(AttributeConstant.ACTION_BATTERING_CAR, this::battering);
        actions.put(AttributeConstant.ACTION_OILING_CAR, this::oiling);
        actions.put(AttributeConstant.ACTION_COOLING_CAR, this::cooling);
        actions.put(AttributeConstant.ACTION_CLEANING_CAR, this::cleaning);
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }

    public  void loadTamagotchiInfo(){
        
    }
}