package MainPackage.Main;

import Library.*;
import Serialization.SerializationManager;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static final String tableFormatBook = "%-50s%-50s%-15s%n";
    public static final String tableFormatReader = "%-20s%-20s%-20s%n%n";
    public static final String tableFormatBookTook = "\t\t\t\t\t%-50s%-50s%-20s%-20s%n";
    public static Date todayDate = Calendar.getInstance().getTime();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");

        Library library = new Library();
        library.allBooks = SerializationManager.deserialize(library.allBooks, "Books");
        library.cards = SerializationManager.deserialize(library.cards, "Cards");

        boolean userWantsToContinue = true;
        while (userWantsToContinue) {
            System.out.println("""

                    Menu:
                    1. Print Books in Library
                    2. Print Readers
                    3. Take Book
                    4. Return Book
                    5. Sort Books in Library
                    6. Find Readers Who Took 2+ books
                    7. Check Author Popularity
                    8. Find The Best Reader
                    9. Make Mailing
                    10. Remind Debetors
                    0. Exit""");
            int decision = scanner.nextInt();
            switch (decision) {
                case 1 -> {
                    System.out.println("Books in library:\n");
                    System.out.printf(tableFormatBook, "Name", "Author", "Year\n");
                    for (var book : library.allBooks) {
                        System.out.printf(tableFormatBook, book.getName(), book.getAuthor(), book.getYear());
                    }
                }
                case 2 -> {
                    System.out.println("Readers:\n");
                    System.out.printf(tableFormatReader, "First Name", "Second Name", "Email");
                    for (var card : library.cards) {
                        System.out.printf(tableFormatReader, card.getFirstName(), card.getSecondName(), card.getEmail());
                        for (var book : card.listOfBooks) {
                            System.out.printf(tableFormatBookTook, book.getBook().getName(), book.getBook().getAuthor(),
                                    book.getReaderTook(), book.getReturnBy());
                        }
                        System.out.println("\n");
                    }
                }
                case 3 -> {
                    System.out.println("Enter reader name:");
                    String cardName = scanner.next();
                    var card = library.cards.stream().
                            filter(c -> Objects.equals(c.getFirstName(), cardName)).
                            findFirst().
                            orElse(null);
                    assert card != null;
                    System.out.println("Enter book name:");
                    String bookName = scanner.next();
                    System.out.println("Enter date of taking:");
                    String takeDate = scanner.next();
                    System.out.println("Enter plan date of return:");
                    String returnDate = scanner.next();
                    card.takeBookFromLibrary(library, bookName, takeDate, returnDate);
                    System.out.println("Book taken");
                }
                case 4 -> {
                    System.out.println("Enter reader name:");
                    String cardName = scanner.next();
                    var card = library.cards.stream().
                            filter(c -> Objects.equals(c.getFirstName(), cardName)).
                            findFirst().
                            orElse(null);
                    assert card != null;
                    System.out.println("Enter book name:");
                    String bookName = scanner.next();
                    System.out.println("Enter date of return:");
                    String returnDate = scanner.next();
                    card.returnBookToLibrary(library, bookName, returnDate);
                    System.out.println("Book returned");
                }
                case 5 -> {
                    System.out.println("Enter sort by what:");
                    for (var what : bookProperties.values()) {
                        System.out.println(what);
                    }
                    String what = scanner.next();
                    library.sortBooksBy(bookProperties.valueOf(what));
                }
                case 6 -> {
                    library.findReaders2PlusBooks();
                }
                case 7 -> {
                    System.out.println("Enter author name:");
                    String author = scanner.next();
                    library.checkAuthorPopularity(author);
                }
                case 8 -> {
                    library.findTheBestReader();
                }
                case 9 -> {
                    library.makeMailing();
                }
                case 10 -> {
                    library.remindDebtors();
                }
                case 0 -> {
                    userWantsToContinue = false;
                    System.out.println("Bye");
                }
                default -> System.out.println("Enter numbers from the menu");
            }
        }
    }
}
