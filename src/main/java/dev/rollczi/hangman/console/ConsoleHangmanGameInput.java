package dev.rollczi.hangman.console;

import dev.rollczi.hangman.view.HangmanGameInput;

import java.util.Scanner;

public class ConsoleHangmanGameInput implements HangmanGameInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public char readGuess() {
        String line = scanner.nextLine();

        if (line.length() == 0) {
            return readGuess();
        }

        return line.charAt(0);
    }

}
