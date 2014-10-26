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
