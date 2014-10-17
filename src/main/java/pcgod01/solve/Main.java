package main.java.pcgod01.solve;

import main.java.pcgod01.puzzle.Move;
import main.java.pcgod01.puzzle.Puzzle;
import main.java.pcgod01.puzzle.cube.Cube;
import main.java.pcgod01.repeat.Repeater;

public class Main {
	public static final void main(String[] args) {
        Move[] moves = new Move[] {new Move("x"), new Move("U"),
                                   new Move("R'"), new Move("U'")};
        Puzzle cube = new Cube(3);
        System.out.println(Repeater.getRotation(cube, moves));
	}
}
