package edu.epam.lasttask.entity;

import edu.epam.lasttask.exception.ThreadException;

public interface Visitor {
    void requestHookah() throws ThreadException;
    String smoke();
}
