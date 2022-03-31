package com.epam.jwd.util;

import com.epam.jwd.entity.Component;
import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponentUtil {

    private static final String QUESTION_PATTERN = "[\\w\\W]+\\?";

    public List<Component> getAll(Component component, ComponentType type) {
        List<Component> result = new ArrayList<>();
        for (Component child : component.getChildren()) {
            if (child.getType() == type) {
                result.add(child);
            }
            result.addAll(getAll(child, type));
        }
        return result;
    }

    public Component getSentencesByNumberOfWords(Component text) { // task option - 2
        List<Component> sentences = getAll(text, ComponentType.SENTENCE);
        sentences.sort(Comparator.comparingInt(sentence -> sentence.getChildren().size()));
        return pack(sentences, ComponentType.TEXT);
    }

    public List<String> getUniqueWordsFromSentence(Component text, int numberOfSentence) { // task option - 3
        List<Component> sentences = getAll(text, ComponentType.SENTENCE);
        if (sentences.size() < 2
                || numberOfSentence < 0
                || numberOfSentence >= sentences.size()) {
            throw new IllegalArgumentException();
        }
        Component referenceSentence = sentences.get(numberOfSentence);
        sentences.remove(referenceSentence);
        Component paragraph = pack(sentences, ComponentType.PARAGRAPH);
        return referenceSentence.getChildren()
                .stream()
                .filter(word -> !contains(paragraph, word))
                .map(Component::getValue)
                .toList();
    }

    public List<String> getWordsFromQuestions(Component text, int lengthOfWords) { // task option - 4
        List<Component> sentences = getAll(text, ComponentType.SENTENCE);
        sentences.removeIf(component -> !isInterrogativeSentence(component));
        Component paragraph = pack(sentences, ComponentType.PARAGRAPH);
        List<Component> words = getAll(paragraph, ComponentType.WORD);
        return words.stream()
                .map(Component::getValue)
                .filter(word -> word.length() == lengthOfWords)
                .toList();
    }

    public boolean contains(Component component, Component value) {
        for (Component child : component.getChildren()) {
            if ((child.getType() == value.getType() && child.equals(value))
                    || contains(child, value)) {
                return true;
            }
        }
        return false;
    }

    public Component pack(List<Component> children, ComponentType type) {
        Component result = new Container(type);
        for (Component child : children) {
            result.add(child);
        }
        return result;
    }

    public boolean isInterrogativeSentence(Component sentence) {
        Pattern pattern = Pattern.compile(QUESTION_PATTERN);
        Matcher matcher = pattern.matcher(sentence.toString());
        return matcher.find();
    }
}
