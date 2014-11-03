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

import java.io.IOException;
import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

public abstract class XMLCompatible {
    private String doctype = "";
    
    protected abstract boolean doReadXML(Element doc, int index);
    protected abstract boolean doWriteXML(String path);
    
    public boolean readXML(String path, int index) {
        Document dom;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(path);

            DocumentType doctype = dom.getDoctype();

            if (doctype != null) {
                String doctypeString = doctype.getSystemId();
                File doctypeFile;
                
                if (XMLCompatible.isRelative(doctypeString)) {
                    File xml = new File(path);
                    String name = xml.getName();
                    doctypeFile = new File(path.split(name)[0] + doctypeString);
                } else {
                    doctypeFile = new File(doctypeString);
                }

                if (!doctypeFile.exists()) {
                    return false;
                }

                this.doctype = doctypeString;
            } else {
                return false;
            }

            Element doc = dom.getDocumentElement();
            
            return this.doReadXML(doc, index);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
            
    public abstract boolean writeXML(String path);

    protected final String getTagString(Element e, int i, String tag) {
        NodeList list = e.getElementsByTagName(tag);

        Element item = (Element) list.item(i);

        if (item == null) {
            return "";
        }
        
        Text text = (Text) item.getFirstChild();
            
        if (text == null) {
            return "";
        }
        
        String content = text.getNodeValue();

        if (content == null || content.isEmpty()) {
            return "";
        }

        return content;
    }

    public static boolean isRelative(String path) {
        String os = System.getProperty("os.name");
        os.toLowerCase();

        if (os.startsWith("windows")) {
            return path.substring(1, 2) != ":";
        } else {
            return !path.startsWith("/");
        }
    }
}
