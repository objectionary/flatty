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
package org.objectionary.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the object with application entity.
 * @since 0.1.0
 */
public final class ObjectWithApplication extends Entity {

    /**
     * The name of the object with application.
     */
    private final String name;

    /**
     * The application of the object with application.
     */
    private final Map<String, Entity> application;

    /**
     * Constructor.
     * @param name The name of the object with application.
     * @param application The application of the object with application.
     */
    public ObjectWithApplication(final String name, final Map<String, Entity> application) {
        this.name = name;
        this.application = application;
    }

    /**
     * Returns the name of the object with application.
     * @return The name of the object with application.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the application of the object with application.
     * @return The application of the object with application.
     */
    public Map<String, Entity> getApplication() {
        return this.application;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        final int size = this.application.size();
        int count = 0;
        for (final Map.Entry<String, Entity> entry : this.getApplication().entrySet()) {
            buffer.append(entry.getKey()).append(" ↦ ").append(entry.getValue());
            count += 1;
            if (count < size) {
                buffer.append(", ");
            }
        }
        return String.format("%s(%s)", this.getName(), buffer);
    }

    @Override
    public Entity copy() {
        final Map<String, Entity> copy = new HashMap<>(this.application.size());
        for (final Map.Entry<String, Entity> entry : this.application.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().copy());
        }
        return new ObjectWithApplication(this.getName(), copy);
    }

    @Override
    public Entity reframe() {
        final Map<String, Entity> reframed = new HashMap<>(this.application.size());
        for (final Map.Entry<String, Entity> entry : this.application.entrySet()) {
            reframed.put(entry.getKey(), entry.getValue().reframe());
        }
        return new ObjectWithApplication(this.getName(), reframed);
    }
}
