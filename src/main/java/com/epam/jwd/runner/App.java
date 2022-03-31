package com.epam.jwd.runner;

import com.epam.jwd.entity.Component;
import com.epam.jwd.reader.Reader;
import com.epam.jwd.writer.Writer;
import com.epam.jwd.parser.ParagraphParser;
import com.epam.jwd.parser.Parser;
import com.epam.jwd.parser.SentenceParser;
import com.epam.jwd.parser.TextParser;
import com.epam.jwd.util.ComponentUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static final String INPUT_FILE_PATH = "input.txt";
    private static final String OUTPUT_FILE_PATH = "output.txt";
    private static final String SORTED_SENTENCES_PATH = "sorted_sentences.txt";
    private static final String UNIQUE_WORDS_PATH = "unique_words.txt";
    private static final String WORDS_FROM_QUESTIONS_PATH = "words_from_questions.txt";
    private static final int TARGET_SENTENCE_NUMBER = 0;
    private static final int TARGET_WORD_LENGTH = 6;

    public static void main(String[] args) {
        try {
            String source = Reader.readFile(INPUT_FILE_PATH);
            Parser parser = getParserChain();
            Component text = parser.parse(source);
            Writer.writeFile(OUTPUT_FILE_PATH, text.toString());

            ComponentUtil util = new ComponentUtil();
            Component sortedSentences = util.getSentencesByNumberOfWords(text.clone());
            Writer.writeFile(SORTED_SENTENCES_PATH, sortedSentences.toString());

            List<String> uniqueWords = util.getUniqueWordsFromSentence(text.clone(), TARGET_SENTENCE_NUMBER);
            Writer.writeFile(UNIQUE_WORDS_PATH, uniqueWords.toString());

            List<String> wordsFromQuestions = util.getWordsFromQuestions(text.clone(), TARGET_WORD_LENGTH);
            Writer.writeFile(WORDS_FROM_QUESTIONS_PATH, wordsFromQuestions.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Forms a chain of handlers.
     * @return the first parser in the chain
     */
    private static Parser getParserChain() {
        Parser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        return textParser;
    }
}
