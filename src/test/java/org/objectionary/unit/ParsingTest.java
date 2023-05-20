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
package org.objectionary.unit;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.objectionary.Parser;

/**
 * Test skeleton.
 * @since 0.1.0
 */
final class ParsingTest {

    /**
     * Test parsing.
     */
    @Test
    void printingTest() {
        final String[] input = {
            "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν3(𝜋) ⟧",
            "ν1(𝜋) ↦ ⟦ Δ ↦ 0x002A ⟧",
            "ν2(𝜋) ↦ ⟦ λ ↦ int-add, ρ ↦ 𝜋.𝛼0, 𝛼0 ↦ 𝜋.𝛼1 ⟧",
            "ν3(𝜋) ↦ ⟦ 𝜑 ↦ ν2(ξ), 𝛼0 ↦ ν1, 𝛼1 ↦ ν1 ⟧",
            "ν5(𝜋) ↦ ⟦ 𝜑 ↦ ν3(ξ) ⟧",
        };
        final String[] correct = {
            "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν3(𝜋) ⟧",
            "ν1(𝜋) ↦ ⟦ Δ ↦ 0x002A ⟧",
            "ν2(𝜋) ↦ ⟦ λ ↦ int-add, ρ ↦ 𝜋.𝛼0, 𝛼0 ↦ 𝜋.𝛼1 ⟧",
            "ν3(𝜋) ↦ ⟦ 𝜑 ↦ ν2(ξ), 𝛼1 ↦ ν1(𝜋), 𝛼0 ↦ ν1(𝜋) ⟧",
            "ν5(𝜋) ↦ ⟦ 𝜑 ↦ ν3(ξ) ⟧",
        };
        final Parser parser = new Parser(String.join("\n", input));
        final boolean equals = parser.parse().toString().equals(String.join("\n", correct));
        MatcherAssert.assertThat(
            equals,
            Matchers.equalTo(true)
        );
    }

    /**
     * Test parsing with nested application.
     */
    @Test
    void printingWithNestingTest() {
        final String[] input = {
            "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν1( x ↦ ν2( y ↦ 0x0007 ) ) ⟧",
            "ν1(𝜋) ↦ ⟦ x ↦ ø ⟧",
            "ν2(𝜋) ↦ ⟦ y ↦ ø ⟧",
        };
        final String[] correct = {
            "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν1(x ↦ ν2(y ↦ 0x0007)) ⟧",
            "ν1(𝜋) ↦ ⟦ x ↦ ø ⟧",
            "ν2(𝜋) ↦ ⟦ y ↦ ø ⟧",
        };
        final Parser parser = new Parser(String.join("\n", input));
        final boolean equals = parser.parse().toString().equals(String.join("\n", correct));
        MatcherAssert.assertThat(
            equals,
            Matchers.equalTo(true)
        );
    }
}
