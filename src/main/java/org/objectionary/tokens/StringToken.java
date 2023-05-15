package org.objectionary.tokens;

public class StringToken extends Token{
	String value;

	public StringToken(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
