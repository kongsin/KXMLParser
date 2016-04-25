package com.klimited.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.klimited.example.Address;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by kongsin on 26/4/2559.
 */

public class XMLParser {

    public Object fromXml(String xml, Object obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlResource = factory.newDocumentBuilder();
            StringBuilder xmlStringBuilder = new StringBuilder();
            xmlStringBuilder.append(xml);
            ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
            Document doc = xmlResource.parse(input);
            doc.getDocumentElement().normalize();
            getNodeObject(doc, obj);
            return obj;
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Object getNodeObject(Document doc, Object obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (isNativeObject(f)) {
                Element[] val = getData(f, doc.getDocumentElement().getElementsByTagName(f.getName()));
                if (val != null && val.length > 0) {
                    putValue(f, obj, val);
                }
            } else {
                if (f.getType().isArray()) {
                    Element[] elements = getData(f, doc.getDocumentElement().getElementsByTagName(f.getName()));
                    Object[] tmpObject = (Object[]) Array.newInstance(f.getType().getComponentType(), elements.length);
                    for (int i = 0; i < Arrays.asList(tmpObject).size(); i++) {
                        tmpObject[i] = getNodeObject(doc, f.getType().getComponentType().newInstance());
                    }
                    f.set(obj, tmpObject);
                } else {
                    Object tmpObject = getNodeObject(doc, f.getType().newInstance());
                    f.set(obj, tmpObject);
                }
            }
        }
        return obj;
    }

    private Element[] getData(Field f, NodeList list) {
        Element[] e = new Element[list.getLength()];
        for (int i = 0; i < list.getLength(); i++) {
            e[i] = (Element) list.item(i);
        }
        return e;
    }

    private Object putValue(Field f, Object obj, Object[] value) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        String type = f.getType().getCanonicalName();
        if (type.startsWith(char.class.getName())) {
            if (f.getType().isArray()) {
                char[] ch = (char[]) Array.newInstance(char.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = ((Element) value[i]).getTextContent().charAt(0);
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setChar(obj, e.getTextContent().charAt(0));
                }
            }
        } else if (type.startsWith(Character.class.getName())) {
            if (f.getType().isArray()) {
                Object[] ch = (Object[]) Array.newInstance(Character.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = ((Element) value[i]).getTextContent().charAt(0);
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, e.getTextContent().charAt(0));
                }
            }
        } else if (type.startsWith(int.class.getName())) {
            if (f.getType().isArray()) {
                int[] ch = (int[]) Array.newInstance(int.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Integer.parseInt(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setInt(obj, Integer.parseInt(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Integer.class.getName())) {
            if (f.getType().isArray()) {
                Object[] ch = (Object[]) Array.newInstance(Integer.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Integer.parseInt(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Integer.parseInt(e.getTextContent()));
                }
            }
        } else if (type.startsWith(short.class.getName())) {
            if (f.getType().isArray()) {
                short[] ch = (short[]) Array.newInstance(short.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Short.parseShort(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setShort(obj, Short.parseShort(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Short.class.getName())) {
            if (f.getType().isArray()) {
                Short[] ch = (Short[]) Array.newInstance(Short.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Short.parseShort(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Short.parseShort(e.getTextContent()));
                }
            }
        } else if (type.startsWith(long.class.getName())) {
            if (f.getType().isArray()) {
                long[] ch = (long[]) Array.newInstance(long.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Long.parseLong(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setLong(obj, Long.parseLong(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Long.class.getName())) {
            if (f.getType().isArray()) {
                Long[] ch = (Long[]) Array.newInstance(Long.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Long.parseLong(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Long.parseLong(e.getTextContent()));
                }
            }
        } else if (type.startsWith(boolean.class.getName())) {
            if (f.getType().isArray()) {
                boolean[] ch = (boolean[]) Array.newInstance(boolean.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Boolean.parseBoolean(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setBoolean(obj, Boolean.parseBoolean(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Boolean.class.getName())) {
            if (f.getType().isArray()) {
                Boolean[] ch = (Boolean[]) Array.newInstance(Boolean.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Boolean.parseBoolean(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Boolean.parseBoolean(e.getTextContent()));
                }
            }
        } else if (type.startsWith(float.class.getName())) {
            if (f.getType().isArray()) {
                float[] ch = (float[]) Array.newInstance(float.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Float.parseFloat(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setFloat(obj, Float.parseFloat(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Float.class.getName())) {
            if (f.getType().isArray()) {
                Float[] ch = (Float[]) Array.newInstance(Float.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Float.parseFloat(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Float.parseFloat(e.getTextContent()));
                }
            }
        } else if (type.startsWith(double.class.getName())) {
            if (f.getType().isArray()) {
                double[] ch = (double[]) Array.newInstance(double.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Double.parseDouble(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.setDouble(obj, Double.parseDouble(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Double.class.getName())) {
            if (f.getType().isArray()) {
                Double[] ch = (Double[]) Array.newInstance(Double.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = Double.parseDouble(((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, Double.parseDouble(e.getTextContent()));
                }
            }
        } else if (type.startsWith(String.class.getName())) {
            if (f.getType().isArray()) {
                String[] ch = (String[]) Array.newInstance(String.class, value.length);
                for (int i = 0; i < value.length; i++) {
                    ch[i] = (((Element) value[i]).getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e: (Element[]) value) {
                    f.set(obj, e.getTextContent());
                }
            }
        }
        return obj;
    }

    private boolean isNativeObject(Field f) {
        String type = f.getType().getCanonicalName();
        if (type.startsWith(char.class.getName())) {
            return true;
        } else if (type.startsWith(Character.class.getName())) {
            return true;
        } else if (type.startsWith(int.class.getName())) {
            return true;
        } else if (type.startsWith(Integer.class.getName())) {
            return true;
        } else if (type.startsWith(short.class.getName())) {
            return true;
        } else if (type.startsWith(Short.class.getName())) {
            return true;
        } else if (type.startsWith(long.class.getName())) {
            return true;
        } else if (type.startsWith(Long.class.getName())) {
            return true;
        } else if (type.startsWith(boolean.class.getName())) {
            return true;
        } else if (type.startsWith(Boolean.class.getName())) {
            return true;
        } else if (type.startsWith(float.class.getName())) {
            return true;
        } else if (type.startsWith(Float.class.getName())) {
            return true;
        } else if (type.startsWith(double.class.getName())) {
            return true;
        } else if (type.startsWith(Double.class.getName())) {
            return true;
        } else {
            return type.startsWith(String.class.getName());
        }
    }

    public String toXML(Object obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append(openTag(obj.getClass().getSimpleName()));
        builder.append("\n");
        for (Field _f : fields) {
            if (isNativeObject(_f)) {
                if (_f.getType().isArray()) {
                    Object[] data = (Object[]) _f.get(obj);
                    for (Object o : data) {
                        builder.append("\n");
                        builder.append(openTag(_f.getName()));
                        builder.append(o.toString());
                        builder.append(closeTag(_f.getName()));
                        builder.append("\n");
                    }
                } else {
                    builder.append("\n");
                    builder.append(openTag(_f.getName()));
                    builder.append(_f.get(obj).toString());
                    builder.append(closeTag(_f.getName()));
                    builder.append("\n");
                }
            } else {
                Object tmpObject = _f.get(obj);
                builder.append(toXML(tmpObject));
            }
        }
        builder.append("\n");
        builder.append(closeTag(obj.getClass().getSimpleName()));
        return builder.toString().replace("\n\n", "\n");
    }

    private String openTag(String tagName) {
        StringBuilder b = new StringBuilder();
        b.append("<");
        b.append(tagName);
        b.append(">");
        return b.toString();
    }

    private String closeTag(String tagName) {
        StringBuilder b = new StringBuilder();
        b.append("</");
        b.append(tagName);
        b.append(">");
        return b.toString();
    }

}

