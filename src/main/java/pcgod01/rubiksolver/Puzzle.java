package main.java.pcgod01.rubiksolver;

public abstract class Puzzle {
    private final int sideLength;
    
    protected Puzzle(int sideLength) {
        this.sideLength = sideLength;
        this.initPieces();
    }
    
    public int getSideLength() {
        return this.sideLength;
    }
    
    public abstract Puzzle applyMove(Move move);
    public abstract void initPieces();
}
