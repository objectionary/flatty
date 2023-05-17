package org.objectionary.entities;

import java.util.Map;

/**
 * This class represents the object with application entity.
 */
public class ObjectWithApplication extends Entity {

    /**
     * The name of the object with application.
     */
    private final String name;

    /**
     * The application of the object with application.
     */
    private final Map<String, Entity> application;

    /**
     * Constructor.
     *
     * @param name        The name of the object with application.
     * @param application The application of the object with application.
     */
    public ObjectWithApplication(String name, Map<String, Entity> application) {
        this.name = name;
        this.application = application;
    }

    /**
     * @return The name of the object with application.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The application of the object with application.
     */
    public Map<String, Entity> getApplication() {
        return application;
    }

    /**
     * @return The string representation of the object with application.
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        final int size = application.size();
        int count = 0;
        for (Map.Entry<String, Entity> entry : getApplication().entrySet()) {
            buffer.append(entry.getKey()).append(" ↦ ").append(entry.getValue());
            if (++count < size) {
                buffer.append(", ");
            }
        }
        return getName() + "(" + buffer + ")";
    }
}
