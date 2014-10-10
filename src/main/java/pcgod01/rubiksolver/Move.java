package main.java.pcgod01.rubiksolver;

public final class Move {
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
        else if (!(o instanceof Move)) return false;
        
        Move move = (Move) o;

        if (move.regex.equals(move.key)) {
            if (this.regex.equals(this.key)) {
                return this.key.equals(move.key);
            }

            return move.key.matches(this.regex);
        } else {
            if (this.regex.equals(this.key)) {
                return this.key.matches(move.regex);
            }

            return this.key.equals(move.key);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 23 + key.hashCode();
        return hash;
    }
}
