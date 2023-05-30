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

/**
 * This class represents the objects box.
 * @since 0.1.0
 */
public final class ObjectsBox {

    /**
     * The box of objects.
     */
    private final Map<String, Map<String, Entity>> box;

    /**
     * Constructor.
     */
    public ObjectsBox() {
        this.box = new HashMap<>();
    }

    /**
     * Puts an object into the box.
     * @param name The name of the object.
     * @param bindings The bindings of the object.
     */
    public void putObject(final String name, final Map<String, Entity> bindings) {
        this.box.put(name, bindings);
    }

    /**
     * Gets an object by name.
     * @param name The name of the object.
     * @return The object.
     */
    public Map<String, Entity> getObject(final String name) {
        return this.box.get(name);
    }

    /**
     * Gets the box of objects.
     * @return The box of objects.
     */
    public Map<String, Map<String, Entity>> content() {
        return this.box;
    }

    /**
     * Converts the box of objects to a string.
     * @checkstyle NoJavadocForOverriddenMethodsCheck (10 lines)
     * @todo #25:30min We have to implement the toString() method.
     *  It should return a string representation of the objects box,
     *  which will be used in phi emulator.
     */
    @Override
    public String toString() {
        return null;
    }

}
