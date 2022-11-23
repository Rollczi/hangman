package dev.rollczi.hangman;

import dev.rollczi.hangman.console.ConsoleHangmanGameView;
import dev.rollczi.hangman.planed.PlanedHangmanGameInput;
import dev.rollczi.hangman.word.HangmanWordRegistry;
import dev.rollczi.hangman.word.HangmanWordSelector;
import dev.rollczi.hangman.word.HangmanWordSource;


public class Main {

    public static void main(String[] args) {
        HangmanWordSource wordSource = HangmanWordSource.of("siema");
        HangmanWordRegistry registry = new HangmanWordRegistry();
        registry.addWords(wordSource);

        HangmanWordSelector selector = HangmanWordSelector.random();

        ConsoleHangmanGameView view = new ConsoleHangmanGameView();
        PlanedHangmanGameInput input = new PlanedHangmanGameInput('s', 'i', 'e', 'm', 'a');

        HangmanManager hangmanManager = new HangmanManager(registry, selector, input, view);
        HangmanGame game = hangmanManager.createGame();

        hangmanManager.startGame(game);
    }

}
