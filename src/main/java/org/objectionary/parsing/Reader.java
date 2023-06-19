package org.objectionary.parsing;

import org.objectionary.Tokenizer;
import org.objectionary.entities.Entity;

import java.util.Map;

/**
 * Entities reader.
 */
public final class Reader {

    /**
     * The tokenizer.
     */
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Tokenizer tokenizer;

    public Reader(final Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Reads one entity.
     * @return The parsed entity.
     * @todo #49:30min Implement this method.
     *  This method should read one entity recursively.
     */
    public Entity readOne() {
        return null;
    }

    /**
     * Reads nested entity.
     * @return The parsed nested entity.
     * @todo #49:30min Implement this method.
     *  This method should read nested entity recursively.
     */
    public Map<String, Entity> readNested() {
        return null;
    }
}
