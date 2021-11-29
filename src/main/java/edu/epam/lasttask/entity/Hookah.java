package edu.epam.lasttask.entity;

import edu.epam.lasttask.state.HookahState;

public class Hookah {
    public static Hookah instance;
    HookahState state;

    private Hookah() {
        instance = new Hookah();
    }

    public static Hookah getInstance() {
        if(instance == null){
            instance = new Hookah();
        }
        return instance;
    }
}
