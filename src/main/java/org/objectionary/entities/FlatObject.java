package org.objectionary.entities;

public class FlatObject extends Entity {

    private final String name;

    private final String locator;

    public FlatObject(String name) {
        this.name = name;
        this.locator = "𝜋";
    }

    public FlatObject(String name, String locator) {
        this.name = name;
        this.locator = locator;
    }

    public String getName() {
        return name;
    }

    public String getLocator() {
        return locator;
    }

    @Override
    public String toString() {
        return name + "(" + locator + ")";
    }
}


