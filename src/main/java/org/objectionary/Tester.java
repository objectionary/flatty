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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is temporary here.
 * It will be removed once the integration tests are implemented.
 *
 * @since 0.1.0
 */
public final class Tester {

    /**
     * Constructor.
     */
    private Tester() {
        // Utility class
    }

    /**
     * Runs the integration tests.
     *
     * @param args The command line arguments
     */
    public static void main(final String[] args) {
        final List<String> lines = new ArrayList<>(1);
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (final IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
        final Parser parser = new Parser(String.join("\n", lines));
        final Flatter flatter = new Flatter(parser.parse());
        final String output = flatter.flat().toString();
        try (
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(args[0].replace("input", "flat"))
            )
        ) {
            writer.write(output);
            writer.flush();
        } catch (final IOException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }
}
