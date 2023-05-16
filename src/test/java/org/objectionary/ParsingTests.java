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
import org.objectionary.entities.Entity;

import java.util.Map;

/**
 * Parsing tests.
 *
 * @since 0.1.0
 */
final class ParsingTests {

    private static String objectsTreeToString(Map<String, Map<String, Entity>> objects) {
        StringBuilder final_buffer = new StringBuilder();

        for (Map.Entry<String, Map<String, Entity>> object_entry : objects.entrySet()) {
            final_buffer.append(object_entry.getKey());
            final_buffer.append(" ↦ ⟦ ");
            int size = object_entry.getValue().size();
            int count = 0;
            for (Map.Entry<String, Entity> binding : object_entry.getValue().entrySet()) {
                final_buffer.append(binding.getKey());
                final_buffer.append(" ↦ ");
                final_buffer.append(binding.getValue());
                if (++count < size) {
                    final_buffer.append(", ");
                }
            }
            final_buffer.append(" ⟧\n");
        }

        return final_buffer.toString();
    }

    @Test
    void printingTest() {
        String output = objectsTreeToString(
                Parser.parse(
                        "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν3(𝜋) ⟧\n" +
                                "ν1(𝜋) ↦ ⟦ Δ ↦ 0x002A ⟧\n" +
                                "ν2(𝜋) ↦ ⟦ λ ↦ int-add, ρ ↦ 𝜋.𝛼0, 𝛼0 ↦ 𝜋.𝛼1 ⟧\n" +
                                "ν3(𝜋) ↦ ⟦ 𝜑 ↦ ν2(ξ), 𝛼0 ↦ ν1(𝜋), 𝛼1 ↦ ν1(𝜋) ⟧\n" +
                                "ν5(𝜋) ↦ ⟦ 𝜑 ↦ ν3(ξ) ⟧"
                )
        );
        System.out.println(output);
        MatcherAssert.assertThat(
                output,
                Matchers.equalTo(
                        "ν0 ↦ ⟦ 𝜑 ↦ ν3(𝜋) ⟧\nν1 ↦ ⟦ Δ ↦ 42 ⟧\nν2 ↦ ⟦ ρ ↦ 𝜋.𝛼0, λ ↦ int-add, 𝛼0 ↦ 𝜋.𝛼1 ⟧\nν3 ↦ ⟦ 𝜑 ↦ ν2(ξ), 𝛼1 ↦ ν1(𝜋), 𝛼0 ↦ ν1(𝜋) ⟧\nν5 ↦ ⟦ 𝜑 ↦ ν3(ξ) ⟧\n"
                )
        );
    }

    @Test
    void printingTest2() {
        String output = objectsTreeToString(
                Parser.parse(
                        "ν0(𝜋) ↦ ⟦ 𝜑 ↦ ν1( x ↦ ν2( y ↦ 0x0007 ) ) ⟧\n" +
                                "ν1(𝜋) ↦ ⟦ x ↦ ø ⟧\n" +
                                "ν2(𝜋) ↦ ⟦ y ↦ ø ⟧"
                )
        );
        System.out.println(output);
        MatcherAssert.assertThat(
                output,
                Matchers.equalTo(
                        "ν0 ↦ ⟦ 𝜑 ↦ ν1(x ↦ ν2(y ↦ 7)) ⟧\nν1 ↦ ⟦ x ↦ ø ⟧\nν2 ↦ ⟦ y ↦ ø ⟧\n"
                )
        );
    }


}
