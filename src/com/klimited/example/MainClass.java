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
 *
 * @author kongsin
 */
public class MainClass {

    private String xml = "<MyObj> "
            + "<NSData>"
            + "<a>z</a>"
            + "<firstAlphabet>k</firstAlphabet>"
            + "<firstAlphabet>o</firstAlphabet>"
            + "<firstAlphabet>n</firstAlphabet>"
            + "<firstAlphabet>g</firstAlphabet>"
            + "</NSData>"
            + "<NSData>"
            + "<a>z</a>"
            + "<firstAlphabet>k</firstAlphabet>"
            + "<firstAlphabet>o</firstAlphabet>"
            + "<firstAlphabet>n</firstAlphabet>"
            + "<firstAlphabet>g</firstAlphabet>"
            + "</NSData>"
            + "</MyObj>";

    public static void main(String[] args) {
        try {
            MainClass m = new MainClass();
            MyObj obj = (MyObj) new XMLParser().fromXml(m.xml, new MyObj());
            System.out.println(new XMLParser().toXML(obj));
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
