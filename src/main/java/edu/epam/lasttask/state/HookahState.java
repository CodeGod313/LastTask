package edu.epam.lasttask.state;

import edu.epam.lasttask.entity.Hookah;

public interface HookahState {
    void order(Hookah hookah);
    void stopSmoking(Hookah hookah);
}
