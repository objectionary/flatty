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
package org.objectionary.parsing;

import java.util.HashMap;
import java.util.Map;
import org.objectionary.Tokenizer;
import org.objectionary.entities.Entity;

/**
 * Entities reader.
 * @since 0.1.0
 * @checkstyle NonStaticMethodCheck (100 lines)
 */
public final class EntityRead {

    /**
     * The tokenizer.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Tokenizer tokenizer;

    /**
     * Constructor.
     * @param tokenizer The tokenizer.
     */
    public EntityRead(final Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Reads one entity.
     * @return The parsed entity.
     * @todo #49:30min Implement this method.
     *  This method should read one entity recursively.
     */
    public Entity readOne() {
        return null;
    }

    /**
     * Reads nested entity.
     * @return The parsed nested entity.
     * @todo #49:30min Implement this method.
     *  This method should read nested entity recursively.
     */
    public Map<String, Entity> readNested() {
        return new HashMap<>();
    }
}
