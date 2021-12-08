package edu.epam.lasttask.entity;

import edu.epam.lasttask.state.HookahState;
import edu.epam.lasttask.state.ReadyState;

import java.util.StringJoiner;
import java.util.UUID;

public class Hookah {
    private final UUID id;
    private HookahState state;

    public Hookah() {
        state = new ReadyState();
        id = UUID.randomUUID();
    }

    public void setState(HookahState state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public HookahState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hookah hookah = (Hookah) o;

        if (id != null ? !id.equals(hookah.id) : hookah.id != null) return false;
        return state != null ? state.equals(hookah.state) : hookah.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Hookah.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("state=" + state)
                .toString();
    }
}
