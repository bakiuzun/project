package com.example.model;

public class ActionConstant {
    //Stats max
    final static int KILOMAX = 2;
    final static int LIFE_MAX = 100;
    final static int WEIGHT_MAX = 100;
    final static int HUNGER_MAX = 100;
    final static int HYGIENE_MAX = 100;
    final static int MOOD_MAX = 100;
    final static int TIREDNESS_MAX = 100;
    final static int BATTERY_MAX = 100;
    final static int OIL_MAX = 100;
    final static int TEMPERATURE_MAX = 100;

    //Tamagotchi weight
    final static int CAT_WEIGHT = 10;
    final static int DOG_WEIGHT = 15;
    final static int RABBIT_WEIGHT = 5;
    final static int TURTLE_WEIGHT = 20;

    //Actions
    final static int EATING = 10;
    final static int LOSING_WEIGHT_EAT = 5;
    final static int WASHING_HYGIENE = 10;
    final static int FAIREDUSPORTFAIM = -5;
    final static int FAIREDUSPORTHUMEUR = 5;
    final static int DOING_SPORT = 10;
    final static int GAINING_WEIGHT = 10;
    final static int SLEEPING = 10;
    final static int USING_TOILET_HYGIENE = -5;
    final static int PLAYING = 10;
    final static int BATTERING = 10;
    final static int OILING = 10;
    final static int TEMPERATURE = 10;

    //Decaying stats overtime
    final static int BASE_DELTA = 2;

    final static int DELTA_HUNGER_TURTLE = 1;
    final static int DELTA_HYGIENE_TURTLE = 4;
    final static int DELTA_MOOD_TURTLE = 3;
    final static int DELTA_TIREDNESS_TURTLE = 2;
    final static int DELTA_WEIGHT_TURTLE = 1;
        
    final static int DELTA_HUNGER_CAT = 2;
    final static int DELTA_HYGIENE_CAT = 4;
    final static int DELTA_MOOD_CAT = 4;
    final static int DELTA_TIREDNESS_CAT = 4;
    final static int DELTA_WEIGHT_CAT = 3;
        
    final static int DELTA_HUNGER_DOG = 2;
    final static int DELTA_HYGIENE_DOG = 4;
    final static int DELTA_MOOD_DOG = 3;
    final static int DELTA_TIREDNESS_DOG = 2;
    final static int DELTA_WEIGHT_DOG = 2;
        
    final static int DELTA_HUNGER_RABBIT = 3;
    final static int DELTA_HYGIENE_RABBIT = 4;
    final static int DELTA_MOOD_RABBIT = 2;
    final static int DELTA_TIREDNESS_RABBIT = 1;
    final static int DELTA_WEIGHT_RABBIT = 3;

    final static int DELTA_BATTERY_ROBOT = 4;
    final static int DELTA_OIL_ROBOT = 2;
    final static int DELTA_HEAT_ROBOT = 1;

    final static int DELTA_BATTERY_CAR = 2;
    final static int DELTA_OIL_CAR = 4;
    final static int DELTA_TEMPERATURE_CAR = 3;


}
