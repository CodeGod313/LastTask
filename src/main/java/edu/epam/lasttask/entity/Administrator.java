package edu.epam.lasttask.entity;

import edu.epam.lasttask.exception.ThreadException;
import edu.epam.lasttask.state.HookahState;
import edu.epam.lasttask.state.OrderedState;
import edu.epam.lasttask.state.ReadyState;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Administrator {
    static Logger logger = LogManager.getLogger(Administrator.class);
    public static final String READY_STATE_STRING = "ReadyState";
    public static final int FIRST_INDEX = 0;
    private static Administrator instance;
    private final HookahBar hookahBar;
    private static final Lock lock = new ReentrantLock();
    private final Condition condition;

    private Administrator() {
        hookahBar = HookahBar.getInstance();
        condition = lock.newCondition();
    }

    public static Administrator getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Administrator();
            }
        } finally {
            lock.unlock();
        }

        return instance;
    }

    public Hookah receiveHookah() throws ThreadException {
        lock.lock();
        Hookah hookah = null;
        try {
            while (hookah == null) {
                List<Hookah> hookahs = hookahBar.getHookahs();
                List<Hookah> freeHookahs = hookahs
                        .stream()
                        .filter(x -> {
                            HookahState state = x.getState();
                            String stateString = state.toString();
                            return stateString.equals(READY_STATE_STRING);
                        })
                        .toList();
                if (!freeHookahs.isEmpty()) {
                    hookah = freeHookahs.get(FIRST_INDEX);
                    hookah.setState(new OrderedState());
                } else {
                    condition.await();
                }
            }
        } catch (InterruptedException e) {
            logger.error("Thread execution error", e);
            throw new ThreadException("Thread execution error", e);
        } finally {
            lock.unlock();
        }
        return hookah;
    }

    public void putHookah(Hookah hookah) {
        lock.lock();
        try {
            hookah.setState(new ReadyState());
            hookahBar
                    .getHookahs()
                    .add(hookah);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Administrator that = (Administrator) o;

        if (hookahBar != null ? !hookahBar.equals(that.hookahBar) : that.hookahBar != null) return false;
        return condition != null ? condition.equals(that.condition) : that.condition == null;
    }

    @Override
    public int hashCode() {
        int result = hookahBar != null ? hookahBar.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Administrator.class.getSimpleName() + "[", "]")
                .add("hookahBar=" + hookahBar)
                .add("condition=" + condition)
                .toString();
    }
}