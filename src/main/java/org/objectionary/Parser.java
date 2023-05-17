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

import java.util.HashMap;
import java.util.Map;
import org.objectionary.entities.Data;
import org.objectionary.entities.Empty;
import org.objectionary.entities.Entity;
import org.objectionary.entities.FlatObject;
import org.objectionary.entities.Lambda;
import org.objectionary.entities.Locator;
import org.objectionary.entities.ObjectWithApplication;
import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

/**
 * This class represents the parser.
 * @since 0.1.0
 */
public final class Parser {

    /**
     * The input.
     */
    final String input;

    /**
     * Checks if the token is empty.
     * @param token The token to check.
     * @return True if the token is empty.
     */
    private static boolean isEmpty(final String token) {
        return token.equals("ø");
    }

    /**
     * Checks if the token is a locator.
     * @param token The token to check.
     * @return True if the token is a locator.
     */
    private static boolean isLocator(final String token) {
        return token.startsWith("𝜋.") || token.startsWith("ξ.");
    }

    /**
     * Checks if the token is a data.
     * @param token The token to check.
     * @return True if the token is data.
     */
    private static boolean isData(final String token) {
        return token.startsWith("0x");
    }

    /**
     * Checks if the token is a lambda.
     * @param token The token to check.
     * @return True if the token is a lambda.
     */
    private static boolean isLambda(final String token) {
        return token.startsWith("bool") || token.startsWith("int");
    }

    /**
     * Checks if the token is an object.
     * @param token The token to check.
     * @return True if the token is an object.
     */
    private static boolean isObject(final String token) {
        return token.charAt(0) == 'ν';
    }

    /**
     * Since there is no difference in the application and object structures (except the brackets),
     * we will parse both of these objects with this function.
     * @param tokenizer The tokenizer to use.
     * @return The parsed entity.
     */
    private static Map<String, Entity> readNested(final Tokenizer tokenizer) {
        final Map<String, Entity> result = new HashMap<>();
        while (true) {
            final Token token = tokenizer.getToken();
            if (token instanceof BracketToken) {
                final BracketToken bracket = (BracketToken) token;
                if (bracket.getState() == BracketToken.BracketType.CLOSE) {
                    break;
                }
            }
            assert token instanceof StringToken;
            final String name = ((StringToken) token).getValue();
            tokenizer.next();
            assert tokenizer.getToken() instanceof ArrowToken;
            tokenizer.next();
            final Entity entity = readOne(tokenizer);
            result.put(name, entity);
            tokenizer.next();
        }
        return result;
    }

    /**
     * Reads one entity.
     * @param tokenizer The tokenizer to use.
     * @return The parsed entity.
     */
    private static Entity readOne(final Tokenizer tokenizer) {
        final Token token = tokenizer.getToken();
        if (!(token instanceof StringToken)) {
            throw new IllegalArgumentException("Expected string token");
        }
        final String value = ((StringToken) token).getValue();
        final Entity result;
        if (isEmpty(value)) {
            result = new Empty();
        } else if (isLocator(value)) {
            result = new Locator(value);
        } else if (isData(value)) {
            result = new Data(Integer.parseInt(value.substring(2), 16));
        } else if (isLambda(value)) {
            result = new Lambda(value);
        } else if (value.contains(")")) {
            if (!isObject(value)) {
                throw new IllegalArgumentException("Expected object");
            }
            result = new FlatObject(
                value.substring(0, value.indexOf('(')),
                value.substring(value.indexOf('(') + 1, value.indexOf(')'))
            );
        } else if (value.contains("(")) {
            if (!isObject(value)) {
                throw new IllegalArgumentException("Expected object");
            }
            tokenizer.next();
            final Map<String, Entity> application = readNested(tokenizer);
            result = new ObjectWithApplication(
                value.substring(0, value.indexOf('(')), application
            );
        } else {
            if (!isObject(value)) {
                throw new IllegalArgumentException("Expected object");
            }
            result = new FlatObject(value);
        }
        return result;
    }

    /**
     * Parses one line.
     * @param line The line to parse.
     * @param result The result map.
     */
    private static void parseOneLine(
        final String line,
        final Map<String, Map<String, Entity>> result
    ) {
        final Tokenizer tokenizer = new Tokenizer(line);
        final Token token = tokenizer.getToken();
        String name = ((StringToken) token).getValue();
        name = name.substring(0, name.indexOf('('));
        tokenizer.next();
        tokenizer.next();
        tokenizer.next();
        final Map<String, Entity> bindings = readNested(tokenizer);
        result.put(name, bindings);
    }

    /**
     * Parses the input.
     * @return The parsed map.
     */
    public Map<String, Map<String, Entity>> parse() {
        final String[] lines = this.input.replace(",", "").split("\n");
        final Map<String, Map<String, Entity>> result = new HashMap<>();
        for (final String line : lines) {
            parseOneLine(line, result);
        }
        return result;
    }

    /**
     * Constructor.
     * @param input The input to parse.
     */
    public Parser(final String input) {
        this.input = input;
    }
}
