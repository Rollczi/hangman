package dev.rollczi.hangman;

import dev.rollczi.hangman.view.HangmanGameInput;
import dev.rollczi.hangman.view.HangmanGameView;
import dev.rollczi.hangman.word.HangmanWordRegistry;
import dev.rollczi.hangman.word.HangmanWordSelector;

import java.util.*;

public class HangmanManager {

    private final HangmanWordRegistry wordRegistry;
    private final HangmanWordSelector wordSelector;
    private final HangmanGameInput interact;
    private final HangmanGameView view;

    private final Map<UUID, HangmanGame> games = new HashMap<>();

    public HangmanManager(HangmanWordRegistry wordRegistry, HangmanWordSelector wordSelector, HangmanGameInput interact, HangmanGameView view) {
        this.wordRegistry = wordRegistry;
        this.wordSelector = wordSelector;
        this.interact = interact;
        this.view = view;
    }

    public void startGame(HangmanGame hangmanGame) {
        while (true) {
            view.clear();
            view.displayGame(hangmanGame);

            if (hangmanGame.isFinished()) {
                break;
            }

            char guess = interact.readGuess();
            GuessResult guessResult = hangmanGame.guessLetter(guess);

            view.displayResult(hangmanGame, guessResult);

            if (guessResult == GuessResult.WIN) {
                break;
            }
        }

        games.remove(hangmanGame.getUuid());
    }

    public HangmanGame createGame() {
        String word = wordSelector.selectWord(wordRegistry.getWords());
        HangmanGame hangmanGame = new HangmanGame(word, 10);

        games.put(hangmanGame.getUuid(), hangmanGame);

        return hangmanGame;
    }

    public Set<UUID> getGames() {
        return Collections.unmodifiableSet(games.keySet());
    }

}
