package org.objectionary.entities;

import java.util.Map;

public class ObjectWithApplication extends Entity {

    private final String name;

    private final Map<String, Entity> application;

    public ObjectWithApplication(String name, Map<String, Entity> application) {
        this.name = name;
        this.application = application;
    }

    public String getName() {
        return name;
    }

    public Map<String, Entity> getApplication() {
        return application;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        int size = application.size();
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
