package final_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BondTest {

    @Test
    void constructor_ValidInputs_ShouldCreateObject() {
        assertDoesNotThrow(() -> new Bond(1000, 0.05, 0.02, 2025, 50, "Corporate Bond"));
    }

    @Test
    void constructor_NegativeAmount_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Bond(-1000, 0.05, 0.02, 2025, 50, "Corporate Bond"));
    }

    @Test
    void calculateReturn_SingleYearToMaturity_ShouldReturnCorrectNPV() {
        Bond bond = new Bond(1000, 0.05, 0.02, 2025, 50, "Corporate Bond");
        double expectedNPV = 50 / Math.pow(1.05, 1) + (1000 + 50) / Math.pow(1.05, 1);
        assertEquals(expectedNPV, bond.calculateReturn(), 0.01);
    }

    @Test
    void calculateReturn_MultipleYearsToMaturity_ShouldReturnCorrectNPV() {
        Bond bond = new Bond(1000, 0.05, 0.02, 2027, 50, "Corporate Bond");
        double expectedNPV = 0;
        for (int i = 1; i <= 3; i++) {
            expectedNPV += 50 / Math.pow(1.05, i);
        }
        expectedNPV += (1000 + 50) / Math.pow(1.05, 3);
        assertEquals(expectedNPV, bond.calculateReturn(), 0.01);
    }

    @Test
    void calculateReturn_ZeroInterestRate_ShouldAccumulateWithoutDiscount() {
        Bond bond = new Bond(1000, 0, 0.02, 2025, 50, "Corporate Bond");
        double expectedNPV = 50 + (1000 + 50); // No discounting
        assertEquals(expectedNPV, bond.calculateReturn(), 0.01);
    }

    @Test
    void getMaturityAndCoupon_AfterConstruction_ShouldReturnExpectedValues() {
        Bond bond = new Bond(1000, 0.05, 0.02, 2027, 50, "Corporate Bond");
        assertEquals(2027, bond.getMaturity());
        assertEquals(50, bond.getCoupon(), 0.01);
    }

    @Test
    void constructorWithNegativeAmountShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(-1, 0.05, 0.1, 2025, 5, "Government Bond");
        }, "Amount cannot be negative.");
    }

    @Test
    void constructorWithNegativeInterestRateShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, -0.01, 0.1, 2025, 5, "Government Bond");
        }, "Interest rate cannot be negative.");
    }

    @Test
    void constructorWithNegativeVolatilityShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, 0.05, -0.1, 2025, 5, "Government Bond");
        }, "Volatility cannot be negative.");
    }

    @Test
    void constructorWithNegativeCouponShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, 0.05, 0.1, 2025, -5, "Government Bond");
        }, "Coupon cannot be negative.");
    }

    @Test
    void constructorWithInvalidMaturityShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, 0.05, 0.1, 2023, 5, "Government Bond");
        }, "Maturity year must not be earlier than 2024.");
    }

    @Test
    void constructorWithNullDescriptionShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, 0.05, 0.1, 2025, 5, null);
        }, "Description cannot be null or empty.");
    }

    @Test
    void constructorWithEmptyDescriptionShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bond(1000, 0.05, 0.1, 2025, 5, "");
        }, "Description cannot be null or empty.");
    }

    @Test
    void setAmountWithNegativeValueShouldThrow() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        assertThrows(IllegalArgumentException.class, () -> {
            bond.setAmount(-1);
        }, "Amount cannot be negative.");
    }

    @Test
    void setInterestRateWithNegativeValueShouldThrow() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        assertThrows(IllegalArgumentException.class, () -> {
            bond.setInterestRate(-0.01);
        }, "Interest rate cannot be negative.");
    }

    @Test
    void setVolatilityWithNegativeValueShouldThrow() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        assertThrows(IllegalArgumentException.class, () -> {
            bond.setVolatility(-0.1);
        }, "Volatility cannot be negative.");
    }

    @Test
    void setAmountWithPositiveValueShouldSucceed() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        bond.setAmount(1500);
        assertEquals(1500, bond.getAmount(), "Amount should be updated to 1500.");
    }

    @Test
    void setInterestRateWithPositiveValueShouldSucceed() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        bond.setInterestRate(0.07);
        assertEquals(0.07, bond.getInterestRate(), "Interest rate should be updated to 0.07.");
    }

    @Test
    void setVolatilityWithPositiveValueShouldSucceed() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Government Bond");
        bond.setVolatility(0.2);
        assertEquals(0.2, bond.getVolatility(), "Volatility should be updated to 0.2.");
    }

    @Test
    void getVolatilityShouldReturnCorrectValue() {
        Bond bond = new Bond(1000, 0.05, 0.15, 2025, 5, "Government Bond");
        assertEquals(0.15, bond.getVolatility(), "Should return the correct volatility value of 0.15.");
    }

    @Test
    void getDescriptionShouldReturnCorrectValue() {
        Bond bond = new Bond(1000, 0.05, 0.1, 2025, 5, "Corporate Bond");
        assertEquals("Corporate Bond", bond.getDescription(), "Should return the correct description.");
    }
}
