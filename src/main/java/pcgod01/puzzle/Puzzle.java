package main.java.pcgod01.puzzle;

public abstract class Puzzle {
    protected final int sideLength;
    
    protected Puzzle(int sideLength) {
        this.sideLength = sideLength;
    }
    
    public int getSideLength() {
        return this.sideLength;
    }
    
    public abstract Puzzle applyMove(Move move);
}
