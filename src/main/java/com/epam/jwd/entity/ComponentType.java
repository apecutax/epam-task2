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

    public String getValue() {
        return delimiter;
    }
}
