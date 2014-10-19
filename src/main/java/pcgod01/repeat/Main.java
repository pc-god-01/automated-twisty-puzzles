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
import main.java.pcgod01.io.XMLLoader;

public final class Main {
    private final static String USAGE = "Usage: repeat move-file [puzzle-file]";

    // TODO options
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.err.println(USAGE);
            System.exit(1);
        }

        for (String command : args) {
            if (command.isEmpty()) {
                System.err.println(USAGE);
                System.exit(1);
            }
        }

        XMLLoader loader = new XMLLoader(args[0]);
        Move[] moves = loader.getMoveSequence();

        if (args.length == 2 && !args[1].isEmpty()) {
            loader.close();
        }
        
        // TODO Load puzzle class
        Puzzle puzzle = new Cube(3);
        // TODO Calclate result
        int repeats = Repeater.getRepeats(puzzle, moves);

        System.out.println(repeats);
        loader.close();
        System.exit(0);
    }
}
