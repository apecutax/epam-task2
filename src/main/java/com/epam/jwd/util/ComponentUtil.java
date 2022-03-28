package com.epam.jwd.util;

import com.epam.jwd.entity.Component;
import com.epam.jwd.entity.ComponentType;
import com.epam.jwd.entity.Container;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComponentUtil {
    
    public static Component getAll(Component component, ComponentType type) {
        Container result = new Container(type); // correct type?
        for (Component child : component.getChildren()) {
            if (child.getType() == type) {
                result.add(child);
            }
            Component list = getAll(child, type);
            if (!isEmpty(list)) {
                result.add(list);
            }
        }
        return result;
    }

    public static void sortSentencesByNumberOfWords(Component text) {
        List<Component> sentences = ComponentUtil.getAll(text, ComponentType.SENTENCE).getChildren();
        sentences.sort(Comparator.comparingInt(a -> a.getChildren().size()));
    }

    public static List<String> secondTask(Component text) {
        Component sentences = ComponentUtil.getAll(text, ComponentType.SENTENCE);
        List<String> result = new ArrayList<>();
        if (size(sentences) < 2) {
            return result;
        }
        Component referenceSentence = sentences.getChildren().get(0);
        sentences.getChildren().remove(referenceSentence);
        for (Component child : referenceSentence.getChildren()) {
            if (!contains(sentences, child.getValue())) {
                result.add(child.getValue());
            }
        }
        return result;
    }

    public static boolean isEmpty(Component component) {
        return size(component) == 0;
    }

    public static int size(Component component) {
        return component.getChildren().size();
    }

    public static boolean contains(Component component, String value) {
        for (Component child : component.getChildren()) {
            if (child.getValue().equals(value) || contains(child, value)) {
                return true;
            }
        }
        return false;
    }
}
