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

import java.util.Arrays;
import java.util.List;

/**
 * This class represents the locator entity.
 * @since 0.1.0
 */
public final class Locator extends Entity {

    /**
     * The path of the locator.
     */
    private final String path;

    /**
     * Constructor.
     * @param path The path of the locator.
     */
    public Locator(final String path) {
        this.path = path;
    }

    /**
     * Returns the path of the locator.
     * @return The path of the locator.
     */
    public List<String> getPath() {
        return Arrays.asList(this.path.split("\\."));
    }

    @Override
    public String toString() {
        return this.path;
    }

    @Override
    public Entity copy() {
        return new Locator(String.join(".", this.getPath()));
    }

    @Override
    public Entity reframe() {
        return new Locator("𝜋.".concat(String.join(".", this.path).replace("ξ.", "")));
    }
}