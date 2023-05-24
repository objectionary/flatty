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

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

/**
 * Tokenizer test.
 *
 * @since 0.1.0
 */
final class TokenizerTest {

    @Disabled
    @Test
    void tokenizerTest() {
        final String input = "ν1(𝜋) ↦ ⟦ 𝜑 ↦ ν2( a ↦ ξ.x ) ⟧";
        final Tokenizer tokenizer = new Tokenizer(input);
        MatcherAssert.assertThat(tokenizer.hasNext(), Matchers.equalTo(true));
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(StringToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(ArrowToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(BracketToken.class));
        MatcherAssert.assertThat(
            (BracketToken) tokenizer.getToken(), Matchers.equalTo(BracketToken.BracketType.OPEN)
        );
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(StringToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(ArrowToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(StringToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(StringToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(ArrowToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(StringToken.class));
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(BracketToken.class));
        MatcherAssert.assertThat(
            (BracketToken) tokenizer.getToken(), Matchers.equalTo(BracketToken.BracketType.CLOSE)
        );
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.getToken(), Matchers.instanceOf(BracketToken.class));
        MatcherAssert.assertThat(
            (BracketToken) tokenizer.getToken(), Matchers.equalTo(BracketToken.BracketType.CLOSE)
        );
        tokenizer.next();
        MatcherAssert.assertThat(tokenizer.hasNext(), Matchers.equalTo(false));
    }
}
