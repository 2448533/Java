import java.util.ArrayList;
import java.util.Scanner;


class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private int overdueDays;
    private static final double FINE_PER_DAY = 5.0; 

    private static int totalBooks = 0;
    private static int borrowedBooks = 0;

    public Book(int bookId, String title, String author) {
        if (bookId <= 0) throw new IllegalArgumentException("Book ID must be positive.");
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.overdueDays = 0;
        totalBooks++;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (!isAvailable) {
            System.out.println("Book is not available for borrowing.");
        } else {
            isAvailable = false;
            borrowedBooks++;
            System.out.println("Book borrowed successfully.");
        }
    }

    public void returnBook(int overdueDays) {
        if (isAvailable) {
            System.out.println("Book is already returned.");
            return;
        }
        this.overdueDays = overdueDays;
        isAvailable = true;
        borrowedBooks--;
        System.out.println("Book returned successfully.");
        if (overdueDays > 0) {
            System.out.println("Overdue fine: " + calculateFine());
        }
    }

    private double calculateFine() {
        return overdueDays * FINE_PER_DAY;
    }

    public void displayInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}


class ReferenceBook extends Book {
    private int edition;

    public ReferenceBook(int bookId, String title, String author, int edition) {
        super(bookId, title, author);
        if (edition <= 0) throw new IllegalArgumentException("Edition number must be positive.");
        this.edition = edition;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Edition: " + edition);
    }
}

// Derived class: FictionBook
class FictionBook extends Book {
    private String genre;

    public FictionBook(int bookId, String title, String author, String genre) {
        super(bookId, title, author);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Genre: " + genre);
    }
}


class Periodical extends ReferenceBook {
    private String issueFrequency;

    public Periodical(int bookId, String title, String author, int edition, String issueFrequency) {
        super(bookId, title, author, edition);
        this.issueFrequency = issueFrequency;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Issue Frequency: " + issueFrequency);
    }
}


public class PracticalTestBook {
    private static ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding some books to the library
        library.add(new ReferenceBook(1, "Introduction to Algorithms", "Thomas H. Cormen", 4));
        library.add(new FictionBook(2, "Coraline", "Neil Gaiman", "Fantasy"));
        library.add(new Periodical(3, "National Geographic", "Various", 12, "Monthly"));
        library.add(new ReferenceBook(4, "Database System Concepts", "Abraham Silberschatz", 7));
        library.add(new ReferenceBook(5, "Artificial Intelligence: A Modern Approach", "Stuart Russell", 4));
        library.add(new FictionBook(6, "The Hunger Games", "Suzanne Collins", "Dystopian"));
        library.add(new FictionBook(7, "Divergent", "Veronica Roth", "Dystopian, Science Fiction"));
        library.add(new Periodical(8, "The Economist", "Various", 45, "Weekly"));

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. View All Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View Library Statistics");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayAllBooks();
                case 2 -> borrowBook(scanner);
                case 3 -> returnBook(scanner);
                case 4 -> displayLibraryStatistics();
                case 5 -> {
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAllBooks() {
        System.out.println("\n--- List of Books ---");
        for (Book book : library) {
            book.displayInfo();
            System.out.println();
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter the Book ID to borrow: ");
        int bookId = scanner.nextInt();
        for (Book book : library) {
            if (book.getBookId() == bookId) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter the Book ID to return: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter the number of overdue days (if any): ");
        int overdueDays = scanner.nextInt();

        for (Book book : library) {
            if (book.getBookId() == bookId) {
                book.returnBook(overdueDays);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void displayLibraryStatistics() {
        System.out.println("\n--- Library Statistics ---");
        System.out.println("Total Books in Library: " + Book.getTotalBooks());
        System.out.println("Currently Borrowed Books: " + Book.getBorrowedBooks());
        System.out.println("Available Books: " + (Book.getTotalBooks() - Book.getBorrowedBooks()));
    }
}
