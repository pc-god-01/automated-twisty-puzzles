/**
 * When given a twisty puzzle and a sequence of moves, this program outputs
 * the number of times the moves need to be repeated to return to the
 * original.
 * Copyright (C) 2014  Greg Brown
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.java.pcgod01.repeat;

import main.java.pcgod01.puzzle.Move;
import main.java.pcgod01.puzzle.Puzzle;
import main.java.pcgod01.puzzle.cube.Cube;

public final class Main {
    private final static String USAGE = "Usage: repeat move-file puzzle-file";
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(USAGE);
            System.exit(1);
        }

        for (String command : args) {
            if (command.isEmpty()) {
                System.err.println(USAGE);
                System.exit(1);
            }
        }
        // TODO Load move file
        Move[] moves = new Move[] {new Move("R'"), new Move("U"),
                                   new Move("R"), new Move("U'")};
        // TODO Load puzzle class
        Puzzle puzzle = new Cube(3);
        // TODO Calclate result
        int repeats = Repeater.getRepeats(puzzle, moves);
        System.out.println(repeats);
        System.exit(0);
    }
}
