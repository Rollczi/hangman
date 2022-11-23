package dev.rollczi.hangman.view;

import dev.rollczi.hangman.GuessResult;
import dev.rollczi.hangman.HangmanGame;

public interface HangmanGameView {

    void displayResult(HangmanGame game, GuessResult result);

    void clear();

    void displayGame(HangmanGame hangmanGame);

}
