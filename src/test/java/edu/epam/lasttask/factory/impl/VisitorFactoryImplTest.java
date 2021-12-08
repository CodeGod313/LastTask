package edu.epam.lasttask.factory.impl;

import edu.epam.lasttask.entity.Person;
import edu.epam.lasttask.factory.VisitorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VisitorFactoryImplTest {

    List<String> strings;
    VisitorFactory visitorFactory;

    @BeforeAll
    public void setUp() {
        visitorFactory = new VisitorFactoryImpl();
        strings = new ArrayList<>();
        strings.add("person");
        strings.add("Ales");
        strings.add("13");
    }

    @Test
    void createVisitorFromStrings() {
        Person expected = new Person("Ales", 13);
        Person actual = (Person) visitorFactory.createVisitorFromStrings(strings).get();
        Assertions.assertEquals(expected, actual);
    }
}