package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hoursWorked = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int numDependents = scanner.nextInt();

        System.out.println("\nWhich life insurance plan do you want to select?");
        System.out.println("  (1) no plan");
        System.out.println("  (2) single plan");
        System.out.println("  (3) married plan");
        System.out.println("  (4) married with children plan");

        int lifeInsurancePlan;
        while (true) {
            System.out.print("Enter option (1-4): ");
            lifeInsurancePlan = scanner.nextInt();
            if (lifeInsurancePlan >= 1 && lifeInsurancePlan <= 4) {
                if (lifeInsurancePlan == 4 && numDependents == 0) {
                    System.out.println("Sorry! You need at least one child to select that plan.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }

        PayrollCalculator payroll = new PayrollCalculator(hoursWorked, numDependents, 16.78, lifeInsurancePlan);
        payroll.printPayrollStub(); // Call the new method

        System.out.println("Thank you for using the Payroll Program!");
        scanner.close();
    }
}
