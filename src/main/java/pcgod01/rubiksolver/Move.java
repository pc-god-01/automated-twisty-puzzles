package main.java.pcgod01.rubiksolver;

public final class Move {
    private final String key;
    
    protected Move(String key) {
        this.key = key;
    }
    
    @Override
    public String toString() {
        return this.key;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof Move)) return false;
        
        Move move = (Move) o;
        
        return move.key.equals(this.key);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 23 + key.hashCode();
        return hash;
    }
}
