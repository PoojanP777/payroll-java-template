package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Payroll Program!");
        
        // Get user input for hours worked
        double hoursWorked;
        do {
            System.out.print("How many hours did you work this week? ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            hoursWorked = scanner.nextDouble();
        } while (hoursWorked < 0);

        // Get user input for number of dependents
        int dependents;
        do {
            System.out.print("How many children do you have? ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            dependents = scanner.nextInt();
            if (dependents < 0) {
                System.out.println("Negative dependents detected. Setting to 0.");
                dependents = 0;
            }
        } while (dependents < 0);

        // Constants
        final double HOURLY_RATE = 16.78;
        final double OVERTIME_RATE = HOURLY_RATE * 1.5;
        final int REGULAR_HOURS = 40;
        final double SOCIAL_SECURITY_TAX = 0.06;
        final double FEDERAL_INCOME_TAX = 0.14;
        final double STATE_INCOME_TAX = 0.05;
        final double UNION_DUES = 10.00;
        final double INSURANCE_COST = (dependents >= 3) ? 35.00 : 15.00;

        // Life insurance selection
        int lifeInsuranceOption;
        double lifeInsuranceCost = 0.0;
        do {
            System.out.println("Which life insurance plan do you want to select?");
            System.out.println("  (1) No plan");
            System.out.println("  (2) Single plan ($5)");
            System.out.println("  (3) Married plan ($10)");
            System.out.println("  (4) Married with children plan ($15)");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next();
            }
            lifeInsuranceOption = scanner.nextInt();
            if (lifeInsuranceOption == 4 && dependents == 0) {
                System.out.println("Sorry! You need at least one child to select this plan.");
                lifeInsuranceOption = 0;
            }
        } while (lifeInsuranceOption < 1 || lifeInsuranceOption > 4);

        // Assign cost based on selection
        switch (lifeInsuranceOption) {
            case 2:
                lifeInsuranceCost = 5.00;
                break;
            case 3:
                lifeInsuranceCost = 10.00;
                break;
            case 4:
                lifeInsuranceCost = 15.00;
                break;
            default:
                lifeInsuranceCost = 0.00;
        }

        // Gross pay calculation
        double grossPay;
        if (hoursWorked > REGULAR_HOURS) {
            grossPay = (REGULAR_HOURS * HOURLY_RATE) + ((hoursWorked - REGULAR_HOURS) * OVERTIME_RATE);
        } else {
            grossPay = hoursWorked * HOURLY_RATE;
        }

        // Tax calculations
        double socialSecurity = grossPay * SOCIAL_SECURITY_TAX;
        double federalTax = grossPay * FEDERAL_INCOME_TAX;
        double stateTax = grossPay * STATE_INCOME_TAX;

        // Total deductions
        double totalDeductions = socialSecurity + federalTax + stateTax + UNION_DUES + INSURANCE_COST + lifeInsuranceCost;

        // Net pay calculation
        double netPay = grossPay - totalDeductions;

        // Adjust for negative pay
        double remainingDues = 0.0;
        if (netPay < 0) {
            remainingDues = Math.abs(netPay);
            netPay = 0;
        }

        // Print payroll report
        System.out.println("\nPayroll Stub:\n");
        System.out.printf("   Hours:   %.2f\n", hoursWorked);
        System.out.printf("    Rate:   $%.2f/hr\n", HOURLY_RATE);
        System.out.printf("   Gross:   $%.2f\n", grossPay);
        System.out.printf("\n  SocSec:   $%.2f\n", socialSecurity);
        System.out.printf("  FedTax:   $%.2f\n", federalTax);
        System.out.printf("   StTax:   $%.2f\n", stateTax);
        System.out.printf("   Union:   $%.2f\n", UNION_DUES);
        System.out.printf("     Ins:   $%.2f\n", INSURANCE_COST);
        System.out.printf(" LifeIns:   $%.2f\n", lifeInsuranceCost);
        System.out.printf("\n     Net:   $%.2f\n", netPay);

        if (remainingDues > 0) {
            System.out.println("\nThe employee still owes:\n");
            System.out.printf("   Union:   $%.2f\n", UNION_DUES);
            System.out.printf("     Ins:   $%.2f\n", INSURANCE_COST);
        }

        System.out.println("\nThank you for using the Payroll Program!");

        scanner.close();
    }
}

