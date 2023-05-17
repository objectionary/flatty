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

import org.objectionary.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the objects box.
 * @since 0.1.0
 */
public final class ObjectsBox {
    private final HashMap<String, HashMap<String, Entity>> box;

    /**
     * Constructor.
     */
    public ObjectsBox() {
        this(new HashMap<>());
    }

    /**
     * Constructor.
     * @param box The box.
     */
    public ObjectsBox(final HashMap<String, HashMap<String, Entity>> box) {
        this.box = box;
    }

    /**
     * Puts an object into the box.
     * @param name The name of the object.
     * @param bindings The bindings of the object.
     */
    public void putObject(final String name, final HashMap<String, Entity> bindings) {
        this.box.put(name, bindings);
    }

    @Override
    public String toString() {
        final List<String> results = new ArrayList<>();
        for (final Map.Entry<String, HashMap<String, Entity>> entry : box.entrySet()) {
            final StringBuilder builder = new StringBuilder();
            builder.append(entry.getKey());
            builder.append(" ↦ ⟦ ");
            final int size = entry.getValue().size();
            int count = 0;
            for (final Map.Entry<String, Entity> binding : entry.getValue().entrySet()) {
                builder.append(binding.getKey());
                builder.append(" ↦ ");
                builder.append(binding.getValue());
                count += 1;
                if (count < size) {
                    builder.append(", ");
                }
            }
            builder.append(" ⟧");
            results.add(builder.toString());
        }
        return String.join("\n", results);
    }

}
