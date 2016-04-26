# KXMLParser
The library for map XML to an object and convert XML to an object

### There have a simple XML data
```
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
```

### And we have an object for map with XML

```
public class bookstore {
   public book[] book;
}

```

```

public class book {
    public String title;
    public String[] author;
    public String year;
    public float price;
}

```

### Try to map XML to object

```

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

```

### Last one is the result

```
Harry Potter
[Ljava.lang.String;@49476842
2005
29.99
Learning XML
[Ljava.lang.String;@78308db1
2003
39.95

```
