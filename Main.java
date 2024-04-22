import java.util.*;

class Book {
    String title;
    String author;
    int BookNum;

    public Book(String title, String author, int BookNum) {
        this.title = title;
        this.author = author;
        this.BookNum = BookNum;
    }
}
class BorrowedBook{
    int BookNum;
    String Name;
    String BorrowerId;
    Book Books;

    public BorrowedBook(int BookNum , String Name, String BorrowerId, Book Books) {
        this.BookNum = BookNum;
        this.Name = Name;
        this.BorrowerId = BorrowerId;
        this.Books= Books;
    }
}
class Library {
    ArrayList<Book> books;
    ArrayList<BorrowedBook> borrowedBooks;

    public Library() {
        books = new ArrayList();
        borrowedBooks = new ArrayList();
    }

    public void addBorrowedBook(BorrowedBook BorrowedBook) {
        borrowedBooks.add(BorrowedBook);
        for (Book book: books ) {
            if (BorrowedBook.BookNum == book.BookNum) {
                books.remove(book);
                break;
            }
        }
    }

    public void removeBorrowedBook(int BookNum) {
        
        for (BorrowedBook BorrowedBook: borrowedBooks ) {
            if (BorrowedBook.BookNum == BookNum) {
                books.add(BorrowedBook.Books);
                borrowedBooks.remove(BorrowedBook);
                break;
            }
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int BookNum) {
        for (Book book: books ) {
            if (book.BookNum == BookNum) {
                books.remove(book);
                break;
            }
        }
    }

    public Book getBook(int BookNum) {
        for (Book book: books ) {
            if (book.BookNum == BookNum){
                return book;
            }
        }
        return null;
    }


    public void editBook(int BookNum, Book newBook) {
        for (Book book: books ) {
            if (book.BookNum == BookNum) {
                book.author = newBook.author;
                book.title = newBook.title;
            }
        }
    }
    public void displayBorrowedBooks() {
        for (BorrowedBook BorrowedBook : borrowedBooks) {
            System.out.println("Name: " + BorrowedBook.Name);
            System.out.println("Borrowed Id: " + BorrowedBook.BorrowerId);
            System.out.println("Book Number: " + BorrowedBook.BookNum);
            System.out.println();
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
    public static int CheckChoice() {
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
    public static int CheckNumber() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;

        while (true)
        {
            String input = scanner.nextLine();
            try {
                x = Integer.parseInt(input);
                if (x > 0){
                    return x;
                }
                else
                {
                    System.out.println(input + " is not within the range.");
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
        int BookNum = 0;
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        do{
            choice = CheckChoice();
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
                    Book currentBook = null;
                    System.out.print("Enter Book Number: ");
                    BookNum = CheckNumber();

                    System.out.println("Done...");
                    library.addBook(new Book(title, author, BookNum));
                    break;
                // Take Books
                case 3:
                    System.out.println("============================================================================");
                    if (library.books.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }

                    System.out.println("Taking Books..");
                    library.displayBooks();
                    boolean cError = false;
                    Book TakeBook = null;
                    BookNum = 0;
                    do{
                        try{
                        System.out.print("Enter Book Number to take:");
                        BookNum = scanner.nextInt();
                        TakeBook = library.getBook(BookNum);
                        System.out.print("Please enter your name:");
                        String name = scanner.nextLine();
                        System.out.print("Pls enter your BorrowerId:");
                        String borrowerId = scanner.nextLine();
                        library.addBorrowedBook(new BorrowedBook(BookNum, name, borrowerId, TakeBook));
                        cError = true;
                        }
                        catch(Exception e)
                        {
                            System.out.println(BookNum + " is not applicable. Pls try again");
                        }
                        
                        }while(cError == false);


                // Return Books
                case 4:
                    System.out.println("Returning Books..");
                    library.displayBorrowedBooks();
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
                    BookNum = scanner.nextInt();
                    library.removeBook(BookNum);
                    break;
                // Exit
                case 6:
                    currentBook = null;
                    BookNum = 0;
                    System.out.println("============================================================================");
                    if (library.books.isEmpty()){
                        System.out.println("Currently No Available Books, Please Go to Add Books..");
                        break;
                    }
                    System.out.println("Editing...");
                    
                    do{
                        try{
                        System.out.println("Current Available Books:\n");
                        library.displayBooks();
                        System.out.println("Enter Book Number to take:      (Type 'Menu' to go back to the menu.. )");
                        BookNum = scanner.nextInt();

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
                    System.out.println();
                    
                    System.out.print("Enter New title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter New author: ");
                    author = scanner.nextLine();
                    Book newBook = new Book(title, author, BookNum);
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
