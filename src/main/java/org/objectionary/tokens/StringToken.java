package org.objectionary.tokens;

/**
 * This class represents the string token.
 * @since 0.1.0
 */
public class StringToken extends Token {
    /**
     * The value of the string.
     */
    String value;

    /**
     * Constructor.
     *
     * @param value The value of the string.
     */
    public StringToken(final String value) {
        this.value = value;
    }

    /**
     * @return The value of the string.
     */
    public String getValue() {
        return this.value;
    }
}
