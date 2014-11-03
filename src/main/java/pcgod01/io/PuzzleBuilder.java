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

import java.util.Map;
import java.util.HashMap;
import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

import main.java.pcgod01.puzzle.Puzzle;

public class PuzzleBuilder extends XMLCompatible {
    private       String              clazz;
    private       String              doctype;
    private       Puzzle              puzzle;
    private final Map<String, String> args = new HashMap<>();

    public boolean writeXML(String path) {
        return false;
    }
    
    public boolean doReadXML(Element doc, int index) {
        try {
            Element builder = (Element) doc.getElementsByTagName("puzzle-builder").item(index);

            if (builder == null) {
                return false;
            }

            this.clazz = this.getTagString(builder, 0, "class");

            this.readArgs(builder);
            ClassLoader loader = PuzzleBuilder.class.getClassLoader();
            Class puzzle = loader.loadClass(this.clazz);
            Puzzle clazz = (Puzzle) puzzle.newInstance();
            this.puzzle = clazz.setProperties(args);
            
            return true;
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean doWriteXML(String path) {
        return false;
    }

    private void readArgs(Element builder) {
        NodeList list = builder.getElementsByTagName("args");

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

            String[] args = content.split(":");

            this.args.put(args[0], (args.length < 2 ? "" : args[1]));
        }
    }

    public Puzzle getPuzzle() {
        return this.puzzle;
    }
}
