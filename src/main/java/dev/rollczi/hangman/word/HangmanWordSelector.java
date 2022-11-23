package dev.rollczi.hangman.word;

import java.util.*;

public interface HangmanWordSelector {

    String selectWord(Collection<String> words);

    static HangmanWordSelector random() {
        return new HangmanRandomWordSelectorImpl();
    }

}
