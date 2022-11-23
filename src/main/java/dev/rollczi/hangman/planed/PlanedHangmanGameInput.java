package dev.rollczi.hangman.planed;

import dev.rollczi.hangman.view.HangmanGameInput;

import java.util.Iterator;
import java.util.List;

public class PlanedHangmanGameInput implements HangmanGameInput {

    private final Iterator<Character> iterator;

    public PlanedHangmanGameInput(List<Character> planed) {
        this.iterator = planed.listIterator();
    }

    public PlanedHangmanGameInput(Character... planed) {
        this(List.of(planed));
    }

    @Override
    public char readGuess() {
        if (this.iterator.hasNext()) {
            return this.iterator.next();
        }

        return Character.MIN_VALUE;
    }

}
