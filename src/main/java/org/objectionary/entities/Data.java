package org.objectionary.entities;

/**
 * This class represents the data entity.
 */
public class Data extends Entity {

    /**
     * The data value.
     */
    private final int data;

    /**
     * Constructor.
     *
     * @param data The data value.
     */
    public Data(int data) {
        this.data = data;
    }

    /**
     * @return The data value.
     */
    public int getData() {
        return this.data;
    }

    /**
     * @return The string representation of the data value.
     */
    @Override
    public String toString() {
        return String.valueOf(getData());
    }
}
