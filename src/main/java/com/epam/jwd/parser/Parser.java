package com.epam.jwd.parser;

import com.epam.jwd.entity.Container;

public abstract class Parser {

    private Parser next;

    public abstract Container parse(String source);

    public void setNext(Parser next) {
        this.next = next;
    }

    protected Container parseNext(String source) {
        if (next == null) {
            throw new NullPointerException();
        }
        return next.parse(source);
    }
}
