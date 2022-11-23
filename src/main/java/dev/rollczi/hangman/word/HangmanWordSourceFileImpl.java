package dev.rollczi.hangman.word;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class HangmanWordSourceFileImpl implements HangmanWordSource {

    private final File file;

    HangmanWordSourceFileImpl(File file) {
        this.file = file;
    }

    @Override
    public List<String> getWords() {
        try(Scanner reader = new Scanner(file)) {
            List<String> words = new ArrayList<>();

            if (reader.hasNextLine()) {
                words.add(reader.nextLine());
            }

            return Collections.unmodifiableList(words);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
