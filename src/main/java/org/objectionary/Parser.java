package org.objectionary;

import java.util.HashMap;
import java.util.Map;
import org.objectionary.entities.*;
import org.objectionary.tokens.ArrowToken;
import org.objectionary.tokens.BracketToken;
import org.objectionary.tokens.StringToken;
import org.objectionary.tokens.Token;

public class Parser {

  private static boolean isEmpty(final String token) {
    return token.equals("ø");
  }

  private static boolean isLocator(final String token) {
    return token.startsWith("𝜋") || token.startsWith("ξ");
  }

  private static boolean isData(final String token) {
    return token.startsWith("0x");
  }

  private static boolean isLambda(final String token) {
    return token.startsWith("bool") || token.startsWith("int");
  }

  private static boolean isObject(final String token) {
    return token.startsWith("ν");
  }

  /**
   * Since there is no difference in the application and object structures (except the brackets), we
   * will parse both of these objects with this function.
   */
  private static Map<String, Entity> readNested(final Tokenizer tokenizer) {
    final Map<String, Entity> result = new HashMap<>();

    while (true) {
      final Token token = tokenizer.getToken();

      if (token instanceof BracketToken) {
        final BracketToken bracketToken = (BracketToken) token;

        if (bracketToken.getState() == BracketToken.BracketType.CLOSE) {
          break;
        }
      }

      assert token instanceof StringToken;
      final String name = ((StringToken) token).getValue();

      tokenizer.next();
      assert tokenizer.getToken() instanceof ArrowToken;
      tokenizer.next();

      final Entity entity = readOne(tokenizer);

      result.put(name, entity);

      tokenizer.next();
    }

    return result;
  }

  private static Entity readOne(Tokenizer tokenizer) {
    final Token token = tokenizer.getToken();
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
      return new FlatObject(
          value.substring(0, value.indexOf("(")),
          value.substring(value.indexOf("(") + 1, value.indexOf(")")));
    }

    // v( ... )
    tokenizer.next();
    final Map<String, Entity> application = readNested(tokenizer);
    return new ObjectWithApplication(value.substring(0, value.indexOf("(")), application);
  }

  private static void parseOneLine(String line, Map<String, Map<String, Entity>> result) {
    final Tokenizer tokenizer = new Tokenizer(line);

    final Token token = tokenizer.getToken();

    String name = ((StringToken) token).getValue();
    name = name.substring(0, name.indexOf("("));

    tokenizer.next();
    tokenizer.next();
    tokenizer.next();

    final Map<String, Entity> bindings = readNested(tokenizer);

    result.put(name, bindings);
  }

  public static Map<String, Map<String, Entity>> parse(String input) {
    final String[] lines = input.replace(",", "").split("\n");

    final Map<String, Map<String, Entity>> result = new HashMap<>();

    for (String line : lines) {
      parseOneLine(line, result);
    }

    return result;
  }
}
