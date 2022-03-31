package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element extends Component {

    private static final String CLEANING_PATTERN = "[^A-Za-z0-9']+";
    private static final String REPLACEMENT = "";

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

    /**
     * Returns the value of the component without punctuation marks and special characters.
     * It is also used for a more correct comparison.
     * @return the value of the component
     */
    public String getValue() {
        return value.replaceAll(CLEANING_PATTERN, REPLACEMENT);
    }

    /**
     * Returns a copy of the component.
     * @return a copy of the component
     */
    @Override
    public Component clone() {
        return new Element(getType(), value);
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

    /**
     * Returns the value of the component with punctuation marks and special characters.
     * @return the value of the component
     */
    @Override
    public String toString() {
        return value;
    }
}
