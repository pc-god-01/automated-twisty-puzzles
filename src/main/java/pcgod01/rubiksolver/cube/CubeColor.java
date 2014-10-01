package main.java.pcgod01.rubiksolver.cube;

import main.java.pcgod01.rubiksolver.Color;

public class CubeColor extends Color {
    public static final Color RED = new CubeColor(0, "red");
    public static final Color BLUE = new CubeColor(1, "blue");
    public static final Color WHITE = new CubeColor(2, "whte");
    public static final Color ORANGE = new CubeColor(3, "orange");
    public static final Color GREEN = new CubeColor(4, "green");
    public static final Color YELLOW = new CubeColor(5, "yellow");
    
    private CubeColor(int value, String name) {
        super(value, name);
    }
}
