/**
 * When given a twisty puzzle and a sequence of moves, this program outputs
 * the number of times the moves need to be repeated to return to the
 * original.
 * Copyright (C) 2014  Greg Brown
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.java.pcgod01.puzzle.cube;

import java.util.Arrays;
import java.util.Map;

import main.java.pcgod01.puzzle.*;

public final class Cube extends Puzzle implements Cloneable {
    private final static Validator VALIDATOR;
    private final static Color     GREEN  = new Color("green");
    private final static Color     BLUE   = new Color("blue");
    private final static Color     RED    = new Color("red");
    private final static Color     ORANGE = new Color("orange");
    private final static Color     WHITE  = new Color("white");
    private final static Color     YELLOW = new Color("yellow");

    private int         sideLength;
    private Piece[][][] cube;
    
    static {
        VALIDATOR = new Validator();
        VALIDATOR.addColor(GREEN, BLUE, RED, ORANGE, WHITE, YELLOW);
        
        final String[] moveNames = new String[] {"F",    "B",    "R",
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

    public Cube() {
        super();
    }
 
    public Cube(int sideLength) {
        super();
        this.sideLength = sideLength;
        this.initPieces();
    }

    protected Cube(Piece[][][] cube) {
        super();
        this.cube = cube;
        this.sideLength = cube.length;
    }

    public static Validator getValidator() {
        return VALIDATOR;
    }
    
    @Override
    public Puzzle applyMove(final Move move) {
        if (!VALIDATOR.isMoveValid(move)) {
            throw new IllegalArgumentException("invalid move");
        }
        
        int turns = 1;
        final String key = move.getKey().toLowerCase();
        int slices = 1;

        if (key.endsWith("'")) {
            turns = 3;
        } else if (key.endsWith("2")) {
            turns = 2;
        }

        if (Character.isDigit(key.charAt(0))) {
            slices = Integer.parseInt(key.substring(0, 1));
        }

        if (key.contains("x") || key.contains("y") || key.contains("z")) {
            slices = this.sideLength;
        }

        if (key.contains("f") || key.contains("z")) {
            return this.rotateFront(slices, turns);
        } else if (key.contains("b")) {
            return this.rotateBack(slices, turns);
        } else if (key.contains("r") || key.contains("x")) {
            return this.rotateRight(slices, turns);
        } else if (key.contains("l")) {
            return this.rotateLeft(slices, turns);
        } else if (key.contains("u") || key.contains("y")) {
            return this.rotateUp(slices, turns);
        } else if (key.contains("d")) {
            return this.rotateDown(slices, turns);
        }
      
        return this;
    }

    @Override
    public Puzzle setProperties(Map<String, String> properties) {
        this.sideLength = Integer.parseInt(properties.get("side-length"));

        this.initPieces();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Cube)) return false;

        Cube cube = (Cube) o;

        if (cube.sideLength != this.sideLength) return false;

        for (int x = 0; x < this.sideLength; x++) {
            for (int y = 0; y < this.sideLength; y++) {
                for (int z = 0; z < this.sideLength; z++) {
                    if (!this.cube[x][y][z].equals(cube.cube[x][y][z]))
                        return false;
                }
            }
        }

        return true;
    }

    @Override
    public Object clone() {
        final int length = this.sideLength;
        Piece[][][] cube = new Piece[length][length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    cube[i][j][k] = (Piece) this.cube[i][j][k].clone();
                }
            }
        }

        return new Cube(cube);
    }

    protected void initPieces() {
        final int length = this.sideLength;
        Piece[][][] cube = new Piece[length][length][length];

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < length; z++) {
                    Color[] c = new Color[6];

                    if (x == 0) {
                        c[2] = Cube.RED;
                    } else if (x == length - 1) {
                        c[3] = Cube.ORANGE;
                    }

                    if (y == 0) {
                        c[4] = Cube.WHITE;
                    } else if (y == length - 1) {
                        c[5] = Cube.YELLOW;
                    }

                    if (z == 0) {
                        c[0] = Cube.GREEN;
                    } else if (z == length - 1) {
                        c[1] = Cube.BLUE;
                    }

                    cube[x][y][z] = new CubePiece(c[0], c[1], c[2],
                                                  c[3], c[4], c[5]);
                }
            }
        }
        
        this.cube = cube;
    }

    public Piece[][][] getCube() {
        return this.cube;
    }

    private Puzzle rotateFront(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateFront(slices, turns + 4);

        this.rotateFront(slices, turns - 1);
        
        Move move = new Move("F");
        int length = this.cube.length;
        int modifier = length - 1;
        
        for (int i = 0; i < slices; i++) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
                
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[y][-x + modifier][i] = this.cube[x][y][i];
                    newP[y][-x + modifier][i].rotate(move);
                }
            }
                
            this.cube = newP;
        
        }
        
        return this;
    }

    private Puzzle rotateBack(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateBack(slices, turns + 4);
        
        this.rotateBack(slices, turns - 1);

        Move move = new Move("B");
        int length = this.cube.length;
        int modifier = length - 1;
        
        for (int i = length - 1; i > length - slices - 1; i--) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
            
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[-y + modifier][x][i] = this.cube[x][y][i];
                    newP[-y + modifier][x][i].rotate(move);
                }
            }
            
            this.cube = newP;
        }
        
        return this;
    }

    private Puzzle rotateRight(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateRight(slices, turns + 4);

        this.rotateRight(slices, turns - 1);
        
        Move move = new Move("R");
        int length = this.cube.length;
        int modifier = length - 1;
        
        for (int i = 0; i < slices; i++) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
                
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[i][y][-x + modifier] = this.cube[i][x][y];
                    newP[i][y][-x + modifier].rotate(move);
                }
            }
                
            this.cube = newP;
        
        }
        
        return this;
    }

    private Puzzle rotateLeft(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateLeft(slices, turns + 4);
        
        this.rotateLeft(slices, turns - 1);

        Move move = new Move("L");
        int length = this.cube.length;
        int modifier = length - 1;
        
        for (int i = length - 1; i > length - slices - 1; i--) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
            
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[i][-y + modifier][x] = this.cube[i][x][y];
                    newP[i][-y + modifier][x].rotate(move);
                }
            }
            
            this.cube = newP;
        }
        
        return this;
    }

    private Puzzle rotateUp(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateUp(slices, turns + 4);

        this.rotateUp(slices, turns - 1);
        
        Move move = new Move("U");
        int length = this.cube.length;
        int modifier = (int) (length - 1);
        
        for (int i = 0; i < slices; i++) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
                
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[-x + modifier][i][y] = this.cube[y][i][x];
                    newP[-x + modifier][i][y].rotate(move);
                }
            }
                
            this.cube = newP;
        
        }
        
        return this;
    }

    private Puzzle rotateDown(int slices, int turns) {
        if (turns % 4 == 0) return this;
        if (turns < 0) return this.rotateDown(slices, turns + 4);
        
        this.rotateDown(slices, turns - 1);

        Move move = new Move("D");
        int length = this.cube.length;
        int modifier = length - 1;
        
        for (int i = length - 1; i > length - slices - 1; i--) {
            Cube clone = (Cube) this.clone();
            Piece[][][] newP = clone.getCube();
            
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    newP[x][i][-y + modifier] = this.cube[y][i][x];
                    newP[x][i][-y + modifier].rotate(move);
                }
            }
            
            this.cube = newP;
        }
        
        return this;
    }
}
