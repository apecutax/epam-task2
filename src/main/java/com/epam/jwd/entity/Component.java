package com.epam.jwd.entity;

import java.util.List;

public abstract class Component implements Cloneable {

    private final ComponentType type;

    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Returns the specified component type
     * @return the specified component type
     */
    public ComponentType getType() {
        return type;
    }

    public abstract void add(Component child);
    public abstract void remove(Component child);
    public abstract List<Component> getChildren();
    public abstract String getValue();
    @Override
    public abstract Component clone();
    public abstract String toString();


}
