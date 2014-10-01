package main.java.pcgod01.rubiksolver;

public abstract class Color {
    private final int value;
    private final String name;
    
    public Color(int value, String name) {
        this.value = value;
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
        
        return color.value == this.value &&
               color.name.equals(this.name);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.value;
        hash = hash * 31 + this.name.hashCode();
        return hash;
    }
}
