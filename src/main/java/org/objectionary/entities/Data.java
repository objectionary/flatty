package org.objectionary.entities;

public class Data extends Entity {

    private final int data;

    public Data(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return String.valueOf(getData());
    }
}
