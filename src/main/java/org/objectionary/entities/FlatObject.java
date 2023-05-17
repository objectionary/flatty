package org.objectionary.entities;

public class FlatObject extends Entity {

    /**
     * The name of the object.
     */
    private final String name;

    /**
     * The locator of the object.
     */
    private final String locator;

    /**
     * Constructor.
     *
     * @param name The name of the object.
     */
    public FlatObject(final String name) {
        this.name = name;
        this.locator = "𝜋";
    }

    /**
     * Constructor.
     *
     * @param name    The name of the object.
     * @param locator The locator of the object.
     */
    public FlatObject(final String name, final String locator) {
        this.name = name;
        this.locator = locator;
    }

    /**
     * @return The name of the object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The locator of the object.
     */
    public String getLocator() {
        return this.locator;
    }

    /**
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        return getName() + "(" + getLocator() + ")";
    }
}
