package edu.epam.lasttask.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class HookahBar implements Runnable{
    public static final int MAX_VISITORS_INSIDE = 5;
    private Queue<Visitor> visitorsInside;
    private Queue<Visitor> visitorsOutside;
    private Deque<Hookah> hookahList;

    public HookahBar(Deque<Hookah> hookahList) {
        this.hookahList = hookahList;
    }

    public void addVisitor(Visitor visitor) {
        if (visitorsInside.size() < MAX_VISITORS_INSIDE) {
            visitorsInside.add(visitor);
            visitor.requestHookah();
        }else {
            visitorsOutside.add(visitor);
        }
    }

    public Deque<Hookah> getHookahList() {
        return hookahList;
    }

    @Override
    public void run() {

    }
}
