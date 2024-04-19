import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    String BookNum;

    public Book(String title, String author, String BookNum) {
        this.title = title;
        this.author = author;
        this.BookNum = BookNum;
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

    public Book getBook(String BookNum) {
        for (Book book: books ) {
            if (book.BookNum.equals(BookNum)) {
                return book;
            }
        }
        return null;
    }


    public void editBook(String BookNum, Book newBook) {
        for (Book book: books ) {
            if (book.BookNum.equals(BookNum)) {
                book.author = newBook.author;
                book.title = newBook.title;
            }
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Book Number: " + book.BookNum);
            System.out.println();
        }
    }
}

public class Main
{
    public static int IdentifyNumber() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;

        while (true)
        {
            System.out.println("Menu");
            System.out.println("Display Books(1)");
            System.out.println("Add Book     (2)");
            System.out.println("Return Book  (3)");
            System.out.println("Remove book  (4)");
            System.out.println("Exit         (5)");
            System.out.print(":");
            String input = scanner.nextLine();

            try {
                x = Integer.parseInt(input);
                if (x > 0){
                    return x;
                }
                else
                {
                    System.out.println(input + " is not a positive number");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a number.");
            }
        }
    }
    public static void main(String[] args)
    {
        boolean bError = true;
        int choice = 0;
        String BookNum;
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        do{
            choice = IdentifyNumber();
            switch (choice)
            {
                // Display Books|Take Books
                case 1:
                    System.out.println("===================================");
                    System.out.println("Here are the Books available.");
                    library.displayBooks();
                    System.out.println("Enter Book Number: ");
                    BookNum = scanner.nextLine();
                    library.removeBook(BookNum);
                    break;
                // Add Books
                case 2:
                    System.out.println("===================================");
                    System.out.println("Adding book..");
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Book Number: ");
                    BookNum = scanner.nextLine();

                    System.out.println("Done...");
                    System.out.println("===================================");
                    library.addBook(new Book(title, author, BookNum));
                    break;

                // Return Books
                case 3:
                    System.out.println(":");
                    break;

                // Delete
                case 4:
                    System.out.println("===================================");
                    library.displayBooks();
                    System.out.print("Enter Book Number: ");
                    BookNum = scanner.nextLine();
                    library.removeBook(BookNum);
                    break;

                // Exit
                case 5:
                    System.out.println("Thank you, for you cooperation.");
                    bError = false;
                    break;
                case 6:
                    System.out.println("Editing...");
                    System.out.println("Enter Book Number to edit: ");
                    BookNum = scanner.nextLine();

                    Book currentBook = library.getBook(BookNum);
                    System.out.println("Title: " + currentBook.title);
                    System.out.println("Author: " + currentBook.author);
                    System.out.println("Book Number: " + currentBook.BookNum);
                    System.out.println();

                    System.out.print("Enter New title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter New author: ");
                    author = scanner.nextLine();
                    Book newBook = new Book(title, author, BookNum);
                    library.editBook(BookNum, newBook);
                    break;

            }
        }while(bError);
        scanner.close();
    }
}