package com.example.model;

public abstract class NonVivant extends Tamagotchi {

    protected int batterie;
    protected int huile;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.batterie = 100;
        this.huile = 100;

    }


    public void loadAction(){
        super.loadAction();
    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put("batterie", String.valueOf(this.batterie));
        attributes.put("huile", String.valueOf(this.huile));
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
