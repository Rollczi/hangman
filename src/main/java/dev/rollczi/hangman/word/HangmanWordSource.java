package dev.rollczi.hangman.word;

import java.io.File;
import java.util.Collections;
import java.util.List;

public interface HangmanWordSource {

    List<String> getWords();

    static HangmanWordSource empty() {
        return Collections::emptyList;
    }

    static HangmanWordSource of(String... words) {
        return () -> List.of(words);
    }

    static HangmanWordSource of(List<String> words) {
        return () -> words;
    }

    static HangmanWordSource ofFile(File file) {
        return new HangmanWordSourceFileImpl(file);
    }

}
