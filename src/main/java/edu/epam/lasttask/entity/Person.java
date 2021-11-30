package edu.epam.lasttask.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

public class Person implements Callable<String>, Visitor {
    static Logger logger = LogManager.getLogger(Person.class);
    String name;
    Integer numberOfPuffs;
    Hookah hookah;

    public Person(String name, Integer numberOfPuffs) {
        this.name = name;
        this.numberOfPuffs = numberOfPuffs;
    }

    public void setHookah(Hookah hookah) {
        this.hookah = hookah;
    }

    @Override
    public String call() throws Exception {
        return null;
    }

    @Override
    public void requestHookah() {

    }

    @Override
    public void smoke() {

    }
}
