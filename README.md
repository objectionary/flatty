# Flatty

This project describes an optimization method for removing nesting from programs written in phi-calculus. The nested structures in the language are composed of objects that form hierarchical structures, which can make it difficult to perform static analysis of such structures. The proposed method reframes expressions in such a way that they no longer contain nested objects, making it easier to perform static analysis.

### Quick start

```
import org.objectionary.Flatter;
import org.objectionary.Parser;

String[] lines = {
    "ν1(𝜋) ↦ ⟦ x ↦ ν3, 𝜑 ↦ ν2( a ↦ ξ.x, b ↦ ξ.x ) ⟧",
    "ν2(𝜋) ↦ ⟦ λ ↦ int-times, a ↦ ø, b ↦ ø ⟧",
    "ν3(𝜋) ↦ ⟦ Δ ↦ 0x0007 ⟧",
};
Parser parser = new Parser(String.join("\n", lines));
Flatter flatter = new Flatter(parser.parse());
String result = flatter.flat().toString();
```

### Installation
To use Flatty in your project, you can follow the steps below:

1. Download the Flatty repository.

2. Add the Flatty JAR file to your project's classpath.

3. Import the necessary classes in your Java code:
```
import flatty.Parser;
import flatty.Flatter;
```

### Usage

The basic usage of Flatty involves the following steps:

1. Use the Parser class to parse the input program written in EO.

2. Create a Flatter object by passing the parsed program to it.

3. Execute the flat method to optimize the program.

Retrieve the optimized code as a string.

### Examples

You can find examples of some nested objects in the `src/test/java/org/objectionary/integration/resourses` directory.

### Run unit-tests

    $  mvn clean install -Pqulice

### Run integration tests

    $  git clone https://github.com/objectionary/phie
    $  python3 src/test/java/org/objectionary/integration/tester.py <path_to_phie_dir>
