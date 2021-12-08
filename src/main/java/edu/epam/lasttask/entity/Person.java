package edu.epam.lasttask.entity;

import edu.epam.lasttask.exception.ThreadException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Person implements Callable<String>, Visitor {
    public static final String PREFIX_OF_REVIEW = "Puff was ";
    public static final String GREAT_STRING = "great!!!\n";
    public static final String AWFUL_STRING = "awful :(\n";
    public static final int TIMEOUT = 2;
    public static final String SMOKE_REVIEW_DESCRIPTION = "Smoke review from ";
    public static final String NEW_LINE = "\n";
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

    public Hookah getHookah() {
        return hookah;
    }

    @Override
    public String call() {
        String review = smoke();
        Administrator administrator = Administrator.getInstance();
        administrator.putHookah(hookah);
        return review;
    }

    @Override
    public void requestHookah() throws ThreadException {
        Administrator administrator = Administrator.getInstance();
        hookah = administrator.receiveHookah();
    }

    @Override
    public String smoke() {
        StringBuilder smokeReview = new StringBuilder(SMOKE_REVIEW_DESCRIPTION + name + NEW_LINE);
        Random random = new Random();
        for (int i = 0; i < numberOfPuffs; i++) {
            try {
                TimeUnit.SECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                logger.error("Error while executing thread sequence", e);
            }
            smokeReview.append(PREFIX_OF_REVIEW);
            if (random.nextBoolean()) {
                smokeReview.append(GREAT_STRING);
            } else {
                smokeReview.append(AWFUL_STRING);
            }
        }
        return smokeReview.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (numberOfPuffs != null ? !numberOfPuffs.equals(person.numberOfPuffs) : person.numberOfPuffs != null)
            return false;
        return hookah != null ? hookah.equals(person.hookah) : person.hookah == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (numberOfPuffs != null ? numberOfPuffs.hashCode() : 0);
        result = 31 * result + (hookah != null ? hookah.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("numberOfPuffs=" + numberOfPuffs)
                .add("hookah=" + hookah)
                .toString();
    }
}
