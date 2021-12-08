package edu.epam.lasttask.entity;

import edu.epam.lasttask.exception.ThreadException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HookahBar implements Runnable {
    static Logger logger = LogManager.getLogger(HookahBar.class);
    private static HookahBar instance;
    public static final int MAX_VISITORS_INSIDE = 5;
    private final Deque<Visitor> visitorsInside;
    private final Deque<Visitor> visitorsOutside;
    private List<Hookah> hookahs;
    private final List<String> reviews;
    private static final Lock lock = new ReentrantLock();

    private HookahBar(List<Hookah> hookahs, List<Visitor> visitors) {
        this.hookahs = hookahs;
        reviews = new ArrayList<>();
        visitorsInside = new ArrayDeque<>();
        visitorsOutside = new ArrayDeque<>();
        visitors.forEach(this::addVisitor);
    }

    private HookahBar() {
        reviews = new ArrayList<>();
        visitorsInside = new ArrayDeque<>();
        visitorsOutside = new ArrayDeque<>();
    }

    public static HookahBar getInstance(List<Hookah> hookahs, List<Visitor> visitors) {
        lock.lock();
        try {
            if (instance == null) {
                instance = new HookahBar(hookahs, visitors);
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }


    public static HookahBar getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new HookahBar();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public void addVisitor(Visitor visitor) {
        lock.lock();
        try {
            if (visitorsInside.size() < MAX_VISITORS_INSIDE) {
                visitorsInside.add(visitor);
            } else {
                visitorsOutside.add(visitor);
            }
        } finally {
            lock.unlock();
        }
    }

    public void addHookah(Hookah hookah) {
        hookahs.add(hookah);
    }

    public List<Hookah> getHookahs() {
        return hookahs;
    }

    public List<String> getReviews() {
        return reviews;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(hookahs.size());
        List<Future<String>> futures = new ArrayList<>();
        while (!visitorsInside.isEmpty()) {
            Visitor visitor = visitorsInside.pop();
            try {
                visitor.requestHookah();
            } catch (ThreadException e) {
                logger.error("Execution error", e);
            }
            if (visitor.getClass() == Person.class) {
                futures.add(executorService.submit((Person) visitor));
            } else {
                futures.add(executorService.submit((CompanyOfVisitors) visitor));
            }
            if (visitorsInside.size() < MAX_VISITORS_INSIDE && !visitorsOutside.isEmpty()) {
                visitorsInside.push(visitorsOutside.pop());
            }
        }
        futures.forEach(x -> {
            try {
                String review = x.get();
                reviews.add(review);
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Execution error", e);
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HookahBar hookahBar = (HookahBar) o;

        if (visitorsInside != null ? !visitorsInside.equals(hookahBar.visitorsInside) : hookahBar.visitorsInside != null)
            return false;
        if (visitorsOutside != null ? !visitorsOutside.equals(hookahBar.visitorsOutside) : hookahBar.visitorsOutside != null)
            return false;
        if (hookahs != null ? !hookahs.equals(hookahBar.hookahs) : hookahBar.hookahs != null) return false;
        return reviews != null ? reviews.equals(hookahBar.reviews) : hookahBar.reviews == null;
    }

    @Override
    public int hashCode() {
        int result = visitorsInside != null ? visitorsInside.hashCode() : 0;
        result = 31 * result + (visitorsOutside != null ? visitorsOutside.hashCode() : 0);
        result = 31 * result + (hookahs != null ? hookahs.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HookahBar.class.getSimpleName() + "[", "]")
                .add("visitorsInside=" + visitorsInside)
                .add("visitorsOutside=" + visitorsOutside)
                .add("hookahs=" + hookahs)
                .add("reviews=" + reviews)
                .toString();
    }
}