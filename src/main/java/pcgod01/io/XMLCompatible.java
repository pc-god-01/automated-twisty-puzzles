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

import org.w3c.dom.*;

public abstract class XMLCompatible {
    public abstract boolean readXML(String path, int index);
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
}
