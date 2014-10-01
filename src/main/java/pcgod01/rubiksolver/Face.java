package main.java.pcgod01.rubiksolver;

@Deprecated
public abstract class Face {
    private final int sideLength;
    
    protected Face(int sideLength) {
        this.sideLength = sideLength;
    }
    
    public abstract Face rotate(int turns);
}
