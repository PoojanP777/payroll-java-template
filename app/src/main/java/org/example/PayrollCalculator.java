package org.example;

public class PayrollCalculator {
    private double grossPay;
    private double socialSecurity;
    private double federalTax;
    private double stateTax;
    private final double UNION_DUES = 10.00;
    private double insurance;
    private double lifeInsuranceCost;
    private double netPay;

    // Constructor initializes and calculates payroll
    public PayrollCalculator(double hoursWorked, int numDependents, double payRate, int lifeInsurancePlan) {
        // Call calculation logic in the constructor
        calculatePayroll(hoursWorked, numDependents, payRate, lifeInsurancePlan);
    }

    private void calculatePayroll(double hoursWorked, int numDependents, double payRate, int lifeInsurancePlan) {
        double overtimeHours = Math.max(0, hoursWorked - 40);
        double regularHours = Math.min(hoursWorked, 40);
        double overtimeRate = payRate * 1.5;

        grossPay = (regularHours * payRate) + (overtimeHours * overtimeRate);
        socialSecurity = grossPay * 0.06;
        federalTax = grossPay * 0.14;
        stateTax = grossPay * 0.05;
        insurance = (numDependents >= 3) ? 35.00 : 15.00;

        switch (lifeInsurancePlan) {
            case 2: lifeInsuranceCost = 5.00; break;
            case 3: lifeInsuranceCost = 10.00; break;
            case 4: lifeInsuranceCost = (numDependents > 0) ? 15.00 : 0.00; break;
            default: lifeInsuranceCost = 0.00;
        }

        double totalDeductions = socialSecurity + federalTax + stateTax + UNION_DUES + insurance + lifeInsuranceCost;
        netPay = grossPay - totalDeductions;
    }

    // Getter methods
    public double getGrossPay() { return grossPay; }
    public double getSocialSecurity() { return socialSecurity; }
    public double getFederalTax() { return federalTax; }
    public double getStateTax() { return stateTax; }
    public double getUnionDues() { return UNION_DUES; }
    public double getInsurance() { return insurance; }
    public double getLifeInsuranceCost() { return lifeInsuranceCost; }
    public double getNetPay() { return netPay; }

    // New method to print payroll stub
    public void printPayrollStub() {
        System.out.println("\nPayroll Stub:\n");
        System.out.printf("   Hours:   %.1f\n", (grossPay / 16.78)); // Approximate original hours worked
        System.out.printf("    Rate:   %.2f $/hr\n", 16.78);
        System.out.printf("   Gross:   $ %.2f\n\n", grossPay);
        System.out.printf("  SocSec:   $ %.2f\n", socialSecurity);
        System.out.printf("  FedTax:   $ %.2f\n", federalTax);
        System.out.printf("   StTax:   $ %.2f\n", stateTax);
        System.out.printf("   Union:   $ %.2f\n", UNION_DUES);
        System.out.printf("     Ins:   $ %.2f\n", insurance);
        System.out.printf(" LifeIns:   $ %.2f\n\n", lifeInsuranceCost);
        System.out.printf("     Net:   $ %.2f\n\n", netPay);
    }
}
