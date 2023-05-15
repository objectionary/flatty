package org.objectionary;

import org.objectionary.entities.*;
import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

	private static boolean isEmpty (String token) {
		return token.equals("ø");
	}

	private static boolean isLocator (String token) {
		return token.startsWith("𝜋") || token.startsWith("ξ");
	}

	private static boolean isData (String token) {
		return token.startsWith("0x");
	}

	private static boolean isLambda (String token) {
		return token.startsWith("bool") || token.startsWith("int");
	}

	private static boolean isObject (String token) {
		return token.startsWith("ν");
	}

	/**
	 * Since there is no difference in the application and
	 * object structures (except the brackets), we will parse
	 * both of these objects with this function.
	 */
	private static Map<String, Entity> readNested(Tokenizer tokenizer) {
		Map<String, Entity> result = new HashMap<>();

		while (true) {
			Token token = tokenizer.getToken();

			if (token instanceof BracketToken) {
				BracketToken bracketToken = (BracketToken) token;

				if (bracketToken.getState() == BracketToken.BracketType.CLOSE) {
					break;
				}
			}

			assert token instanceof StringToken;
			String name = ((StringToken) token).getValue();

			tokenizer.next();
			assert tokenizer.getToken() instanceof ArrowToken;
			tokenizer.next();

			Entity entity = readOne(tokenizer);

			result.put(name, entity);

			tokenizer.next();
		}

		return result;
	}

	private static Entity readOne (Tokenizer tokenizer) {
		Token token = tokenizer.getToken();
		if (!(token instanceof StringToken)) {
			throw new RuntimeException("Expected string token");
		}
		String value = ((StringToken) token).getValue();

		if (isEmpty(value)) {
			return new Empty();
		}

		if (isLocator(value)) {
			return new Locator(value);
		}

		if (isData(value)) {
			return new Data(Integer.parseInt(value.substring(2), 16));
		}

		if (isLambda(value)) {
			return new Lambda(value);
		}

		if (!isObject(value)) {
			throw new RuntimeException("Unknown token: " + value);
		}

		// without application
		if (!value.contains("(")) {
			return new FlatObject(value);
		}

		// v(pi) or v(xi)
		if (value.contains(")")) {
			return new FlatObject(value.substring(0, value.indexOf("(")), value.substring(value.indexOf("(") + 1, value.indexOf(")")));
		}

		// v( ... )
		tokenizer.next();
		Map<String, Entity> application = readNested(tokenizer);
		return new ObjectWithApplication(value.substring(0, value.indexOf("(")), application);
	}

	private static void parseOneLine (String line, Map<String, Map<String, Entity>> result) {
		Tokenizer tokenizer = new Tokenizer(line);

		Token token = tokenizer.getToken();

		String name = ((StringToken) token).getValue();
		name = name.substring(0, name.indexOf("("));

		tokenizer.next();
		tokenizer.next();
		tokenizer.next();

		Map<String, Entity> bindings = readNested(tokenizer);

		result.put(name, bindings);
	}

	public static Map<String, Map<String, Entity>> parse(String input) {
		String[] lines = input.replace(",", "").split("\n");

		Map<String, Map<String, Entity>> result = new HashMap<>();

		for (String line : lines) {
			parseOneLine(line, result);
		}

		return result;
	}

}
