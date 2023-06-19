package org.objectionary;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.objectionary.entities.*;
import org.objectionary.parsing.Reader;

/**
 * Entities reader test.
 *
 * @since 0.1.0
 * @todo #49:30min Add more tests for Reader.
 *  It should be both reading one entity and nested entities tests.
 *  Also we have to enable this tests.
 */
final class ReaderTest {

    @Disabled
    @Test
    void readOneEmptyTest() {
        final String input = "ø";
        final Reader reader = new Reader(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.readOne(),
            Matchers.instanceOf(Empty.class)
        );
    }

    @Disabled
    @Test
    void readOneLocatorTest() {
        final String input = "𝜋.𝜋.𝜋.x";
        final Reader reader = new Reader(new Tokenizer(input));
        MatcherAssert.assertThat(
            reader.readOne(),
            Matchers.instanceOf(Locator.class)
        );
    }
}