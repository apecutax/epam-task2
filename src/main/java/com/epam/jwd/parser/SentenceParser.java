package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;
import com.epam.jwd.entity.Element;

public class SentenceParser extends Parser {

    private static final String SPLIT_PATTERN = "\s";

    /**
     * Divides sentences into words. Punctuation marks and special characters are part of the word.
     * @param source - text to be processed
     * @return processed text in the form of a Container
     */
    @Override
    public Container parse(String source) {
        String[] words = source.split(SPLIT_PATTERN);
        Container sentence = new Container(ComponentType.SENTENCE);
        for (String word : words) {
            sentence.add(new Element(ComponentType.WORD, word));
        }
        return sentence;
    }
}
