package com.example.model;

public abstract class NonVivant extends Tamagotchi {

    protected int battery;
    protected int oil;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.battery = ActionConstant.BATTERY;
        this.oil = ActionConstant.OIL;

    }


    public void loadAction(){
        super.loadAction();
    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put(AttributeConstant.BATTERY, String.valueOf(this.battery));
        attributes.put(AttributeConstant.OIL, String.valueOf(this.oil));
    }
    
    public void recharger(){

    }

    public void huiler(){

    }

    /*
     * Méthode corréspondant au sommeil/repos d'un Tamagochi non vivant 
     * 
     * 
     */
    public void maj(){

    }


}
