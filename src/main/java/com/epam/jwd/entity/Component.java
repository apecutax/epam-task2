package com.epam.jwd.entity;

import java.util.List;

public abstract class Component {

    private final ComponentType type;

    public Component(ComponentType type) {
        this.type = type;
    }

    public ComponentType getType() {
        return type;
    }

    public abstract void add(Component child);
    public abstract void remove(Component child);
    public abstract List<Component> getChildren();
    public abstract String getValue();
    public abstract String toString();
}
