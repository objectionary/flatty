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

import org.objectionary.tokens.Token;

/**
 * This class is representing the tokens stream.
 * @since 0.1.0
 *
 * @checkstyle MultilineJavadocTagsCheck (500 lines)
 */
public final class Tokenizer {

    /**
     * The tokens.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final String[] tokens;

    /**
     * Constructor.
     *
     * @param input The input string.
     *
     * @todo #15:30min This constructor should split the input string into
     *  tokens. Also position should be set to 0. Implement it.
     */
    public Tokenizer(final String input) {
        this.tokens = input.split(" ");
    }

    /**
     * Checks if there is a next token.
     * @return True if there is a next token, false otherwise.
     *
     * @todo #15:30min This method should return true if there is a next token,
     *  false otherwise. Implement it. After finishing this task, remove
     *  checkstyle annotation.
     * @checkstyle NonStaticMethodCheck (10 lines)
     */
    boolean hasNext() {
        return false;
    }

    /**
     * Increments the position.
     *
     * @todo #15:30min This method should move the position to the next token.
     *  This method should not return anything. Implement it. After finishing
     *  remove checkstyle annotation.
     * @checkstyle NonStaticMethodCheck (10 lines)
     */
    void next() {
        // Empty
    }

    /**
     * Returns the current token.
     * @return The current token.
     *
     * @todo #15:30min This method should return the current token.
     *  It should not change state of the tokenizer. Implement it. After
     *  finishing remove checkstyle annotation.
     * @checkstyle NonStaticMethodCheck (10 lines)
     */
    Token getToken() {
        return null;
    }

}
