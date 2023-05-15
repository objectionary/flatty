package org.objectionary;

import org.objectionary.tokens.*;

/**
 * This class is working with one object.
 * All commas must be removed.
 */
public class Tokenizer {

	private final String[] tokens;
	private int position;


	public Tokenizer(String input) {
		this.tokens = input.split(" ");
		this.position = 0;
	}

	boolean hasNext() {
		return position < tokens.length;
	}

	void next() {
		++position;
	}

	Token getToken() {
		String token = tokens[position];

		if (token.equals("↦")) {
			return new ArrowToken();
		}

		if (token.equals("(") || token.equals("⟦")) {
			return new BracketToken(BracketToken.BracketType.OPEN);
		}
		if (token.equals(")") || token.equals("⟧")) {
			return new BracketToken(BracketToken.BracketType.CLOSE);
		}

		return new StringToken(token);
	}
}
