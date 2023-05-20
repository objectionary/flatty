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

/**
 * This class represents the flat object entity.
 * @since 0.1.0
 */
public final class FlatObject extends Entity {

    /**
     * The name of the object.
     */
    private final String name;

    /**
     * The locator of the object.
     */
    private final String locator;

    /**
     * Constructor.
     *
     * @param name The name of the object.
     */
    public FlatObject(final String name) {
        this(name, "𝜋");
    }

    /**
     * Constructor.
     * @param name The name of the object.
     * @param locator The locator of the object.
     */
    public FlatObject(final String name, final String locator) {
        this.name = name;
        this.locator = locator;
    }

    /**
     * Returns the name of the object.
     * @return The name of the object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the locator of the object.
     * @return The locator of the object.
     */
    public String getLocator() {
        return this.locator;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", this.getName(), this.getLocator());
    }

    @Override
    public Entity copy() {
        return new FlatObject(this.getName(), this.getLocator());
    }

    @Override
    public Entity reframe() {
        return new FlatObject(this.getName(), "𝜋".concat(this.getLocator()));
    }
}
