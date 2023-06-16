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
import org.junit.jupiter.api.Test;
import org.objectionary.parsing.TypeChecker;

/**
 * TypeChecker test.
 *
 * @since 0.1.0
 */
final class TypeCheckerTest {

    @Test
    void isEmptyTest() {
        MatcherAssert.assertThat(
            new TypeChecker("ø").isEmpty(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("ø()").isEmpty(),
            Matchers.is(false)
        );
    }

    @Test
    void isLocatorTest() {
        MatcherAssert.assertThat(
            new TypeChecker("𝜋.𝜋.𝜋.x").isLocator(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("ξ.y").isLocator(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("ν0").isLocator(),
            Matchers.is(false)
        );
    }

    @Test
    void isDataTest() {
        MatcherAssert.assertThat(
            new TypeChecker("0x1234").isData(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("42").isData(),
            Matchers.is(false)
        );
    }

    @Test
    void isLambdaTest() {
        MatcherAssert.assertThat(
            new TypeChecker("int-times").isLambda(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("bool-if").isLambda(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("42").isLambda(),
            Matchers.is(false)
        );
    }

    @Test
    void isObjectTest() {
        MatcherAssert.assertThat(
            new TypeChecker("ν0(𝜋)").isObject(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("ν1(ξ)").isObject(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("ν2").isObject(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new TypeChecker("x(𝜋)").isObject(),
            Matchers.is(false)
        );
        MatcherAssert.assertThat(
            new TypeChecker("y").isObject(),
            Matchers.is(false)
        );
    }
}
