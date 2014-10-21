package main.java.pcgod01.io;

import org.w3c.dom.*;

import main.java.pcgod01.puzzle.Puzzle;

public interface PuzzleBuilder<T extends Puzzle> {
    public T buildFromXML(Element args);
    public T build(Object... args);
}
