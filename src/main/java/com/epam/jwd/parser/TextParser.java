package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;
import com.epam.jwd.entity.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    private static final String SPLIT_PATTERN = "\\R(?=[^\\t\\n]+\\{)|(?<=[^\\t]})\\R|\\R(?=[0-9]+\\.)|\\R(?=[A-Z])";
    private static final String CODE_PATTERN = "^.+\\{[\\w\\W]+?^}";

    /**
     * Divides the text into paragraphs.
     * If the paragraph is a block of code, then the final element is added.
     * Otherwise, processing continues in the next parser.
     * @param source - text to be processed
     * @return processed text in the form of a Container
     */
    @Override
    public Container parse(String source) {
        Container text = new Container(ComponentType.TEXT);
        String[] paragraphs = source.split(SPLIT_PATTERN);
        for (String paragraph : paragraphs) {
            if (isCode(paragraph)) {
                text.add(new Element(ComponentType.CODE, paragraph));
                continue;
            }
            text.add(parseNext(paragraph));
        }
        return text;
    }

    /**
     * Determines whether the text is a block of code.
     * @param text - the text to be defined
     * @return true if it is a code block otherwise false
     */
    private boolean isCode(String text) {
        Pattern pattern = Pattern.compile(CODE_PATTERN, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
