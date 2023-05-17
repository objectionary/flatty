/*
 * This class is working with one object.
 * All commas must be removed.
 */
package org.objectionary;

import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

/**
 * This class is representing the tokens stream.
 */
public class Tokenizer {

    /**
     * The tokens.
     */
    private final String[] tokens;

    /**
     * The current position.
     */
    private int position;

    /**
     * Constructor.
     *
     * @param input The input string.
     */
    public Tokenizer(String input) {
        this.tokens = input.split(" ");
        this.position = 0;
    }

    /**
     * Increments the position.
     */
    void next() {
        position++;
    }

    /**
     * @return The current token.
     */
    Token getToken() {
        final String token = tokens[position];
        if (token.equals("↦")) {
            return new ArrowToken();
        }
        if (token.equals("(") || token.equals("⟦")) {
            return new BracketToken(BracketToken.BracketType.OPEN);
        }
        if (token.equals(")") || token.equals("⟧")) {
            return new BracketToken(BracketToken.BracketType.CLOSE);
        }
        return new StringToken(token);
    }
}
