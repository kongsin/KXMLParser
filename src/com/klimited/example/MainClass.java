/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimited.example;

import com.klimited.core.XMLParser;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kongsin
 */
public class MainClass {

    private String xml = "<bookstore>\n" +
            "  <book category=\"children\">\n" +
            "    <title>Harry Potter</title>\n" +
            "    <author>J K. Rowling</author>\n" +
            "    <author>K. Kongsin</author>\n" +
            "    <year>2005</year>\n" +
            "    <price>29.99</price>\n" +
            "  </book>\n" +
            "  <book category=\"web\">\n" +
            "    <title>Learning XML</title>\n" +
            "    <author>Erik T. Ray</author>\n" +
            "    <year>2003</year>\n" +
            "    <price>39.95</price>\n" +
            "  </book>\n" +
            "</bookstore>";

    public static void main(String[] args) {
        try {
            MainClass m = new MainClass();
            CATALOG catalog = (CATALOG) new XMLParser().fromXml(new URL("http://www.w3schools.com/xml/cd_catalog.xml"), new CATALOG());
            System.out.println(new XMLParser().toXML(catalog));
            //System.out.println(new XMLParser().toXML(obj));
        } catch (IllegalArgumentException | MalformedURLException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
