package main.java.pcgod01.puzzle.cube;

import org.w3c.dom.Element;

import main.java.pcgod01.io.PuzzleBuilder;

public final class Builder implements PuzzleBuilder<Cube> {
    @Override
    public Cube buildFromXML(Element args) {
        if (args.hasAttribute("sideLength")) {
            int sideLength = Integer.parseInt(args.getAttribute("sideLength"));
            return new Cube(sideLength);
        }
        
        return new Cube(3);
    }

    @Override
    public Cube build(Object... args) {
        if (args.length == 0) {
            return new Cube(3);
        }

        // TODO cube builder
        return null;
    }
}
