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
package main.java.pcgod01.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.w3c.dom.*;

import main.java.pcgod01.puzzle.Move;
import main.java.pcgod01.puzzle.Puzzle;

public final class XMLLoader {
    private final String   path;
    private       Document doc;
    private       boolean  open;

    public XMLLoader(String path) {
        this.path = path;
        this.open();
    }
    
    public Move[] getMoveSequence(int index) {
        if (this.doc == null) {
            return new Move[0];
        }
        
        NodeList moveSequences = this.doc.getElementsByTagName("move-sequence");

        if (moveSequences.getLength() <= index) {
            return new Move[0];
        }
        
        Element moveSequence = (Element) moveSequences.item(index);
        NodeList moveNodes = moveSequence.getElementsByTagName("move");

        if (moveNodes.getLength() == 0) {
            return new Move[0];
        }

        Move[] moves = new Move[moveNodes.getLength()];

        for (int i = 0; i < moveNodes.getLength(); i++) {
            Element moveElement = (Element) moveNodes.item(i);
            String key = moveElement.getAttribute("key");
            Move move = new Move(key);
            moves[i] = move;
        }

        return moves;
    }

    public Puzzle getPuzzle(int index) {
        if (this.doc == null) {
            return null;
        }

        NodeList puzzleBuiders = this.doc.getElementsByTagName("puzzle-builder");

        if (puzzleBuiders.getLength() <= index) {
            System.out.println("no builder element");
            return null;
        }

        Element puzzleBuilder = (Element) puzzleBuiders.item(index);

        if (puzzleBuilder.hasAttribute("class")) {
            String clazz = puzzleBuilder.getAttribute("class");
            Element args = null;

            NodeList list = puzzleBuilder.getElementsByTagName("args");

            if (list.getLength() > 0) {
                args = (Element) list.item(0);
            }

            try {
                PuzzleBuilder builder = (PuzzleBuilder) Class.forName(clazz).newInstance();
                return builder.buildFromXML(args);
            } catch (ClassNotFoundException | InstantiationException |
                     IllegalAccessException e) {
                System.out.println("Error");
                System.out.println(e);
                return null;
            }
        }
        System.out.println("no class atribute");
        return null;
    }

    public void open() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.parse(new File(this.path));
            this.open = true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
            this.doc = null;
            this.open = false;
        }
    }
        

    public void close() {
        this.doc = null;
        this.open = false;
    }
}
