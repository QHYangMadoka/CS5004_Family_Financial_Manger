package final_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RealEstateTest {
    @Test
    void constructorValidParameters() {
        RealEstate realEstate = new RealEstate(1000000, 0.05, 0.1, 50000, "Urban Commercial Property");
        assertEquals(1000000, realEstate.getAmount(), "Amount should match constructor input.");
        assertEquals(0.05, realEstate.getInterestRate(), "Interest rate should match constructor input.");
        assertEquals(0.1, realEstate.getVolatility(), "Volatility should match constructor input.");
        assertEquals(50000, realEstate.getNetOperatingIncome(), "Net Operating Income should match constructor input.");
        assertEquals("Urban Commercial Property", realEstate.getDescription(), "Description should match constructor input.");
    }

    @Test
    void constructorNegativeAmountThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RealEstate(-1000000, 0.05, 0.1, 50000, "Urban Commercial Property");
        });
        assertTrue(exception.getMessage().contains("Amount cannot be negative"));
    }

    @Test
    void constructorNegativeInterestRateThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RealEstate(1000000, -0.05, 0.1, 50000, "Urban Commercial Property");
        });
        assertTrue(exception.getMessage().contains("Interest rate cannot be negative"));
    }

    @Test
    void constructorNegativeVolatilityThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RealEstate(1000000, 0.05, -0.1, 50000, "Urban Commercial Property");
        });
        assertTrue(exception.getMessage().contains("Volatility cannot be negative"));
    }

    @Test
    void calculateReturnCalculatesCorrectly() {
        RealEstate realEstate = new RealEstate(1000000, 0.05, 0.1, 50000, "Urban Commercial Property");
        double expectedReturn = 50000 / 0.05 + 1000000;  // NOI / rate + market value
        assertEquals(expectedReturn, realEstate.calculateReturn(), "Calculate return should compute the correct value.");
    }

    @Test
    void getNetOperatingIncomeReturnsCorrectValue() {
        RealEstate realEstate = new RealEstate(1000000, 0.05, 0.1, 50000, "Urban Commercial Property");
        assertEquals(50000, realEstate.getNetOperatingIncome(), "Should return correct net operating income.");
    }
}
