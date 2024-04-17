
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    String BookNum;
    boolean availability;

    public Book(String title, String author, String BookNum, boolean availability) {
        this.title = title;
        this.author = author;
        this.BookNum = BookNum;
        this.availability = availability;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String BookNum) {
        for (Book book: books ) {
            if (book.BookNum.equals(BookNum)) {
                books.remove(book);
                break;
            }
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Book Number: " + book.BookNum);
            System.out.println("Availability: " + book.availability);
            System.out.println();
        }
    }
}

class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                scanner.nextLine();
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                System.out.print("Enter Book Number: ");
                String BookNum = scanner.nextLine();
                System.out.print("Enter availability: ");
                boolean availability = scanner.nextBoolean();
                library.addBook(new Book(title, author, BookNum, availability));
            } else if (choice == 2) {
                library.displayBooks();
                scanner.nextLine();
                System.out.print("Enter Book Number: ");
                String BookNum = scanner.nextLine();
                library.removeBook(BookNum);
            } else if (choice == 3) {
                System.out.println("Here are the Books available.");
                library.displayBooks();
            } else if (choice == 4) {
                break;
            }
        }

        scanner.close();
    }
}
