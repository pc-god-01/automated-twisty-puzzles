package main.java.pcgod01.puzzle;

public final class Move implements Cloneable {
    private final String  key;
    private       String regex = null;
    
    public Move(String key) {
        this.regex = key.replace("*", "[2-9]?");
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
    
    @Override
    public String toString() {
        return this.key;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        
        Move move = (Move) o;

        if (move.regex.equals(this.regex)) return true;
        if (move.key.equals(this.key)) return true;
        return move.key.matches(this.regex) ||
               this.key.matches(move.regex);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 23 + key.hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        return new Move(this.key);
    }
}
