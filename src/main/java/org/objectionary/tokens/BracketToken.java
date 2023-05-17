package org.objectionary.tokens;

/**
 * It makes no sense to create two different types of object and application brackets separately.
 * TODO explain why
 */
public class BracketToken extends Token {

    private final BracketType state;

    public BracketToken(BracketType state) {
        this.state = state;
    }

    public BracketType getState() {
        return this.state;
    }

    public enum BracketType {
        OPEN,
        CLOSE
    }
}
