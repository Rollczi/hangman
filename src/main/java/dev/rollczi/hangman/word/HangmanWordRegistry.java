package dev.rollczi.hangman.word;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HangmanWordRegistry {

    private final Set<String> words = new HashSet<>();

    public void addWord(String word) {
        words.add(word);
    }

    public void addWords(String... words) {
        for (String word : words) {
            addWord(word);
        }
    }

    public void addWords(HangmanWordSource wordSource) {
        this.words.addAll(wordSource.getWords());
    }

    public Set<String> getWords() {
        return Collections.unmodifiableSet(words);
    }

}
