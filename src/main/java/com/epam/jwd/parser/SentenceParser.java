package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;
import com.epam.jwd.entity.Element;

public class SentenceParser extends Parser {

    private final static String PATTERN = "\s";

    @Override
    public Container parse(String source) {
        String[] words = source.split(PATTERN);
        Container sentence = new Container(ComponentType.SENTENCE);
        for (String word : words) {
            sentence.add(new Element(ComponentType.WORD, word));
        }
        return sentence;
    }
}
