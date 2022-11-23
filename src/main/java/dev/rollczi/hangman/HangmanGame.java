package dev.rollczi.hangman;

import java.util.*;

public class HangmanGame {

    private final UUID uuid;
    private final String word;
    private int lives;

    private final Set<Character> coveredCharacters = new HashSet<>();

    public HangmanGame(String word, int lives) {
        this.uuid = UUID.randomUUID();
        this.word = word;
        this.lives = lives;

        for (char character : word.toCharArray()) {
            this.coveredCharacters.add(character);
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getViewString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            builder.append(coveredCharacters.contains(letter) ? '_' : letter);
        }

        return builder.toString();
    }

    public GuessResult guessLetter(char letter) {
        if (!word.contains(String.valueOf(letter))) {
            lives--;

            if (this.isLose()) {
                return GuessResult.LOSE;
            }

            return GuessResult.WRONG;
        }

        boolean isRemoved = coveredCharacters.remove(letter);

        if (!isRemoved) {
            return GuessResult.ALREADY_GUESSED;
        }

        if (this.isWin()) {
            return GuessResult.WIN;
        }

        return GuessResult.CORRECT;
    }

    public String getWord() {
        return word;
    }

    public int getLives() {
        return lives;
    }

    public boolean isWin() {
        if (this.isLose()) {
            return false;
        }

        return coveredCharacters.isEmpty();
    }

    public boolean isLose() {
        return lives <= 0;
    }

    public boolean isFinished() {
        return this.isLose() || this.isWin();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HangmanGame that = (HangmanGame) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
