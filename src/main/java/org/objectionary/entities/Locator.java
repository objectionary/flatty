package org.objectionary.entities;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents the locator entity.
 * @since 0.1.0
 */
public class Locator extends Entity {

    /**
     * The path of the locator.
     */
    private final List<String> path;

    /**
     * Constructor.
     *
     * @param path The path of the locator.
     */
    public Locator(final String path) {
        this.path = Arrays.asList(path.split("\\."));
    }

    /**
     * @return The path of the locator.
     */
    public List<String> getPath() {
        return this.path;
    }

    /**
     * @return The string representation of the locator.
     */
    @Override
    public String toString() {
        return String.join(".", getPath());
    }
}
