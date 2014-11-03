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
import main.java.pcgod01.io.*;

public final class Main {
    private final static String USAGE = "Usage: repeat <options> <move-file> [puzzle-file]\n"
    + "use -h for a list of possible options";

    private final static String HELP = "Usage: repeat <options> <move-file> [puzzle-file]\n"
    + "where possible options include:\n"
    + "  -h         Print a sysnopsis of standard options\n"
    + "  -help      Synonymous to -h\n"
    + "  -m <index> Index of move in file\n"
    + "  -p <index> Index of puzzle in file\n"
    + "  -o <file>  Output file\n";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println(Main.HELP);
            System.exit(1);
        }

        int index = 0;
        int moveIndex = 0;
        int puzzleIndex = 0;
        String outPath = "";
        boolean sameFile = true;

        try {
            while (args[index].startsWith("-")) {
                switch(args[index]) {
                    case "-o":
                        outPath = args[index + 1];
                        index += 2;
                        break;
                    case "-m":
                        moveIndex = Integer.parseInt(args[index + 1]);
                        index += 2;
                        break;
                    case "-p":
                        puzzleIndex = Integer.parseInt(args[index + 1]);
                        index += 2;
                        break;
                    case "-help": // FALLTHROUGH
                    case "-h":
                        System.out.println(Main.HELP);
                        System.exit(0);
                        break;
                    default:
                        System.err.println("repeat: invalid flag: " + args[0]);
                        System.err.println(Main.USAGE);
                        System.exit(1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println(Main.USAGE);
            System.exit(1);
        }
        
        int count = args.length - index;

        if (count < 1 || count > 2) {
            System.err.println(Main.USAGE);
            System.exit(1);
        } else if (count == 2) {
            sameFile = false;
        }

        int repeats;
        String path = args[index];
        String realPath = path;

        if (XMLCompatible.isRelative(path)) {
            realPath = "../" + path;
        }
        
        MoveSequence ms = new MoveSequence();
        PuzzleBuilder pb = new PuzzleBuilder();
        boolean success = ms.readXML(realPath, moveIndex);

        if (!success) {
            System.err.println("Failed to read file " + path);
            System.exit(1);
        }

        if (!sameFile) {
            path = args[index + 1];
            realPath = path;

            if (XMLCompatible.isRelative(path)) {
                realPath = "../" + path;
            }
        }
            
        success = pb.readXML(realPath, puzzleIndex);

        if (!success) {
            System.err.println("Failed to read file " + path);
            System.exit(1);
        }

        Move[] moves = ms.getMoves();
        Puzzle puzzle = pb.getPuzzle();
        repeats = Repeater.getRepeats(puzzle, moves);
        

        if (!outPath.isEmpty()) {
            if (XMLCompatible.isRelative(outPath)) {
                outPath = "../" + outPath;
            }
            
            ms.setRepeats(repeats);
            ms.writeXML(outPath);
            pb.writeXML(outPath);
        }
        
        System.out.println(repeats);
        System.exit(0);
    }
}
