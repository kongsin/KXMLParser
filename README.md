# KXMLParser
The library for map XML to an object and convert XML to an object

### There have a simple XML data
```
String xml = "<MyObj> "
        + "<name>Kongsin</name>"
        + "<lastName>Pansansou</lastName>"
        + "<Address>"
        + "<district>Amnatcharoen</district>"
        + "<zipCode>37000</zipCode>"
        + "<homeAddress>31/8 kudpladuk ,meung</homeAddress>"
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
```

### And we have an object for map with XML

```
public class MyObj {
   public String name;
   public String lastName;
   public Address address;
   public Double age;
   public boolean sex;
   public int a;
   public Integer b;
   public float c;
   public Float d;
   public long e;
   public Long f;
   public NSData data;
}

```

### Try to map XML to object

```
try {
    MyObj obj = (MyObj) new XMLParser().fromXml(xml, new MyObj());
    System.out.println("name : "+obj.name);
    System.out.println("last name : "+obj.lastName);
    System.out.println("Address : "+obj.address.district);
    System.out.println("name : "+obj.address.zipCode);
} catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
}
```

### Last one is the result

```
name : Kongsin
last name : Pansansou
Address : Amnatcharoen
name : 37000

```
