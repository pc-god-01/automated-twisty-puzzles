package main.java.pcgod01.puzzle.cube;

import main.java.pcgod01.puzzle.*;

public final class CubePiece extends Piece {
    public static final int F = 0;
    public static final int B = 1;
    public static final int R = 2;
    public static final int L = 3;
    public static final int U = 4;
    public static final int D = 5;
    
    public CubePiece(Color f, Color b, Color r, Color l, Color u, Color d) {
        super(new Color[] {f, b, r, l, u, d}, Cube.getValidator());
    }

    @Override
    public Piece rotate(Move move) {
        if (!this.validator.isMoveValid(move)) {
            throw new IllegalArgumentException("invalid move");
        }

        String key = move.getKey().toLowerCase();
        int turns = 1;

        if (key.endsWith("'")) {
            turns = 3;
        } else if (key.endsWith("2")) {
            turns = 2;
        }

        return this.rotate(key, turns);
    }

    private Piece rotate(String key, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotate(key, turns + 4);
        if (turns > 1) return this.rotate(key, turns - 1);
        
        Color[] c = this.colors;
        Color[] newC;

        if (key.contains("f") || key.contains("z")) {
            newC = new Color[] {c[F], c[B], c[U], c[D], c[L], c[R]};
        } else if (key.contains("b")) {
            newC = new Color[] {c[F], c[B], c[D], c[U], c[R], c[L]};
        } else if (key.contains("r") || key.contains("x")) {
            newC = new Color[] {c[D], c[U], c[R], c[L], c[F], c[B]};
        } else if (key.contains("l")) {
            newC = new Color[] {c[U], c[D], c[R], c[L], c[B], c[F]};
        } else if (key.contains("u") || key.contains("y")) {
            newC = new Color[] {c[R], c[L], c[B], c[F], c[U], c[D]};
        } else if (key.contains("d")) {
            newC = new Color[] {c[L], c[R], c[F], c[B], c[U], c[D]};
        } else {
            return this;
        }

        this.colors = newC;
        
        return this;
    }

    @Override
    public Object clone() {
        Color[] c = new Color[this.colors.length];

        for (int i = 0; i < colors.length; i++) {
            if (this.colors[i] == null) {
                c[i] = null;
                continue;
            }
            
            c[i] = (Color) this.colors[i].clone();
        }

        return new CubePiece(c[0], c[1], c[2], c[3], c[4], c[5]);
    }
}
