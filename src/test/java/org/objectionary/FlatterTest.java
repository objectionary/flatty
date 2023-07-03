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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.objectionary.entities.Data;
import org.objectionary.entities.Empty;
import org.objectionary.entities.Entity;
import org.objectionary.entities.FlatObject;
import org.objectionary.entities.Lambda;
import org.objectionary.entities.Locator;
import org.objectionary.entities.NestedObject;

/**
 * Test case for {@link Flatter}.
 * @since 0.1.0
 *
 * @todo #23:30min Enable several tests in this FlatterTest class.
 *  Since they are failed due to NPE, we need to fix them. The tests
 *  are disabled for now.
 */
final class FlatterTest {

    /**
     * Literal for v0.
     */
    private static final String INIT_OBJECT = "ν0";

    /**
     * Flatter can flatten empty object.
     */
    @Test
    void testFlatter() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("x", new Empty());
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat(),
            Matchers.equalTo(box)
        );
    }

    @Test
    void boxWithDataToStringTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("Δ", new Data(Integer.parseInt("000A", 16)));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString(),
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ Δ ↦ 0x000A ⟧")
        );
    }

    @Test
    void boxWithLocatorToStringTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("x", new Locator("𝜋.𝜋.y"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString(),
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ x ↦ 𝜋.𝜋.y ⟧")
        );
    }

    @Test
    @Disabled
    void boxWithFlatObjectToStringTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("y", new FlatObject("bar", "ξ"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString(),
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ y ↦ bar(ξ) ⟧")
        );
    }

    @Test
    void boxWithLambdaToStringTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("λ", new Lambda("Plus"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString(),
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ λ ↦ Plus ⟧")
        );
    }

    @Test
    @Disabled
    void boxWithNestedObjectToStringTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> application = new HashMap<>();
        application.put("x", new Locator("𝜋.𝜋.z"));
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("y", new NestedObject("v", application));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString(),
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ y ↦ v( x ↦ 𝜋.𝜋.z ) ⟧")
        );
    }

    @Test
    @Disabled
    void zeroObjectOrderTest() {
        final ObjectsBox box = new ObjectsBox();
        Map<String, Entity> bindings = new HashMap<>();
        bindings.put("x", new Empty());
        box.put("a", bindings);
        bindings = new HashMap<>();
        bindings.put("y", new Empty());
        box.put("b", bindings);
        bindings = new HashMap<>();
        bindings.put("z", new Empty());
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString().split("\n")[0],
            Matchers.equalTo("ν0(𝜋) ↦ ⟦ z ↦ ø ⟧")
        );
    }

    @Test
    @Disabled
    void deltaOrderTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("Δ", new Data(Integer.parseInt("000A", 16)));
        bindings.put("x", new Empty());
        bindings.put("y", new FlatObject("bar", "𝜋"));
        bindings.put("a", new Lambda("Atom"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString().split(" ")[3],
            Matchers.equalTo("Δ")
        );
    }

    @Test
    @Disabled
    void lambdaOrderTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("λ", new Data(Integer.parseInt("000A", 16)));
        bindings.put("a1", new Empty());
        bindings.put("a2", new FlatObject("bar", "𝜋"));
        bindings.put("a3", new Lambda("Atom"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString().split(" ")[3],
            Matchers.equalTo("λ")
        );
    }

    @Test
    @Disabled
    void phiOrderTest() {
        final ObjectsBox box = new ObjectsBox();
        final Map<String, Entity> bindings = new HashMap<>();
        bindings.put("𝜑", new Data(Integer.parseInt("000A", 16)));
        bindings.put("a", new Empty());
        bindings.put("b", new FlatObject("d", "𝜋"));
        bindings.put("c", new Lambda("Atom"));
        box.put(FlatterTest.INIT_OBJECT, bindings);
        final Flatter flatter = new Flatter(box);
        MatcherAssert.assertThat(
            flatter.flat().toString().split(" ")[3],
            Matchers.equalTo("𝜑")
        );
    }

}
