package edu.epam.lasttask.factory;

import edu.epam.lasttask.entity.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorFactory {
    Optional<Visitor> createVisitorFromStrings(List<String> strings);
}
