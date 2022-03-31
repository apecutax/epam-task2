package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Container extends Component {

    private final List<Component> children = new ArrayList<>();

    public Container(ComponentType type) {
        super(type);
    }

    /**
     * Adds a component to the list of children.
     * @param child - component to add to the list
     */
    public void add(Component child) {
        children.add(child);
    }

    /**
     * Removes a component to the list of children.
     * @param child - component to remove to the list
     */
    public void remove(Component child) {
        children.remove(child);
    }

    /**
     * Returns a list of children
     * @return a list of children
     */
    public List<Component> getChildren() {
        return children;
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a copy of the component as well as the entire chain of its children.
     * @return a copy of the component
     */
    @Override
    public Component clone() {
        Container container = new Container(getType());
        for (Component child : children) {
            container.add(child.clone());
        }
        return container;
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Container container = (Container) object;
        return toString().equals(container.toString());
    }

    @Override
    public String toString() {
        return children.stream()
                .map(Component::toString)
                .collect(Collectors.joining(getType().getDelimiter()));
    }
}