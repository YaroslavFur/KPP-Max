package Library;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    public ArrayList<Book> allBooks;
    public ArrayList<Card> cards;

    public Library()
    {
        allBooks = new ArrayList<Book>();
        cards = new ArrayList<Card>();
    }

    public void sortBooksBy(bookProperties byWhat)
    {
        System.out.println("Sorting by " + byWhat + "...");
        allBooks.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                switch (byWhat) {
                    case name:
                        return b1.getName().compareTo(b2.getName());
                    case author:
                        return b1.getAuthor().compareTo(b2.getAuthor());
                    case year:
                        return Integer.compare(b1.getYear(), b2.getYear());
                    default:
                        System.out.println("Wrong sort criteria \"" + byWhat + "\"");
                        return 0;
                }
            }
        });
    }

    public void findReaders2PlusBooks()
    {
        var readers2Plus = this.cards.stream().
            filter(card -> card.listOfBooks.size() >= 2).
            collect(Collectors.toCollection(ArrayList<Card>::new));

        for (var reader: readers2Plus) {
            System.out.println(reader.getFirstName());
        }
    }

    public void checkAuthorPopularity(String author)
    {
        long numOfCardsWithThisAuthorBooks = this.cards.stream().
                filter(card -> card.listOfBooks.stream().
                        anyMatch(bookDates -> Objects.equals(bookDates.getBook().getAuthor(), author))).
                count();
        System.out.println(numOfCardsWithThisAuthorBooks + " reader" + (numOfCardsWithThisAuthorBooks == 1 ?
                "" : "s") + " took books of " + author);
    }

    public void findTheBestReader()
    {
        long numOfBooks = this.cards.stream().
                mapToInt(card -> card.listOfBooks.size()).
                max().
                orElse(0);
        String theBestReader = this.cards.stream().
                filter(card -> card.listOfBooks.size() == numOfBooks).
                findFirst().get().getFirstName();
        System.out.println("The best reader - " + theBestReader + " - has " + numOfBooks + " books");
    }

    public void makeMailing()
    {
        var smallReaders = this.cards.stream().
                filter(card -> card.listOfBooks.size() < 2).
                collect(Collectors.toCollection(ArrayList<Card>::new));
        var bigReaders = this.cards.stream().
                filter(card -> card.listOfBooks.size() >= 2).
                collect(Collectors.toCollection(ArrayList<Card>::new));
        Book newBook = this.allBooks.stream().
                max(Comparator.comparing(book -> book.getYear())).
                orElse(null);
        for (var reader: smallReaders) {
            System.out.println(reader.getFirstName() + " should visit library, " +
                    "here's newest book - " + newBook.getName());
        }
        for (var reader: bigReaders) {
            System.out.println(reader.getFirstName() + ", don't forget to return " +
                    reader.listOfBooks.size() + " books");
        }
    }

    public void remindDebtors()
    {
        Date todayDate = Calendar.getInstance().getTime();

        var debtors = this.cards.stream().
                filter(card -> card.listOfBooks.stream().
                        anyMatch(bookDates -> bookDates.getReturnByDate().before(todayDate))).
                collect(Collectors.toCollection(ArrayList<Card>::new));
        for (var debtor: debtors) {
            System.out.println(debtor.getFirstName() + ", you are late at returning " +
                    "book" + (debtor.listOfBooks.size() == 1 ? "" : "s") + " back to library");
        };
    }
}
