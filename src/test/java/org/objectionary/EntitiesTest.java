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
import org.objectionary.entities.Data;
import org.objectionary.entities.Empty;
import org.objectionary.entities.FlatObject;
import org.objectionary.entities.Lambda;
import org.objectionary.entities.Locator;
import org.objectionary.parsing.Entities;

/**
 * Entities reader test.
 *
 * @since 0.1.0
 */
final class EntitiesTest {

    @Disabled
    @Test
    void readOneEmptyTest() {
        final String input = "ø";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(Empty.class)
        );
    }

    @Disabled
    @Test
    void readOneLocatorTest() {
        final String input = "𝜋.𝜋.𝜋.x";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(Locator.class)
        );
    }

    @Disabled
    @Test
    void readOneLambdaTest() {
        final String input = "int-times";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(Lambda.class)
        );
    }

    @Disabled
    @Test
    void readOneDataTest() {
        final String input = "0x0042";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(Data.class)
        );
    }

    @Disabled
    @Test
    void readOneObjectTest() {
        final String input = "ν(𝜋)";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(FlatObject.class)
        );
    }

    @Disabled
    @Test
    void readOneFailedTest() {
        final String input = "...";
        final Entities reader = new Entities(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.one(),
            Matchers.instanceOf(IllegalArgumentException.class)
        );
    }
}
