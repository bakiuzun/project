package com.example.model.tama;


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


    public void init_new_tamagothi(){

        this.life = ActionConstant.LIFE_MAX;
        this.actionEnCours = "Pas d'action en cours";
        this.lieuActuel = new Lieu(NomLieu.HOME);
        // the sessions is affected using the setSessions this is why we don't find it here
    }

    public void updateState(){
       //updateAttributes();

    }

    public void loadAction(){
        /// peut etr eyaura 
      
    }

    public void standartUpdate(){

        System.out.println("TAMAGOTCHI GET IN");
    }

    public void addAttributes(){
        this.attributes.put(AttributeConstant.LIFE, String.valueOf(this.life));
        this.attributes.put(AttributeConstant.ONGOING_ACTION, actionEnCours);
        this.attributes.put(AttributeConstant.TAMAGOTCHI_TYPE,  typeTamagotchi.name());
        this.attributes.put(AttributeConstant.ACTUAL_LOCATION,  lieuActuel.getNomLieu().name());
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


}
