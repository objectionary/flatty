package org.objectionary.entities;

/**
 * This class represents the lambda entity.
 * @since 0.1.0
 */
public class Lambda extends Entity {

    /**
     * The function of the lambda.
     */
    private final String function;

    /**
     * Constructor.
     *
     * @param function The function of the lambda.
     */
    public Lambda(final String function) {
        this.function = function;
    }

    /**
     * @return The function of the lambda.
     */
    public String getFunction() {
        return this.function;
    }

    /**
     * @return The string representation of the lambda.
     */
    @Override
    public String toString() {
        return getFunction();
    }
}
