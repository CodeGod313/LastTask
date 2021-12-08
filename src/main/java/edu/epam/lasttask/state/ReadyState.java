package edu.epam.lasttask.state;

import edu.epam.lasttask.entity.Hookah;

public class ReadyState implements HookahState {

    public static final String READY_STATE_STRING = "ReadyState";

    @Override
    public void order(Hookah hookah) {
        hookah.setState(new OrderedState());
    }

    @Override
    public void stopSmoking(Hookah hookah) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return READY_STATE_STRING;
    }
}
