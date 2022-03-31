package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;

public class ParagraphParser extends Parser {

    private static final String SPLIT_PATTERN = "(?<=[.!?:]) (?=[A-Z])";

    /**
     * Divides a paragraph into sentences.
     * @param source - text to be processed
     * @return processed text in the form of a Container
     */
    @Override
    public Container parse(String source) {
        String[] sentences = source.split(SPLIT_PATTERN);
        Container paragraph = new Container(ComponentType.PARAGRAPH);
        for (String sentence : sentences) {
            paragraph.add(parseNext(sentence));
        }
        return paragraph;
    }
}
