package org.objectionary.tokens;

/**
 * This class represents the both application and object brackets.
 */
public class BracketToken extends Token {

    /**
     * The state of the bracket.
     */
    private final BracketType state;

    /**
     * Constructor.
     *
     * @param state The state of the bracket.
     */
    public BracketToken(final BracketType state) {
        this.state = state;
    }

    /**
     * @return The state of the bracket.
     */
    public BracketType getState() {
        return this.state;
    }

    /**
     * Enum for the bracket type.
     */
    public enum BracketType {
        OPEN,
        CLOSE
    }
}
