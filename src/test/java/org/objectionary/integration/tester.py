import subprocess
import os
import sys

JAVA_FILES = [
    "main/java/org/objectionary/entities/Data.java",
    "main/java/org/objectionary/entities/Empty.java",
    "main/java/org/objectionary/entities/Entity.java",
    "main/java/org/objectionary/entities/FlatObject.java",
    "main/java/org/objectionary/entities/Lambda.java",
    "main/java/org/objectionary/entities/Locator.java",
    "main/java/org/objectionary/entities/ObjectWithApplication.java",
    "main/java/org/objectionary/tokens/ArrowToken.java",
    "main/java/org/objectionary/tokens/BracketToken.java",
    "main/java/org/objectionary/tokens/StringToken.java",
    "main/java/org/objectionary/tokens/Token.java",
    "main/java/org/objectionary/Flatter.java",
    "main/java/org/objectionary/ObjectsBox.java",
    "main/java/org/objectionary/Parser.java",
    "main/java/org/objectionary/Tokenizer.java",
    "main/java/org/objectionary/Tester.java",
]

def walk_files(directory):
    for root, dirs, files in os.walk(directory):
        for f in files:
            file_path = os.path.abspath(os.path.join(root, f))
            run_java(file_path)
            flat_file = file_path.replace("input", "flat")
            with open(file_path, 'r') as file:
                correct = file.readline().split("\n")[0]
            run_rust(flat_file, correct)
            subprocess.run(["rm", flat_file])

def run_java(test_name):
    compiled_files = []
    command = ["javac"]
    for file_path in JAVA_FILES:
        command.append(file_path)
        class_name = file_path.split(".")[0]
        compiled_files.append(class_name)
    subprocess.run(command, cwd="src")
    subprocess.run(["java", "org.objectionary.Tester", test_name], cwd="src/main/java")
    for compiled_file in compiled_files:
        file_path = compiled_file + ".class"
        subprocess.run(["rm", file_path], cwd="src")

def run_rust(test_name, correct):
    directory = sys.argv[1]
    subprocess.run(["cargo", "build", "--release"], cwd=directory)
    subprocess.run(["target/release/custom_executor", test_name, correct], cwd=directory)

walk_files("src/test/java/org/objectionary/integration/resources")
