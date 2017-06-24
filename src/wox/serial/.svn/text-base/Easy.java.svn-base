package wox.serial;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;

public class Easy {
    public static void save(Object ob, String filename) {
        try {
            ObjectWriter writer = new SimpleWriter();
            Element el = writer.write(ob);
            XMLOutputter out = new XMLOutputter(); // ("  ", true);
            FileWriter file = new FileWriter(filename);
            out.output(el, file);
            file.close();
            System.out.println("Saved object to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String saveString(Object ob, int sizeEstimate) {
        try {
            // accurate size estimates allow for more efficient use of
            // a byte array output stream
            ObjectWriter writer = new SimpleWriter();
            Element el = writer.write(ob);
            XMLOutputter out = new XMLOutputter(); // ("  ", true);
            out.setFormat(Format.getPrettyFormat());
            ByteArrayOutputStream bout = new ByteArrayOutputStream(sizeEstimate);
            out.output(el, bout);
            return bout.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object load(String filename) {
        try {
            SAXBuilder builder = new SAXBuilder();
            InputStream is = new FileInputStream(filename);
            Document doc = builder.build(is);
            Element el = doc.getRootElement();
            ObjectReader reader = new SimpleReader();
            return reader.read(el);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object loadString(String data) {
        // loads from a String
        try {
            SAXBuilder builder = new SAXBuilder();
            Reader is = new StringReader(data);
            Document doc = builder.build(is);
            Element el = doc.getRootElement();
            ObjectReader reader = new SimpleReader();
            return reader.read(el);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
