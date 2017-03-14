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

    public static void main(String[] args) {
        try {
            CATALOG catalog = new XMLParser().fromXml(new URL("https://www.w3schools.com/xml/cd_catalog.xml"), CATALOG.class);
            System.out.println(new XMLParser().toXML(catalog));
        } catch (IllegalArgumentException | MalformedURLException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
