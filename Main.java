import java.util.*;

class Book {
    String title;
    String author;
    int bookNum;
    Borrower borrower;
    String status;

    public Book(String title, String author, int bookNum, Borrower borrower, String status) {
        this.title = title;
        this.author = author;
        this.bookNum = bookNum;
        this.borrower = borrower;
        this.status = status;
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

    public Library() {
        books = new ArrayList();
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book, ArrayList<Book> arrayOfBooks) {
        arrayOfBooks.add(book);
    }

    public void removeBook(int bookNum, ArrayList<Book> arrayOfBooks) {
        var existingBook = getBook(bookNum);

        if(existingBook != null) {
            arrayOfBooks.remove(existingBook);
        }
    }

    public Book getBook(int bookNum) {
        for (Book book: books ) {
            if (book.bookNum == bookNum){
                return book;
            }
        }
        
        return null;
    }

    public boolean isBookExisting (int bookNum) {
        for (Book book: books ) {
            if (book.bookNum == bookNum){
                return true;
            }
        }

        return false;
    }

    public void editBook(int bookNum, Book newBook) {
        for (Book book: books ) {
            if (book.bookNum == bookNum) {
                book.author = newBook.author;
                book.title = newBook.title;
                book.status = newBook.status;
                book.borrower = newBook.borrower;
            }
        }
    }


    public void displayBooks(String status) {
        for (Book book : books) {
            if (book.status == status) {
                System.out.println("Title: " + book.title);
                System.out.println("Author: " + book.author);
                System.out.println("Book Number: " + book.bookNum);
                if (book.status == ("Borrowed") || book.status == ("Reserved")){
                    System.out.println("Book borrower: " + book.borrower.borrowerName);
                    System.out.println("BorrowerId: " + book.borrower.borrowerId);
                }
                System.out.println("status: " + book.status);
                System.out.println();
            }
        }
        
    }

    public ArrayList<Book> getReservedBooks() {
        ArrayList<Book> reservedBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.status == "Reserved") {
                reservedBooks.add(book);
            }
        }

        return reservedBooks;
    }

    public ArrayList<Book> getBorrowedBooks() {
        ArrayList<Book> borrowedBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.status == "Borrowed") {
                borrowedBooks.add(book);
            }
        }

        return borrowedBooks;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.status == "Available") {
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }
}

public class Main
{
    public static int checkChoice() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;

        while (true)
        {
            System.out.println("============================================================================");
            System.out.println("                                |Library|");
            System.out.println("============================================================================");
            System.out.println("[1]  Display Books");
            System.out.println("[2]  Add Book");
            System.out.println("[3]  Edit Books");
            System.out.println("[4]  Remove book");
            System.out.println("[5]  Display Borrowed");
            System.out.println("[6]  Borrow Book");
            System.out.println("[7]  Return Book");
            System.out.println("[8]  Display reserve books");
            System.out.println("[9]  Reserve a book");
            System.out.println("[10] Exit");
            System.out.print(":");
            String input = scanner.nextLine();

            try {
                x = Integer.parseInt(input);
                if (x > 0){
                    return x;
                }
                else
                {
                    System.out.println(input + " is not an option. Please pick between options 1-10.");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a number. Pls try again");
            }
        }
    }

    public static int checkNumber() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;

        while (true)
        {
            System.out.print(":");
            String input = scanner.nextLine();
            try {
                x = Integer.parseInt(input);
                if (x > 0){
                    return x;
                }
                else
                {
                    System.out.println(input + " is not an option. Please pick between options 1-10.");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a number. Pls try again");
            }
        }
    }

    public static void main(String[] args)
    {
        Book book = null;
        boolean bError = true;
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        do{
            choice = checkChoice();
            ArrayList<Book> reservedBooks = library.getReservedBooks();
            ArrayList<Book> availableBooks = library.getAvailableBooks();
            ArrayList<Book> borrowedBooks = library.getBorrowedBooks();
            ArrayList<Book> allBooks = library.getAllBooks();
            int bookNum = 0;
            Book newBook;

            switch (choice)
            {
                // Display Books | Done
                case 1:
                    System.out.println("============================================================================");
                
                    if (allBooks.isEmpty() || availableBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }
                    
                    System.out.println("Here are the available Books.");
                    library.displayBooks("Available");
                    
                    break;

                // Add Books | Done
                case 2:
                    System.out.println("============================================================================");
                    System.out.println("Adding book..");

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();

                    while(true) {
                        System.out.println("Enter Book Number");
                        bookNum = checkNumber();
                        
                        String status = "Available";
                        newBook = new Book(title, author, bookNum, null, status);

                        if (!library.isBookExisting(bookNum)){
                            System.out.println("Book Added");
                            library.addBook(newBook, library.books);
                            break;
                        }
                        else{
                            System.out.println("Book number already exist, please try again..");
                        }
                    }
                    break;

                // Edit | Done
                case 3:
                    Book currentBook = null;
                    System.out.println("============================================================================");

                    if (allBooks.isEmpty() || availableBooks.isEmpty()){
                        System.out.println("Currently No Available Books, Please Go to Add Books..");
                        break;
                    }

                    System.out.println("Editing...");
                    
                    do{
                        try{
                            System.out.println("Current Available Books:\n");
                            library.displayBooks("Available");
                            System.out.println("Enter Book Number to take:");
                            bookNum = checkNumber();
                            
                            currentBook = library.getBook(bookNum);
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
                    System.out.println("You have Selected Book Number:" + currentBook.bookNum);
                    System.out.println("Title: " + currentBook.title);
                    System.out.println("Author: " + currentBook.author);
                    System.out.println("Book Number: " + currentBook.bookNum);
                    System.out.println();
                    
                    System.out.print("Enter New title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter New author: ");
                    author = scanner.nextLine();
                    newBook = new Book(title, author, bookNum, null, currentBook.status);
                    library.editBook(bookNum, newBook);
                    System.out.println("Done...");
                    break;

                // Removing Books | Done
                case 4:
                    System.out.println("============================================================================");

                    if (availableBooks.isEmpty()){
                        System.out.println("Currently No Available Books, Please Go to Add Books..");
                        break;
                    }

                    System.out.println("Remove Books\n");
                    library.displayBooks("Available");
                    System.out.print("Enter Book Number: ");
                    bookNum = checkNumber();
                    book = library.getBook(bookNum);
                    System.out.println("The Book"+ book +"has been Removed..");
                    library.removeBook(bookNum, availableBooks);
                    System.out.println("Going back to menu..");
                    break;

                // Display Borrowed Books | Done
                case 5:
                    System.out.println("============================================================================");

                    if (borrowedBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }
                    

                    System.out.println("Here are the Borrowed Books.");
                    library.displayBooks("Borrowed");
                
                break;

                // Borrow a Book | Done
                case 6:
                    System.out.println("============================================================================");
                    if (allBooks.isEmpty() || availableBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }

                    System.out.println("Displaying Available books..");
                    library.displayBooks("Available");
                    while (!library.isBookExisting(bookNum)) {
                        System.out.println("Enter Book Number to take");
                        bookNum = checkNumber();
                        
                        if (!library.isBookExisting(bookNum)){
                            System.out.println("\n"+bookNum + " does not match. Please try again..\n");
                        }
                    }

                    book = library.getBook(bookNum);
                    System.out.print("Please enter your name:");
                    String borrowerName = scanner.nextLine();

                    System.out.println("Pls enter your borrowerId:"); // BorrowerId should be automatically added by the system, not the user. Implement this functionality in the future
                    int borrowerId = checkNumber();

                    Borrower newBorrower = new Borrower(borrowerId, borrowerName);
                    String status = "Borrowed";
                    
                    newBook = new Book(book.title, book.author, bookNum, newBorrower, status);
                    library.editBook(bookNum, newBook);
                        
                    System.out.println("The Book has been Borrowed..");
                    System.out.println("Going back to menu..");
                    break;


                // Return a Book | Done
                case 7:
                    
                    System.out.println("============================================================================");
                    if (allBooks.isEmpty() || borrowedBooks.isEmpty() && reservedBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }
                    
                    System.out.println("Returning Books..");
                    library.displayBooks("Borrowed");

                    System.out.println("Enter Book Number to return");
                    
                    while (!library.isBookExisting(bookNum)) {
                        bookNum = checkNumber();
                    }
                    
                    // Add a checking here if the bookNum exists | Done
                    book = library.getBook(bookNum);
                    status = "Available";
                    
                    newBook = new Book(book.title, book.author, bookNum, null, status);
                    library.editBook(bookNum, newBook);
                        
                    System.out.println("The Book has been Returned..");
                    System.out.println("Going back to menu..");
                    break;
                    
                // Display Reserved Books | Done
                case 8:
                    System.out.println("============================================================================");
                    if (allBooks.isEmpty() || reservedBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }

                    System.out.println("Here are the Reserved Books.");
                    library.displayBooks("Reserved");
                    
                    break;
                    
                // Reserve a Book | Done
                case 9:
                    System.out.println("============================================================================");
                    if (allBooks.isEmpty())
                    {
                        System.out.println("No Available Books, Please Add Books..");
                        break;
                    }

                    System.out.println("Reserving Books..");
                    library.displayBooks("Available");
                    borrowerId = 0; // what are these for?
                    bookNum = 0; // what are these for?

                    System.out.println("Enter Book Number to take");
                    bookNum = checkNumber();

                    // add validation if book exist
                    book = library.getBook(bookNum);
                    System.out.print("Please enter your name:");
                    borrowerName = scanner.next();

                    System.out.println("Pls enter your borrowerId:");
                    borrowerId = checkNumber();

                    newBorrower = new Borrower(borrowerId, borrowerName);
                    status = "Reserved";
                    
                    newBook = new Book(book.title, book.author, bookNum, newBorrower, status);
                    library.editBook(bookNum, newBook);
                    
                    System.out.println("The Book has been reserved..");
                    System.out.println("Going back to menu..");
                    break;

                // Exit | Done
                case 10:
                    System.out.println("Thank you, for you cooperation.");
                    bError = false;
                    break;
            }
        }while(bError);
        scanner.close();
    }
}
