package dev.rollczi.hangman.console;

import dev.rollczi.hangman.GuessResult;
import dev.rollczi.hangman.HangmanGame;
import dev.rollczi.hangman.view.HangmanGameView;

import java.util.Map;

public class ConsoleHangmanGameView implements HangmanGameView {

    private final Map<GuessResult, String> messages = Map.of(
            GuessResult.WRONG, "Wrong letter!",
            GuessResult.ALREADY_GUESSED, "You already guessed this letter!",
            GuessResult.CORRECT, "Correct letter!",
            GuessResult.WIN, "You win!",
            GuessResult.LOSE, "You lose!"
    );


    @Override
    public void displayResult(HangmanGame game, GuessResult result) {
        System.out.println(messages.get(result));
    }

    @Override
    public void clear() {
        for (int line = 0; line < 30; line++) {
            System.out.println();
        }
    }

    @Override
    public void displayGame(HangmanGame hangmanGame) {
        System.out.println("Word: " + hangmanGame.getViewString());
        System.out.println("Lives: " + hangmanGame.getLives());
    }

}
