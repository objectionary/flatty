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

import java.util.Map;

/**
 * This class represents the object with application entity.
 * @since 0.1.0
 */
public final class NestedObject extends Entity {

    /**
     * The name of the object with application.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final String name;

    /**
     * The application of the object with application.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Map<String, Entity> application;

    /**
     * Constructor.
     * @param name The name of the object with application.
     * @param application The application of the object with application.
     */
    public NestedObject(final String name, final Map<String, Entity> application) {
        this.name = name;
        this.application = application;
    }
}
