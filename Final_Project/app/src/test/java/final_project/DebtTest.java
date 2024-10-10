package final_project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class DebtTest {
    @Test
    void constructorValidParameters() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals(1000, debt.getAmount(), "Amount should be correct.");
        assertEquals(0.05, debt.getInterestRate(), "Interest rate should be correct.");
        assertTrue(debt.isOneTime(), "Debt should be one-time.");
        assertEquals(2025, debt.getTime(), "Time should be correct.");
        assertEquals(DebtCategory.MORTGAGE, debt.getCategory(), "Category should be correct.");
        assertEquals("Home loan", debt.getDescription(), "Description should be correct.");
    }

    @Test
    void constructorNegativeAmountThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Debt(-1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        });
        assertTrue(exception.getMessage().contains("Amount cannot be negative"));
    }

    @Test
    void constructorNegativeInterestRateThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Debt(1000, -0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        });
        assertTrue(exception.getMessage().contains("Interest rate cannot be negative"));
    }

    @Test
    void constructorTimeBefore2024ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Debt(1000, 0.05, true, 2023, DebtCategory.MORTGAGE, "Home loan");
        });
        assertTrue(exception.getMessage().contains("Time must not be earlier than 2024"));
    }

    @Test
    void constructorEmptyDescriptionThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "");
        });
        assertTrue(exception.getMessage().contains("Description cannot be null or empty"));
    }

    @Test
    void constructorNullDescriptionThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, null);
        });
        assertTrue(exception.getMessage().contains("Description cannot be null or empty"));
    }

    @Test
    void getAmountReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals(1000, debt.getAmount(), "getAmount should return the correct value.");
    }

    @Test
    void getInterestRateReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals(0.05, debt.getInterestRate(), "getInterestRate should return the correct value.");
    }

    @Test
    void getTimeReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals(2025, debt.getTime(), "getTime should return the correct year.");
    }

    @Test
    void isOneTimeReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertTrue(debt.isOneTime(), "isOneTime should return true for one-time debts.");
    }

    @Test
    void getCategoryReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals(DebtCategory.MORTGAGE, debt.getCategory(), "getCategory should return the correct category.");
    }

    @Test
    void getDescriptionReturnsCorrectValue() {
        Debt debt = new Debt(1000, 0.05, true, 2025, DebtCategory.MORTGAGE, "Home loan");
        assertEquals("Home loan", debt.getDescription(), "getDescription should return the correct description.");
    }
}
