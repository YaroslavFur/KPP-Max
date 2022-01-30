package Library;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookDates implements Serializable {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Book book;
    private Date readerTook;
    private Date returnBy;

    public BookDates()
    {
        setBook(null);
        setReaderTook(null);
        setReturnBy(null);
    }

    public BookDates(Book book, String readerTook, String returnBy)
    {
        setBook(book);
        setReaderTook(readerTook);
        setReturnBy(returnBy);
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getReaderTook() {
        return simpleDateFormat.format(readerTook);
    }

    public void setReaderTook(String readerTook) {
        try {
            this.readerTook = simpleDateFormat.parse(readerTook);
        }
        catch (ParseException parseException)
        {
            System.out.println("Date \"" + returnBy + "\" is incorrect");
        }
    }

    public Date getReaderTookDate() {
        return readerTook;
    }

    public void setReaderTookDate(Date readerTook)
    {
        this.readerTook = readerTook;
    }

    public String getReturnBy() {
        return simpleDateFormat.format(returnBy);
    }

    public void setReturnBy(String returnBy) {
        try {
            this.returnBy = simpleDateFormat.parse(returnBy);
        }
        catch (ParseException parseException)
        {
            System.out.println("Date \"" + returnBy + "\" is incorrect");
        }
    }

    public Date getReturnByDate() {
        return returnBy;
    }

    public void setReturnByDate(Date returnBy)
    {
        this.returnBy = returnBy;
    }
}
