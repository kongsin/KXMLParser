/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimited.example;

import com.klimited.core.XMLParser;

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
            bookstore obj = (bookstore) new XMLParser().fromXml(m.xml, new bookstore());
            for (book book : obj.book) {
                System.out.println(book.title);
                System.out.println(book.author);
                System.out.println(book.year);
                System.out.println(book.price);
            }
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
