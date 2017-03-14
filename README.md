# KXMLParser
Very easy to map xml value into an object!

### Download Link
[_KXMLParser.jar_](KXMLParser.jar)

### Feature 
- Map XML to Object
- Convert Object to XML String
- Stream XML from URL and map to object

### And we have an object for map with XML just field name the same with XML tag name

```
public class CATALOG {
   public CD[] CD;
}

```

```

public class CD {
    public String TITLE;
    public String ARTIST;
    public String COUNTRY;
    public String COMPANY;
    public Float PRICE;
    public Integer YEAR;
}

```

### Try to map XML to object

```
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
```

### The result from the example XML

```
Empire Burlesque
Bob Dylan
Columbia
USA
1985
10.9

```
