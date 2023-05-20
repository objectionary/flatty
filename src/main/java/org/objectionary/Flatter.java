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
import org.objectionary.entities.Entity;
import org.objectionary.entities.FlatObject;
import org.objectionary.entities.ObjectWithApplication;

/**
 * This class realizes the flattening of the objects.
 * @since 0.1.0
 */
public final class Flatter {

    /**
     * The counter of created objects.
     */
    private static int counter;

    /**
     * The objects box.
     */
    private final ObjectsBox box;

    /**
     * Constructor.
     * @param box The objects box.
     */
    public Flatter(final ObjectsBox box) {
        this.box = box;
    }

    /**
     * Flattens the objects.
     * @return The flattened objects box.
     */
    public ObjectsBox flat() {
        Flatter.counter = findMaxIndex(box) + 1;
        boolean found = true;
        while (found) {
            found = false;
            for (
                final Map.Entry<String, Map<String, Entity>> entry : this.box.getBox().entrySet()
            ) {
                for (final Map.Entry<String, Entity> binding : entry.getValue().entrySet()) {
                    if (binding.getValue() instanceof ObjectWithApplication) {
                        flatOne(
                            this.box,
                            binding.getKey(),
                            (ObjectWithApplication) binding.getValue(),
                            entry.getValue()
                        );
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        return this.box;
    }

    /**
     * Flattens one object.
     * @param box The box which contains all objects.
     * @param key The name of binding to this non-flat object.
     * @param object The non-flat object itself.
     * @param user The object which contains the non-flat object.
     */
    private static void flatOne(
        final ObjectsBox box,
        final String key,
        final ObjectWithApplication object,
        final Map<String, Entity> user
    ) {
        final Map<String, Entity> bindings = deepCopy(box.getObject(object.getName()));
        final Map<String, Entity> application = deepCopy(object.getApplication());
        final Map<String, Entity> reframed = deepReframe(application);
        for (final Map.Entry<String, Entity> entry : reframed.entrySet()) {
            if (bindings.containsKey(entry.getKey())) {
                bindings.put(entry.getKey(), entry.getValue());
            }
        }
        final String name = String.format("ν%d", Flatter.counter);
        Flatter.counter += 1;
        box.putObject(name, bindings);
        user.put(key, new FlatObject(name, "ξ"));
        // TODO add graph transformation
    }

    /**
     * Finds the maximum index of the objects.
     * @param box The objects box.
     * @return The maximum index of the objects.
     */
    private static int findMaxIndex(final ObjectsBox box) {
        return box.getBox().keySet().stream()
            .map(key -> Integer.parseInt(key.substring(1)))
            .max(Integer::compareTo).orElse(0);
    }

    /**
     * Returns the deep copy of the bindings.
     * @param bindings The bindings.
     * @return The deep copy of the bindings.
     */
    private static Map<String, Entity> deepCopy(final Map<String, Entity> bindings) {
        final Map<String, Entity> result = new HashMap<>(bindings.size());
        for (final Map.Entry<String, Entity> entry : bindings.entrySet()) {
            result.put(entry.getKey(), entry.getValue().copy());
        }
        return result;
    }

    /**
     * Returns the deep reframe of the bindings.
     * @param bindings The bindings.
     * @return The deep reframe of the bindings.
     */
    private static Map<String, Entity> deepReframe(final Map<String, Entity> bindings) {
        final Map<String, Entity> result = new HashMap<>(bindings.size());
        for (final Map.Entry<String, Entity> entry : bindings.entrySet()) {
            result.put(entry.getKey(), entry.getValue().reframe());
        }
        return result;
    }

}
