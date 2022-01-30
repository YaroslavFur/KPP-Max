package Library;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private String author;
    private String name;
    private int year;

    public Book()
    {
        setAuthor(null);
        setName(null);
        setYear(0);
    }

    public Book(String author, String name, int year)
    {
        setAuthor(author);
        setName(name);
        setYear(year);
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
