package main.java.pcgod01.rubiksolver.cube;

import main.java.pcgod01.rubiksolver.Piece;
import main.java.pcgod01.rubiksolver.Color;
import main.java.pcgod01.rubiksolver.Move;

public final class CubePiece extends Piece {
    public CubePiece(Color f, Color u, Color r, Color b, Color d, Color l) {
        super(new Color[] {f, u, r, b, d, l}, Cube.getValidator());
    }

    @Override
    public Piece rotate(Move move) {
        if (this.validator.isMoveValid(move)) {
            throw new IllegalArgumentException("invalid move");
        }

        String key = move.getKey();
        // TODO ROTATE
        
        return this;
    }
}
