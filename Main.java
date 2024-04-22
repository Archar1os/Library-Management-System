import java.util.*;

class Book {
    String title;
    String author;
    int bookNum;
    Borrower borrower;

    public Book(String title, String author, int bookNum, Borrower borrower) {
        this.title = title;
        this.author = author;
        this.bookNum = bookNum;
        this.borrower = borrower;
    }
}

class Borrower{
    int borrowerId;
    String borrowerName;

    public Borrower(int borrowerId,  String borrowerName) {
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
    }
}

class Library {
    ArrayList<Book> books;
    ArrayList<Book> borrowedBooks;
    ArrayList<Book> reservedBooks;

    public Library() {
        books = new ArrayList();
        borrowedBooks = new ArrayList();
        reservedBooks = new ArrayList();
    }

    public void addBorrowedBook(Book book) {
        var existingBook = getBook(book.bookNum, books);

        if(existingBook != null){
            addBook(existingBook, borrowedBooks);
            books.remove(existingBook);
        }
        
    }

    public void returnBorrowedBook(int bookNum) {
        var existingBook = getBook(bookNum, borrowedBooks);

        if(existingBook != null) {
            addBook(existingBook, books);
            borrowedBooks.remove(existingBook);
        }
    }

    public void addBook(Book book, ArrayList<Book> arrayOfBooks) {
        arrayOfBooks.add(book);
    }

    public void removeBook(int bookNum, ArrayList<Book> arrayOfBooks) {
        var existingBook = getBook(bookNum, books);

        if(existingBook != null) {
            arrayOfBooks.remove(existingBook);
        }
    }

    public Book getBook(int bookNum, ArrayList<Book> arrayOfBooks) {
        for (Book book: arrayOfBooks ) {
            if (book.bookNum == bookNum){
                return book;
            }
        }
        return null;
    }

    //Can only be editted in Books
    public void editBook(int bookNum, Book newBook) {
        for (Book book: books ) {
            if (book.bookNum == bookNum) {
                book.author = newBook.author;
                book.title = newBook.title;
            }
        }
    }

    public void displayBorrowedBooks() {
        for (Book book : borrowedBooks) {
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Book Number: " + book.bookNum);
            System.out.println("Book borrower: " + book.borrower.borrowerName);
            System.out.println();
        }
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Book Number: " + book.bookNum);
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
            System.out.println("[3] Borrow Book");
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
        int bookNum = 0;
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        do{
            choice = CheckChoice();
            Book newBook;

            switch (choice)
            {
                // Display Books
                case 1:
                    System.out.println("============================================================================");
                    if (library.books.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }
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
                    bookNum = CheckNumber();

                    System.out.println("Done...");
                    newBook = new Book(title, author, bookNum, null);
                    library.addBook(newBook, library.books);
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
                    bookNum = 0;

                    do{
                        try{
                            System.out.print("Enter Book Number to take:");
                            bookNum = scanner.nextInt();

                            Book book = library.getBook(bookNum, library.books);
                            System.out.print("Please enter your name:");
                            String borrowerName = scanner.next();
                            System.out.print("Pls enter your borrowerId:");
                            int borrowerId = scanner.nextInt();

                            Borrower newBorrower = new Borrower(borrowerId, borrowerName);
                            book.borrower = newBorrower;
                            
                            library.addBorrowedBook(book);
                            cError = true;
                        }
                        catch(Exception e)
                        {
                            System.out.println(bookNum + " is not applicable. Pls try again");
                        }
                        
                    }while(cError == false);
                    break;


                // Return Books
                case 4:
                    System.out.println("Returning Books..");
                    library.displayBorrowedBooks();
                    break;

                // Removing Books
                case 5:
                    System.out.println("============================================================================");
                    if (library.books.isEmpty()){
                        System.out.println("Currently No Available Books, Please Go to Add Books..");
                        break;
                    }
                    System.out.println("Remove Books\n");
                    library.displayBooks();
                    System.out.print("Enter Book Number: ");
                    bookNum = scanner.nextInt();
                    library.removeBook(bookNum, library.books);
                    break;
                // Edit
                case 6:
                    currentBook = null;
                    bookNum = 0;
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
                        System.out.println("Enter Book Number to take:");
                        bookNum = scanner.nextInt();

                        currentBook = library.getBook(bookNum, library.books);
                        if (currentBook == null)
                            {
                            System.out.println("============================================================================");
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(bookNum + " is not applicable. Pls try again");
                        }

                    }while(currentBook == null);
                    System.out.println("You have Selected Book Number:"+currentBook.bookNum);
                    System.out.println("Title: " + currentBook.title);
                    System.out.println("Author: " + currentBook.author);
                    System.out.println("Book Number: " + currentBook.bookNum);
                    System.out.println();
                    
                    System.out.print("Enter New title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter New author: ");
                    author = scanner.nextLine();
                    newBook = new Book(title, author, bookNum, null);
                    library.editBook(bookNum, newBook);
                    break;
                // Exit
                case 10:
                    System.out.println("Thank you, for you cooperation.");
                    bError = false;
                    break;
            }
        }while(bError);
        scanner.close();
    }
}
