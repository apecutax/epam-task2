package com.epam.jwd.parser;

import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;
import com.epam.jwd.entity.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    //private final static String PATTERN = "(^.+\\{[\\w\\W]+?^})|(^\\t.+)|(^[0-9]\\..+)|[A-Z][\\w\\W]+?[.!?:]";
    private final static String SPLIT_PATTERN = "\\R(?=[^\\t\\n]+\\{)|(?<=[^\\t]})\\R|\\R(?=[0-9]+\\.)|\\R(?=[A-Z])";
    private final static String CODE_PATTERN = "^.+\\{[\\w\\W]+?^}";

    @Override
    public Container parse(String source) {
        Container text = new Container(ComponentType.TEXT);
        String[] paragraphs = source.split(SPLIT_PATTERN);
        Pattern pattern = Pattern.compile(CODE_PATTERN, Pattern.MULTILINE);
        for (String paragraph : paragraphs) {
            Matcher matcher = pattern.matcher(paragraph);
            if (matcher.find()) {
                text.add(new Element(ComponentType.CODE, paragraph));
                continue;
            }
            text.add(parseNext(paragraph));
        }
        return text;
    }
}
