package dev.rollczi.hangman.gui;

import dev.rollczi.hangman.GuessResult;
import dev.rollczi.hangman.HangmanGame;
import dev.rollczi.hangman.view.HangmanGameInput;
import dev.rollczi.hangman.view.HangmanGameView;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GuiHangmanGameViewInput implements HangmanGameView, HangmanGameInput {

    private final Map<GuessResult, String> messages = Map.of(
            GuessResult.WRONG, "Wrong letter!",
            GuessResult.ALREADY_GUESSED, "You already guessed this letter!",
            GuessResult.CORRECT, "Correct letter!",
            GuessResult.WIN, "You win!",
            GuessResult.LOSE, "You lose!"
    );

    private final JFrame frame;
    private final JTextArea textArea;
    private final JTextField field;
    private final JButton button;

    private CompletableFuture<Character> completable = new CompletableFuture<>();

    public GuiHangmanGameViewInput() {
        frame = new JFrame("Hangman");
        frame.setSize(250, 250);
        frame.setLocation(300,200);

        textArea = new JTextArea(5, 40);
        frame.getContentPane().add(BorderLayout.NORTH, textArea);

        field = new JTextField(1);
        frame.getContentPane().add(BorderLayout.CENTER, field);

        button = new JButton("Próba");
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        button.addActionListener(e -> {
            String text = field.getText();

            if (text.isEmpty()) {
                return;
            }

            field.setText("");
            completable.complete(text.charAt(0));
            completable = new CompletableFuture<>();
        });

        frame.setVisible(true);
    }

    @Override
    public void displayResult(HangmanGame game, GuessResult result) {
        textArea.append(messages.get(result));
    }

    @Override
    public void clear() {
        textArea.setText("");
    }

    @Override
    public void displayGame(HangmanGame hangmanGame) {
        textArea.append("Word: " + hangmanGame.getViewString() + "\n");
        textArea.append("Lives: " + hangmanGame.getLives() + "\n");
    }

    @Override
    public char readGuess() {
        try {
            return completable.get();
        }
        catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }
}
