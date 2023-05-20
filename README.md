# Flatty

This project describes an optimization method for removing nesting from programs written in EO programming language. The nested structures in the language are composed of objects that form hierarchical structures, which can make it difficult to perform static analysis of such structures. The proposed method reframes expressions in such a way that they no longer contain nested objects, making it easier to perform static analysis. The project provides a formal proof of the correctness of the optimization method.
### Run unit-tests

    $  mvn clean install -Pqulice

### Run integration tests

    $  python3 src/test/java/org/objectionary/integration/tester.py <path_to_phi_dir>