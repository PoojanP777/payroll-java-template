package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testGrossPayWithoutOvertime() {
        double hourlyRate = 16.78;
        double hoursWorked = 40;
        double expectedGrossPay = hourlyRate * hoursWorked;
        assertEquals(expectedGrossPay, calculateGrossPay(hoursWorked, hourlyRate), 0.01);
    }

    @Test
    void testGrossPayWithOvertime() {
        double hourlyRate = 16.78;
        double overtimeRate = hourlyRate * 1.5;
        double regularHours = 40;
        double overtimeHours = 10;
        double expectedGrossPay = (regularHours * hourlyRate) + (overtimeHours * overtimeRate);
        assertEquals(expectedGrossPay, calculateGrossPay(50, hourlyRate), 0.01);
    }

    @Test
    void testNegativeDependents() {
        int dependents = -3;
        int expectedDependents = 0;
        assertEquals(expectedDependents, handleNegativeDependents(dependents));
    }

    @Test
    void testNetPayCalculation() {
        double grossPay = 500.00;
        double socialSecurity = grossPay * 0.06;
        double federalTax = grossPay * 0.14;
        double stateTax = grossPay * 0.05;
        double unionDues = 10.00;
        double insurance = 15.00;
        double expectedNetPay = grossPay - (socialSecurity + federalTax + stateTax + unionDues + insurance);
        assertEquals(expectedNetPay, calculateNetPay(grossPay, 0), 0.01);
    }

    // Helper functions for testing
    private double calculateGrossPay(double hoursWorked, double hourlyRate) {
        final double REGULAR_HOURS = 40;
        final double OVERTIME_RATE = hourlyRate * 1.5;
        return (hoursWorked > REGULAR_HOURS) 
            ? (REGULAR_HOURS * hourlyRate) + ((hoursWorked - REGULAR_HOURS) * OVERTIME_RATE)
            : hoursWorked * hourlyRate;
    }

    private int handleNegativeDependents(int dependents) {
        return Math.max(dependents, 0);
    }

    private double calculateNetPay(double grossPay, double lifeInsuranceCost) {
        final double SOCIAL_SECURITY_TAX = 0.06;
        final double FEDERAL_INCOME_TAX = 0.14;
        final double STATE_INCOME_TAX = 0.05;
        final double UNION_DUES = 10.00;
        final double INSURANCE_COST = 15.00;
        return grossPay - (grossPay * SOCIAL_SECURITY_TAX + grossPay * FEDERAL_INCOME_TAX + grossPay * STATE_INCOME_TAX + UNION_DUES + INSURANCE_COST + lifeInsuranceCost);
    }
}
