package main.java.pcgod01.repeat;

import main.java.pcgod01.puzzle.Puzzle;
import main.java.pcgod01.puzzle.Move;

public final class Repeater {
    public static int getRepeats(Puzzle puzzle, Move[] moves) {
        int tries = 0;
        Puzzle copy = (Puzzle) puzzle.clone();

        do {
            for (Move move : moves) {
                puzzle.applyMove(move);
            }

            tries++;
        } while (!puzzle.equals(copy));

        return tries;
    }
}
