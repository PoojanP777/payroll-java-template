package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void payrollCalculationsAreCorrect() {
        PayrollCalculator payroll = new PayrollCalculator(40, 2, 16.78, 2);

        // Use getter methods instead of private fields
        assertEquals(671.20, payroll.getGrossPay(), 0.01);
        assertEquals(40.27, payroll.getSocialSecurity(), 0.01);
        assertEquals(93.97, payroll.getFederalTax(), 0.01);
        assertEquals(33.56, payroll.getStateTax(), 0.01);
        assertEquals(10.00, payroll.getUnionDues(), 0.01);
        assertEquals(15.00, payroll.getInsurance(), 0.01);
        assertEquals(5.00, payroll.getLifeInsuranceCost(), 0.01);
        assertEquals(473.40, payroll.getNetPay(), 0.01);
    }
}
