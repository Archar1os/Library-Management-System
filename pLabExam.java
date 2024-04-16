import java.util.Scanner;
public class pLabExam
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        double Choice;
        String isRepeat = "N";
        do{
            System.out.println("Menu");
            System.out.println("[1] Computes interest rate of an amount over certain period of years");
            System.out.println("[2] Computes employee's salary");
            System.out.println("[3] Exit");
            System.out.print("Enter Choice:");
            Choice = scanner.nextDouble();

            if (Choice == 1) 
            {
                System.out.print("Enter the principal amount:");
                double Principal_Amount = scanner.nextDouble();
                System.out.print("Enter annual interest rate(as a decimal):");
                double Annual_Interest_Rate = scanner.nextDouble();
                System.out.print("Enter the number of years:");
                int Years = scanner.nextInt();
                CalculateInterestRate(Principal_Amount, Annual_Interest_Rate, Years);
            }
            else if (Choice == 2)
            {
                System.out.print("Enter hourly pay rate: Php ");
                double HourlyPay = scanner.nextDouble();
                System.out.print("Enter hours worked:");
                double HoursWorked = scanner.nextDouble();
                ComputeSalary(HourlyPay, HoursWorked);
            }
            else if (Choice == 3)
            {
                System.out.println("Thank you for using my program.");
            }

            System.out.println("Do you want to repeat again? Answer [Y/N]:");
            isRepeat = scanner.next();

        } while (isRepeat.equalsIgnoreCase("Y") && Choice < 3);
        
    }
    public static void CalculateInterestRate(double Principal_Amount, double Annual_Interest_Rate, double Years)
    {
        System.out.println("            Principal        Interest         Total Amount");
        double interest = 0;
        for (int num = 1; num <= Years; num++) 
        {
            interest += Principal_Amount * Annual_Interest_Rate ;
            double Total_Amount = Principal_Amount + interest;
            System.out.println("Year " + num + "  Php"+ "" +String.format("%,.2f",Principal_Amount)+"      "+ String.format("%,.2f",interest)+ "        "+String.format("%,.2f",Total_Amount));
            Principal_Amount += interest;

        }
    
}
