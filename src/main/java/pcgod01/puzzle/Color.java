package main.java.pcgod01.puzzle;

public class Color implements Cloneable {
    private final String name;
    
    public Color(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (!(o instanceof Color)) return false;
        
        Color color = (Color) o;
        
        return color.name.equals(this.name);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.name.hashCode();
        return hash;
    }

    @Override
    public Object clone() {
        return new Color(this.name);
    }
}
