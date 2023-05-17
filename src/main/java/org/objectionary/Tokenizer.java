/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023 Objectionary.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.objectionary;

import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

/**
 * This class is representing the tokens stream.
 * @since 0.1.0
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
    public Tokenizer(final String input) {
        this.tokens = input.split(" ");
        this.position = 0;
    }

    /**
     * Increments the position.
     */
    void next() {
        this.position += 1;
    }

    /**
     * Returns the current token.
     * @return The current token.
     */
    Token getToken() {
        final String token = this.tokens[this.position];
        final Token result;
        switch (token) {
            case "↦":
                result = new ArrowToken();
                break;
            case "(":
            case "⟦":
                result = new BracketToken(BracketToken.BracketType.OPEN);
                break;
            case ")":
            case "⟧":
                result = new BracketToken(BracketToken.BracketType.CLOSE);
                break;
            default:
                result = new StringToken(token);
                break;
        }
        return result;
    }

}
