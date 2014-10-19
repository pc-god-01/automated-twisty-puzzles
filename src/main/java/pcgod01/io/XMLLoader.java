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

public final class XMLLoader {
    private final String   path;
    private Document       doc;
    private boolean        open;

    public XMLLoader(String path) {
        this.path = path;
        this.open();
    }

    public Move[] getMoveSequence() {
        return this.getMoveSequence(0);
    }
    
    public Move[] getMoveSequence(int index) {
        if (this.doc == null) {
            System.out.println("doc is null");
            return new Move[0];
        }
        
        NodeList moveSequences = this.doc.getElementsByTagName("move-sequence");

        if (moveSequences.getLength() == 0) {
            System.out.println("doc has no move-sequence");
            return new Move[0];
        }
        
        Element moveSequence = (Element) moveSequences.item(index);
        NodeList moveNodes = moveSequence.getElementsByTagName("move");

        if (moveNodes.getLength() == 0) {
            System.out.println("move-sequence has no move");
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
