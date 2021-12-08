package edu.epam.lasttask.entity;

import edu.epam.lasttask.exception.ThreadException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Callable;

public class CompanyOfVisitors implements Callable<String>, Visitor {
    static Logger logger = LogManager.getLogger(CompanyOfVisitors.class);
    List<Visitor> members;

    public CompanyOfVisitors(List<Visitor> members) {
        this.members = members;
    }

    @Override
    public String call() {
        String review = smoke();
        Administrator administrator = Administrator.getInstance();
        Person member = (Person) members.get(0);
        Hookah hookah = member.getHookah();
        administrator.putHookah(hookah);
        return review;
    }

    @Override
    public void requestHookah() throws ThreadException {
        Administrator administrator = Administrator.getInstance();
        Hookah hookah = administrator.receiveHookah();
        members.forEach(x -> {
            Person person = (Person) x;
            person.setHookah(hookah);
        });
    }

    @Override
    public String smoke() {
        StringBuilder smokeReview = new StringBuilder();
        members.forEach(x -> smokeReview.append(x.smoke()));
        return smokeReview.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyOfVisitors that = (CompanyOfVisitors) o;

        return members != null ? members.equals(that.members) : that.members == null;
    }

    @Override
    public int hashCode() {
        return members != null ? members.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompanyOfVisitors.class.getSimpleName() + "[", "]")
                .add("members=" + members)
                .toString();
    }
}
