package com.epam.jwd.entity;

public enum ComponentType {
    TEXT("\n"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    CODE(""),
    WORD("");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Returns a delimiter for this type of component.
     * Used to convert a chain of components into text.
     * @return a delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }
}
