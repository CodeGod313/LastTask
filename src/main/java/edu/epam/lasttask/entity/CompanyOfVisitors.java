package edu.epam.lasttask.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

public class CompanyOfVisitors implements Callable<String>, Visitor {
    static Logger logger = LogManager.getLogger(CompanyOfVisitors.class);
    List<Person> members;

    public CompanyOfVisitors(List<Person> members) {
        this.members = members;
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
