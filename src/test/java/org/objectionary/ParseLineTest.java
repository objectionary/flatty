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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.objectionary.parsing.LineParser;

/**
 * Line parser test.
 *
 * @since 0.1.0
 */
final class ParseLineTest {

    /**
    * Simple parsing test.
    */
    @Disabled
    @Test
    void simpleParsingTest() {
        final ObjectsBox box = new ObjectsBox();
        final String line = "ν0(𝜋) ↦ ⟦ λ ↦ int-neg, ρ ↦ 𝜋.𝛼0 ⟧";
        final LineParser parser = new LineParser(box);
        parser.parseLine(line);
        MatcherAssert.assertThat(parser.toString(), Matchers.equalTo(line));
    }

    /**
     * Simple nested test.
     */
    @Disabled
    @Test
    void simpleNestedTest() {
        final ObjectsBox box = new ObjectsBox();
        final String line = "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν2( a ↦ ξ.x ) ⟧";
        final LineParser parser = new LineParser(box);
        parser.parseLine(line);
        MatcherAssert.assertThat(parser.toString(), Matchers.equalTo(line));
    }

}
