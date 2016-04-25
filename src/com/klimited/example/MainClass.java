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
            + "<name>Kongsin</name>"
            + "<lastName>Pansansou</lastName>"
            + "<Address>"
            + "<district>Amnatcharoen</district>"
            + "<zipCode>37000</zipCode>"
            + "<homeAddress>31/8 kudpladuk ,meung</homeAddress>"
            + "</Address>"
            + "<Address>"
            + "<district>Ubon Ratchathani</district>"
            + "<zipCode>10013</zipCode>"
            + "<homeAddress>115/8 naimeung ,meung</homeAddress>"
            + "</Address>"
            + "<age>24</age>"
            + "<a>24</a>"
            + "<b>24</b>"
            + "<c>24</c>"
            + "<d>24</d>"
            + "<e>24</e>"
            + "<f>24</f>"
            + "<NSData>"
            + "<t>TT</t>"
            + "<u>10</u>"
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
