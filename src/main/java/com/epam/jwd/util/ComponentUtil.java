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

    private static final String INTERROGATIVE_SENTENCE_PATTERN = "[\\w\\W]+\\?";

    public static List<Component> getAll(Component component, ComponentType type) {
        List<Component> result = new ArrayList<>();
        for (Component child : component.getChildren()) {
            if (child.getType() == type) {
                result.add(child);
            }
            result.addAll(getAll(child, type));
        }
        return result;
    }

    public static void sortSentencesByNumberOfWords(Component text) { // task option - 2
        List<Component> sentences = ComponentUtil.getAll(text, ComponentType.SENTENCE);
        sentences.sort(Comparator.comparingInt(a -> a.getChildren().size()));
    }

    public static List<String> getUniqueWordsFromSentence(Component text, int numberOfSentence) { // task option - 3
        List<Component> sentences = ComponentUtil.getAll(text, ComponentType.SENTENCE);
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

    public static List<String> getWordsFromInterrogativeSentences(Component text, int lengthOfWords) { // task option - 4
        List<Component> sentences = ComponentUtil.getAll(text, ComponentType.SENTENCE);
        sentences.removeIf(component -> !isInterrogativeSentence(component));
        List<Component> words = ComponentUtil.getAll(pack(sentences, ComponentType.PARAGRAPH), ComponentType.WORD);
        return words.stream()
                .map(Component::getValue)
                .filter(word -> word.length() == lengthOfWords)
                .toList();
    }

    public static boolean contains(Component component, Component value) {
        for (Component child : component.getChildren()) {
            if ((child.getType() == value.getType() && child.equals(value))
                    || contains(child, value)) {
                return true;
            }
        }
        return false;
    }

    public static Component pack(List<Component> children, ComponentType type) {
        Component result = new Container(type);
        for (Component child : children) {
            result.add(child);
        }
        return result;
    }

    public static boolean isInterrogativeSentence(Component sentence) {
        Pattern pattern = Pattern.compile(INTERROGATIVE_SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(sentence.toString());
        return matcher.find();
    }
}
