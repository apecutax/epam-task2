package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element extends Component {

    private final static String PATTERN = "[^A-Za-z0-9']+";
    private final static String REPLACEMENT = "";

    private final String value;

    public Element(ComponentType type, String value) {
        super(type);
        this.value = value;
    }

    @Override
    public void add(Component child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component child) {
        throw new UnsupportedOperationException();
    }

    public List<Component> getChildren() {
        return new ArrayList<>();
    }

    public String getValue() {
        return value.replaceAll(PATTERN, REPLACEMENT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Element element = (Element) object;
        return getValue().equals(element.getValue());
    }

    @Override
    public String toString() {
        return value;
    }
}
