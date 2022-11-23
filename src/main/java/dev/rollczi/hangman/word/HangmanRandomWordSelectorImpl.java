package dev.rollczi.hangman.word;

import java.util.*;

public class HangmanRandomWordSelectorImpl implements HangmanWordSelector {

    private final Random random = new Random();
    private final Set<String> usedWords = new HashSet<>();

    @Override
    public String selectWord(Collection<String> words) {
        List<String> availableWords = new ArrayList<>(words);
        availableWords.removeAll(usedWords);

        if (availableWords.isEmpty()) {
            usedWords.clear();
            availableWords.addAll(words);
        }

        String word = availableWords.get(random.nextInt(availableWords.size()));
        usedWords.add(word);

        return word;
    }

}
