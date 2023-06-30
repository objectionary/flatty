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

import java.util.*;

import org.objectionary.entities.Entity;
import org.objectionary.entities.FlatObject;
import org.objectionary.entities.Locator;
import org.objectionary.entities.NestedObject;

/**
 * This class realizes the flattening of the objects.
 * @since 0.1.0
 */
public final class Flatter {

    /**
     * The counter of created objects.
     */
    private static int counter;

    /**
     * The objects box.
     */
    private final ObjectsBox box;

    /**
     * Constructor.
     * @param box The objects box.
     */
    public Flatter(final ObjectsBox box) {
        this.box = box;
    }

    /**
     * Flattens the objects.
     * @return The flattened objects box.
     */
    public ObjectsBox flat() {
        Flatter.counter = findMaxIndex() + 1;
        boolean found = true;
        while (found) {
            found = false;
            for (
                    final Map.Entry<String, Map<String, Entity>> entry : this.box.content().entrySet()
            ) {
                for (final Map.Entry<String, Entity> binding : entry.getValue().entrySet()) {
                    if (binding.getValue() instanceof NestedObject) {
                        flatOne(
                                binding.getKey(),
                                (NestedObject) binding.getValue(),
                                entry.getValue()
                        );
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        removeUnusedObjects();
        resuspendLocalBinds();
        renameObjects();
        return this.box;
    }

    /**
     * Flattens one object.
     * @param key The name of binding to this non-flat object.
     * @param object The non-flat object itself.
     * @param user The object which contains the non-flat object.
     */
    private void flatOne(
            final String key,
            final NestedObject object,
            final Map<String, Entity> user
    ) {
        final Map<String, Entity> bindings = deepCopy(this.box.get(object.getName()));
        final Map<String, Entity> application = deepCopy(object.getApplication());
        final Map<String, Entity> reframed = deepReframe(application);
        for (final Map.Entry<String, Entity> entry : reframed.entrySet()) {
            if (bindings.containsKey(entry.getKey())) {
                bindings.put(entry.getKey(), entry.getValue());
            }
        }
        final String name = String.format("ν%d", Flatter.counter);
        Flatter.counter += 1;
        this.box.put(name, bindings);
        user.put(key, new FlatObject(name, "ξ"));
    }

    /**
     * Removes unused objects from the box.
     */
    private void removeUnusedObjects() {
        final Set<String> uses = new HashSet<>();

        final Queue<String> queue = new LinkedList<>();
        queue.add("ν0");

        while (!queue.isEmpty()) {
            final String name = queue.poll();
            uses.add(name);
            for (final Map.Entry<String, Entity> binding : this.box.get(name).entrySet()) {
                if (binding.getValue() instanceof FlatObject) {
                    final String objectName = ((FlatObject) binding.getValue()).getName();
                    if (!uses.contains(objectName)) {
                        queue.add(objectName);
                    }
                }
            }
        }

        this.box.content().entrySet().removeIf(entry -> !uses.contains(entry.getKey()));
    }

    /**
     * Takes all locators to local variables and clearly sets them up.
     */
    private void resuspendLocalBinds() {
        for (final Map<String, Entity> bindings : this.box.content().values()) {
            boolean found = true;
            while (found) {
                found = false;
                for (final Map.Entry<String, Entity> binding : bindings.entrySet()) {
                    if (binding.getValue() instanceof Locator) {
                        final List<String> locator = ((Locator) binding.getValue()).getPath();
                        if (!locator.get(0).equals("ξ")) {
                            continue;
                        }
                        final String name = locator.get(1);
                        final Entity entity = bindings.get(name);
                        bindings.remove(name);
                        bindings.put(binding.getKey(), entity);
                        found = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Renames the objects to use smaller indexes.
     */
    private void renameObjects() {
        final List<Integer> numbersWeHave = new ArrayList<>();
        for (final String name : this.box.content().keySet()) {
            numbersWeHave.add(Integer.parseInt(name.substring(1)));
        }
        Collections.sort(numbersWeHave);

        final int objectsInBoxCount = this.box.content().size();

        for (int i = 0; i < objectsInBoxCount;++i) {
            final String to = String.valueOf(i);
            final String from = String.valueOf(numbersWeHave.get(i));
            if (to.equals(from)) {
                continue;
            }
            changeName(String.format("ν%s", from), String.format("ν%s", to));
        }
    }

    private void changeName(final String from, final String to) {
        for (final Map.Entry<String, Map<String, Entity>> bindings : this.box.content().entrySet()) {
            for (final Map.Entry<String, Entity> binding : bindings.getValue().entrySet()) {
                if (
                        binding.getValue() instanceof FlatObject &&
                                ((FlatObject) binding.getValue()).getName().equals(from)
                ) {
                    binding.setValue(
                            new FlatObject(to, ((FlatObject) binding.getValue()).getLocator())
                    );
                }
            }
        }
        this.box.put(to, this.box.get(from));
        this.box.content().remove(from);
    }

    /**
     * Finds the maximum index of the objects.
     * @return The maximum index of the objects.
     */
    private int findMaxIndex() {
        return this.box.content().keySet().stream()
                .map(key -> Integer.parseInt(key.substring(1)))
                .max(Integer::compareTo).orElse(0);
    }

    /**
     * Returns the deep copy of the bindings.
     * @param bindings The bindings.
     * @return The deep copy of the bindings.
     */
    private static Map<String, Entity> deepCopy(final Map<String, Entity> bindings) {
        final Map<String, Entity> result = new HashMap<>(bindings.size());
        for (final Map.Entry<String, Entity> entry : bindings.entrySet()) {
            result.put(entry.getKey(), entry.getValue().copy());
        }
        return result;
    }

    /**
     * Returns the deep reframe of the bindings.
     * @param bindings The bindings.
     * @return The deep reframe of the bindings.
     */
    private static Map<String, Entity> deepReframe(final Map<String, Entity> bindings) {
        final Map<String, Entity> result = new HashMap<>(bindings.size());
        for (final Map.Entry<String, Entity> entry : bindings.entrySet()) {
            result.put(entry.getKey(), entry.getValue().reframe());
        }
        return result;
    }

}