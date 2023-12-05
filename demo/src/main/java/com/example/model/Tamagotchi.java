package com.example.model;


import java.util.HashMap;
import java.util.Map;


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


}
