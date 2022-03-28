package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;

public class ParagraphParser extends Parser {

    private final static String PATTERN = "(?<=[.!?:]) (?=[A-Z])";

    @Override
    public Container parse(String source) {
        String[] sentences = source.split(PATTERN);
        Container paragraph = new Container(ComponentType.PARAGRAPH);
        for (String sentence : sentences) {
            paragraph.add(parseNext(sentence));
        }
        return paragraph;
    }
}
