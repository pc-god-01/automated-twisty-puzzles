package main.java.pcgod01.rubiksolver;

import java.util.List;
import java.util.Arrays;

public class Validator {
    private final List<Color> colors;
    private final List<Move> moves;

    public Validator() {
        this(new Color[0], new Move[0]);
    }
    
    public Validator(Color[] validColors, Move[] validMoves) {
        this.colors = Arrays.asList(validColors);
        this.moves = Arrays.asList(validMoves);
    }
    
    public boolean isColorValid(Color... colors) {
        if (colors == null)
            throw new IllegalArgumentException("colors cannot be null");
            
        for (Color color : colors) {
            if (!this.colors.contains(color)) return false;
        }
        
        return true;
    }

    public boolean isMoveValid(Move... moves) {
        if (moves == null)
            throw new IllegalArgumentException("moves cannot be null");

        for (Move move : moves) {
            if (this.colors.contains(move)) return false;
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
}
