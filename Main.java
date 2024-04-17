
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("View Books(1)");
        System.out.println("Add Books (2)");
        System.out.println("Delete    (3)");
        System.out.println("Exit      (4)");
        System.out.println(":");
        int user = input.nextInt();
        if (user == 1)
        {
            System.out.println("ok1");
        }
        else if (user == 2)
        {
            System.out.println("ok2");
        }
        else if (user == 3)
        {
            System.out.println("ok3");
        }
        else if (user == 4)
        {
            System.out.println("Thank you for visiting the library.");
            break
        }
        else
        {
            System.out.println("error");
        }
        scanner.close();
    }
}
