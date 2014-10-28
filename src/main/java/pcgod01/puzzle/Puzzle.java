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
package main.java.pcgod01.puzzle;

import java.util.Map;

/**
 * A twisty puzzle that can be fully manipulated.
 * 
 * @author  Greg Brown <pc-god-01@users.noreply.github.com>
 * @version 0.1
 * @since   0.1
 */
public abstract class Puzzle implements Cloneable {
    /**
     * Applies a move to a puzzle.
     * 
     * @param  move the move to apply to the puzzle
     * @return the puzzle with the move applied
     * @throws IllegalArgumentException if move is invalid
     */
    public abstract Puzzle applyMove(Move move);
    
    /**
     * Sets the properties of a puzzle.
     *
     * @param properties the properties of the puzzle
     * @return the puzzle with the applied properties
     */
    public abstract Puzzle setProperties(Map<String, String> properties);

    /**
     * Returns a deep-clone of the puzzle.
     *
     * @see java.lang.Object#clone()
     */
    @Override
    public abstract Object clone();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean equals(Object o);
}
