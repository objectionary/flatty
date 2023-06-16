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
package org.objectionary.parsing;

/**
 * This class is representing the type checker.
 * @since 0.1.0
 */
public final class TypeChecker {

    /**
     * The token to check the type of.
     */
    private final String token;

    /**
     * Constructor.
     * @param token The token to check the type of.
     */
    public TypeChecker(final String token) {
        this.token = token;
    }

    /**
     * Checks if the token is an empty.
     * @return True if the token is an empty.
     */
    public boolean isEmpty() {
        return this.token.equals("ø");
    }

    /**
     * Checks if the token is a locator.
     * @return True if the token is a locator.
     */
    public boolean isLocator() {
        return this.token.startsWith("𝜋.") || this.token.startsWith("ξ.");
    }

    /**
     * Checks if the token is a data.
     * @return True if the token is a data.
     */
    public boolean isData() {
        return this.token.startsWith("0x");
    }

    /**
     * Checks if the token is a lambda.
     * @return True if the token is a lambda.
     */
    public boolean isLambda() {
        return this.token.startsWith("bool") || this.token.startsWith("int");
    }

    /**
     * Checks if the token is an object.
     * @return True if the token is an object.
     */
    public boolean isObject() {
        return this.token.charAt(0) == 'ν';
    }
}
