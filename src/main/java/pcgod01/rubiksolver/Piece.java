package main.java.pcgod01.rubiksolver;

// todo override 'toString' method
public abstract class Piece {
    protected final Color[] colors;
    
    protected Piece(int directions, Color[] colors) {
        if (colors.length != directions) {
            throw new IllegalArgumentException("'colors' should be of " +
                                               "length 'directions'");
        }
        
        this.colors = colors;
    }
    
    public abstract Piece rotate(Move move);
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        
        Piece piece = (Piece) o;
        
        return piece.colors.equals(this.colors);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.colors.hashCode();
        return hash;
}
