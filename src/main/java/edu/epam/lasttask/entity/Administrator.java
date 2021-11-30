package edu.epam.lasttask.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Administrator {
    static Logger logger = LogManager.getLogger(Administrator.class);
    private static Administrator instance;
    private HookahBar hookahBar;
    private Lock lock;

    private Administrator() {
        hookahBar = HookahBar.getInstance();
        lock = new ReentrantLock();
    }

    public static Administrator getInstance() {
        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }

    public Hookah receiveHookah() {
        lock.lock();
        try {
            Deque<Hookah> hookahs = hookahBar.getHookahList();
            if (hookahs.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            logger.error("Tread interruption error", e);
        } finally {
            lock.unlock();
        }
    }

    public void putHookah(Hookah hookah) {
        lock.lock();
        try {
            Deque<Hookah> hookahs = hookahBar.getHookahList();
            hookahs.add(hookah);
            notify();
        } finally {
            lock.unlock();
        }
    }
}
