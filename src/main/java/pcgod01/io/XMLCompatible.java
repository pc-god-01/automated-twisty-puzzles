package main.java.pcgod01.io;

import org.w3c.dom.*;

public interface XMLCompatible {
    public boolean readXML(String path, int index);
    public boolean openXML(String path);
}
