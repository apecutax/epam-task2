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

    public void add(Component child) {
        children.add(child);
    }

    public void remove(Component child) {
        children.remove(child);
    }

    public List<Component> getChildren() {
        return children;
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException();
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
                .collect(Collectors.joining(getType().getValue()));
    }
}