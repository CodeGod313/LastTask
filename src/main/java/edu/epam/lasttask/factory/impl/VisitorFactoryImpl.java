package edu.epam.lasttask.factory.impl;

import edu.epam.lasttask.entity.CompanyOfVisitors;
import edu.epam.lasttask.entity.Person;
import edu.epam.lasttask.entity.Visitor;
import edu.epam.lasttask.factory.VisitorFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitorFactoryImpl implements VisitorFactory {
    static Logger logger = LogManager.getLogger(VisitorFactoryImpl.class);
    public static final String COMPANY_TYPE = "company";
    public static final String PERSON_TYPE = "person";
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int THIRD_INDEX = 2;

    @Override
    public Optional<Visitor> createVisitorFromStrings(List<String> strings) {
        Visitor visitor = null;
        String visitorType = strings.get(FIRST_INDEX);
        switch (visitorType) {
            case COMPANY_TYPE -> {
                List<Visitor> members = new ArrayList<>();
                for (int i = 1; i < strings.size() - 2; i += 2) {
                    String name = strings.get(i);
                    String numberOfPuffsString = strings.get(i + 1);
                    Integer numberOfPuffs = Integer.parseInt(numberOfPuffsString);
                    Person person = new Person(name, numberOfPuffs);
                    members.add(person);
                }
                visitor = new CompanyOfVisitors(members);
            }
            case PERSON_TYPE -> {
                String name = strings.get(SECOND_INDEX);
                String numberOfPuffsString = strings.get(THIRD_INDEX);
                Integer numberOfPuffs = Integer.parseInt(numberOfPuffsString);
                visitor = new Person(name, numberOfPuffs);
            }
            default -> logger.error("Unexpected value");
        }
        if(visitor == null){
            return Optional.empty();
        }
        return Optional.of(visitor);
    }
}
