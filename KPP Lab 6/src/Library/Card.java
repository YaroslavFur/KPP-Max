package Library;

import javax.lang.model.type.ArrayType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Card implements Serializable {
    private String firstName;
    private String secondName;
    private String email;
    public final ArrayList<BookDates> listOfBooks;

    public Card()
    {
        setFirstName(null);
        setSecondName(null);
        setEmail(null);
        listOfBooks = new ArrayList<BookDates>();
    }

    public Card(String firstName, String secondName, String email)
    {
        setFirstName(firstName);
        setSecondName(secondName);
        setEmail(email);
        listOfBooks = new ArrayList<BookDates>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void takeBookFromLibrary(Library library, String name, String readerTook, String returnBy) {
        Book book = library.allBooks.stream().filter(b -> Objects.equals(b.getName(), name)).findFirst().orElse(null);
        if (book != null) {
            this.listOfBooks.add(new BookDates(book, readerTook, returnBy));
            library.allBooks.remove(book);
        }
        else
            System.out.println("There's no book \"" + name + "\" in library");
    }

    public void returnBookToLibrary(Library library, String name, String returned)
    {
        BookDates bookDates = listOfBooks.stream().
                filter(b -> Objects.equals(b.getBook().getName(), name)).
                findFirst().
                orElse(null);
        try {
            this.listOfBooks.remove(bookDates);
            library.allBooks.add(bookDates.getBook());
        }
        catch (NullPointerException exception) {
            System.out.println("Reader " + getFirstName() + " " + getSecondName() + " has no book \"" + name + "\"");
        }
    }
}
