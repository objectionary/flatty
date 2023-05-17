/*
 * This class is working with one object.
 * All commas must be removed.
 */
package org.objectionary;

import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

public class Tokenizer {

    private final String[] tokens;
    private int position;

    public Tokenizer(String input) {
        this.tokens = input.split(" ");
        this.position = 0;
    }

    void next() {
        position++;
    }

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
