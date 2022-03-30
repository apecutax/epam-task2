package com.epam.jwd.runner;

import com.epam.jwd.entity.Component;
import com.epam.jwd.file.Reader;
import com.epam.jwd.file.Writer;
import com.epam.jwd.parser.ParagraphParser;
import com.epam.jwd.parser.Parser;
import com.epam.jwd.parser.SentenceParser;
import com.epam.jwd.parser.TextParser;
import com.epam.jwd.util.ComponentUtil;

import java.io.IOException;
import java.util.List;

public class App {

    private final static String INPUT_FILE_PATH = "input.txt";
    private final static String OUTPUT_FILE_PATH = "output.txt";

    public static void main(String[] args) {
        try {
            String source = Reader.readFile(INPUT_FILE_PATH);
            Parser parser = getParserChain();
            Component text = parser.parse(source);
            Writer.writeFile(OUTPUT_FILE_PATH, text.toString());

            ComponentUtil.sortSentencesByNumberOfWords(text);
            List<String> uniqueWordsFromSentence = ComponentUtil.getUniqueWordsFromSentence(text, 0);
            List<String> wordsFromInterrogativeSentences = ComponentUtil.getWordsFromInterrogativeSentences(text, 6);
            System.out.println(uniqueWordsFromSentence);
            System.out.println(wordsFromInterrogativeSentences);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parser getParserChain() {
        Parser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        return textParser;
    }
}
