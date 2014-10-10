package main.java.pcgod01.rubiksolver.cube;

import main.java.pcgod01.rubiksolver.*;

// TODO rotation, customized start
public class Cube extends Puzzle {
    private final static Validator VALIDATOR;
    private final static Color     GREEN  = new Color("green");
    private final static Color     BLUE   = new Color("blue");
    private final static Color     RED    = new Color("red");
    private final static Color     ORANGE = new Color("orange");
    private final static Color     WHITE  = new Color("white");
    private final static Color     YELLOW = new Color("yellow");

    private Piece[][][] cube = null;
    
    static {
        VALIDATOR = new Validator();
        VALIDATOR.addColor(GREEN, BLUE, RED, ORANGE, WHITE, YELLOW);
        
        String[] moveNames = new String[] {"F",    "B",    "R",
                                           "L",    "U",    "D",
                                           "F'",   "B'",   "R'",
                                           "L'",   "U'",   "D'",
                                           "F2",   "B2",   "R2",
                                           "L2",   "U2",   "D2",
                                           "*Fw",  "*Bw",  "*Rw",
                                           "*Lw",  "*Uw",  "*Dw",
                                           "*Fw'", "*Bw'", "*Rw'",
                                           "*Lw'", "*Uw'", "*Dw'",
                                           "*Fw2", "*Bw2", "*Rw2",
                                           "*Lw2", "*Uw2", "*Dw2",
                                           "z",    "x",    "y",
                                           "z'",   "x'",   "y'",
                                           "z2",   "x2",   "y2"};

        for (String name : moveNames) {
            VALIDATOR.addMove(new Move(name));
        }
    }
    
    public Cube(int sideLength) {
        super(sideLength);
    }

    public static Validator getValidator() {
        return VALIDATOR;
    }

    @Override
    public Puzzle applyMove(Move move) {
        if (!VALIDATOR.isMoveValid(move)) {
            throw new IllegalArgumentException("move cannot be applied" +
                                               " to this puzzle");
        }
        
        int turns = 1;
        String key = move.getKey();

        if (key.endsWith("'")) {
            turns = 3;
        } else if (key.endsWith("2")) {
            turns = 2;
        }

        // TODO rotate
        
        return this;
    }

    @Override
    public void initPieces() {
        Piece[][][] cube = new Piece[3][3][3];

        // 1 side
        cube[1][1][0] = newPiece(GREEN, null, null, null, null, null);
        cube[1][1][2] = newPiece(null, BLUE, null, null, null, null);
        cube[0][1][1] = newPiece(null, null, RED, null, null, null);
        cube[2][1][1] = newPiece(null, null, null, ORANGE, null, null);
        cube[1][0][1] = newPiece(null, null, null, null, WHITE, null);
        cube[1][2][1] = newPiece(null, null, null, null, null, YELLOW);

        // 2 sides
        cube[0][1][0] = newPiece(GREEN, null, RED, null, null, null);
        cube[2][1][0] = newPiece(GREEN, null, null, ORANGE, null, null);
        cube[1][0][0] = newPiece(GREEN, null, null, null, WHITE, null);
        cube[1][2][0] = newPiece(GREEN, null, null, null, null, YELLOW);
        cube[0][1][2] = newPiece(null, BLUE, RED, null, null, null);
        cube[2][1][2] = newPiece(null, BLUE, null, ORANGE, null, null);
        cube[1][0][2] = newPiece(null, BLUE, null, null, WHITE, null);
        cube[1][2][2] = newPiece(null, BLUE, null, null, null, YELLOW);
        cube[0][0][1] = newPiece(null, null, RED, null, WHITE, null);
        cube[0][2][1] = newPiece(null, null, RED, null, null, YELLOW);
        cube[2][0][1] = newPiece(null, null, null, ORANGE, WHITE, null);
        cube[2][2][1] = newPiece(null, null, null, ORANGE, null, YELLOW);

        // 3 sides
        cube[0][0][0] = newPiece(GREEN, null, RED, null, WHITE, null);
        cube[0][2][0] = newPiece(GREEN, null, RED, null, null, YELLOW);
        cube[2][0][0] = newPiece(GREEN, null, null, ORANGE, WHITE, null);
        cube[2][2][0] = newPiece(GREEN, null, null, ORANGE, null, YELLOW);
        cube[0][0][2] = newPiece(null, BLUE, RED, null, WHITE, null);
        cube[0][2][2] = newPiece(null, BLUE, RED, null, null, YELLOW);
        cube[2][0][2] = newPiece(null, BLUE, null, ORANGE, WHITE, null);
        cube[2][2][2] = newPiece(null, BLUE, null, ORANGE, null, YELLOW);
        
        this.cube = cube;
    }

    private final Piece newPiece(Color f, Color b, Color r, Color l, Color u, Color d) {
        return new CubePiece(f, b, r, l, u, d);
    }
}
