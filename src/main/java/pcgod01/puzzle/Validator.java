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
