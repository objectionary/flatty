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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.objectionary.Tokenizer;
import org.objectionary.entities.*;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

/**
 * Entities reader.
 * @since 0.1.0
 * @checkstyle NonStaticMethodCheck (100 lines)
 */
public final class Entities {

    /**
     * The tokenizer.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Tokenizer tokenizer;

    /**
     * Constructor.
     * @param tokenizer The tokenizer.
     */
    public Entities(final Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Reads one entity.
     * @return The parsed entity.
     */
    public Entity one() {
        final Token token = tokenizer.getToken();
        if (!(token instanceof StringToken)) {
            throw new IllegalArgumentException("Expected string token");
        }
        final String value = ((StringToken) token).getValue();
        final Entity result;
        final TypeChecker type = new TypeChecker(value);
        if (type.isEmpty()) {
            result = new Empty();
        } else if (type.isLocator()) {
            result = new Locator(value);
        } else if (type.isData()) {
            result = new Data(Integer.parseInt(value.substring(2), 16));
        } else if (type.isLambda()) {
            result = new Lambda(value);
        } else if (type.isObject()) {
            result = createObject(value);
        } else {
            System.out.println(value);
            throw new IllegalArgumentException("Unknown token");
        }
        return result;
    }

    /**
     * Reads nested entity.
     * @return The parsed nested entity.
     * @todo #49:30min Implement this method.
     *  This method should read nested entity recursively.
     */
    public Map<String, Entity> nested() {
        return new HashMap<>();
    }

    /**
     * Creates an object.
     * @param value The value to parse.
     * @return The parsed entity.
     */
    private Entity createObject(final String value) {
        final Entity result;
        if (value.contains(")")) {
            result = new FlatObject(
                value.substring(0, value.indexOf('(')),
                value.substring(value.indexOf('(') + 1, value.indexOf(')'))
            );
        } else if (value.contains("(")) {
            tokenizer.next();
            final Map<String, Entity> application = nested();
            result = new NestedObject(
                value.substring(0, value.indexOf('(')), application
            );
        } else {
            result = new FlatObject(value, "");
        }
        return result;
    }
}
