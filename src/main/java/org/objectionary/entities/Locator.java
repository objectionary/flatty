package org.objectionary.entities;

import java.util.Arrays;
import java.util.List;

public class Locator extends Entity {

	private final List<String> path;

	public Locator(String path) {
		this.path = Arrays.asList(path.split("\\."));
	}

	public Locator(List<String> path) {
		this.path = path;
	}

	public List<String> getPath() {
		return path;
	}

	@Override
	public String toString() {
		return String.join(".", path);
	}
}
