package main.java.pcgod01.rubiksolver;

// todo override 'toString' method
public abstract class Piece {
    protected final Color[]   colors;
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
        
        return piece.colors.equals(this.colors);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.colors.hashCode();
        return hash;
    }
}
