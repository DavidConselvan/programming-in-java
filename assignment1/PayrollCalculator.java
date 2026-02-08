package assignment1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class PayrollCalculator {
    public static void main(String[] args) {
        // Declaring constants
        final double TAX_RATE = 0.2;
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Try-catch block to handle potential input errors
        try {
            System.out.print("Enter employee's name: ");
            String name = scanner.nextLine();

            System.out.print("Enter hourly wage: ");
            double hourlyWage = scanner.nextDouble();

            System.out.print("Enter hours worked in a week: ");
            int hoursWorked = scanner.nextInt();

            // Validate input values
            if (hourlyWage < 0 || hoursWorked < 0) {
                System.out.println("Hourly wage and hours worked must be non-negative.");
                return;
            }
            
            // Make calculations
            double grossSalary = hourlyWage * hoursWorked;
            double taxDeducted = grossSalary * TAX_RATE;
            double netSalary = grossSalary - taxDeducted;

            //Print results
            System.out.printf("Payroll Summary for %s:%n", name);
            System.out.println("-----------------------------------");
            System.out.printf("Hours Worked: %d%n", hoursWorked);
            System.out.printf("Hourly Wage: $%.2f%n", hourlyWage);
            System.out.printf("Gross Salary: $%.2f%n", grossSalary);
            System.out.printf("Tax Deducted: $%.2f%n", taxDeducted);
            System.out.printf("Net Salary: $%.2f%n", netSalary);
        
        //Catch block to handle input mismatch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values for hourly wage and hours worked.");

        //Close the scanner
        } finally {
            scanner.close();
        }
    }
}