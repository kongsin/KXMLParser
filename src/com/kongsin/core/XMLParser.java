package com.kongsin.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by kongsin on 26/4/2559.
 */

public class XMLParser {

    public <T> T fromXml(URL url, Class<T> modelClass){
        try {
            Scanner scan = new Scanner(url.openStream());
            String val = "";
            while (scan.hasNext()) {
                val += scan.nextLine();
            }
            try {
                return fromXml(val, modelClass);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public <T> T fromXml(String xml, Class<T> modelClass) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlResource = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document doc = xmlResource.parse(input);
            doc.getDocumentElement().normalize();
            return getNodeObject(doc.getDocumentElement(), modelClass);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T getNodeObject(Element val, Class<T> mObj) throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        T _obj = mObj.newInstance();
        Field[] fields = mObj.getDeclaredFields();
        for (Field f : fields) {
            if (isNativeObject(f)) {
                putValue(f, _obj, val);
            } else {
                if (f.getType().isArray()) {
                    Element[] elements = getData(val.getElementsByTagName(f.getName()));
                    Object[] tmpObject = (Object[]) Array.newInstance(f.getType().getComponentType(), elements.length);
                    for (int i = 0; i < Arrays.asList(tmpObject).size(); i++) {
                        tmpObject[i] = getNodeObject(elements[i], f.getType().getComponentType());
                    }
                    f.set(_obj, tmpObject);
                } else {
                    Object tmpObject = getNodeObject(val, f.getType().getComponentType());
                    f.set(_obj, tmpObject);
                }
            }
        }
        return _obj;
    }

    private Element[] getData(NodeList list) {
        Element[] e = new Element[list.getLength()];
        for (int i = 0; i < list.getLength(); i++) {
            e[i] = (Element) list.item(i);
        }
        return e;
    }

    private Object putValue(Field f, Object obj, Element value) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        String type = f.getType().getCanonicalName();
        Element[] v = getData(value.getElementsByTagName(f.getName()));
        if (type.startsWith(char.class.getName())) {
            if (f.getType().isArray()) {
                char[] ch = (char[]) Array.newInstance(char.class, v.length);
                for (int i = 0; i < ch.length; i++) {
                    ch[i] = v[i].getTextContent().charAt(0);
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setChar(obj, e.getTextContent().charAt(0));
                }
            }
        } else if (type.startsWith(Character.class.getName())) {
            if (f.getType().isArray()) {
                Object[] ch = (Object[]) Array.newInstance(Character.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = v[i].getTextContent().charAt(0);
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, e.getTextContent().charAt(0));
                }
            }
        } else if (type.startsWith(int.class.getName())) {
            if (f.getType().isArray()) {
                int[] ch = (int[]) Array.newInstance(int.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Integer.parseInt(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setInt(obj, Integer.parseInt(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Integer.class.getName())) {
            if (f.getType().isArray()) {
                Object[] ch = (Object[]) Array.newInstance(Integer.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Integer.parseInt(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Integer.parseInt(e.getTextContent()));
                }
            }
        } else if (type.startsWith(short.class.getName())) {
            if (f.getType().isArray()) {
                short[] ch = (short[]) Array.newInstance(short.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Short.parseShort(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setShort(obj, Short.parseShort(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Short.class.getName())) {
            if (f.getType().isArray()) {
                Short[] ch = (Short[]) Array.newInstance(Short.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Short.parseShort(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Short.parseShort(e.getTextContent()));
                }
            }
        } else if (type.startsWith(long.class.getName())) {
            if (f.getType().isArray()) {
                long[] ch = (long[]) Array.newInstance(long.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Long.parseLong(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setLong(obj, Long.parseLong(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Long.class.getName())) {
            if (f.getType().isArray()) {
                Long[] ch = (Long[]) Array.newInstance(Long.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Long.parseLong(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Long.parseLong(e.getTextContent()));
                }
            }
        } else if (type.startsWith(boolean.class.getName())) {
            if (f.getType().isArray()) {
                boolean[] ch = (boolean[]) Array.newInstance(boolean.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Boolean.parseBoolean(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setBoolean(obj, Boolean.parseBoolean(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Boolean.class.getName())) {
            if (f.getType().isArray()) {
                Boolean[] ch = (Boolean[]) Array.newInstance(Boolean.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Boolean.parseBoolean(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Boolean.parseBoolean(e.getTextContent()));
                }
            }
        } else if (type.startsWith(float.class.getName())) {
            if (f.getType().isArray()) {
                float[] ch = (float[]) Array.newInstance(float.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Float.parseFloat(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setFloat(obj, Float.parseFloat(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Float.class.getName())) {
            if (f.getType().isArray()) {
                Float[] ch = (Float[]) Array.newInstance(Float.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Float.parseFloat(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Float.parseFloat(e.getTextContent()));
                }
            }
        } else if (type.startsWith(double.class.getName())) {
            if (f.getType().isArray()) {
                double[] ch = (double[]) Array.newInstance(double.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Double.parseDouble(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.setDouble(obj, Double.parseDouble(e.getTextContent()));
                }
            }
        } else if (type.startsWith(Double.class.getName())) {
            if (f.getType().isArray()) {
                Double[] ch = (Double[]) Array.newInstance(Double.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = Double.parseDouble(v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
                    f.set(obj, Double.parseDouble(e.getTextContent()));
                }
            }
        } else if (type.startsWith(String.class.getName())) {
            if (f.getType().isArray()) {
                String[] ch = (String[]) Array.newInstance(String.class, v.length);
                for (int i = 0; i < v.length; i++) {
                    ch[i] = (v[i].getTextContent());
                }
                f.set(obj, ch);
            } else {
                for (Element e : v) {
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

    public String toXML(Object obj) throws IllegalAccessException {
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
                if (_f.getType().isArray()) {
                    Object[] data = (Object[]) _f.get(obj);
                    for (Object o : data) {
                        builder.append(toXML(o));
                    }
                } else {
                    Object tmpObject = _f.get(obj);
                    builder.append(toXML(tmpObject));
                }
            }
        }
        builder.append("\n");
        builder.append(closeTag(obj.getClass().getSimpleName()));
        return builder.toString().replace("\n\n", "\n");
    }

    private String openTag(String tagName) {
        return "<" +
                tagName +
                ">";
    }

    private String closeTag(String tagName) {
        return "</" +
                tagName +
                ">";
    }

}

