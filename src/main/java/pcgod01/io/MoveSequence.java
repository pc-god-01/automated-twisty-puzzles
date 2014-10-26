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

import java.util.ArrayList;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

import main.java.pcgod01.puzzle.Move;

public final class MoveSequence implements XMLCompatible {
    private final ArrayList<Move> moves;
    private       int             repeats;

    public MoveSequence() {
        this.moves = new ArrayList<Move>();
    }

    public boolean readXML(String path, int index) {
        Document dom;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(path);

            Element doc = dom.getDocumentElement();

            Element sequence = (Element) doc.getElementsByTagName("move-sequence").item(index);

            this.addMoves(sequence);
            String repeats = MoveSequence.getTagString(sequence, 0, "repeats", "0");
            this.repeats = Integer.parseInt(repeats);

            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean writeXML(String path) {
        // TODO
        return false;
    }

    private void addMoves(Element sequence) {
        NodeList list = sequence.getElementsByTagName("move");

        for (int i = 0; i < list.getLength(); i++) {
            Element item = (Element) list.item(i);
            Text text = (Text) item.getFirstChild();
            
            if (text == null) {
                continue;
            }
            
            String content = text.getNodeValue();
                
            if (content == null || content.isEmpty()) {
                continue;
            }

            this.moves.add(new Move(content));
        }
    }

    public Move[] getMoves() {
        return this.moves.toArray(new Move[this.moves.size()]);
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }
    
    public static String getTagString(Element element, int index,
                                      String tag, String def) {
        NodeList list = element.getElementsByTagName(tag);

        Element item = (Element) list.item(index);

        if (item == null) {
            return def;
        }
        
        Text text = (Text) item.getFirstChild();
            
        if (text == null) {
            return def;
        }
        
        String content = text.getNodeValue();

        if (content == null || content.isEmpty()) {
            return def;
        }

        return content;
    }
}
