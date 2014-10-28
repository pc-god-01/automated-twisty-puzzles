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
import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

import main.java.pcgod01.puzzle.Move;

public final class MoveSequence extends XMLCompatible {
    private final ArrayList<Move> moves;
    private       int             repeats;
    private       String          doctype;

    public MoveSequence() {
        this.moves = new ArrayList<Move>();
    }

    @Override
    public boolean readXML(String path, int index) {
        Document dom;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(path);

            DocumentType doctype = dom.getDoctype();

            if (doctype != null) {
                this.doctype = doctype.getSystemId();
            } else {
                this.doctype = null;
            }

            Element doc = dom.getDocumentElement();
            Element sequence = (Element) doc.getElementsByTagName("move-sequence").item(index);

            if (sequence == null) {
                return false;
            }

            String repeats = this.getTagString(sequence, 0, "repeats");
            
            if (repeats != null && !repeats.isEmpty()) {
                this.repeats = Integer.parseInt(repeats);
            }
            
            this.readMoves(sequence);

            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean writeXML(String path) {
        Document dom;
        Element e = null;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            boolean exists = new File(path).exists();

            if (exists) {
                dom = builder.parse(path);
            } else {
                dom = builder.newDocument();
            }

            Element atp;

            if (exists) {
                atp = dom.getDocumentElement();
            } else {
                atp = dom.createElement("atp");
            }
            
            Element root =  dom.createElement("move-sequence");
            atp.appendChild(root);

            if (this.repeats > 0) {
                e = dom.createElement("repeats");
                e.appendChild(dom.createTextNode("" + repeats));
                root.appendChild(e);
            }

            this.saveMoves(dom, root);

            if (!exists) {
                dom.appendChild(atp);
            }

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                
                if (this.doctype != null) {
                    tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype);
                }
                
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                tr.transform(new DOMSource(dom),
                             new StreamResult(new FileOutputStream(path)));

                return true;
            } catch (TransformerException | FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.println(ex.getMessage());
        }    
            
        return false;
    }

    private void readMoves(Element sequence) {
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

    private void saveMoves(Document dom, Element root) {
        Element e;

        for (Move move : this.moves) {
            e = dom.createElement("move");
            e.appendChild(dom.createTextNode(move.getKey()));
            root.appendChild(e);
        }
    }

    public Move[] getMoves() {
        return this.moves.toArray(new Move[this.moves.size()]);
    }

    public int getRepeats() {
        return this.repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }
}
