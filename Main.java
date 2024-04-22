import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    String BookNum;
    Boolean availability;

    public Book(String title, String author, String BookNum, Boolean availability) {
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
                book.availability = newBook.availability;
            }
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            String available = "No";
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Book Number: " + book.BookNum);
            if (book.availability == true){
                available = "Yes";
            }
            System.out.println("Accessible: " + available);
            System.out.println();
        }
    }
    public boolean availableBooks()
    {
        for (Book book : books)
        {
            if (book.availability == true)
            {
                System.out.println("Title: " + book.title);
                System.out.println("Author: " + book.author);
                System.out.println("Book Number: " + book.BookNum);
                String available = "Yes";
                System.out.println("Accessible: " + available);
                System.out.println();
            }
        }
        return true;
    }
}

public class Main
{
    public static int IdentifyNumber() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;

        while (true)
        {
            System.out.println("============================================================================");
            System.out.println("Menu");
            System.out.println("[1] Display Books");
            System.out.println("[2] Add Book");
            System.out.println("[3] Take Book");
            System.out.println("[4] Return Book");
            System.out.println("[5] Remove book");
            System.out.println("[6] Edit Books");
            System.out.println("[7] Exit");
            System.out.print(":");
            String input = scanner.nextLine();

            try {
                x = Integer.parseInt(input);
                if (x > 0){
                    return x;
                }
                else
                {
                    System.out.println(input + " is not an option. Please pick between options 1-7.");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a number. Pls try again");
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
                // Display Books
                case 1:
                    System.out.println("============================================================================");
                    System.out.println("Here are the available Books.");
                    library.displayBooks();
                    
                    break;
                // Add Books
                case 2:
                    System.out.println("============================================================================");
                    System.out.println("Adding book..");
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Book Number: ");
                    BookNum = scanner.nextLine();
                    Boolean availability = true;

                    System.out.println("Done...");
                    library.addBook(new Book(title, author, BookNum, availability));
                    break;
                // Take Books
                case 3:
                    System.out.println("============================================================================");
                    if (library.books.isEmpty()|| library.availableBooks() == false)
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }

                    System.out.println("Taking Books..");
                    boolean cError = false;
                    Book TakeBook = null;
                    BookNum = null;
                    do{
                        try{
                        library.availableBooks();
                        System.out.println("Enter Book Number to take:      (Type 'Menu' to go back to the menu.. )");
                        BookNum = scanner.nextLine();
                        TakeBook = library.getBook(BookNum);
                        if (TakeBook.availability == false)
                            {
                            System.out.println("============================================================================");
                            System.out.println("This book has already been taken..\n");
                            }
                        else if (BookNum.toLowerCase() == "menu"|| TakeBook.availability == true)
                        {
                            cError = true;
                            break;
                        }
                        }
                        catch(Exception e)
                        {
                            System.out.println(BookNum + " is not applicable. Pls try again");
                        }
                        
                        }while(cError == false);
                        if (library.availableBooks() == false || BookNum.toLowerCase() == "menu")
                        {
                            break;
                        }
                        Boolean available = false;
                        Book newBook = new Book(TakeBook.title, TakeBook.author, TakeBook.BookNum, available);
                        library.editBook(BookNum, newBook);
                        

                // Return Books
                case 4:
                    System.out.println(":");
                    break;

                // Delete
                case 5:
                    System.out.println("============================================================================");
                    if (library.books.isEmpty()){
                        System.out.println("Currently No Available Books, Please Go to Add Books..");
                        break;
                    }
                    System.out.println("Remove Books\n");
                    library.displayBooks();
                    System.out.print("Enter Book Number: ");
                    BookNum = scanner.nextLine();
                    library.removeBook(BookNum);
                    break;
                // Exit
                case 6:
                    Book currentBook = null;
                    BookNum = null;
                    System.out.println("============================================================================");
                    if (library.books.isEmpty()){
                        System.out.println("Currently No Available Books..");
                        break;
                    }
                    System.out.println("Editing...");
                    
                    do{
                        try{
                        System.out.println("Current Available Books:\n");
                        library.displayBooks();
                        System.out.println("Enter Book Number to take:      (Type 'Menu' to go back to the menu.. )");
                        BookNum = scanner.nextLine();

                        currentBook = library.getBook(BookNum);
                        if (currentBook == null)
                            {
                            System.out.println("============================================================================");
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(BookNum + " is not applicable. Pls try again");
                        }

                    }while(currentBook == null);
                    System.out.println("You have Selected Book Number:"+currentBook.BookNum);
                    System.out.println("Title: " + currentBook.title);
                    System.out.println("Author: " + currentBook.author);
                    System.out.println("Book Number: " + currentBook.BookNum);
                    System.out.println("Accessible: " + currentBook.availability);
                    System.out.println();
                    
                    System.out.print("Enter New title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter New author: ");
                    author = scanner.nextLine();
                    availability = true;
                    newBook = new Book(title, author, BookNum, availability);
                    library.editBook(BookNum, newBook);

                    //System.out.println("Enter Book Number: ");
                    //BookNum = scanner.nextLine();
                    //library.getBook(BookNum);
                    break;
                // Exit
                default:
                    System.out.println("Thank you, for you cooperation.");
                    bError = false;
                    break;
            }
        }while(bError);
        scanner.close();
    }
}
