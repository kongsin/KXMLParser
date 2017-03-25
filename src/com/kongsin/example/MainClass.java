/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kongsin.example;

import com.kongsin.core.XMLParser;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author kongsin
 */
public class MainClass {

    public static void main(String[] args) {
        try {
            CATALOG catalog = new XMLParser().fromXml(new URL("https://www.w3schools.com/xml/cd_catalog.xml"), CATALOG.class);
            for (CD cd : catalog.CD) {
                System.out.println(cd.TITLE);
                System.out.println(cd.ARTIST);
                System.out.println(cd.COMPANY);
                System.out.println(cd.COUNTRY);
                System.out.println(cd.YEAR);
                System.out.println(cd.PRICE);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
