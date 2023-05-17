package org.objectionary.entities;

import java.util.Arrays;
import java.util.List;

public class Locator extends Entity {

    private final List<String> path;

    public Locator(String path) {
        this.path = Arrays.asList(path.split("\\."));
    }

    public List<String> getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return String.join(".", getPath());
    }
}
