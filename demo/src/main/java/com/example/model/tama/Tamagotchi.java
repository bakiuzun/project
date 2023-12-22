package com.example.model.tama;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.Session;
import com.example.model.TypeTamagotchi;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;
import com.example.model.utils.Utility;


public abstract class Tamagotchi {


    protected Map<String,String> attributes = new HashMap<>();
    protected Map<String,Runnable> actions = new HashMap<>();
    
    protected String actionEnCours;
    protected int life;

    private Session maSessions;
    protected TypeTamagotchi typeTamagotchi;
    protected Lieu lieuActuel;
    
    protected int reduce_life_by = 0;


    public void init_new_tamagothi(){

        this.life = ActionConstant.LIFE_MAX;
        this.actionEnCours = "Pas d'action en cours";
        this.lieuActuel = new Lieu(NomLieu.HOME);
        // the sessions is affected using the setSessions this is why we don't find it here
    }

    public void updateState(){
        this.life = Math.min(Math.max(this.life += reduce_life_by,0),100); // can change for each update

        reduce_life_by = 0;
        replace_new_attributes_values();
    }

    public void loadAction(){
        /// peut etr eyaura 
      
    }


    public void clearAction(){this.actions.clear();}
    
    public void addAttributes(){
        this.attributes.put(AttributeConstant.LIFE, String.valueOf(this.life));
        this.attributes.put(AttributeConstant.ONGOING_ACTION, actionEnCours);
        this.attributes.put(AttributeConstant.TAMAGOTCHI_TYPE,  typeTamagotchi.name());
        this.attributes.put(AttributeConstant.ACTUAL_LOCATION,  lieuActuel.getNomLieu().name());
    }

    public void replace_new_attributes_values(){
        attributes.replace(AttributeConstant.LIFE, String.valueOf(this.life));
        attributes.replace(AttributeConstant.ONGOING_ACTION, actionEnCours);
        attributes.replace(AttributeConstant.TAMAGOTCHI_TYPE,  typeTamagotchi.name());
        attributes.replace(AttributeConstant.ACTUAL_LOCATION,  lieuActuel.getNomLieu().name());

    }

    public void setSession(Session session){
        this.maSessions = session;
    }

    public Map<String, Runnable> getActions(){
        return this.actions;
    }


    public Map<String,String> getAttributes(){
        return this.attributes;
    }

    public Session getSession(){
        return maSessions;
    }

    public void doAction(String actionName){
        this.actions.get(actionName).run();
    }

    public TypeTamagotchi getTypeTamagotchi(){
        return typeTamagotchi;
    }
    public int getLife(){
        return this.life;
    }

    public void setTypeTamagotchi(TypeTamagotchi typeTamagotchi){
        this.typeTamagotchi = typeTamagotchi;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder(); // Use StringBuilder for efficient string concatenation

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            ret.append(key).append(": ").append(value).append(",").append("\n");
        }
        return ret.toString();
    }



    public void loadTamaFromDatabase(JSONObject tama){
        this.life = Integer.parseInt((String) tama.get(AttributeConstant.LIFE));
        this.actionEnCours = (String) tama.get(AttributeConstant.ONGOING_ACTION);
        this.lieuActuel = new Lieu(Utility.fromStringToNomLieu((String) tama.get(AttributeConstant.ACTUAL_LOCATION)));
        this.typeTamagotchi = Utility.fromStringToTamgotchiType((String) tama.get(AttributeConstant.TAMAGOTCHI_TYPE));
    }

    public String getActionEnCours() {
        return actionEnCours;
    }

    public void setActionEnCours(String actionEnCours) {
        this.actionEnCours = actionEnCours;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Session getMaSessions() {
        return maSessions;
    }

    public void setMaSessions(Session maSessions) {
        this.maSessions = maSessions;
    }

    public Lieu getLieuActuel() {
        return lieuActuel;
    }

    public void setLieuActuel(Lieu lieuActuel) {
        this.lieuActuel = lieuActuel;
    }

    public ArrayList<String> printAttributes(){
        ArrayList<String> res = new ArrayList<>();

        res.add(AttributeConstant.LIFE + " " + this.life + "%");
        return res;
    }


}
