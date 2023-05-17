package org.objectionary.entities;

public class Lambda extends Entity {

    private final String function;

    public Lambda(String function) {
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    @Override
    public String toString() {
        return getFunction();
    }
}
