package final_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    @Test
    void constructorValidParameters() {
        assertDoesNotThrow(() -> new Stock(1000, 0.05, 0.1, 50, 0.03, "Tech Stock"));
    }

    @Test
    void constructorNegativeAmountThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Stock(-1000, 0.05, 0.1, 50, 0.03, "Tech Stock"));
        assertEquals("Amount cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorNegativeInterestRateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Stock(1000, -0.05, 0.1, 50, 0.03, "Tech Stock"));
        assertEquals("Interest rate cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorNegativeVolatilityThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Stock(1000, 0.05, -0.1, 50, 0.03, "Tech Stock"));
        assertEquals("Volatility cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorNegativeDividendThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Stock(1000, 0.05, 0.1, -50, 0.03, "Tech Stock"));
        assertEquals("Dividend cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorEmptyDescriptionThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Stock(1000, 0.05, 0.1, 50, 0.03, ""));
        assertEquals("Description cannot be null or empty.", exception.getMessage());
    }

    @Test
    void calculateReturnDiscountRateLessThanOrEqualToGrowthRateThrowsException() {
        Stock stock = new Stock(1000, 0.04, 0.1, 50, 0.05, "Tech Stock");
        Exception exception = assertThrows(IllegalArgumentException.class, stock::calculateReturn);
        assertEquals("Discount rate must be greater than the dividend growth rate to avoid infinite intrinsic value.", exception.getMessage());
    }

    @Test
    void calculateReturnValidScenario() {
        Stock stock = new Stock(1000, 0.07, 0.1, 50, 0.02, "Tech Stock");
        double expectedIntrinsicValue = 50 / (0.07 - 0.02);  // Dividend / (interestRate - growthRate)
        assertEquals(Math.max(expectedIntrinsicValue, 1000), stock.calculateReturn(), "Should return the higher of intrinsic value or amount.");
    }

    @Test
    void getDividendReturnsCorrectValue() {
        Stock stock = new Stock(1000, 0.07, 0.1, 50, 0.02, "Tech Stock");
        assertEquals(50, stock.getDividend(), "Should return correct dividend.");
    }

    @Test
    void getDividendGrowthRateReturnsCorrectValue() {
        Stock stock = new Stock(1000, 0.07, 0.1, 50, 0.02, "Tech Stock");
        assertEquals(0.02, stock.getDividendGrowthRate(), "Should return correct dividend growth rate.");
    }
}
