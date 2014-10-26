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

import java.util.Arrays;

// todo override 'toString' method
public abstract class Piece implements Cloneable {
    protected       Color[]   colors;
    protected final Validator validator;
    
    protected Piece(Color[] colors, Validator validator) {
        if (!validator.isColorValid(colors)) {
            throw new IllegalArgumentException("'colors' is not valid");
        }
        
        this.colors = colors;
        this.validator = validator;
    }
    
    public abstract Piece rotate(Move move);
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        
        Piece piece = (Piece) o;
        
        return Arrays.equals(piece.colors, this.colors) &&
               piece.validator == this.validator;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.colors.hashCode();
        return hash;
    }

    @Override
    public abstract Object clone();
    ////////// TEMPORARY
    public Color getColor(int index) {
        return this.colors[index];
    }
}
