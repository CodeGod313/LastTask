package edu.epam.lasttask.state;

import edu.epam.lasttask.entity.Hookah;

public class OrderedState implements HookahState {

    public static final String ORDERED_STATE_STRING = "OrderedState";

    @Override
    public void order(Hookah hookah) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stopSmoking(Hookah hookah) {
        hookah.setState(new ReadyState());
    }

    @Override
    public String toString() {
        return ORDERED_STATE_STRING;
    }
}
