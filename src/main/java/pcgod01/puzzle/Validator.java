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

import java.util.*;

public class Validator implements Cloneable {
    private final ArrayList<Color> colors;
    private final ArrayList<Move>  moves;

    public Validator() {
        this(new Color[0], new Move[0]);
    }
    
    public Validator(Color[] validColors, Move[] validMoves) {
        this.colors = new ArrayList<Color>(Arrays.asList(validColors));
        this.moves = new ArrayList<Move>(Arrays.asList(validMoves));
    }
    
    public boolean isColorValid(Color... colors) {
        if (colors == null)
            throw new IllegalArgumentException("colors cannot be null");
            
        for (Color color : colors) {
            if (color == null) continue;
            if (!this.colors.contains(color)) return false;
        }
        
        return true;
    }

    public boolean isMoveValid(Move... moves) {
        if (moves == null)
            throw new IllegalArgumentException("moves cannot be null");

        for (Move move : moves) {
            if (move == null) continue;
            if (!this.moves.contains(move)) return false;
        }

        return true;
    }

    public Validator addColor(Color... colors) {
        this.colors.addAll(Arrays.asList(colors));
        return this;
    }

    public Validator addMove(Move... moves) {
        this.moves.addAll(Arrays.asList(moves));
        return this;
    }

    @Override
    public Object clone() {
        Color[] colorArray = (Color[]) this.colors.toArray();
        Move[] moveArray = (Move[]) this.moves.toArray();
        Color[] colors = new Color[colorArray.length];
        Move[] moves = new Move[moveArray.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = (Color) colorArray[i].clone();
        }

        for (int i = 0; i < moves.length; i++) {
            moves[i] = (Move) moveArray[i].clone();
        }
        
        return new Validator(colors, moves);
    }
}
