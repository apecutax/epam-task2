package com.epam.jwd.parser;

import com.epam.jwd.entity.Container;

public abstract class Parser {

    private Parser next;

    /**
     * The main method that should implement the parsing of the transmitted text.
     * @param source - text to be processed
     * @return processed text in the form of a Container
     */
    public abstract Container parse(String source);

    /**
     * Sets the following parser.
     * @param next - the next parser
     */
    public void setNext(Parser next) {
        this.next = next;
    }

    /**
     * Calls the next parser if it is specified.
     * @param source - text to be processed
     * @return processed text in the form of a Container
     */
    protected Container parseNext(String source) {
        if (next == null) {
            throw new NullPointerException();
        }
        return next.parse(source);
    }
}
